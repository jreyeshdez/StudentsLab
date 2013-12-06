package db_manager;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	private static final String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String JDBC_CONN_STRING = "jdbc:mysql://localhost/practica_rst";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWD = "root";
	protected Connection con = null;

	protected DBConnection() {

	}

	public Connection getConnection() {
		try {
			if (con != null && !con.isClosed())
				return con;
			else {
                Class.forName(JDBC_DRIVER_CLASS);
        		con = DriverManager.getConnection(
                        JDBC_CONN_STRING,
                        JDBC_USER,
						JDBC_PASSWD);
				return con;
			}
		} catch (Exception e) {
			System.out.println("Connection error: " + e);
			con = null;
			return null;
		}
	}
}
