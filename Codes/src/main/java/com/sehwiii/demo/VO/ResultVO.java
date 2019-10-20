package com.sehwiii.demo.VO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author 161250078
 */
public class ResultVO implements Serializable {
    /*
     * rid:String, 结果id(uid + tid + picIndex)
     * uid:String, 工人id
     * tid:String, 任务id
     * pid:String, 图片id
     * resPic:String, 结果图片名
     * resPicPath:String, 结果图片路径
     * noteList:String, 注释List
     */

    private static final long serialVersionUID = 1L;

    private String rid;
    private String uid;
    private String tid;
    private String pid;
    private String resPic;
    private String resPicPath;
    private ArrayList<String> noteList;

    public ResultVO() {
        this.rid = "";
        this.uid = "";
        this.tid = "";
        this.pid = "";
        this.resPic = "";
        this.resPicPath = "";
        this.noteList = new ArrayList<>();
    }

    public ResultVO(String rid, String uid, String tid, String pid, String resPic, String resPicPath, ArrayList<String> noteList) {
        this.rid = rid;
        this.uid = uid;
        this.tid = tid;
        this.pid = pid;
        this.resPic = resPic;
        this.resPicPath = resPicPath;
        this.noteList = noteList;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getResPic() {
        return resPic;
    }

    public void setResPic(String resPic) {
        this.resPic = resPic;
    }

    public String getResPicPath() {
        return resPicPath;
    }

    public void setResPicPath(String resPicPath) {
        this.resPicPath = resPicPath;
    }

    public ArrayList<String> getNoteList() {
        return noteList;
    }

    public void setNoteList(ArrayList<String> noteList) {
        this.noteList = noteList;
    }
}
