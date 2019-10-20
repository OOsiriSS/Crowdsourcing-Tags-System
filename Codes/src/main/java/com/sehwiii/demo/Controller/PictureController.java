package com.sehwiii.demo.Controller;

import com.sehwiii.demo.Service.PictureService;
import com.sehwiii.demo.Service.ServiceImpl.PictureServiceImpl;
import com.sehwiii.demo.Service.ServiceImpl.TaskServiceImpl;
import com.sehwiii.demo.Service.ServiceImpl.UserServiceImpl;
import com.sehwiii.demo.Service.TaskService;
import com.sehwiii.demo.Service.UserService;
import com.sehwiii.demo.VO.PictureVO;
import com.sehwiii.demo.util.PictureCtrl;
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
public class PictureController {
    private PictureService pictureService = new PictureServiceImpl();
    private UserService userService = new UserServiceImpl();
    private TaskService taskService = new TaskServiceImpl();

    @RequestMapping(value = "/addPic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addPic(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String index = map.get("index").toString();
        String imgData = map.get("imgData").toString();
        String name = map.get("name").toString();
        String tid = taskService.getNextTid();
        String pid = uid + tid + index;
        String dir = pictureService.getSaveDir(pid);
        File f = new File(dir);
        if (!f.exists()) {
            f.mkdirs();
        }
        String picPath = dir + "/" + pid + ".png";
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgData);
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            BufferedImage bi = ImageIO.read(bais);
            File file = new File(picPath);
            ImageIO.write(bi, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PictureVO vo = new PictureVO();
        vo.setTid(tid);
        vo.setPid(pid);
        vo.setUid(uid);
        vo.setPname(name);
        vo.setPath(picPath);
        pictureService.addPicture(vo);
        ArrayList<PictureVO> pictureVOS = pictureService.getPicOfTask(tid);
        int size = pictureVOS.size();
        PictureVO[] picArray = new PictureVO[size];
        picArray = pictureVOS.toArray(picArray);
        Map<String, Object> res = new HashMap<>();
        res.put("mes", 1);
        res.put("picInfo", picArray);
        return res;
    }

    @RequestMapping(value = "/deletePic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deletePic(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = taskService.getNextTid();
        String index = map.get("index").toString();
        String pid = uid + tid + index;
        Map<String, Object> res = new HashMap<>();

        int i = Integer.parseInt(index) - 1;
        if (i >= 0) {
            File file = new File(pictureService.getPic(pid).getPath());
            file.delete();
            pictureService.deletePic(pid);
        }
        ArrayList<PictureVO> picList = pictureService.getPicOfTask(tid);
        PictureVO[] picArray = new PictureVO[picList.size()];
        picArray = picList.toArray(picArray);

        if (i < 0) {
            res.put("index", i);
            res.put("picArray", picArray);
            return res;
        } else if (i == 0) {
            res.put("index", i);
            res.put("picArray", picArray);
            return res;
        }
        String lastPid = uid + tid + i;
        String lastPath = pictureService.getSaveDir(pid) + "/" + lastPid + ".png";
        String imgData = PictureCtrl.getImgData(lastPath);
        res.put("index", i);
        res.put("picArray", picArray);
        res.put("path", imgData);
        return res;
    }

    @RequestMapping(value = "/deletePicOfTask", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deletePicOfTask(Map<String, Object> map) {
        String tid = map.get("tid").toString();
        pictureService.deletePicOfTask(tid);
        Map<String, Object> res = new HashMap<>();
        res.put("mes", 1);
        return res;
    }

    @RequestMapping(value = "/getPicOfTask", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPicOfTask(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        ArrayList<PictureVO> pictureVOS = pictureService.getPicOfTask(tid);
        int size = pictureVOS.size();
        PictureVO[] picArray = new PictureVO[size];
        picArray = pictureVOS.toArray(picArray);
        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", userService.getUser(uid));
        res.put("picList", picArray);
        return res;
    }
}
