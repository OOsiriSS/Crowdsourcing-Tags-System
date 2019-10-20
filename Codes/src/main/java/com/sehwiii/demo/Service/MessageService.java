package com.sehwiii.demo.Service;

import com.sehwiii.demo.VO.MessageVO;

import java.util.ArrayList;


/**
 * @author 161250078
 */
public interface MessageService {

    /**
     * 增加一条信息
     *
     * @param vo
     * @return
     */
    public int addMessage(MessageVO vo);

    /**
     * 更新一条信息
     *
     * @param vo
     * @return
     */
    public int updateMessage(MessageVO vo);

    /**
     * 根据时间获得一条信息
     *
     * @param mid
     * @return
     */
    public MessageVO getMessage(int mid);

    /**
     * 获得发给某个用户的所有信息
     *
     * @param uid
     * @return
     */
    public ArrayList<MessageVO> getMessageOfUser(String uid);

    /**
     * 获得发给某个用户的所有信息（按时间排序）
     *
     * @param uid
     * @return
     */
    public ArrayList<MessageVO> getSortedMessageByTime(String uid);

    /**
     * 获得某用户未读信息的个数
     *
     * @param uid
     * @return
     */
    public int getUnReadNum(String uid);

    /**
     * 向工人发送任务已审批的消息
     *
     * @param tid
     * @param uid
     * @param approve
     * @return
     */
    public int sendApproveMessage(String tid, String uid, int approve);

    /**
     * 向发起者发送工人任务已提交的消息
     *
     * @param tid
     * @param uid
     * @return
     */
    public int sendSubmitMessage(String tid, String uid);

    /**
     * 向工人发送提醒信息
     *
     * @param uid
     * @return
     */
    public int sendRemindMessage(String uid);

    /**
     * 另某个工人的所有信息已读
     *
     * @param uid
     */
    public void makeAllRead(String uid);

    /**
     * 获得下一个消息的id
     * @return
     */
    public int getNextID();
}
