package com.nagarro.spring.form.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AccountDetails {

	private static final long serialVersionUID = 1L;
	private Integer accountID;
	private String startDate;
	private String endDate;
	private String startAmount;
	private String endAmount;
	private String statementDate;
	private String statementAmount;
	private String accountType;
	private String accountNumber;

	private List<AccountDetails> statementList = new ArrayList<AccountDetails>();

	public List<AccountDetails> getStatementList() {
		return statementList;
	}

	public void setStatementList(List<AccountDetails> statementList) {
		this.statementList = statementList;
	}

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(String startAmount) {
		this.startAmount = startAmount;
	}

	public String getEndAmount() {
		return endAmount;
	}

	public void setEndAmount(String endAmount) {
		this.endAmount = endAmount;
	}

	public String getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}

	public String getStatementAmount() {
		return statementAmount;
	}

	public void setStatementAmount(String statementAmount) {
		this.statementAmount = statementAmount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = mask(accountNumber);
	}

	public String mask(String str) {
		char[] ch = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			ch[i] = str.charAt(i);

		}
		for (int i = 0; i < str.length(); i++) {
			if (i > 3)
				ch[i] = 'X';

		}
		return String.valueOf(ch);
	}

}
