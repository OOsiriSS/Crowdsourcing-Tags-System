package com.sehwiii.demo.Service.ServiceImpl;

import com.sehwiii.demo.DAO.DAOImpl.WorkerTaskDAOImpl;
import com.sehwiii.demo.DAO.WorkerTaskDAO;
import com.sehwiii.demo.Service.MessageService;
import com.sehwiii.demo.Service.WorkerTaskService;
import com.sehwiii.demo.VO.MessageVO;
import com.sehwiii.demo.VO.TaskVO;
import com.sehwiii.demo.VO.UserVO;
import com.sehwiii.demo.VO.WorkerTaskVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 161250078
 */

public class WorkerTaskServiceImpl implements WorkerTaskService {

    private WorkerTaskDAO workerTaskDAO = new WorkerTaskDAOImpl();

    @Override
    public int acceptTask(String uid, String tid) {
        WorkerTaskVO workerTaskVO = new WorkerTaskVO(uid, tid);
        return workerTaskDAO.addWorkerTask(workerTaskVO);
    }

    @Override
    public int submitTask(String uid, String tid) {
        WorkerTaskVO vo = getWorkerTask(uid, tid);
        vo.setSubmitted(true);
        MessageServiceImpl messageService = new MessageServiceImpl();
        messageService.sendSubmitMessage(tid, uid);
        TaskServiceImpl taskService = new TaskServiceImpl();
        TaskVO taskVO = taskService.getTask(tid);
        double p = 0.0;
        if (taskVO.getLevel() > 5) {
            p = 0.05;
        } else if (taskVO.getLevel() > 10) {
            p = 0.1;
        } else if (taskVO.getLevel() > 20) {
            p = 0.15;
        } else if (taskVO.getLevel() > 30) {
            p = 0.25;
        } else if (taskVO.getLevel() > 50) {
            p = 0.5;
        }
        UserServiceImpl userService = new UserServiceImpl();
        UserVO uvo = userService.getUser(uid);
        double point = uvo.getPoint();
        point += taskVO.getReward() * (1 + p) * (1 + uvo.getPer());
        uvo.setPoint(point);
        userService.updateUser(uvo);
        return workerTaskDAO.updateWorkerTask(vo);
    }

    @Override
    public int approveTask(String uid, String tid, int approve) {
        WorkerTaskVO vo = getWorkerTask(uid, tid);
        vo.setApprove(approve);
        MessageServiceImpl messageService = new MessageServiceImpl();
        messageService.sendApproveMessage(tid, uid, approve);
        TaskServiceImpl taskService = new TaskServiceImpl();
        TaskVO tvo = taskService.getTask(tid);
        UserServiceImpl userService = new UserServiceImpl();
        UserVO uvo = userService.getUser(uid);
        double point = uvo.getPoint();
        point += tvo.getBonus() * (1 + approve / 10) * (1 + uvo.getPer());
        uvo.setPoint(point);
        int rNum = uvo.getrNum();
        int lNum = uvo.getlNum();
        if (lNum > 0) {
            lNum--;
        } else if (lNum < 0) {
            lNum++;
        }
        if (approve >= 8) {
            rNum++;
            if (rNum >= 5) {
                lNum =+ 5;
                uvo.setPer(0.5);
            }
        } else if (approve <= 3) {
            rNum--;
            if (rNum <= -5) {
                lNum =- 5;
                uvo.setPer(-0.5);
            }
        }
        uvo.setlNum(lNum);
        uvo.setrNum(rNum);
        userService.updateUser(uvo);
        messageService.sendRemindMessage(uid);
        return workerTaskDAO.updateWorkerTask(vo);
    }

    @Override
    public WorkerTaskVO getWorkerTask(String uid, String tid) {
        return workerTaskDAO.getWorkerTaskVO(uid, tid);
    }

    @Override
    public ArrayList<String> getTaskOfWorker(String uid) {
        return workerTaskDAO.getTaskOfWorker(uid);
    }

    @Override
    public ArrayList<String> getWorkerOfTask(String tid) {
        return workerTaskDAO.getWorkerOfTask(tid);
    }

    @Override
    public int getSubmitNumOfTask(String tid) {
        int count = 0;
        ArrayList<String> uidList = getWorkerOfTask(tid);
        if (uidList == null || uidList.size() == 0) {
            return 0;
        }
        for (int i = 0; i < uidList.size(); i++) {
            WorkerTaskVO vo = getWorkerTask(uidList.get(i), tid);
            if (vo.isSubmitted()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getApprovedNumOfTask(String tid) {
        int count = 0;
        ArrayList<String> uidList = getWorkerOfTask(tid);
        if (uidList == null || uidList.size() == 0) {
            return 0;
        }
        for (int i = 0; i < uidList.size(); i++) {
            WorkerTaskVO vo = getWorkerTask(uidList.get(i), tid);
            if (vo.getApprove() != 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String getLabelProcessOfTask(String uid, String tid) {
        String res = "";
        PictureServiceImpl pictureService = new PictureServiceImpl();
        ResultServiceImpl resultService = new ResultServiceImpl();
        int picNum = pictureService.getPicOfTask(tid).size();
        int resNum = resultService.getResNumOfTask(tid, uid);
        res = res + resNum + "/" + picNum;
        return res;
    }

    @Override
    public Map<String, Integer> getTaskCountInfo(String tid) {
        ArrayList<String> wList = workerTaskDAO.getWorkerOfTask(tid);
        int goodCount = 0, badCount = 0, commonCount = 0, unApprovedCount = 0;
        for (String uid : wList) {
            WorkerTaskVO workerTaskVO = workerTaskDAO.getWorkerTaskVO(uid, tid);
            if (workerTaskVO.getApprove() >= 8) {
                goodCount++;
            } else if (workerTaskVO.getApprove() <= 3 && workerTaskVO.getApprove() > 0) {
                badCount++;
            } else if (workerTaskVO.getApprove() > 3 && workerTaskVO.getApprove() < 8) {
                commonCount++;
            } else {
                unApprovedCount++;
            }
        }
        Map<String, Integer> res = new HashMap<>();
        res.put("goodCount", goodCount);
        res.put("badCount", badCount);
        res.put("commonCount", commonCount);
        res.put("unApprovedCount", unApprovedCount);
        return res;
    }

    @Override
    public ArrayList<WorkerTaskVO> getLatestTenCountInfo(String uid) {
        MessageServiceImpl messageService = new MessageServiceImpl();
        ArrayList<MessageVO> rankByTime = messageService.getSortedMessageByTime(uid);
        ArrayList<MessageVO> need = new ArrayList<>();
        for (MessageVO messageVO : rankByTime) {
            if (messageVO.getContent().contains("被发起者审批")) {
                need.add(messageVO);
            }
        }
        ArrayList<WorkerTaskVO> res = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < need.size(); i++) {
            if (count >= 10) {
                break;
            }
            count++;
            String tid = need.get(i).getContent().substring(7, 12);
            String time = need.get(i).getTime();
            WorkerTaskVO workerTaskVO = getWorkerTask(uid, tid);
            workerTaskVO.setLabelProcess(time);
            res.add(workerTaskVO);
        }
        return res;
    }
}
