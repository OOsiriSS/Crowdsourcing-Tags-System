package com.sehwiii.demo.Controller;

import com.sehwiii.demo.Service.ServiceImpl.TaskServiceImpl;
import com.sehwiii.demo.Service.ServiceImpl.UserServiceImpl;
import com.sehwiii.demo.Service.TaskService;
import com.sehwiii.demo.Service.UserService;
import com.sehwiii.demo.VO.TaskVO;
import com.sehwiii.demo.VO.UserVO;
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
public class AdministratorController {

    private UserService userService = new UserServiceImpl();
    private TaskService taskService = new TaskServiceImpl();

    @RequestMapping(value = "/showUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showUserInfo() {
        ArrayList<UserVO> all = userService.getAllUser();
        int size = all.size();
        UserVO[] allArray = new UserVO[size];
        allArray = all.toArray(allArray);
        Map<String, Object> res = new HashMap<>();
        res.put("workerNum", userService.getWorkerNum());
        res.put("sponsorNum", userService.getSponsorNum());
        res.put("userInfo", allArray);
        return res;
    }

    @RequestMapping(value = "/showWorkerInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showWorkerInfo() {
        ArrayList<UserVO> worker = userService.getWorker();
        int size = worker.size();
        UserVO[] workerArray = new UserVO[size];
        workerArray = worker.toArray(workerArray);
        Map<String, Object> res = new HashMap<>();
        res.put("workerNum", userService.getWorkerNum());
        res.put("workerInfo", workerArray);
        return res;
    }

    @RequestMapping(value = "/showSponsorInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showSponsorInfo() {
        ArrayList<UserVO> sponsor = userService.getSponsor();
        int size = sponsor.size();
        UserVO[] sponsorArray = new UserVO[size];
        sponsorArray = sponsor.toArray(sponsorArray);
        Map<String, Object> res = new HashMap<>();
        res.put("sponsorNum", userService.getSponsorNum());
        res.put("sponsorInfo", sponsorArray);
        return res;
    }

    @RequestMapping(value = "/showTaskInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showTaskInfo() {
        ArrayList<TaskVO> all = taskService.getAllTask();
        int size = all.size();
        TaskVO[] allArray = new TaskVO[size];
        allArray = all.toArray(allArray);
        for (int i = 0; i < allArray.length; i++) {
            TaskVO taskVO = allArray[i];
            taskVO.setFinishProcess(taskService.getFinishProcess(taskVO.getTid()));
            taskVO.setSponsor(userService.getUser(taskVO.getSponsor()).getUname());
            allArray[i] = taskVO;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("unFinishNum", taskService.getUnFinishedNum());
        res.put("finishNum", taskService.getFinishedNum());
        res.put("taskInfo", allArray);
        return res;
    }

    @RequestMapping(value = "/showFinishInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showFinishInfo() {
        ArrayList<TaskVO> finish = taskService.getFinishedTask();
        int size = finish.size();
        TaskVO[] finishArray = new TaskVO[size];
        finishArray = finish.toArray(finishArray);
        for (int i = 0; i < finishArray.length; i++) {
            TaskVO taskVO = finishArray[i];
            taskVO.setFinishProcess(taskService.getFinishProcess(taskVO.getTid()));
            taskVO.setSponsor(userService.getUser(taskVO.getSponsor()).getUname());
            finishArray[i] = taskVO;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("finishNum", taskService.getFinishedNum());
        res.put("finishInfo", finishArray);
        return res;
    }

    @RequestMapping(value = "/showUnFinishInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> showUnFinishInfo() {
        ArrayList<TaskVO> unFinish = taskService.getUnFinishedTask();
        int size = unFinish.size();
        TaskVO[] unFinishArray = new TaskVO[size];
        unFinishArray = unFinish.toArray(unFinishArray);
        for (int i = 0; i < unFinishArray.length; i++) {
            TaskVO taskVO = unFinishArray[i];
            taskVO.setFinishProcess(taskService.getFinishProcess(taskVO.getTid()));
            taskVO.setSponsor(userService.getUser(taskVO.getSponsor()).getUname());
            unFinishArray[i] = taskVO;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("unFinishNum", taskService.getUnFinishedNum());
        res.put("unFinishInfo", unFinishArray);
        return res;
    }

    @RequestMapping(value = "/getWorkerCountInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getWorkerCountInfo(@RequestParam Map<String, Object> map) {
        Map<String, Object> res = new HashMap<>();
        Map<String, Integer> count = userService.getWorkerCountInfo();
        res.put("goodNum", count.get("goodNum"));
        res.put("badNum", count.get("badNum"));
        res.put("commonNum", count.get("commonNum"));
        return res;
    }
}
