package com.sehwiii.demo.Controller;

import com.sehwiii.demo.Service.*;
import com.sehwiii.demo.Service.ServiceImpl.*;
import com.sehwiii.demo.VO.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 161250078
 */



@Controller
public class WorkerController {

    private UserService userService = new UserServiceImpl();
    private TaskService taskService = new TaskServiceImpl();
    private MessageService messageService = new MessageServiceImpl();
    private WorkerTaskService workerTaskService = new WorkerTaskServiceImpl();
    private ResultService resultService = new ResultServiceImpl();
    private PictureService pictureService = new PictureServiceImpl();

    @RequestMapping(value = "/getWorkerInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserInfo(@RequestParam Map<String, Object> map) {
        String uid = map.get("id").toString();
        UserVO vo = userService.getUser(uid);
        int mesNum = messageService.getUnReadNum(uid);
        ArrayList<TaskVO> allTask = taskService.getAllTask();
        int size = allTask.size();
        TaskVO[] all = new TaskVO[size];
        all = allTask.toArray(all);
        for (int i = 0; i < size; i++) {
            TaskVO tvo = all[i];
            String sname = userService.getUser(tvo.getSponsor()).getUname();
            tvo.setSponsor(sname);
            all[i] = tvo;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", vo);
        res.put("mesNum", mesNum);
        res.put("allTask", all);
        return res;
    }

    @RequestMapping(value = "/searchTaskByKey", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> searchTaskByKey(@RequestParam Map<String, Object> map) {
        String type = map.get("type").toString();
        String key = map.get("key").toString();
        System.out.println(type + " " + key);
        ArrayList<TaskVO> searchTask = taskService.searchTaskBYKey(key, type);
        int size = searchTask.size();
        TaskVO[] search = new TaskVO[size];
        search = searchTask.toArray(search);
        for (int i = 0; i < size; i++) {
            TaskVO tvo = search[i];
            String sname = userService.getUser(tvo.getSponsor()).getUname();
            tvo.setSponsor(sname);
            search[i] = tvo;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("searchInfo", search);
        return res;
    }

    @RequestMapping(value = "/getTaskOfWorker", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTaskOfWorker(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        ArrayList<String> tidList = workerTaskService.getTaskOfWorker(uid);
        int size = tidList.size();
        TaskVO[] workerTask = new TaskVO[size];
        for (int i = 0; i < size; i++) {
            TaskVO taskVO = taskService.getTask(tidList.get(i));
            taskVO.setSponsor(userService.getUser(taskVO.getSponsor()).getUname());
            taskVO.setFinishProcess(workerTaskService.getLabelProcessOfTask(uid, tidList.get(i)));
            workerTask[i] = taskVO;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", userService.getUser(uid));
        res.put("workerTask", workerTask);
        return res;
    }

    @RequestMapping(value = "/acceptTask", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> acceptTask(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        Map<String, Object> res = new HashMap<>();
        ArrayList<String> wList = workerTaskService.getWorkerOfTask(tid);
        if (wList.contains(uid)) {
            res.put("message", "您已接受该任务！！！");
            return res;
        }
        int num = taskService.getTask(tid).getNum();
        if (wList.size() >= num) {
            res.put("message", "该任务接受人数已满！！！");
            return res;
        }
        workerTaskService.acceptTask(uid, tid);
        res.put("message", "成功接受该任务！！！");
        return res;
    }

    @RequestMapping(value = "/submitTask", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitTask(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        Map<String, Object> res = new HashMap<>();
        int done = resultService.getResNumOfTask(tid, uid);
        int num = pictureService.getPicOfTask(tid).size();
        if (done < num) {
            res.put("message", "还有未标注的图片，不能提交！！！");
            return res;
        }
        WorkerTaskVO workerTaskVO = workerTaskService.getWorkerTask(uid, tid);
        if (workerTaskVO.isSubmitted()) {
            res.put("message", "该任务已提交！！！");
            return res;
        }
        workerTaskService.submitTask(uid, tid);
        res.put("message", "提交成功！！！");
        return res;
    }

    @RequestMapping(value = "/deleteRes", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteRes(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        String pid = map.get("pid").toString();
        Map<String, Object> res = new HashMap<>();
        WorkerTaskVO vo = workerTaskService.getWorkerTask(uid, tid);
        if (vo.isSubmitted()) {
            res.put("message", "该任务已经提交，不能删除标注结果！！！");
            return res;
        }
        String rid = uid + tid + pid.substring(10);
        int r = resultService.deleteRes(rid);
        if (r == 0) {
            res.put("message", "还未对该图片进行标注，不能删除！！！");
        } else if (r == 1) {
            res.put("message", "删除成功！！！");
        }
        return res;
    }

    @RequestMapping(value = "/getTaskInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTaskInfo(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        ArrayList<PictureVO> picList = pictureService.getPicOfTask(tid);
        int size = picList.size();
        PictureVO[] picArray = new PictureVO[size];
        picArray = picList.toArray(picArray);
        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", userService.getUser(uid));
        res.put("taskInfo", taskService.getTask(tid));
        res.put("taskDetail", picArray);
        return res;
    }

    @RequestMapping(value = "/getRanking", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getRanking(@RequestParam Map<String, Object> map) {
        String uid = map.get("id").toString();
        ArrayList<UserVO> rankedWorker = userService.getSortedWorker();
        int size = rankedWorker.size();
        UserVO[] rankedUserInfo = new UserVO[size];
        rankedUserInfo = rankedWorker.toArray(rankedUserInfo);
        for (int i = 0; i < size; i++) {
            UserVO vo = rankedUserInfo[i];
            vo.setlNum(userService.getRank(vo.getUid()));
            rankedUserInfo[i] = vo;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", userService.getUser(uid));
        res.put("rank", userService.getRank(uid));
        res.put("rankedUserInfo", rankedUserInfo);
        return res;
    }

    @RequestMapping(value = "/getLatestTenTaskCountInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getLatestTenTaskCountInfo(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        ArrayList<WorkerTaskVO> countInfo = workerTaskService.getLatestTenCountInfo(uid);
        WorkerTaskVO[] count = new WorkerTaskVO[countInfo.size()];
        count = countInfo.toArray(count);
        int[] count1 = new int[countInfo.size()];
        for (int i = 0; i < countInfo.size(); i++) {
            count1[i] = count[i].getApprove();
        }
        Map<String, Object> res = new HashMap<>();
        res.put("count", count1);
        return res;
    }
}
