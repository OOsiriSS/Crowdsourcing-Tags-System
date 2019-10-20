package com.sehwiii.demo.DAO.DAOImpl;

import com.sehwiii.demo.DAO.UserDAO;
import com.sehwiii.demo.VO.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 161250078
 */

public class UserDAOImpl implements UserDAO {

    private Connection con = null;
    private PreparedStatement sta = null;
    private ResultSet res = null;

    @Override
    public int addUser(UserVO uvo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "insert into user (uid, uname , password, type, point, rNum, lNum, per) values (?, ?, ?, ?, ?, ?, ?, ?)";
                sta = con.prepareStatement(sql);
                sta.setString(1, uvo.getUid());
                sta.setString(2, uvo.getUname());
                sta.setString(3, uvo.getPassword());
                sta.setString(4, uvo.getType());
                sta.setDouble(5, uvo.getPoint());
                sta.setInt(6, uvo.getrNum());
                sta.setInt(7, uvo.getlNum());
                sta.setDouble(8, uvo.getPer());
                result = sta.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return result;
    }

    @Override
    public int updateUser(UserVO uvo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "update user set uname = ?, password = ?, point = ?, rNum = ?, lNum = ?, per = ? where uid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, uvo.getUname());
                sta.setString(2, uvo.getPassword());
                sta.setDouble(3, uvo.getPoint());
                sta.setInt(4, uvo.getrNum());
                sta.setInt(5, uvo.getlNum());
                sta.setDouble(6, uvo.getPer());
                sta.setString(7, uvo.getUid());
                result = sta.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return result;
    }

    @Override
    public UserVO getUser(String uid) {
        UserVO uvo = null;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from user where uid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, uid);
                res = sta.executeQuery();
                if (res.next()) {
                    uvo = new UserVO();
                    uvo.setUid(res.getString("uid"));
                    uvo.setUname(res.getString("uname"));
                    uvo.setPassword(res.getString("password"));
                    uvo.setType(res.getString("type"));
                    uvo.setPoint(res.getDouble("point"));
                    uvo.setrNum(res.getInt("rNum"));
                    uvo.setlNum(res.getInt("lNum"));
                    uvo.setPer(res.getDouble("per"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return uvo;
    }

    @Override
    public ArrayList<UserVO> getAllUser() {
        ArrayList<UserVO> vos = new ArrayList<>();
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from user";
                sta = con.prepareStatement(sql);
                res = sta.executeQuery();
                while (res.next()) {
                    UserVO uvo = new UserVO();
                    uvo.setUid(res.getString("uid"));
                    uvo.setUname(res.getString("uname"));
                    uvo.setPassword(res.getString("password"));
                    uvo.setType(res.getString("type"));
                    uvo.setPoint(res.getDouble("point"));
                    uvo.setrNum(res.getInt("rNum"));
                    uvo.setlNum(res.getInt("lNum"));
                    uvo.setPer(res.getDouble("per"));
                    vos.add(uvo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return vos;
    }

    @Override
    public UserVO login(String uid, String password) {
        UserVO vo = null;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from user where uid = ? and password = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, uid);
                sta.setString(2, password);
                res = sta.executeQuery();
                if (res.next()) {
                    vo = getUser(uid);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return vo;
    }

    @Override
    public int deleteUser(String uid) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "delete from user where uid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, uid);
                result = sta.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return result;
    }

    @Override
    public ArrayList<UserVO> getAllWorker() {
        ArrayList<UserVO> ws = new ArrayList<>();
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from user where type = '众包工人'";
                sta = con.prepareStatement(sql);
                res = sta.executeQuery();
                while (res.next()) {
                    UserVO uvo = new UserVO();
                    uvo.setUid(res.getString("uid"));
                    uvo.setUname(res.getString("uname"));
                    uvo.setPassword(res.getString("password"));
                    uvo.setType(res.getString("type"));
                    uvo.setPoint(res.getDouble("point"));
                    uvo.setrNum(res.getInt("rNum"));
                    uvo.setlNum(res.getInt("lNum"));
                    uvo.setPer(res.getDouble("per"));
                    ws.add(uvo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return ws;
    }

    @Override
    public ArrayList<UserVO> getAllSponsor() {
        ArrayList<UserVO> ss = new ArrayList<>();
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from user where type = '众包发起者'";
                sta = con.prepareStatement(sql);
                res = sta.executeQuery();
                while (res.next()) {
                    UserVO uvo = new UserVO();
                    uvo.setUid(res.getString("uid"));
                    uvo.setUname(res.getString("uname"));
                    uvo.setPassword(res.getString("password"));
                    uvo.setType(res.getString("type"));
                    uvo.setPoint(res.getDouble("point"));
                    uvo.setrNum(res.getInt("rNum"));
                    uvo.setlNum(res.getInt("lNum"));
                    uvo.setPer(res.getDouble("per"));
                    ss.add(uvo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return ss;
    }
}
