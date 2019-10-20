package com.sehwiii.demo.Service.ServiceImpl;

import com.sehwiii.demo.DAO.DAOImpl.ResultDAOImpl;
import com.sehwiii.demo.DAO.ResultDAO;
import com.sehwiii.demo.Service.ResultService;
import com.sehwiii.demo.VO.PictureVO;
import com.sehwiii.demo.VO.ResultVO;

import java.util.ArrayList;

/**
 * @author 161250078
 */

public class ResultServiceImpl implements ResultService {

    private ResultDAO resultDAO = new ResultDAOImpl();

    @Override
    public int addRes(ResultVO vo) {
        return resultDAO.addRes(vo);
    }

    @Override
    public int updateRes(ResultVO vo) {
        return resultDAO.updateRes(vo);
    }

    @Override
    public int deleteRes(String rid) {
        return resultDAO.deleteRes(rid);
    }

    @Override
    public ResultVO getRes(String rid) {
        return resultDAO.getRes(rid);
    }

    @Override
    public String getSaveDir(String rid) {
        String dirPath = "";
        String uid = rid.substring(0, 5);
        String tid = rid.substring(5, 10);
        dirPath = dirPath + "src/main/resources/static/resPic/" + uid + "/" + tid;
        return dirPath;
    }

    @Override
    public int getResNumOfTask(String tid, String uid) {
        PictureServiceImpl pictureService = new PictureServiceImpl();
        ArrayList<PictureVO> pictureVOS = pictureService.getPicOfTask(tid);
        int picNum = pictureVOS.size();
        int count = 0;
        for (int i = 0; i < picNum; i++) {
            String rid = uid + tid + String.valueOf(i + 1);
            if (getRes(rid) == null) {
                break;
            }
            count++;
        }
        return count;
    }
}
