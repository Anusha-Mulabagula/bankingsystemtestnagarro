package com.nagarro.spring.form.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private DBConnect() {

	}

	public static Connection getConn() {
		Connection con = null;
		String loadDriver = "org.postgresql.Driver";
		String dbURL = "jdbc:postgresql://localhost:5432/postgres";
		String dbUSERNAME = "postgres";
		String dbPASSWORD = "connecttome";
		try {
			Class.forName(loadDriver);
			con = DriverManager.getConnection(dbURL, dbUSERNAME, dbPASSWORD);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return con;
	}
}