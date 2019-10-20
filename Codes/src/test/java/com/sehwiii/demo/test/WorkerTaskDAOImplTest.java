package com.sehwiii.demo.test;

import com.sehwiii.demo.DAO.DAOImpl.WorkerTaskDAOImpl;
import com.sehwiii.demo.VO.WorkerTaskVO;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class WorkerTaskDAOImplTest {

    @Test
    public void addWorkerTask() {
        WorkerTaskDAOImpl wtdi = new WorkerTaskDAOImpl();
        wtdi.deleteWorkerTask("00003", "00003");
        WorkerTaskVO wtvo = new WorkerTaskVO("00003", "00003");
        int res = wtdi.addWorkerTask(wtvo);
        assertEquals(1, res);
    }

    @Test
    public void updateWorkerTask() {
        WorkerTaskDAOImpl wtdi = new WorkerTaskDAOImpl();
        WorkerTaskVO wtvo = new WorkerTaskVO("00003", "00002");
        wtvo.setSubmitted(true);
        wtvo.setApprove(8);
        int res = wtdi.updateWorkerTask(wtvo);
        assertEquals(1, res);
    }

    @Test
    public void getWorkerOfTask() {
        WorkerTaskDAOImpl wtdi = new WorkerTaskDAOImpl();
        ArrayList<String> act = wtdi.getWorkerOfTask("00003");
        ArrayList<String> exp = new ArrayList<>();
        exp.add("00003");
        assertEquals(exp, act);
    }

    @Test
    public void getTaskOfWorker() {
        WorkerTaskDAOImpl wtdi = new WorkerTaskDAOImpl();
        ArrayList<String> act = wtdi.getTaskOfWorker("00003");
        ArrayList<String> exp = new ArrayList<>();
        exp.add("00003");
        exp.add("00002");
        assertEquals(exp, act);
    }

    @Test
    public void getWorkerTaskVO() {
        WorkerTaskDAOImpl wtdi = new WorkerTaskDAOImpl();
        WorkerTaskVO vo = wtdi.getWorkerTaskVO("00003", "00003");
        String exp = "00003\t00003\tfalse\t0";
        String act = voToString(vo);
        assertEquals(exp, act);
    }

    public String voToString(WorkerTaskVO vo) {
        return vo.getUid() + "\t" + vo.getTid() + "\t" + vo.isSubmitted() + "\t" + vo.getApprove();
    }
}