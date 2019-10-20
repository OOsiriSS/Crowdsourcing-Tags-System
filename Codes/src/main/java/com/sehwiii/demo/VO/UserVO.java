package com.sehwiii.demo.VO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author 161250078
 */
public class UserVO implements Serializable {
    /*
     * uid:String, 用户id
     * uname:String, 用户名
     * password:String, 密码
     * type:String, 用户类型（系统管理员、众包发起者、众包工人）
     * point:double, 用户积分
     * rNum:int, 连续优秀/差评记录
     * lNum:int, 剩余奖励/惩罚任务数
     * per:double, 得分增加/减少比例
     */

    private static final long serialVersionUID = 1L;

    private String uid;
    private String uname;
    private String password;
    private String type;
    private double point;
    private int rNum;
    private int lNum;
    private double per;

    public UserVO() {
        this.uid = "";
        this.uname = "";
        this.password = "";
        this.type = "";
        this.point = 0.0;
        this.rNum = 0;
        this.lNum = 0;
        this.per = 0.0;
    }

    public UserVO(String uid, String uname, String password, String type) {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
        this.type = type;
        this.point = 0.0;
        this.rNum = 0;
        this.lNum = 0;
        this.per = 0.0;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public int getrNum() {
        return rNum;
    }

    public void setrNum(int rNum) {
        this.rNum = rNum;
    }

    public int getlNum() {
        return lNum;
    }

    public void setlNum(int lNum) {
        this.lNum = lNum;
    }

    public double getPer() {
        return per;
    }

    public void setPer(double per) {
        this.per = per;
    }
}
