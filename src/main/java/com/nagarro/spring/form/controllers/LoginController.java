package com.nagarro.spring.form.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

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

import com.nagarro.spring.form.dao.LoginDAO;
import com.nagarro.spring.form.model.LoginBean;
import com.nagarro.spring.form.validator.Constants;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	@Qualifier("userValidator")
	private Validator validator;

	@Autowired
	@Qualifier("loginDao")
	private LoginDAO loginDAO;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@ModelAttribute("login")
	public LoginBean createLoginModel() {
		// ModelAttribute value should be same as used in the empSave.jsp
		return new LoginBean();
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		logger.info("Returning login.jsp page");
		model.addAttribute(Constants.LOGIN, new LoginBean());
		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String userLoginAction(@ModelAttribute("login") @Validated LoginBean loginBean, BindingResult bindingResult,
			Model model, HttpServletRequest request) throws SQLException {
		if (bindingResult.hasErrors()) {
			logger.info("Returning login.jsp page");
			return Constants.LOGIN;
		}
		request.getSession().setAttribute(Constants.USERNAME, loginBean.getName());
		loginDAO.setLoginStatus(request.getSession().getAttribute(Constants.USERNAME).toString(), Boolean.TRUE);
		return "redirect:home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String getLogOut(Model model, HttpServletRequest request) throws SQLException {
		logger.info("Returning logout.jsp page");
		model.addAttribute("login", new LoginBean());
		loginDAO.setLoginStatus(request.getSession().getAttribute(Constants.USERNAME).toString(), Boolean.FALSE);
		return "logout";
	}

}
