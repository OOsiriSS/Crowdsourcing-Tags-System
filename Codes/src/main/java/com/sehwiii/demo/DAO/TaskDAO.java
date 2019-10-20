package com.sehwiii.demo.DAO;

import com.sehwiii.demo.VO.TaskVO;

import java.util.ArrayList;

/**
 * @author 161250078
 */
public interface TaskDAO {

    /**
     * @param tvo
     * @return 增加任务
     */
    public int addTask(TaskVO tvo);

    /**
     * @param tvo
     * @return 更新任务信息
     */
    public int updateTask(TaskVO tvo);

    /**
     * @param tid
     * @return 根据任务id获得某个任务的具体信息
     */
    public TaskVO getTask(String tid);

    /**
     * 获得所有任务的信息
     *
     * @return
     */
    public ArrayList<TaskVO> getAllTask();

    /**
     * @param uid
     * @return 根据用户id获得他发起的所有任务
     */
    public ArrayList<TaskVO> getTaskOfSponsor(String uid);

    /**
     * @param tid
     * @return 删除某个任务
     */
    public int deleteTask(String tid);
}
