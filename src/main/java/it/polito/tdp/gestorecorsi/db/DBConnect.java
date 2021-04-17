package it.polito.tdp.gestorecorsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect
{
	public static Connection getConnection() throws SQLException
	{
		String jdbcURL = "jdbc:mariadb://127.0.0.1/iscritticorsi?user=root&password=root";
		return DriverManager.getConnection(jdbcURL);
	}

}
