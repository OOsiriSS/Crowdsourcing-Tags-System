package com.sehwiii.demo.Service;

import com.sehwiii.demo.VO.ResultVO;


public interface ResultService {

    /**
     * 增加一个结果信息
     *
     * @param vo
     * @return
     */
    public int addRes(ResultVO vo);

    /**
     * 更新结果信息
     * @param vo
     * @return
     */
    public int updateRes(ResultVO vo);

    /**
     * 删除结果信息
     * @param rid
     * @return
     */
    public int deleteRes(String rid);

    /**
     * 根据结果id获得结果信息
     * @param rid
     * @return
     */
    public ResultVO getRes(String rid);

    /**
     * 获得结果图片存储路径
     * @param rid
     * @return
     */
    public String getSaveDir(String rid);

    /**
     * 获得工人对某个任务标注结果个数
     *
     * @param tid
     * @param uid
     * @return
     */
    public int getResNumOfTask(String tid, String uid);
}
