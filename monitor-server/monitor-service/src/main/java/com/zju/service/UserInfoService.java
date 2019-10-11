package com.zju.service;

import com.zju.common.ServiceException;
import com.zju.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;


public interface UserInfoService {
    /**
     * 把用户信息转化为JSONObject，会涉及用户的可传递的信息
     * @param user
     * @return
     * @throws ServiceException
     */
    JSONObject selfInfo(User user) throws ServiceException;
    JSONArray otherInfo(List<User> userList) throws ServiceException;

}
