package com.midai.miya.sys.controller;

import java.io.IOException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.NestedIOException;

public class QmySessionFactoryBean extends SqlSessionFactoryBean{

	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		try{
			return super.buildSqlSessionFactory();
		}catch(NestedIOException e){
			e.printStackTrace();
		}
		return  super.buildSqlSessionFactory();
	}

}
