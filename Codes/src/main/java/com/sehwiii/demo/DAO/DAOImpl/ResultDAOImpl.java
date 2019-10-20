package com.sehwiii.demo.DAO.DAOImpl;

import com.sehwiii.demo.DAO.ResultDAO;
import com.sehwiii.demo.VO.ResultVO;
import com.sehwiii.demo.util.StringListTranf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 161250078
 */
public class ResultDAOImpl implements ResultDAO {

    private Connection con = null;
    private PreparedStatement sta = null;
    private ResultSet res = null;

    @Override
    public int addRes(ResultVO vo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "insert into result (rid, uid, tid, pid, resPic, resPicPath, noteInfo) values (?, ?, ?, ?, ?, ?, ?)";
                sta = con.prepareStatement(sql);
                sta.setString(1, vo.getRid());
                sta.setString(2, vo.getUid());
                sta.setString(3, vo.getTid());
                sta.setString(4, vo.getPid());
                sta.setString(5, vo.getResPic());
                sta.setString(6, vo.getResPicPath());
                sta.setString(7, StringListTranf.listToString(vo.getNoteList()));
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
    public int updateRes(ResultVO vo) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "update result set resPic = ?, resPicPath = ?, noteInfo = ? where rid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, vo.getResPic());
                sta.setString(2, vo.getResPicPath());
                sta.setString(3, StringListTranf.listToString(vo.getNoteList()));
                sta.setString(4, vo.getRid());
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
    public int deleteRes(String rid) {
        int result = 0;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "delete from result where rid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, rid);
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
    public ResultVO getRes(String rid) {
        ResultVO vo = null;
        try {
            con = MySQLCon.getConnection();
            if (con != null) {
                String sql = "select * from result where rid = ?";
                sta = con.prepareStatement(sql);
                sta.setString(1, rid);
                res = sta.executeQuery();
                if (res.next()) {
                    vo = new ResultVO();
                    vo.setRid(res.getString("rid"));
                    vo.setUid(res.getString("uid"));
                    vo.setTid(res.getString("tid"));
                    vo.setPid(res.getString("pid"));
                    vo.setResPic(res.getString("resPic"));
                    vo.setResPicPath(res.getString("resPicPath"));
                    vo.setNoteList(StringListTranf.stringToList(res.getString("noteInfo")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQLCon.closeAll(con, sta, res);
        }
        return vo;
    }
}
