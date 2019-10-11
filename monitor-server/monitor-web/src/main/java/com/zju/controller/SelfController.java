package com.zju.controller;

import com.zju.common.ErrorStatusAndMessage;
import com.zju.common.JSONResult;
import com.zju.entity.User;
import com.zju.request.ChangePasswordRequest;
import com.zju.request.LoginRequest;
import com.zju.service.SelfService;
import com.zju.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SelfController {

    @Autowired
    SelfService selfService;

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/login")
    public JSONResult login(@RequestBody LoginRequest request) {
        User user = selfService.login(request.getName(), request.getPassword());
        return JSONResult.isOk().data(userInfoService.selfInfo(user));
    }

    @PostMapping("/changePassword")
    public JSONResult changePassword(@RequestBody ChangePasswordRequest request) {
        if (selfService.changePassword(request.getId(), request.getOldPassword(), request.getNewPassword())) {
            return JSONResult.isOk().msg("修改成功！");
        }
        return JSONResult.isFail(ErrorStatusAndMessage.Something_not_know);
    }
}
