package com.midai.miya.copywriter.service;

import com.midai.miya.copywriter.model.Copywriter;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface CopywriterService {

     List<Copywriter> findByConditions(Copywriter copywriter,PageUtil page);

     long findByConditionsCount(Copywriter copywriter);

     void save(Copywriter copywriter);

     void update(Copywriter copywriter);

     void delete(Copywriter copywriter);

     long findByName(Copywriter copywriter) ;
     
     Copywriter findById(String copywriterId);
}