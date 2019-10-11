package com.zju.service;

import com.zju.common.ServiceException;
import com.zju.entity.User;
import com.zju.util.JSONUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public JSONObject selfInfo(User user) throws ServiceException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",user.getId());
        jsonObject.put("name",user.getName());
        jsonObject.put("password",user.getPassword());
        return jsonObject;
    }


    @Override
    public JSONArray otherInfo(List<User> userList) throws ServiceException {
        return JSONUtil.fromList(userList,"*");
    }
}
