package com.sehwiii.demo.Service.ServiceImpl;

import com.sehwiii.demo.DAO.DAOImpl.UserDAOImpl;
import com.sehwiii.demo.DAO.UserDAO;
import com.sehwiii.demo.Service.UserService;
import com.sehwiii.demo.VO.UserVO;
import com.sehwiii.demo.util.ToStringID;

import java.util.*;

/**
 * @author 161250078
 */

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public UserVO login(String uid, String password) {
        return userDAO.login(uid, password);
    }

    @Override
    public int register(UserVO vo) {
        return userDAO.addUser(vo);
    }

    @Override
    public UserVO getUser(String uid) {
        return userDAO.getUser(uid);
    }

    @Override
    public ArrayList<UserVO> getAllUser() {
        return userDAO.getAllUser();
    }

    @Override
    public ArrayList<UserVO> getWorker() {
        return userDAO.getAllWorker();
    }

    @Override
    public ArrayList<UserVO> getSponsor() {
        return userDAO.getAllSponsor();
    }

    @Override
    public ArrayList<UserVO> getSortedWorker() {
        ArrayList<UserVO> allWorkerList = userDAO.getAllWorker();
        Collections.sort(allWorkerList, new Comparator<UserVO>() {
            @Override
            public int compare(UserVO o1, UserVO o2) {
                if (o1.getPoint() > o2.getPoint()) {
                    return -1;
                } else if (o1.getPoint() < o2.getPoint()) {
                    return 1;
                }
                return 0;
            }
        });
        return allWorkerList;
    }

    @Override
    public String getNextUid() {
        ArrayList<UserVO> allUser = getAllUser();
        int length = allUser.size();
        return ToStringID.toStringID(length);
    }

    @Override
    public int getRank(String uid) {
        int count = 1;
        UserVO vo = getUser(uid);
        ArrayList<UserVO> allUserList = userDAO.getAllWorker();
        for (int i = 0; i < allUserList.size(); i++) {
            if (vo.getPoint() < allUserList.get(i).getPoint()) {
                count++;
            }
        }
        return count;

    }

    @Override
    public int updateUser(UserVO vo) {
        return userDAO.updateUser(vo);
    }

    @Override
    public int getWorkerNum() {
        ArrayList<UserVO> worker = getWorker();
        if (worker == null || worker.size() == 0) {
            return 0;
        }
        return worker.size();
    }

    @Override
    public int getSponsorNum() {
        ArrayList<UserVO> sponsor = getSponsor();
        if (sponsor == null || sponsor.size() == 0) {
            return 0;
        }
        return sponsor.size();
    }

    @Override
    public Map<String, Integer> getWorkerCountInfo() {
        int goodNum = 0, badNum = 0, commonNum = 0;
        ArrayList<UserVO> allWorker = getWorker();
        for (int i = 0; i < allWorker.size(); i++) {
            UserVO worker = allWorker.get(i);
            if (worker.getlNum() > 0) {
                goodNum++;
            } else if (worker.getlNum() < 0) {
                badNum++;
            } else {
                commonNum++;
            }
        }
        Map<String, Integer> count = new HashMap<>();
        count.put("goodNum", goodNum);
        count.put("badNum", badNum);
        count.put("commonNum", commonNum);
        return count;
    }
}
