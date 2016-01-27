package com.midai.miya.sys.dao;

import com.midai.miya.sys.model.BottomPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface BottomPageDao {

     List<BottomPage> findByConditions(@Param("bottomPage")BottomPage bottomPage,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("bottomPage")BottomPage bottomPage);

     void save(@Param("bottomPage")BottomPage bottomPage);

     void update(@Param("bottomPage")BottomPage bottomPage);

     void delete(@Param("bottomPage")BottomPage bottomPage);

   	 BottomPage findBottomPageById(@Param("bottomPageId")String bottomPageId);

   	 int findBottomPageCountByName(@Param("bottomPage")BottomPage bottomPage);
}