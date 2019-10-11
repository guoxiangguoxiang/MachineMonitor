package com.zju.service;

import com.zju.common.ServiceException;
import com.zju.dao.UserDao;
import com.zju.entity.User;
import com.zju.util.ConditionUtil;
import com.zju.util.DateUtil;
import com.zju.util.JSONUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public JSONArray list(){
        List<User> userList = userDao.findAll();
        return JSONUtil.fromList(userList,"*");
    }

    @Override
    public User add(String name) throws ServiceException {
        if(name.isEmpty()){
            throw new ServiceException("用户名不能为空");
        }
        if (ConditionUtil.isNotNull(userDao.findByName(name))){
            throw new ServiceException("用户已存在");
        }
        User user = new User();
        user.setName(name);
        user.setPassword("123456");
        userDao.save(user);
        return user;
    }

    @Override
    public Boolean delete(Long id) throws ServiceException {
        if (!ConditionUtil.isNotNull(userDao.findById(id))) {
            throw new  ServiceException("未找到用户");
        }
        userDao.delete(userDao.findById(id));
        return true;
    }


}