package com.zju.service;

import com.zju.common.ServiceException;
import com.zju.dao.UserDao;
import com.zju.entity.User;
import com.zju.util.ConditionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelfServiceImpl implements SelfService {

    @Autowired
    UserDao userDao;

    @Override
    public User login(String name, String passwordMd5) throws ServiceException {
        User user = userDao.findByName(name);
        if (ConditionUtil.isNotNull(user)){
            if (!user.getPassword().equals(passwordMd5)) {
                throw new ServiceException("密码错误");
            }else return user ;
            }
        throw new ServiceException("账号不存在");
    }

    @Override
    public boolean changePassword(Long id, String oldPassword, String newPassword) throws ServiceException {
        User user = userDao.findByIdAndPassword(id,oldPassword);
        if(ConditionUtil.isNotNull(user)){
            if(!user.getPassword().equals(oldPassword)){
                throw new ServiceException("密码错误");
            }
            else {
                user.setPassword(newPassword);
                userDao.save(user);
                return true;}
        }
        throw new ServiceException("未知错误");
    }
}
