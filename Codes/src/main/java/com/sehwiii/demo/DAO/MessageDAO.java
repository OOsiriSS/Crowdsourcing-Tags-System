package com.sehwiii.demo.DAO;

import com.sehwiii.demo.VO.MessageVO;

import java.util.ArrayList;

/**
 * @author 161250078
 */
public interface MessageDAO {

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
     * @param time
     * @return
     */
    public MessageVO getMessage(int time);

    /**
     * 获得发给某人的所有信息
     *
     * @param uid
     * @return
     */
    public ArrayList<MessageVO> getMessageOfUser(String uid);

    /**
     * 得到所有信息
     * @return
     */
    public ArrayList<MessageVO> getAllMessage();
}
