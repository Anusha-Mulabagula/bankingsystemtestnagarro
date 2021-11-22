package com.nagarro.spring.form.validator;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nagarro.spring.form.dao.LoginDAO;
import com.nagarro.spring.form.model.LoginBean;

public class LoginPageValidator implements Validator {

	@Autowired
	@Qualifier("loginDao")
	private LoginDAO loginDao;

	// which objects can be validated by this validator
	@Override
	public boolean supports(Class<?> paramClass) {
		return LoginBean.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");

		try {
			if (!loginDao.validate((LoginBean) obj)) {
				ValidationUtils.rejectIfEmpty(errors, "authFailure", "auth.failure");
				return;
			}
			if (loginDao.checkLoginStatus((LoginBean) obj)) {
				ValidationUtils.rejectIfEmpty(errors, "authFailure", "login.failure");
				return;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
