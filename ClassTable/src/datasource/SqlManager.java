package datasource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlManager {

	public static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/swe400-21";
	public static final String LOGIN_NAME = "swe400_2";
	public static final String PASSWORD = "pwd4swe400_2F16";
	protected static Connection m_dbConn = null;
	DatabaseMetaData meta = null;

	public static void main(String[] args) throws SQLException {
		SqlManager t = new SqlManager();
		t.activateJDBC();
		t.createConnection();
		t.createDatabaseMetaData();
	}

	// For a more in-depth tutorial see:
	// https://www.tutorialspoint.com/jdbc/index.htm

	/**
	 * @author Dr. Girard This is the recommended way to activate the JDBC drivers,
	 *         but is only setup to work with one specific driver. Setup to work
	 *         with a MySQL JDBC driver.
	 *
	 *         If the JDBC Jar file is not in your build path this will not work. I
	 *         have the Jar file posted in D2L.
	 * 
	 * @return Returns true if it successfully sets up the driver.
	 */
	public boolean activateJDBC() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return true;
	}

	/**
	 * @author Dr. Girard Creates a connection to the database that you can then
	 *         send commands to.
	 */
	public Connection createConnection() {
		try {
			m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m_dbConn;
	}

	/**
	 * @author Dr. Girard To get the meta data for the DB.
	 */
	public void createDatabaseMetaData() {
		try {
			meta = m_dbConn.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}