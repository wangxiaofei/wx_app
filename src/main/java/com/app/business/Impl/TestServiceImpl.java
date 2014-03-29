package com.app.business.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.business.TestService;
import com.app.business.model.Test;
import com.app.persistance.TestDAO;
import com.app.persistance.UserDAO;


@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDAO testDao;
	@Autowired
	private UserDAO userDao;


	@Transactional( readOnly =false,rollbackFor=Exception.class)
	@Override
	public Test createTest(Test test) {
		userDao.delete(100000);
		Long id  = testDao.create(test);
		test.setId(id);
		return test;
	}

}
