package com.sehwiii.demo.DAO.DAOImpl;

import com.sehwiii.demo.DAO.MessageDAO;
import com.sehwiii.demo.VO.MessageVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 161250078
 */
public class MessageDAOImpl implements MessageDAO {

    private Connection con = null;
    private PreparedStatement sta = null;
    private ResultSet res = null;

    @Override
    public int addMessage(MessageVO vo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "insert into message (time, target, content, isRead, mid) values (?, ?, ?, ?, ?)";
                sta = con.prepareStatement(sql);
                sta.setString(1, vo.getTime());
                sta.setString(2, vo.getTarget());
                sta.setString(3, vo.getContent());
                sta.setBoolean(4, vo.isRead());
                sta.setInt(5, vo.getMid());
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
    public int updateMessage(MessageVO vo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "update message set isRead = ? where mid = ?";
                sta = con.prepareStatement(sql);
                sta.setBoolean(1, vo.isRead());
                sta.setInt(2, vo.getMid());
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
    public MessageVO getMessage(int mid) {
        MessageVO vo = null;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from message where mid = ?";
                sta = con.prepareStatement(sql);
                sta.setInt(1, mid);
                res = sta.executeQuery();
                if (res.next()) {
                    vo = new MessageVO();
                    vo.setTime(res.getString("time"));
                    vo.setTarget(res.getString("target"));
                    vo.setContent(res.getString("content"));
                    vo.setRead(res.getBoolean("isRead"));
                    vo.setMid(res.getInt("mid"));
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
    public ArrayList<MessageVO> getMessageOfUser(String uid) {
        ArrayList<MessageVO> vos = new ArrayList<>();
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from message where target = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, uid);
                res = sta.executeQuery();
                while (res.next()) {
                    MessageVO vo = new MessageVO();
                    vo.setTime(res.getString("time"));
                    vo.setTarget(res.getString("target"));
                    vo.setContent(res.getString("content"));
                    vo.setRead(res.getBoolean("isRead"));
                    vo.setMid(res.getInt("mid"));
                    vos.add(vo);
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
    public ArrayList<MessageVO> getAllMessage() {
        ArrayList<MessageVO> vos = new ArrayList<>();
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from message";
                sta = con.prepareStatement(sql);
                res = sta.executeQuery();
                while (res.next()) {
                    MessageVO vo = new MessageVO();
                    vo.setTime(res.getString("time"));
                    vo.setTarget(res.getString("target"));
                    vo.setContent(res.getString("content"));
                    vo.setRead(res.getBoolean("isRead"));
                    vo.setMid(res.getInt("mid"));
                    vos.add(vo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return vos;
    }

}
