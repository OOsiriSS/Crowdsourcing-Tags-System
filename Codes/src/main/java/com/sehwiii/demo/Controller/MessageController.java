package com.sehwiii.demo.Controller;


import com.sehwiii.demo.Service.MessageService;
import com.sehwiii.demo.Service.ServiceImpl.MessageServiceImpl;
import com.sehwiii.demo.Service.ServiceImpl.UserServiceImpl;
import com.sehwiii.demo.Service.UserService;
import com.sehwiii.demo.VO.MessageVO;
import com.sehwiii.demo.VO.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MessageController {

    private MessageService messageService = new MessageServiceImpl();
    private UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getMessage(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        UserVO userVO = userService.getUser(uid);
        ArrayList<MessageVO> mesList = messageService.getMessageOfUser(uid);
        int size = mesList.size();
        MessageVO[] message = new MessageVO[size];
        message = mesList.toArray(message);
        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", userVO);
        res.put("message", message);
        return res;
    }

    @RequestMapping(value = "/makeAllRead", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> makeAllRead(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        messageService.makeAllRead(uid);
        ArrayList<MessageVO> mesList = messageService.getMessageOfUser(uid);
        int size = mesList.size();
        MessageVO[] message = new MessageVO[size];
        message = mesList.toArray(message);
        Map<String, Object> res = new HashMap<>();
        res.put("message", message);
        return res;
    }
}
