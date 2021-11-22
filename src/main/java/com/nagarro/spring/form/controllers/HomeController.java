package com.nagarro.spring.form.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagarro.spring.form.dao.AccountDAO;
import com.nagarro.spring.form.model.AccountDetails;
import com.nagarro.spring.form.validator.Constants;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	@Qualifier("homeValidator")
	private Validator validator;

	@Autowired
	@Qualifier("accountDao")
	private AccountDAO accountDao;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@ModelAttribute("accountDetails")
	public AccountDetails createAccountDetailsModel() {
		return new AccountDetails();
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getAccountDetailsPage(@ModelAttribute("accountDetails") AccountDetails model) {
		logger.info("Returning home.jsp page");
		return "home";
	}

	@RequestMapping(value = "/home.do", method = RequestMethod.POST)
	public String userLoginAction(@ModelAttribute("accountDetails") @Validated AccountDetails accountDetails,
			BindingResult bindingResult, Model model, HttpServletRequest request) throws HTTPException, SQLException {
		if (bindingResult.hasErrors()) {
			logger.info("Returning login.jsp page");

			return "home";
		}
		if (request.getSession().getAttribute("userName").toString().equalsIgnoreCase(Constants.USER)
				&& isUserSelectedAnyOtherParam(accountDetails)) {

			return "401";
		}
		accountDao.fetchStatementDetails(accountDetails);

		return "bankstatement";
	}

	private boolean isUserSelectedAnyOtherParam(AccountDetails accountDetails) {

		return (!accountDetails.getStartDate().isEmpty() || !accountDetails.getStartAmount().isEmpty()
				|| !accountDetails.getEndDate().isEmpty() || !accountDetails.getEndAmount().isEmpty());
	}

}
