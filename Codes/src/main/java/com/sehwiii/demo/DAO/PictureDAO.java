package com.sehwiii.demo.DAO;

import com.sehwiii.demo.VO.PictureVO;

import java.util.ArrayList;

/**
 * @author 161250078
 */
public interface PictureDAO {

    /**
     * @param pvo
     * @return 添加图片信息
     */
    public int addPic(PictureVO pvo);

    /**
     * 获得图片信息
     *
     * @param pid
     * @return
     */
    public PictureVO getPic(String pid);

    
    /**
     * @param tid
     * @return 根据任务id获得任务图片信息
     */
    public ArrayList<PictureVO> getPicOfTask(String tid);

    /**
     * 更新图片信息
     *
     * @param vo
     * @return
     */
    public int updatePic(PictureVO vo);

    /**
     * 删除图片信息
     *
     * @param pid
     * @return
     */
    public int deletePic(String pid);
}
