package com.sehwiii.demo.test;

import com.sehwiii.demo.DAO.DAOImpl.UserDAOImpl;
import com.sehwiii.demo.VO.UserVO;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserDAOImplTest {

    @Test
    public void addUser() {
        UserDAOImpl udi = new UserDAOImpl();
        UserVO vo = new UserVO("00003", "123", "123456", "众包发起者");
        udi.deleteUser("00003");
        int res = udi.addUser(vo);
        assertEquals(1, res);
    }

    @Test
    public void updateUser() {
        UserDAOImpl udi = new UserDAOImpl();
        UserVO vo = new UserVO("00003", "lll", "123456", "众包工人");
        vo.setPoint(18.0);
        vo.setrNum(4);
        vo.setlNum(5);
        vo.setPer(0.5);
        int res = udi.updateUser(vo);
        assertEquals(1, res);
    }

    @Test
    public void getUser() {
        UserDAOImpl udi = new UserDAOImpl();
        UserVO vo = udi.getUser("00003");
        String exp = "00003\t123456\t123456\t众包发起者\t0.0\t0\t0\t0.0";
        String act = vo.getUid() + "\t" + vo.getUname() + "\t" + vo.getPassword() + "\t" + vo.getType() + "\t"
                + vo.getPoint() + "\t" + vo.getrNum() + "\t" + vo.getlNum() + "\t" + vo.getPer();
        assertEquals(exp, act);
    }

    @Test
    public void getAllUser() {
    }

    @Test
    public void login() {
        UserDAOImpl udi = new UserDAOImpl();
        UserVO vo = udi.login("00003", "123456");
        String exp = "00003\t123456\t123456\t众包发起者\t0.0\t0\t0\t0.0";
        String act = vo.getUid() + "\t" + vo.getUname() + "\t" + vo.getPassword() + "\t" + vo.getType() + "\t"
                + vo.getPoint() + "\t" + vo.getrNum() + "\t" + vo.getlNum() + "\t" + vo.getPer();
        assertEquals(exp, act);
    }
}