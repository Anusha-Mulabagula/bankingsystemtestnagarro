package com.nagarro.spring.form.validator;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nagarro.spring.form.dao.AccountDAO;
import com.nagarro.spring.form.model.AccountDetails;

public class HomeValidator implements Validator {

	@Autowired
	@Qualifier("accountDao")
	private AccountDAO accountDao;

	// which objects can be validated by this validator
	@Override
	public boolean supports(Class<?> paramClass) {
		return AccountDetails.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountID", "accountid.required");
		try {
			if (null != (((AccountDetails) obj).getAccountID()) && !(accountDao.validate((AccountDetails) obj))) {

				errors.reject("input.notvalid", "accountID not correct");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
