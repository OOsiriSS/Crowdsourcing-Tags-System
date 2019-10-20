package com.sehwiii.demo.DAO;

import com.sehwiii.demo.VO.UserVO;

import java.util.ArrayList;

/**
 * @author 161250078
 */
public interface UserDAO {
    /**
     * @param uvo
     * @return 增加用户
     */
    public int addUser(UserVO uvo);

    /**
     * @param uvo
     * @return 更新用户信息
     */
    public int updateUser(UserVO uvo);

    /**
     * @param uid
     * @return 根据uid获得某个用户的信息
     */
    public UserVO getUser(String uid);

    /**
     * @return 获得所有用户的信息
     */
    public ArrayList<UserVO> getAllUser();

    /**
     * @param uid
     * @param password
     * @return 登录方法
     */
    public UserVO login(String uid, String password);

    /**
     * @param uid
     * @return 删除用户信息
     */
    public int deleteUser(String uid);

    /**
     * @return 得到所有工人的信息
     */
    public ArrayList<UserVO> getAllWorker();

    /**
     * 得到所有发起者信息
     *
     * @return
     */
    public ArrayList<UserVO> getAllSponsor();
}
