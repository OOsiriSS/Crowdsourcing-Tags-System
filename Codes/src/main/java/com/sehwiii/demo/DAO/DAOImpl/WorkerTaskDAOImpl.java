package com.sehwiii.demo.DAO.DAOImpl;

import com.sehwiii.demo.DAO.WorkerTaskDAO;
import com.sehwiii.demo.VO.WorkerTaskVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 161250078
 */
public class WorkerTaskDAOImpl implements WorkerTaskDAO {

    private Connection con = null;
    private PreparedStatement sta = null;
    private ResultSet res = null;

    
    @Override
    public int addWorkerTask(WorkerTaskVO vo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "insert into workertask (uid, tid, isSubmitted, approve) values (?, ?, ?, ?)";
                sta = con.prepareStatement(sql);
                sta.setString(1, vo.getUid());
                sta.setString(2, vo.getTid());
                sta.setBoolean(3, vo.isSubmitted());
                sta.setInt(4, vo.getApprove());
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
    public int updateWorkerTask(WorkerTaskVO vo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "update workertask set isSubmitted = ?, approve = ? where uid = ? and tid = ?";
                sta = con.prepareStatement(sql);
                sta.setBoolean(1, vo.isSubmitted());
                sta.setInt(2, vo.getApprove());
                sta.setString(3, vo.getUid());
                sta.setString(4, vo.getTid());
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
    public ArrayList<String> getWorkerOfTask(String tid) {
        ArrayList<String> ws = new ArrayList<>();
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select uid from workertask where tid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, tid);
                res = sta.executeQuery();
                while (res.next()) {
                    String uid = res.getString("uid");
                    ws.add(uid);
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
    public ArrayList<String> getTaskOfWorker(String uid) {
        ArrayList<String> ts = new ArrayList<>();
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select tid from workertask where uid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, uid);
                res = sta.executeQuery();
                while (res.next()) {
                    String tid = res.getString("tid");
                    ts.add(tid);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return ts;
    }

    @Override
    public WorkerTaskVO getWorkerTaskVO(String uid, String tid) {
        WorkerTaskVO vo = null;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from workertask where uid = ? and tid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, uid);
                sta.setString(2, tid);
                res = sta.executeQuery();
                if (res.next()) {
                    vo = new WorkerTaskVO();
                    vo.setUid(res.getString("uid"));
                    vo.setTid(res.getString("tid"));
                    vo.setSubmitted(res.getBoolean("isSubmitted"));
                    vo.setApprove(res.getInt("approve"));
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
    public int deleteWorkerTask(String uid, String tid) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "delete from workertask where uid = ? and tid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, uid);
                sta.setString(2, tid);
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
