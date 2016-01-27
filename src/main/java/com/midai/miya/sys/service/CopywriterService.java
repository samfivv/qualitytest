package com.midai.miya.sys.service;

import com.midai.miya.sys.model.Copywriter;

import java.util.List;

import com.midai.miya.utils.PageUtil;

public interface CopywriterService {

     List<Copywriter> findByConditions(Copywriter copywriter,PageUtil page);

     long findByConditionsCount(Copywriter copywriter);

     void save(Copywriter copywriter);

     void update(Copywriter copywriter);

     void delete(Copywriter copywriter);
     
     Copywriter findById(String copywriterId);
     
     long findByName(Copywriter copywriter);
     
     long findByVersion(String copywriterVersion);
}