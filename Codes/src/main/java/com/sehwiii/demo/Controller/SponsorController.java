package com.sehwiii.demo.Controller;

import com.sehwiii.demo.Service.*;
import com.sehwiii.demo.Service.ServiceImpl.*;
import com.sehwiii.demo.VO.PictureVO;
import com.sehwiii.demo.VO.TaskVO;
import com.sehwiii.demo.VO.UserVO;
import com.sehwiii.demo.VO.WorkerTaskVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 161250078
 */


@Controller
public class SponsorController {

	
    private UserService userService = new UserServiceImpl();
    private TaskService taskService = new TaskServiceImpl();
    private MessageService messageService = new MessageServiceImpl();
    private WorkerTaskService workerTaskService = new WorkerTaskServiceImpl();
    private PictureService pictureService = new PictureServiceImpl();

    @RequestMapping(value = "/getSponsorInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSponsorInfo(@RequestParam Map<String, Object> map) {
        String uid = map.get("id").toString();
        UserVO vo = userService.getUser(uid);
        int mesNum = messageService.getUnReadNum(uid);
        ArrayList<TaskVO> tList = taskService.getTaskOfSponsor(uid);
        int size = tList.size();
        TaskVO[] tArray = new TaskVO[size];
        tArray = tList.toArray(tArray);
        for (int i = 0; i < tArray.length; i++) {
            TaskVO taskVO = tArray[i];
            taskVO.setFinishProcess(taskService.getFinishProcess(taskVO.getTid()));
            taskVO.setApproveProcess(taskService.getApproveProcess(taskVO.getTid()));
            tArray[i] = taskVO;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", vo);
        res.put("mesNum", mesNum);
        res.put("taskInfo", tArray);
        return res;
    }

    @RequestMapping(value = "/getNewTaskID", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getNewTaskID(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", userService.getUser(uid));
        res.put("tid", taskService.getNextTid());
        return res;
    }

    @RequestMapping(value = "/addNewTask", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addNewTask(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tname = map.get("tname").toString();
        String requirement = map.get("requirement").toString();
        int picNum = Integer.parseInt(map.get("picNum").toString());
        int num = Integer.parseInt(map.get("num").toString());
        double reward = Double.parseDouble(map.get("reward").toString());
        double bonus = Double.parseDouble(map.get("bonus").toString());
        String type = map.get("type").toString();
        String tid = taskService.getNextTid();
        pictureService.updateType(tid, type);
        TaskVO vo = new TaskVO(tid, tname, requirement, reward, bonus, num, uid, type);
        vo.setLevelByNum(picNum);
        Map<String, Object> res = new HashMap<>();
        res.put("mes", taskService.addTask(vo));
        res.put("level", vo.getLevel());
        return res;
    }

    @RequestMapping(value = "/getWorkerOfTask", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getWorkerOfTask(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String tid = map.get("tid").toString();
        ArrayList<String> widList = workerTaskService.getWorkerOfTask(tid);
        int size = widList.size();
        WorkerTaskVO[] wtvo = new WorkerTaskVO[size];
        for (int i = 0; i < size; i++) {
            WorkerTaskVO workerTaskVO = workerTaskService.getWorkerTask(widList.get(i), tid);
            workerTaskVO.setLabelProcess(userService.getUser(widList.get(i)).getUname());
            wtvo[i] = workerTaskVO;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", userService.getUser(uid));
        res.put("taskInfo", taskService.getTask(tid));
        res.put("workerList", wtvo);

        return res;
    }

    @RequestMapping(value = "/approveTask", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> approveTask(@RequestParam Map<String, Object> map) {
        String tid = map.get("tid").toString();
        String uid = map.get("wid").toString();
        int approve = Integer.parseInt(map.get("approve").toString());
        Map<String, Object> res = new HashMap<>();
        if (workerTaskService.getWorkerTask(uid, tid).getApprove() > 0) {
            res.put("mes", "您已审批过该结果，不能再次审批！！！");
            return res;
        }
        workerTaskService.approveTask(uid, tid, approve);
        res.put("mes", "审批成功");
        return res;
    }

    @RequestMapping(value = "/getTaskCountInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTaskCountInfo(@RequestParam Map<String, Object> map) {
        String tid = map.get("tid").toString();
        Map<String, Integer> count = workerTaskService.getTaskCountInfo(tid);
        Map<String, Object> res = new HashMap<>();
        res.put("goodCount", count.get("goodCount"));
        res.put("badCount", count.get("badCount"));
        res.put("commonCount", count.get("commonCount"));
        res.put("unApprovedCount", count.get("unApprovedCount"));
        return res;
    }
}
