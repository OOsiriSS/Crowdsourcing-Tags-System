package com.sehwiii.demo.VO;

/**
 * @author 161250078
 */
public class MessageVO {
    /*  time: String, 信息发送时间
        target: String, 信息发送对象id
        content: String, 信息内容
        isRead: boolean, 是否已读
        mid: int, 信息id
     */

    private String time;
    private String target;
    private String content;
    private boolean isRead;
    private int mid;

    public MessageVO() {
        this.time = "";
        this.target = "";
        this.content = "";
        this.isRead = false;
        this.mid = 0;
    }

    public MessageVO(String time, String target, String content, int mid) {
        this.time = time;
        this.target = target;
        this.content = content;
        this.isRead = false;
        this.mid = mid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }
}
