package com.midai.miya.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.news.dao.NewsDao;
import com.midai.miya.news.service.NewsService;
import com.midai.miya.news.model.News;
import com.midai.miya.utils.PageUtil;

@Service
public class NewsServiceImpl implements NewsService {

     @Autowired
     private NewsDao newsDao;

     @Override
     public List<News> findByConditions(News news,PageUtil page) {
        List<News> lists=newsDao.findByConditions(news,page);
        return lists;
     }

     @Override
     public long findByConditionsCount(News news) {
        long count=newsDao.findByConditionsCount(news);
        return count;
     }

     @Override
     public void save(News news) {
        newsDao.save(news);
     }

     @Override
     public void update(News news) {
        newsDao.update(news);
     }

     @Override
     public void delete(News news) {
        newsDao.delete(news);
     }
}

