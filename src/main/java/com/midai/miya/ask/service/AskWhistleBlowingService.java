package com.midai.miya.ask.service;

import com.midai.miya.ask.model.AskWhistleBlowing;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface AskWhistleBlowingService {

     List<AskWhistleBlowing> findByConditions(AskWhistleBlowing askWhistleBlowing,PageUtil page);

     long findByConditionsCount(AskWhistleBlowing askWhistleBlowing);

     void save(AskWhistleBlowing askWhistleBlowing);

     void update(AskWhistleBlowing askWhistleBlowing);

     void delete(AskWhistleBlowing askWhistleBlowing);


   	AskWhistleBlowing findAskWhistleBlowingById(String whistleBlowing);

   	List<AskWhistleBlowing> findReplyByConditions(AskWhistleBlowing askWhistleBlowing,PageUtil page);

   	AskWhistleBlowing findReplyById(String whistleBlowing);
}