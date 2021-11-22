package com.nagarro.spring.form.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.spring.form.model.LoginBean;

public class LoginDAO {

	private static final Logger logger = LoggerFactory.getLogger(LoginDAO.class);

	public boolean validate(LoginBean login) throws SQLException {
		PreparedStatement preparedStatement = null;
		boolean status = false;
		Connection connection = DBConnect.getConn();
		try {
			// Step 2:Create a statement using connection object
			preparedStatement = connection.prepareStatement("select * from BANKUSERS where name = ? and password = ? ");
			preparedStatement.setString(1, login.getName());
			preparedStatement.setString(2, login.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {

			printSQLException(e);
		} finally {
			preparedStatement.close();
		}
		return status;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace();
				logger.error("SQLState:'{} ", ((SQLException) e).getSQLState());
				logger.error("Error Code: '{} ", ((SQLException) e).getErrorCode());
				Throwable t = ex.getCause();
				while (t != null) {
					t = t.getCause();
				}
			}
		}
	}

	public void setLoginStatus(String username, Boolean status) throws SQLException {

		PreparedStatement preparedStatement = null;
		Connection connection = DBConnect.getConn();
		try {
			preparedStatement = connection.prepareStatement("update BANKUSERS set loginstatus = ? where name = ? ");
			preparedStatement.setBoolean(1, status);
			preparedStatement.setString(2, username);

			preparedStatement.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			preparedStatement.close();
		}

	}

	public boolean checkLoginStatus(LoginBean obj) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection connection = DBConnect.getConn();
		try {
			preparedStatement = connection.prepareStatement("select loginstatus from BANKUSERS where name = ? ");
			preparedStatement.setString(1, obj.getName());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return rs.getBoolean("loginstatus");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			preparedStatement.close();
		}

		return false;
	}
}
