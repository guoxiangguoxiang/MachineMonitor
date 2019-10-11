package com.zju.service;

import com.zju.common.ServiceException;
import com.zju.entity.User;
import net.sf.json.JSONArray;

public interface UserService {

     JSONArray list() ;
     User add(String name) throws ServiceException;
     Boolean delete(Long id) throws ServiceException;
}
