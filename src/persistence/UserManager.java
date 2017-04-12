package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CCException;
import object.user;
import object.User;

public class UserManager 
{
	public static void store(User user)
	{	
		int rowsModified;
		String query = "INSERT INTO user (username, password, first_name, last_name, email) VALUES"
				+" (?,?,?,?,?)";
		
		Connection con = DbAccessImpl.connect();
		
		try 
		{
			PreparedStatement ps = con.prepareStatement(query);
			
			// set the PreparedStatement parameters to values given from User or to sql null values if nullable	
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
			if(user.getFirstName() != null) ps.setString(3, user.getFirstName());
			else ps.setNull(3, java.sql.Types.VARCHAR);
		
			if(user.getLastName() != null) ps.setString(4, user.getLastName());
			else ps.setNull(4, java.sql.Types.VARCHAR);
			
			if(user.getEmail() != null) ps.setString(5, user.getEmail());
			else ps.setNull(5, java.sql.Types.VARCHAR);
			
			//execute the query
			rowsModified = DbAccessImpl.update(con, ps);
			
			// set the id value assigned by the database to the user object
			if( rowsModified >= 1 ) {
			    String sql = "select last_insert_id()";
			    if( ps.execute( sql ) ) { // statement returned a result
				
				   // retrieve the result
				   ResultSet r = ps.getResultSet();
				
				   while( r.next() ) {
				   // retrieve the last insert auto_increment value
				   int userId = r.getInt( 1 );
				   if( userId > 0 )
				   user.setId( userId ); // set the user Id for this object 
				   }
		     	 }
		     }else { 
		    	 // throw new CompareCabinsException( "UserManager.store: failed to save a User to the database" );
			 }
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		DbAccessImpl.disconnect(con);
	} //end of store
	
	//TODO
	public static void update(User user, String column, String newValue)
	{	
		int rowsModified = 0;
		
		Connection con = DbAccessImpl.connect();
		
		DbAccessImpl.disconnect(con);
		
	}
	
	public static void delete(User user) throws CCException
	{
		String query = "DELETE FROM user WHERE id = ?";
		PreparedStatement ps;
		Connection con = DbAccessImpl.connect();
		int rowsModified;
		
		if(user.getId() < 0) //object no in database
			return;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,  user.getId());
			rowsModified = ps.executeUpdate();
			
			if(rowsModified != 1)
				throw new CCException("UserManager.delete: failed to delete user");
		}catch(SQLException e) {
			throw new CCException("UserManager.delte: failed to delete user: " + e);
		}
		
		DbAccessImpl.disconnect(con);
		
	} //end of delete
	
	//TODO
	public static void restore(User user)
	{	
		int rowsModified = 0;
		
		Connection con = DbAccessImpl.connect();
		
		DbAccessImpl.disconnect(con);
	}
	
	//SIGN-UP & LOGIN METHODS -------------------------
	
	public boolean signup(User user)
	{
		//check to see if username is taken
		
		boolean usernameFree = true;
		String query = "SELECT username FROM user WHERE username = ?";
		Connection con = DbAccessImpl.connect();
		
		try {
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, user.getUsername());
			ResultSet rs = DbAccessImpl.retrieve(con, preparedStmt);
			
			if (rs.next()) usernameFree = false;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		DbAccessImpl.disconnect(con);
		
		//if not, insert user into database
		
		if(usernameFree) store(user);
		
		return usernameFree;
	}
	
	public boolean login(User user)
	{
		boolean validUser = false;
		String query = "SELECT username, password FROM user WHERE username = ? AND password = ?";
		Connection con = DbAccessImpl.connect();
		
		try {
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, user.getUsername());
				preparedStmt.setString(2, user.getPassword());
				ResultSet rs = DbAccessImpl.retrieve(con, preparedStmt);
				
				//checks to see if user with matching username + password exists
				
				if (rs.next()) validUser = true;
			
			} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DbAccessImpl.disconnect(con);
		
		return validUser;
	}
}
