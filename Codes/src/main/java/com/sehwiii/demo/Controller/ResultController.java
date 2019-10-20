package com.sehwiii.demo.Controller;

import com.sehwiii.demo.Service.PictureService;
import com.sehwiii.demo.Service.ResultService;
import com.sehwiii.demo.Service.ServiceImpl.PictureServiceImpl;
import com.sehwiii.demo.Service.ServiceImpl.ResultServiceImpl;
import com.sehwiii.demo.Service.ServiceImpl.TaskServiceImpl;
import com.sehwiii.demo.Service.TaskService;
import com.sehwiii.demo.VO.ResultVO;
import com.sehwiii.demo.util.PictureCtrl;
import com.sehwiii.demo.util.StringListTranf;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 161250078
 */

@Controller
public class ResultController {
    private ResultService resultService = new ResultServiceImpl();
    private TaskService taskService = new TaskServiceImpl();
    private PictureService pictureService = new PictureServiceImpl();

    @RequestMapping(value = "/saveResPic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveResPic(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        String pid = map.get("pid").toString();
        int index = Integer.parseInt(map.get("index").toString());
        String i = pid.substring(10);
        String rid = uid + tid + i;
        String imgData = map.get("imgData").toString();
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgData);
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            BufferedImage bi = ImageIO.read(bais);
            String dirPath = resultService.getSaveDir(rid);
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dirPath + "/" + rid + "_" + index + ".png");
            ImageIO.write(bi, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> res = new HashMap<>();
        res.put("mes", index);
        return res;
    }

    @RequestMapping(value = "/saveRes", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveRes(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        String pid = map.get("pid").toString();
        int index = Integer.parseInt(map.get("index").toString());
        String noteInfo = map.get("noteInfo").toString();
        String i = pid.substring(10);
        String rid = uid + tid + i;
        ArrayList<String> noteList = StringListTranf.stringToList(noteInfo);
        String resPicName = rid + "_" + index;
        String resPath = resultService.getSaveDir(rid) + "/" + resPicName + ".png";

        String resDir = resultService.getSaveDir(rid);
        File dir = new File(resDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (taskService.getTask(tid).getType().equals("整体标注")) {
            String picPath = pictureService.getPic(pid).getPath();
            PictureCtrl.copyImage(picPath, resPath);
        }

        ResultVO resultVO = new ResultVO();
        resultVO.setRid(rid);
        resultVO.setUid(uid);
        resultVO.setTid(tid);
        resultVO.setPid(pid);
        resultVO.setNoteList(noteList);
        resultVO.setResPic(resPicName);
        resultVO.setResPicPath(resPath);
        Map<String, Object> res = new HashMap<>();
        if (resultService.getRes(rid) != null) {
            res.put("res", resultService.updateRes(resultVO));
        } else {
            res.put("res", resultService.addRes(resultVO));
        }
        return res;
    }

    @RequestMapping(value = "/undo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> undo(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        String pid = map.get("pid").toString();
        int index = Integer.parseInt(map.get("index").toString());
        String i = pid.substring(10);
        String rid = uid + tid + i;
        String resPicName = rid + "_" + index;
        String resPath = resultService.getSaveDir(rid) + "/" + resPicName + ".png";
        Map<String, Object> res = new HashMap<>();
        res.put("path", PictureCtrl.getImgData(resPath));
        return res;
    }

    @RequestMapping(value = "/getResForWorker", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getResForWorker(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        String pid = map.get("pid").toString();
        String i = pid.substring(10);
        String rid = uid + tid + i;
        ResultVO vo = resultService.getRes(rid);
        Map<String, Object> res = new HashMap<>();
        if (vo != null) {
            String resName = vo.getResPic();
            int num = Integer.parseInt(resName.split("_")[1]);
            ArrayList<String> noteList = vo.getNoteList();
            String[] noteInfo = new String[noteList.size()];
            noteInfo = noteList.toArray(noteInfo);
            res.put("type", taskService.getTask(tid).getType());
            res.put("index", num);
            res.put("noteInfo", noteInfo);
            res.put("size", noteList.size());
            res.put("path", PictureCtrl.getImgData(vo.getResPicPath()));
        } else {
            res.put("type", taskService.getTask(tid).getType());
            res.put("index", 0);
            res.put("noteInfo", "null");
            res.put("size", 0);
            res.put("path", PictureCtrl.getImgData(pictureService.getPic(pid).getPath()));
        }
        return res;
    }

    @RequestMapping(value = "/getResForSponsor", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getResForSponsor(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        String index = map.get("index").toString();
        String rid = uid + tid + index;
        Map<String, Object> res = new HashMap<>();
        int num = pictureService.getPicOfTask(tid).size();
        int i = Integer.parseInt(index);
        if (num < i) {
            res.put("mes", 0);
            return res;
        }
        ResultVO vo = resultService.getRes(rid);
        ArrayList<String> noteList = vo.getNoteList();
        String[] noteInfo = new String[noteList.size()];
        noteInfo = noteList.toArray(noteInfo);
        res.put("mes", 1);
        res.put("path", PictureCtrl.getImgData(vo.getResPicPath()));
        res.put("noteInfo", noteInfo);
        return res;
    }
}
