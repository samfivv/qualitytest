package com.midai.miya.sys.service;

import com.midai.miya.sys.model.UserApplication;

import java.util.List;

import com.midai.miya.user.model.User;
import com.midai.miya.utils.PageUtil;

public interface UserApplicationService {

     List<UserApplication> findByConditions(UserApplication userApplication,PageUtil page);

     long findByConditionsCount(UserApplication userApplication);

     void save(UserApplication userApplication);

     void update(UserApplication userApplication);

     void delete(UserApplication userApplication);
     
     UserApplication findById(String applicationId);
     
}