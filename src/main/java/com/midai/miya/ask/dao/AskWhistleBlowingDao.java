package com.midai.miya.ask.dao;

import com.midai.miya.ask.model.AskWhistleBlowing;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface AskWhistleBlowingDao {

     List<AskWhistleBlowing> findByConditions(@Param("askWhistleBlowing")AskWhistleBlowing askWhistleBlowing,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("askWhistleBlowing")AskWhistleBlowing askWhistleBlowing);

     void save(@Param("askWhistleBlowing")AskWhistleBlowing askWhistleBlowing);

     void update(@Param("askWhistleBlowing")AskWhistleBlowing askWhistleBlowing);

     void delete(@Param("askWhistleBlowing")AskWhistleBlowing askWhistleBlowing);


   	 AskWhistleBlowing findAskWhistleBlowingById(@Param("whistleBlowing")String whistleBlowing);

   	 List<AskWhistleBlowing> findReplyByConditions(@Param("askWhistleBlowing")AskWhistleBlowing askWhistleBlowing,@Param("page")PageUtil page);

   	 AskWhistleBlowing findReplyById(String whistleBlowing);
}