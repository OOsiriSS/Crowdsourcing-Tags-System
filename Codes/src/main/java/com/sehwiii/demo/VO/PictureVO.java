package com.sehwiii.demo.VO;

import java.io.Serializable;

/**
 * @author 161520078
 */
public class PictureVO implements Serializable {
    /*
     * pid:String, 图片id（uid + tid +picIndex）
     * uid:String, 发起者id
     * tid:String, 任务id
     * pname:String, 图片名
     * path:String, 图片路径
     * type:String, 标注类型
     */

	
    private static final long serialVersionUID = 1L;

    private String pid;
    private String uid;
    private String tid;
    private String pname;
    private String path;
    private String type;

    public PictureVO() {
        this.pid = "";
        this.uid = "";
        this.tid = "";
        this.pname = "";
        this.path = "";
        this.type = "";
    }

    public PictureVO(String pid, String uid, String tid, String pname, String path) {
        this.pid = pid;
        this.uid = uid;
        this.tid = tid;
        this.pname = pname;
        this.path = path;
        this.type = "";
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
