package com.sehwiii.demo.Service.ServiceImpl;

import com.sehwiii.demo.DAO.DAOImpl.MessageDAOImpl;
import com.sehwiii.demo.DAO.MessageDAO;
import com.sehwiii.demo.Service.MessageService;
import com.sehwiii.demo.VO.MessageVO;
import com.sehwiii.demo.VO.UserVO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * @author 161250078
 */

public class MessageServiceImpl implements MessageService {

    private MessageDAO messageDAO = new MessageDAOImpl();

    @Override
    public int addMessage(MessageVO vo) {
        return messageDAO.addMessage(vo);
    }

    @Override
    public int updateMessage(MessageVO vo) {
        return messageDAO.updateMessage(vo);
    }

    @Override
    public MessageVO getMessage(int mid) {
        return messageDAO.getMessage(mid);
    }

    @Override
    public ArrayList<MessageVO> getMessageOfUser(String uid) {
        return messageDAO.getMessageOfUser(uid);
    }

    @Override
    public ArrayList<MessageVO> getSortedMessageByTime(String uid) {
        ArrayList<MessageVO> messageVOS = getMessageOfUser(uid);
        Collections.sort(messageVOS, new Comparator<MessageVO>() {
            @Override
            public int compare(MessageVO o1, MessageVO o2) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date d1 = dateFormat.parse(o1.getTime());
                    Date d2 = dateFormat.parse(o2.getTime());
                    if (d1.compareTo(d2) > 0) {
                        return 1;
                    } else if (d1.compareTo(d2) < 0) {
                        return -1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        return messageVOS;
    }

    @Override
    public int getUnReadNum(String uid) {
        ArrayList<MessageVO> vos = getMessageOfUser(uid);
        int count = 0;
        if (vos == null || vos.size() == 0) {
            return 0;
        }
        for (int i = 0; i < vos.size(); i++) {
            if (!vos.get(i).isRead()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int sendApproveMessage(String tid, String uid, int approve) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MessageVO vo = new MessageVO();
        vo.setMid(getNextID());
        vo.setTime(df.format(date));
        vo.setTarget(uid);
        vo.setContent("您已完成的任务" + tid + "已被发起者审批，评分为" + approve + "!");
        return addMessage(vo);
    }

    @Override
    public int sendSubmitMessage(String tid, String uid) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MessageVO vo = new MessageVO();
        vo.setMid(getNextID());
        vo.setTime(df.format(date));
        TaskServiceImpl taskService = new TaskServiceImpl();
        vo.setTarget(taskService.getTask(tid).getSponsor());
        vo.setContent("您发布的任务" + tid + "已被工人" + uid + "接受并完成，快去审批吧!");
        return addMessage(vo);
    }

    @Override
    public int sendRemindMessage(String uid) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserServiceImpl userService = new UserServiceImpl();
        MessageVO vo = new MessageVO();
        vo.setMid(getNextID());
        vo.setTime(df.format(date));
        vo.setTarget(uid);
        UserVO uvo = userService.getUser(uid);
        System.out.println(uvo.getrNum() + " " + uvo.getUname());
        if (uvo.getrNum() >= 5) {
            UserVO userVO = userService.getUser(uid);
            userVO.setrNum(0);
            userService.updateUser(userVO);
            vo.setContent("您最近的任务完成得不错，接下来几次任务系统会奖励您一些额外积分！");
            return addMessage(vo);
        } else if (uvo.getrNum() <= -5) {
            UserVO userVO = userService.getUser(uid);
            userVO.setrNum(0);
            userService.updateUser(userVO);
            vo.setContent("您最近的任务完成质量不高，接下来几次任务系统会给予相应惩罚！");
            return addMessage(vo);
        } else if (uvo.getrNum() == 3){
            vo.setContent("您还差" + String.valueOf(5 - uvo.getrNum()) + "次任务，就可以获得奖励了，再加把劲！");
            return addMessage(vo);
        } else if (uvo.getrNum() == -3) {
            vo.setContent("再做差" + String.valueOf(5 + uvo.getrNum()) + "次任务，系统会做出惩罚，努力吧！");
            return addMessage(vo);
        } else {
            return 0;
        }

    }

    @Override
    public void makeAllRead(String uid) {
        ArrayList<MessageVO> vos = getMessageOfUser(uid);
        if (vos == null || vos.size() == 0) {
            return;
        }
        for (int i = 0; i < vos.size(); i++) {
            MessageVO messageVO = vos.get(i);
            if (!messageVO.isRead()) {
                messageVO.setRead(true);
                messageDAO.updateMessage(messageVO);
            }
        }
    }

    @Override
    public int getNextID() {
        ArrayList<MessageVO> all = messageDAO.getAllMessage();
        if (all == null) {
            return 1;
        }
        return all.size() + 1;
    }
}
