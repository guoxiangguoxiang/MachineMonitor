package com.zju.service;

import com.zju.common.ServiceException;
import com.zju.entity.User;

public interface SelfService {

    /**
     * 登录
     * @param name
     * @param password
     * @return
     * @throws ServiceException
     */
    User login(String name, String password) throws ServiceException;

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws ServiceException
     */
    boolean changePassword(Long id, String oldPassword, String newPassword) throws ServiceException;
}
