package com.sehwiii.demo.Service;

import com.sehwiii.demo.VO.WorkerTaskVO;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author 161250078
 */


public interface WorkerTaskService {

    /**
     * 工人接受任务
     *
     * @param uid
     * @param tid
     * @return
     */
    public int acceptTask(String uid, String tid);

    /**
     * 工人提交任务
     *
     * @param uid
     * @param tid
     * @return
     */
    public int submitTask(String uid, String tid);

    /**
     * 发布者审批工人任务
     *
     * @param uid
     * @param tid
     * @param approve
     * @return
     */
    public int approveTask(String uid, String tid, int approve);

    /**
     * 根据uid，tid获得工人任务信息
     *
     * @param uid
     * @param tid
     * @return
     */
    public WorkerTaskVO getWorkerTask(String uid, String tid);

    /**
     * 获得某个工人接受的所有任务id
     *
     * @param uid
     * @return
     */
    public ArrayList<String> getTaskOfWorker(String uid);

    /**
     * 获得接受某个任务的所有工人id
     *
     * @param tid
     * @return
     */
    public ArrayList<String> getWorkerOfTask(String tid);

    /**
     * 获得任务已提交数
     *
     * @param tid
     * @return
     */
    public int getSubmitNumOfTask(String tid);

    /**
     * 获得任务已审批数
     *
     * @param tid
     * @return
     */
    public int getApprovedNumOfTask(String tid);

    /**
     * 获得某个工人对于某个任务的完成进度
     *
     * @param uid
     * @param tid
     * @return
     */
    public String getLabelProcessOfTask(String uid, String tid);

    /**
     * 获得某个任务的统计信息（好评结果/差评结果/一般结果/未评结果数)
     *
     * @param tid
     * @return
     */
    public Map<String, Integer> getTaskCountInfo(String tid);

    /**
     * 获得某个工人最近十次的任务审批信息
     *
     * @param uid
     * @return
     */
    public ArrayList<WorkerTaskVO> getLatestTenCountInfo(String uid);

}
