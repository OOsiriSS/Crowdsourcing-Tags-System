package com.sehwiii.demo.DAO;

import com.sehwiii.demo.VO.WorkerTaskVO;

import java.util.ArrayList;

/**
 * @author 161250078
 */
public interface WorkerTaskDAO {

    /**
     * @param vo
     * @return 工人接受任务后调用此方法，将相应的工人任务属性加入数据库
     */
    public int addWorkerTask(WorkerTaskVO vo);

    /**
     * @param vo
     * @return 更新工人任务的信息
     */
    public int updateWorkerTask(WorkerTaskVO vo);

    /**
     * @param tid
     * @return 根据任务id获得接受此任务的工人id
     */
    public ArrayList<String> getWorkerOfTask(String tid);

    /**
     * @param uid
     * @return 根据工人id获得他接受的所有任务的id
     */
    public ArrayList<String> getTaskOfWorker(String uid);

    /**
     * @param uid
     * @param tid
     * @return 根据工人id和任务id得到WorkerTask信息
     */
    public WorkerTaskVO getWorkerTaskVO(String uid, String tid);

    /**
     * @param uid
     * @param tid
     * @return 删除WorkerTask
     */
    public int deleteWorkerTask(String uid, String tid);
}
