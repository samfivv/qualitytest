package com.midai.miya.news.service;

import com.midai.miya.news.model.News;
import java.util.List;
import com.midai.miya.utils.PageUtil;

public interface NewsService {

     List<News> findByConditions(News news,PageUtil page);

     long findByConditionsCount(News news);

     void save(News news);

     void update(News news);

     void delete(News news);

}