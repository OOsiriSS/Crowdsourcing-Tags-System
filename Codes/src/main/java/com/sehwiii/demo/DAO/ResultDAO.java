package com.sehwiii.demo.DAO;

import com.sehwiii.demo.VO.ResultVO;

/**
 * @author 161250078
 */
public interface ResultDAO {

    /**
     * @param vo
     * @return 增加标注结果信息
     */
    public int addRes(ResultVO vo);

    /**
     * @param vo
     * @return 更新结果信息
     */
    public int updateRes(ResultVO vo);

    /**
     * @param rid
     * @return 删除结果信息
     */
    public int deleteRes(String rid);

    /**
     * @param rid
     * @return 根据id获得结果信息
     */
    public ResultVO getRes(String rid);
}
