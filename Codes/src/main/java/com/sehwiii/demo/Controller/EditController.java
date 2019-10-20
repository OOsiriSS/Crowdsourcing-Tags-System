package com.sehwiii.demo.Controller;

import com.sehwiii.demo.Service.ServiceImpl.UserServiceImpl;
import com.sehwiii.demo.Service.UserService;
import com.sehwiii.demo.VO.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class EditController {
    private UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUser(@RequestParam Map<String, Object> map) {
        String uid = map.get("id").toString();
        UserVO userVO = userService.getUser(uid);
        Map<String, Object> res = new HashMap<>();
        res.put("uname", userVO.getUname());
        return res;
    }

    @RequestMapping(value = "/editName", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editName(@RequestParam Map<String, Object> map) {
        String uid = map.get("id").toString();
        String name = map.get("uname").toString();
        UserVO userVO = userService.getUser(uid);
        userVO.setUname(name);
        Map<String, Object> res = new HashMap<>();
        res.put("mes", userService.updateUser(userVO));
        return res;
    }

    @RequestMapping(value = "/editPassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editPassword(@RequestParam Map<String, Object> map) {
        String uid = map.get("id").toString();
        String oldPassword = map.get("oldPassword").toString();
        String newPassword = map.get("newPassword").toString();
        UserVO userVO = userService.getUser(uid);
        String password = userVO.getPassword();
        Map<String, Object> res = new HashMap<>();
        if (!oldPassword.equals(password)) {
            res.put("mes", 0);
        } else {
            userVO.setPassword(newPassword);
            res.put("mes", userService.updateUser(userVO));
        }
        return res;
    }


}
