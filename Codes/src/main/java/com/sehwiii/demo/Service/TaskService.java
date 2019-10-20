package com.sehwiii.demo.Service;

import com.sehwiii.demo.VO.TaskVO;

import java.util.ArrayList;


/**
 * @author 161250078
 */

public interface TaskService {

    /**
     * 增加任务
     *
     * @param vo
     * @return
     */
    public int addTask(TaskVO vo);

    /**
     * 更新任务信息
     *
     * @param vo
     * @return
     */
    public int updateTask(TaskVO vo);

    /**
     * 获得所有任务
     *
     * @return
     */
    public ArrayList<TaskVO> getAllTask();

    /**
     * 获得单个任务信息
     *
     * @param tid
     * @return
     */
    public TaskVO getTask(String tid);

    /**
     * 根据类型和关键词搜索任务
     *
     * @param key
     * @param type
     * @return
     */
    public ArrayList<TaskVO> searchTaskBYKey(String key, String type);

    /**
     * 获得某个发起者发布的所有任务
     *
     * @param uid
     * @return
     */
    public ArrayList<TaskVO> getTaskOfSponsor(String uid);

    /**
     * 获得下个添加的任务的tid
     *
     * @return
     */
    public String getNextTid();

    /**
     * 获得任务的完成进度
     *
     * @param tid
     * @return
     */
    public String getFinishProcess(String tid);

    /**
     * 获得任务的审批进度
     *
     * @param tid
     * @return
     */
    public String getApproveProcess(String tid);

    /**
     * 获得已完成任务的信息
     *
     * @return
     */
    public ArrayList<TaskVO> getFinishedTask();

    /**
     * 获得未完成任务信息
     *
     * @return
     */
    public ArrayList<TaskVO> getUnFinishedTask();

    /**
     * 获得已完成任务个数
     *
     * @return
     */
    public int getFinishedNum();

    /**
     * 获得未完成任务个数
     *
     * @return
     */
    public int getUnFinishedNum();
}
