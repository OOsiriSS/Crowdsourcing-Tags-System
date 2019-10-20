package com.sehwiii.demo.DAO.DAOImpl;

import com.sehwiii.demo.DAO.PictureDAO;
import com.sehwiii.demo.VO.PictureVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 161250078
 */
public class PictureDAOImpl implements PictureDAO {

    private Connection con = null;
    private PreparedStatement sta = null;
    private ResultSet res = null;

    @Override
    public int addPic(PictureVO pvo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "insert into picture (pid, tid, pname, path, type) values (?, ?, ?, ?, ?)";
                sta = con.prepareStatement(sql);
                sta.setString(1, pvo.getPid());
                sta.setString(2, pvo.getTid());
                sta.setString(3, pvo.getPname());
                sta.setString(4, pvo.getPath());
                sta.setString(5, pvo.getType());
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
    public PictureVO getPic(String pid) {
        PictureVO pvo = null;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from picture where pid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, pid);
                res = sta.executeQuery();
                if (res.next()) {
                    pvo = new PictureVO();
                    pvo.setPid(res.getString("pid"));
                    pvo.setTid(res.getString("tid"));
                    pvo.setPname(res.getString("pname"));
                    pvo.setPath(res.getString("path"));
                    pvo.setType(res.getString("type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return pvo;
    }

    @Override
    public ArrayList<PictureVO> getPicOfTask(String tid) {
        ArrayList<PictureVO> vos = new ArrayList<>();
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from picture where tid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, tid);
                res = sta.executeQuery();
                while (res.next()) {
                    PictureVO pvo = new PictureVO();
                    pvo.setPid(res.getString("pid"));
                    pvo.setTid(res.getString("tid"));
                    pvo.setPname(res.getString("pname"));
                    pvo.setPath(res.getString("path"));
                    pvo.setType(res.getString("type"));
                    vos.add(pvo);
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
    public int updatePic(PictureVO vo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "update picture set type = ? where pid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, vo.getType());
                sta.setString(2, vo.getPid());
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
    public int deletePic(String pid) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "delete from picture where pid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, pid);
                result = sta.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return result;
    }
}
