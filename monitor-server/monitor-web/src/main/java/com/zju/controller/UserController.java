package com.zju.controller;

import com.zju.common.ErrorStatusAndMessage;
import com.zju.common.JSONResult;
import com.zju.entity.User;
import com.zju.request.UserAddRequest;
import com.zju.request.UserDeleteRequest;
import com.zju.service.UserInfoService;
import com.zju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("list")
    public JSONResult list() {
        return JSONResult.isOk().msg("查询成功！！！").put("list", userService.list());
    }

    @PostMapping("add")
    public JSONResult addUser(@RequestBody UserAddRequest request){
        User user = userService.add(request.getName());
        System.out.println(JSONResult.isOk());
        return JSONResult.isOk().msg("添加成功！").data(userInfoService.selfInfo(user));
    }

    @PostMapping("delete")
    public JSONResult deleteUser(@RequestBody UserDeleteRequest request){
        if(userService.delete(request.getId())){
            return JSONResult.isOk().msg("删除成功！");
        }
        return JSONResult.isFail(ErrorStatusAndMessage.Something_not_know);
    }
}