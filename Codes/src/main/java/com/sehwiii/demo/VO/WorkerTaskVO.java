package com.sehwiii.demo.VO;

import java.io.Serializable;

/**
 * @author 161250078
 */
public class WorkerTaskVO implements Serializable {
    /*
     * uid:String, 工人id
     * tid:String, 任务id
     * isSubmitted:boolean, 是否提交
     * approve:int, 质量评定分数
     * labelProcess:String, 标注进度
     */

    private static final long serialVersionUID = 1L;

    private String uid;
    private String tid;
    private boolean isSubmitted;
    private int approve;
    private String labelProcess;

    public WorkerTaskVO() {
        this.uid = "";
        this.tid = "";
        this.isSubmitted = false;
        this.approve = 0;
        this.labelProcess = "";
    }

    public WorkerTaskVO(String uid, String tid) {
        this.uid = uid;
        this.tid = tid;
        this.isSubmitted = false;
        this.approve = 0;
        this.labelProcess = "";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public String getLabelProcess() {
        return labelProcess;
    }

    public void setLabelProcess(String labelProcess) {
        this.labelProcess = labelProcess;
    }
}
