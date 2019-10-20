package com.sehwiii.demo.Service;

import com.sehwiii.demo.VO.UserVO;

import java.util.ArrayList;
import java.util.Map;


/**
 * @author 161250078
 */

public interface UserService {

    /**
     * 登录
     *
     * @param uid
     * @param password
     * @return
     */
    public UserVO login(String uid, String password);

    /**
     * 注册
     *
     * @param vo
     * @return
     */
    public int register(UserVO vo);

    /**
     * 获取单个用户信息
     *
     * @param uid
     * @return
     */
    public UserVO getUser(String uid);

    /**
     * 获得所有用户信息
     *
     * @return
     */
    public ArrayList<UserVO> getAllUser();

    /**
     * 获得所有工人信息
     *
     * @return
     */
    public ArrayList<UserVO> getWorker();

    /**
     * 获得所有发起者信息
     *
     * @return
     */
    public ArrayList<UserVO> getSponsor();

    /**
     * 获得按积分排序的工人信息
     *
     * @return
     */
    public ArrayList<UserVO> getSortedWorker();

    /**
     * 获得下一个注册用户的id
     *
     * @return
     */
    public String getNextUid();

    /**
     * 获得某个工人的排行
     *
     * @param uid
     * @return
     */
    public int getRank(String uid);

    /**
     * 更新用户信息
     *
     * @param vo
     * @return
     */
    public int updateUser(UserVO vo);

    /**
     * 获得工人数
     *
     * @return
     */
    public int getWorkerNum();

    /**
     * 获得发起者数
     *
     * @return
     */
    public int getSponsorNum();

    /**
     * 获得工人的统计信息
     * @return
     */
    public Map<String, Integer> getWorkerCountInfo();
}
