package com.sehwiii.demo.test;

import com.sehwiii.demo.DAO.DAOImpl.TaskDAOImpl;
import com.sehwiii.demo.VO.TaskVO;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class TaskDAOImplTest {

    @Test
    public void addTask() {
        TaskDAOImpl tdi = new TaskDAOImpl();
        tdi.deleteTask("00003");
        TaskVO vo = new TaskVO("00003", "task123", "need", 12.0, 22, 3, "00003", "整体标注");
        vo.setLevel(10);
        int res = tdi.addTask(vo);
        assertEquals(1, res);
    }

    @Test
    public void updateTask() {
        TaskDAOImpl tdi = new TaskDAOImpl();
        TaskVO vo = new TaskVO("00002", "ttt", "fds", 33, 33, 3, "00003", "区域标注");
        vo.setLevel(10);
        int res = tdi.updateTask(vo);
        assertEquals(1, res);
    }

    @Test
    public void getTask() {
        TaskDAOImpl tdi = new TaskDAOImpl();
        TaskVO vo = tdi.getTask("00003");
        String exp = "00003\ttask123\tneed\t12.0\t22.0\t3\t00003\t整体标注\tfalse\t10";
        String act = voToString(vo);
        assertEquals(exp, act);
    }

    @Test
    public void getTaskOfSponsor() {
        TaskDAOImpl tdi = new TaskDAOImpl();
        ArrayList<TaskVO> vos = tdi.getTaskOfSponsor("00003");
        String e1 = "00003\ttask123\tneed\t12.0\t22.0\t3\t00003\t整体标注\tfalse\t10";
        String e2 = "00002\ttask34\tn\t12.4\t5.3\t5\t00003\t区域标注\tfalse\t4";
        ArrayList<String> expList = new ArrayList<>();
        ArrayList<String> actList = new ArrayList<String>();
        expList.add(e1);
        expList.add(e2);
        for (int i = 0; i < vos.size(); i++) {
            actList.add(voToString(vos.get(i)));
        }
        assertEquals(expList, actList);

    }

    public String voToString(TaskVO vo) {
        return vo.getTid() + "\t" + vo.getTname() + "\t" + vo.getRequirement() + "\t" + vo.getReward() + "\t" +
                vo.getBonus() + "\t" + vo.getNum() + "\t" + vo.getSponsor() + "\t" + vo.getType() + "\t" +
                vo.isFinished() + "\t" + vo.getLevel();
    }
}