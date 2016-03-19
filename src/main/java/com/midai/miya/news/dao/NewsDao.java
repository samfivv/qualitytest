package com.midai.miya.news.dao;

import com.midai.miya.news.model.News;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.midai.miya.utils.PageUtil;

public interface NewsDao {

     List<News> findByConditions(@Param("news")News news,@Param("page")PageUtil page);

     long findByConditionsCount(@Param("news")News news);

     void save(@Param("news")News news);

     void update(@Param("news")News news);

     void delete(@Param("news")News news);

}