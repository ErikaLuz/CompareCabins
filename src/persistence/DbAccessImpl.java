package persistence;

import persistence.DbAccessConfiguration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessImpl extends DbAccessConfiguration{
	
	public static Connection connect() 
	{
		Connection con = null;
		try {
			Class.forName(DRIVE_NAME);
			con = DriverManager.getConnection(CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	} // end of connect
	
	public static ResultSet retrieve (Connection con, PreparedStatement ps) 
	{
		ResultSet rset = null;
		try {
			rset = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rset;
	}// end of retrieve

	public static int update(Connection con, PreparedStatement ps )
	{
		int rowsModified = 0;
		try{
			rowsModified = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsModified;
	}
	public static void disconnect(Connection con)
	{
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
                       
}
