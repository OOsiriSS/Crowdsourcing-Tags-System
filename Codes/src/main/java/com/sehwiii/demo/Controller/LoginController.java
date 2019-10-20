package com.sehwiii.demo.Controller;

import com.sehwiii.demo.Service.MessageService;
import com.sehwiii.demo.Service.ServiceImpl.MessageServiceImpl;
import com.sehwiii.demo.Service.ServiceImpl.UserServiceImpl;
import com.sehwiii.demo.Service.UserService;
import com.sehwiii.demo.VO.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 161250078
 */


@Controller
public class LoginController {

    private UserService userService = new UserServiceImpl();
    private MessageService messageService = new MessageServiceImpl();

    @RequestMapping(value = "/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping(value = "/loginService", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginService(@RequestParam Map<String, Object> map) {
        String uid = map.get("userID").toString();
        String password = map.get("password").toString();
        UserVO userVO = userService.login(uid, password);
        if (userVO == null) {
            userVO = new UserVO();
            userVO.setType("error");
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("type", userVO.getType());
        map1.put("uid", userVO.getUid());
        return map1;
    }

    @RequestMapping(value = "/getNewUserID", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getNewID() {
        Map<String, Object> res = new HashMap<>();
        res.put("id", userService.getNextUid());
        return res;
    }

    @RequestMapping(value = "/registerService", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerService(@RequestParam Map<String, Object> map) {
        String uid = map.get("uid").toString();
        String uname = map.get("uname").toString();
        String type = map.get("type").toString();
        String password = map.get("password").toString();
        UserVO userVO = new UserVO(uid, uname, password, type);
        Map<String, Object> res = new HashMap<>();
        res.put("res", userService.register(userVO));
        res.put("uid", uid);
        res.put("type", type);
        return res;
    }

}
