package com.nagarro.spring.form.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.spring.form.model.AccountDetails;

public class AccountDAO {

	private static final Logger logger = LoggerFactory.getLogger(AccountDAO.class);

	public boolean validate(AccountDetails accountDetails) throws SQLException {
		boolean status = false;
		PreparedStatement preparedStatement = null ;
		Connection connection = DBConnect.getConn();
		try {
			// Step 2:Create a statement using connection object
			 preparedStatement = connection
					.prepareStatement("select * from statement where account_id = ?  ");
			preparedStatement.setInt(1, accountDetails.getAccountID());
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			printSQLException(e);
		}
		finally {
			 preparedStatement.close();
		}
		return status;
	}

	public List<AccountDetails> fetchStatementDetails(AccountDetails accountDetails) throws SQLException  {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		List<String> dates = new ArrayList<String>();
		List<String> amounts = new ArrayList<String>();
		List<String> userdatastatement = new ArrayList<String>();
		Connection connection = DBConnect.getConn();
		PreparedStatement preparedStatementToStatementTable = null;

		try {

			// Step 2:Create a statement using connection object

			preparedStatementToStatementTable = connection.prepareStatement("select * from account where id = ?  ");
			preparedStatementToStatementTable.setInt(1, accountDetails.getAccountID());
			ResultSet rsofstatement = preparedStatementToStatementTable.executeQuery();
			while (rsofstatement.next()) {
				accountDetails.setAccountType(rsofstatement.getString("account_type"));
				accountDetails.setAccountNumber(rsofstatement.getString("account_number"));

			}
			preparedStatementToStatementTable.close();
			preparedStatementToStatementTable = connection
					.prepareStatement("select * from statement where account_id = ?  ");
			preparedStatementToStatementTable.setInt(1, accountDetails.getAccountID());
			rsofstatement = preparedStatementToStatementTable.executeQuery();

			while (rsofstatement.next()) {
				AccountDetails accountDetailsResult = new AccountDetails();
				accountDetailsResult.setAccountID(rsofstatement.getInt("account_id"));
				accountDetailsResult.setStatementDate(rsofstatement.getString("datefield"));
				accountDetailsResult.setStatementAmount(rsofstatement.getString("amount"));
				accountDetails.getStatementList().add(accountDetailsResult);

				dates.add(accountDetailsResult.getStatementDate());
				amounts.add(accountDetailsResult.getStatementAmount());
			}

			if (accountDetails.getStartDate().isEmpty() && accountDetails.getStartAmount().isEmpty()) {

				Calendar currentDate = Calendar.getInstance();
				currentDate.add(Calendar.MONTH, -3);

				String startDate = formatter.format(currentDate.getTime());
				String endDate = formatter.format(Calendar.getInstance().getTime());
				getDateRangeDetails(accountDetails, formatter, dates, userdatastatement, startDate, endDate);
				//
				return accountDetails.getStatementList();
			}
			if (!accountDetails.getStartDate().isEmpty() && !accountDetails.getEndDate().isEmpty()) {
				getDateRangeDetails(accountDetails, formatter, dates, userdatastatement, null, null);
			}

			if (!accountDetails.getStatementList().isEmpty() && null != accountDetails.getStartAmount()
					&& null != accountDetails.getEndAmount() && !accountDetails.getStartAmount().isEmpty()
					&& !accountDetails.getEndAmount().isEmpty()) {

				Double startAmount = Double.valueOf(accountDetails.getStartAmount());
				Double endAmount = Double.valueOf(accountDetails.getEndAmount());
				ListIterator<AccountDetails> lstAmountIterator = accountDetails.getStatementList().listIterator();

				while (lstAmountIterator.hasNext()) {
					Double doubleAmount = Double.valueOf(lstAmountIterator.next().getStatementAmount());
					if (checkAmountRange(startAmount, endAmount, doubleAmount)) {
						lstAmountIterator.remove();
					}

				}

			}
		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			preparedStatementToStatementTable.close();
		}
		return accountDetails.getStatementList();

	}

	private boolean checkAmountRange(Double startAmount, Double endAmount, Double doubleAmount) {
		return !((Double.compare(doubleAmount, startAmount) == 0
				|| Double.compare(doubleAmount, startAmount) > 0)
				&& (Double.compare(doubleAmount, endAmount) < 0
						|| Double.compare(doubleAmount, endAmount) == 0));
	}

	private List<Date> calculateRangeOfDates(String startDate, String endDate, SimpleDateFormat formatter) {
		Date start = new Date();
		Date end = new Date();
		try {
			start = formatter.parse(startDate);
			end = formatter.parse(endDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);

		while (calendar.getTime().before(end) || calendar.getTime().equals(end) || calendar.getTime().equals(start)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}

	private void getDateRangeDetails(AccountDetails accountDetails, SimpleDateFormat formatter, List<String> dates,
			List<String> userdatastatement, String startDatefor3months, String endDateFor3months)  {
		List<Date> userDates = null;
		if (accountDetails.getStartDate().isEmpty() && accountDetails.getEndDate().isEmpty()) {
			userDates = calculateRangeOfDates(startDatefor3months, endDateFor3months, formatter);
		} else {
			userDates = calculateRangeOfDates(accountDetails.getStartDate(), accountDetails.getEndDate(), formatter);
		}
		for (Date date : userDates) {
			String dateStr = formatter.format(date);
			userdatastatement.add(dateStr);
		}

		dates.retainAll(userdatastatement);
		ListIterator<AccountDetails> lstIterator = accountDetails.getStatementList().listIterator();
		while (lstIterator.hasNext()) {
			if (!dates.contains(lstIterator.next().getStatementDate())) {
				lstIterator.remove();
			}
		}
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				logger.error("SQLState '{}' ",((SQLException) e).getSQLState());
				logger.error("Error Code: '{}' ",((SQLException) e).getErrorCode());
				logger.error("Message: '{}' ",e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					logger.error("Cause: " , t);
					t = t.getCause();
				}
			}
		}
	}
}
