package com.sehwiii.demo.Service;

import com.sehwiii.demo.VO.PictureVO;

import java.util.ArrayList;


/**
 * @author 161250078
 */
public interface PictureService {

    /**
     * 添加图片
     * @param vo
     * @return
     */
    public int addPicture(PictureVO vo);

    /**
     * 得到图片信息
     * @param pid
     * @return
     */
    public PictureVO getPic(String pid);

    /**
     * 获得某个任务的所有图片信息
     * @param tid
     * @return
     */
    public ArrayList<PictureVO> getPicOfTask(String tid);

    /**
     * 获得图片存储目录
     * @param pid
     * @return
     */
    public String getSaveDir(String pid);

    /**
     * 设置某个任务所有图片的类型
     *
     * @param tid
     * @param type
     */
    public void updateType(String tid, String type);

    /**
     * 删除某个任务的所有图片
     *
     * @param tid
     */
    public void deletePicOfTask(String tid);

    /**
     * 删除某个图片
     *
     * @param pid
     */
    public void deletePic(String pid);
}
