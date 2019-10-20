package com.sehwiii.demo.VO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author 161250078
 */
public class TaskVO implements Serializable {
    /*
     * tid:String, 任务id
     * tname:String, 任务名
     * requirement:String, 任务需求
     * reword:double, 任务基本奖励分
     * bonus:double, 任务额外奖励分
     * num:int, 任务要求人数
     * sponsor:String, 发起者
     * isFinished:boolean, 是否完成（num == 提交任务人数）
     * picList:ArrayList, 图片List
     * type:String, 标注类型
     * level:int, 任务难度
     * finishProcess:String, 完成进度，不存储在数据库内，通过方法计算得到
     * approveProcess:String, 审批进度，其他同上
     */

    private static final long serialVersionUID = 1L;

    private String tid;
    private String tname;
    private String requirement;
    private double reward;
    private double bonus;
    private int num;
    private String sponsor;
    private boolean isFinished;
    private String type;
    private int level;
    private String finishProcess;
    private String approveProcess;

    public TaskVO() {
        this.tid = "";
        this.tname = "";
        this.requirement = "";
        this.reward = 0.0;
        this.bonus = 0.0;
        this.num = 0;
        this.sponsor = "";
        this.isFinished = false;
        this.type = "";
        this.level = 0;
        this.finishProcess = "";
        this.approveProcess = "";
    }

    public TaskVO(String tid, String tname, String requirement, double reward, double bonus, int num, String sponsor, String type) {
        this.tid = tid;
        this.tname = tname;
        this.requirement = requirement;
        this.reward = reward;
        this.bonus = bonus;
        this.num = num;
        this.sponsor = sponsor;
        this.type = type;
        this.isFinished = false;
        this.level = 0;
        this.finishProcess = "";
        this.approveProcess = "";
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFinishProcess() {
        return finishProcess;
    }

    public void setFinishProcess(String finishProcess) {
        this.finishProcess = finishProcess;
    }

    public String getApproveProcess() {
        return approveProcess;
    }

    public void setApproveProcess(String approveProcess) {
        this.approveProcess = approveProcess;
    }

    public void setLevelByNum(int picNum) {
        if (this.type.equals("整体标注")) {
            this.level = picNum / 2;
        } else if (this.type.equals("方框标注")) {
            this.level = picNum * 4 / 5;
        } else if (this.type.equals("区域标注")) {
            this.level = picNum;
        }
    }

}
