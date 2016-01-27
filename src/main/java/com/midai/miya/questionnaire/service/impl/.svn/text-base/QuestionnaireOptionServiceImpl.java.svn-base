package com.midai.miya.questionnaire.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.midai.miya.questionnaire.dao.QuestionnaireOptionDao;
import com.midai.miya.questionnaire.service.QuestionnaireOptionService;
import com.midai.miya.questionnaire.model.QuestionnaireOption;
import com.midai.miya.utils.PageUtil;

@Service
public class QuestionnaireOptionServiceImpl implements QuestionnaireOptionService {

     @Autowired
     private QuestionnaireOptionDao questionnaireOptionDao;

     @Override
     public List<QuestionnaireOption> findAll(QuestionnaireOption QuestionnaireOption,PageUtil page) {
        List<QuestionnaireOption> lists=questionnaireOptionDao.findAll(QuestionnaireOption,page);
        return lists;
     }

     @Override
     public long findAllCount(QuestionnaireOption QuestionnaireOption) {
        long count=questionnaireOptionDao.findAllCount(QuestionnaireOption);
        return count;
     }

     @Override
     public void save(QuestionnaireOption QuestionnaireOption) {
        questionnaireOptionDao.save(QuestionnaireOption);
     }

     @Override
     public void update(QuestionnaireOption QuestionnaireOption) {
        questionnaireOptionDao.update(QuestionnaireOption);
     }

     @Override
     public void delete(QuestionnaireOption QuestionnaireOption) {
        questionnaireOptionDao.delete(QuestionnaireOption);
     }
}

