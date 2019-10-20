package com.sehwiii.demo.DAO.DAOImpl;

import com.sehwiii.demo.DAO.TaskDAO;
import com.sehwiii.demo.VO.TaskVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 161250078
 */
public class TaskDAOImpl implements TaskDAO {

    private Connection con = null;
    private PreparedStatement sta = null;
    private ResultSet res = null;

    @Override
    public int addTask(TaskVO tvo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "insert into task (tid, tname, requirement, reward, bonus, num, uid, isFinished, type, level) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                sta = con.prepareStatement(sql);
                sta.setString(1, tvo.getTid());
                sta.setString(2, tvo.getTname());
                sta.setString(3, tvo.getRequirement());
                sta.setDouble(4, tvo.getReward());
                sta.setDouble(5, tvo.getBonus());
                sta.setInt(6, tvo.getNum());
                sta.setString(7, tvo.getSponsor());
                sta.setBoolean(8, tvo.isFinished());
                sta.setString(9, tvo.getType());
                sta.setInt(10, tvo.getLevel());
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
    public int updateTask(TaskVO tvo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "update task set tname = ?, set requirement = ?, set reward = ?, set bonus = ?, set num = ?, set uid = ?, set isFinished = ?, set type = ?, set level = ? where tid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, tvo.getTid());
                sta.setString(2, tvo.getRequirement());
                sta.setDouble(3, tvo.getReward());
                sta.setDouble(4, tvo.getBonus());
                sta.setInt(5, tvo.getNum());
                sta.setString(6, tvo.getSponsor());
                sta.setBoolean(7, tvo.isFinished());
                sta.setString(8, tvo.getType());
                sta.setInt(9, tvo.getLevel());
                sta.setString(10, tvo.getTid());
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
    public TaskVO getTask(String tid) {
        TaskVO tvo = null;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from task where tid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, tid);
                res = sta.executeQuery();
                if (res.next()) {
                    tvo = new TaskVO();
                    tvo.setTid(res.getString("tid"));
                    tvo.setTname(res.getString("tname"));
                    tvo.setRequirement(res.getString("requirement"));
                    tvo.setReward(res.getDouble("reward"));
                    tvo.setBonus(res.getDouble("bonus"));
                    tvo.setNum(res.getInt("num"));
                    tvo.setSponsor(res.getString("uid"));
                    tvo.setFinished(res.getBoolean("isFinished"));
                    tvo.setType(res.getString("type"));
                    tvo.setLevel(res.getInt("level"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return tvo;
    }

    @Override
    public ArrayList<TaskVO> getAllTask() {
        ArrayList<TaskVO> vos = new ArrayList<>();
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from task";
                sta = con.prepareStatement(sql);
                res = sta.executeQuery();
                while (res.next()) {
                    TaskVO tvo = new TaskVO();
                    tvo.setTid(res.getString("tid"));
                    tvo.setTname(res.getString("tname"));
                    tvo.setRequirement(res.getString("requirement"));
                    tvo.setReward(res.getDouble("reward"));
                    tvo.setBonus(res.getDouble("bonus"));
                    tvo.setNum(res.getInt("num"));
                    tvo.setSponsor(res.getString("uid"));
                    tvo.setFinished(res.getBoolean("isFinished"));
                    tvo.setType(res.getString("type"));
                    tvo.setLevel(res.getInt("level"));
                    vos.add(tvo);
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
    public ArrayList<TaskVO> getTaskOfSponsor(String uid) {
        ArrayList<TaskVO> vos = new ArrayList<>();
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from task where uid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, uid);
                res = sta.executeQuery();
                while (res.next()) {
                    TaskVO tvo = new TaskVO();
                    tvo.setTid(res.getString("tid"));
                    tvo.setTname(res.getString("tname"));
                    tvo.setRequirement(res.getString("requirement"));
                    tvo.setReward(res.getDouble("reward"));
                    tvo.setBonus(res.getDouble("bonus"));
                    tvo.setNum(res.getInt("num"));
                    tvo.setSponsor(res.getString("uid"));
                    tvo.setFinished(res.getBoolean("isFinished"));
                    tvo.setType(res.getString("type"));
                    tvo.setLevel(res.getInt("level"));
                    vos.add(tvo);
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
    public int deleteTask(String tid) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "delete from task where tid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, tid);
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
