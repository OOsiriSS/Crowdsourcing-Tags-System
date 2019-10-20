package com.sehwiii.demo.Service.ServiceImpl;

import com.sehwiii.demo.DAO.DAOImpl.TaskDAOImpl;
import com.sehwiii.demo.DAO.TaskDAO;
import com.sehwiii.demo.Service.TaskService;
import com.sehwiii.demo.Service.UserService;
import com.sehwiii.demo.VO.TaskVO;
import com.sehwiii.demo.VO.UserVO;
import com.sehwiii.demo.util.ToStringID;

import java.util.ArrayList;

/**
 * @author 161250078
 */

public class TaskServiceImpl implements TaskService {

    private TaskDAO taskDAO = new TaskDAOImpl();

    @Override
    public int addTask(TaskVO vo) {
        return taskDAO.addTask(vo);
    }

    @Override
    public int updateTask(TaskVO vo) {
        return taskDAO.updateTask(vo);
    }

    @Override
    public ArrayList<TaskVO> getAllTask() {
        return taskDAO.getAllTask();
    }

    @Override
    public TaskVO getTask(String tid) {
        return taskDAO.getTask(tid);
    }

    @Override
    public ArrayList<TaskVO> searchTaskBYKey(String key, String type) {
        ArrayList<TaskVO> searchList = new ArrayList<>();
        ArrayList<TaskVO> allTaskList = getAllTask();
        if (allTaskList == null) {
            return null;
        }
        for (TaskVO vo : allTaskList) {
            if (type.equals("任务id")) {
                if (vo.getTid().contains(key)) {
                    searchList.add(vo);
                }
            } else if (type.equals("任务名称")) {
                if (vo.getTname().contains(key)) {
                    searchList.add(vo);
                }
            } else if (type.equals("任务要求")) {
                if (vo.getRequirement().contains(key)) {
                    searchList.add(vo);
                }
            } else if (type.equals("发布者姓名")) {
                UserService userService = new UserServiceImpl();
                UserVO uvo = userService.getUser(vo.getSponsor());
                if (uvo.getUname().contains(key)) {
                    searchList.add(vo);
                }
            }
        }
        return searchList;
    }

    @Override
    public ArrayList<TaskVO> getTaskOfSponsor(String uid) {
        return taskDAO.getTaskOfSponsor(uid);
    }

    @Override
    public String getNextTid() {
        ArrayList<TaskVO> allTask = getAllTask();
        int length = allTask.size();
        return ToStringID.toStringID(length + 1);
    }

    @Override
    public String getFinishProcess(String tid) {
        WorkerTaskServiceImpl workerTaskService = new WorkerTaskServiceImpl();
        int count = workerTaskService.getSubmitNumOfTask(tid);
        TaskVO vo = getTask(tid);
        int num = vo.getNum();
        return count + "/" + num;
    }

    @Override
    public String getApproveProcess(String tid) {
        WorkerTaskServiceImpl workerTaskService = new WorkerTaskServiceImpl();
        int approved = workerTaskService.getApprovedNumOfTask(tid);
        int submitted = workerTaskService.getSubmitNumOfTask(tid);
        return approved + "/" + submitted;
    }

    @Override
    public ArrayList<TaskVO> getFinishedTask() {
        WorkerTaskServiceImpl workerTaskService = new WorkerTaskServiceImpl();
        ArrayList<TaskVO> all = getAllTask();
        ArrayList<TaskVO> res = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            int num = all.get(i).getNum();
            int submitted = workerTaskService.getSubmitNumOfTask(all.get(i).getTid());
            if (submitted == num) {
                res.add(all.get(i));
            }
        }
        return res;
    }

    @Override
    public ArrayList<TaskVO> getUnFinishedTask() {
        WorkerTaskServiceImpl workerTaskService = new WorkerTaskServiceImpl();
        ArrayList<TaskVO> all = getAllTask();
        ArrayList<TaskVO> res = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            int num = all.get(i).getNum();
            int submitted = workerTaskService.getSubmitNumOfTask(all.get(i).getTid());
            if (submitted < num) {
                res.add(all.get(i));
            }
        }
        return res;
    }

    @Override
    public int getFinishedNum() {
        ArrayList<TaskVO> f = getFinishedTask();
        if (f == null || f.size() == 0) {
            return 0;
        }
        return f.size();
    }

    @Override
    public int getUnFinishedNum() {
        ArrayList<TaskVO> uf = getUnFinishedTask();
        if (uf == null || uf.size() == 0) {
            return 0;
        }
        return uf.size();
    }
}
