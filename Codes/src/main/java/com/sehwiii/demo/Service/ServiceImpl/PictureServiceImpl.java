package com.sehwiii.demo.Service.ServiceImpl;

import com.sehwiii.demo.DAO.DAOImpl.PictureDAOImpl;
import com.sehwiii.demo.DAO.PictureDAO;
import com.sehwiii.demo.Service.PictureService;
import com.sehwiii.demo.VO.PictureVO;

import java.util.ArrayList;

/**
 * @author 161250078
 */

public class PictureServiceImpl implements PictureService {

    private PictureDAO pictureDAO = new PictureDAOImpl();

    @Override
    public int addPicture(PictureVO vo) {
        return pictureDAO.addPic(vo);
    }

    @Override
    public PictureVO getPic(String pid) {
        return pictureDAO.getPic(pid);
    }

    @Override
    public ArrayList<PictureVO> getPicOfTask(String tid) {
        return pictureDAO.getPicOfTask(tid);
    }

    @Override
    public String getSaveDir(String pid) {
        String uid = pid.substring(0, 5);
        String tid = pid.substring(5, 10);
        String dirPath = "src/main/resources/static/taskPic/" + uid + "/" + tid;
        return dirPath;
    }

    @Override
    public void updateType(String tid, String type) {
        ArrayList<PictureVO> pictureVOS = getPicOfTask(tid);
        for (PictureVO pictureVO : pictureVOS) {
            pictureVO.setType(type);
            pictureDAO.updatePic(pictureVO);
        }
    }

    @Override
    public void deletePicOfTask(String tid) {
        ArrayList<PictureVO> pictureVOS = getPicOfTask(tid);
        for (PictureVO pictureVO : pictureVOS) {
            pictureDAO.deletePic(pictureVO.getPid());
        }
    }

    @Override
    public void deletePic(String pid) {
        pictureDAO.deletePic(pid);
    }
}
