package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import exception.CCException;
import object.Availability;
import object.Cabin;
import object.RentRecord;
import object.User;

public class UserManager 
{
	public static void store(User user) throws CCException
	{	
		String insertSQL 	= "INSERT INTO user (username, password, first_name, last_name, email) VALUES"
							+" (?,?,?,?,?)";
		String updateSQL	= "UPDATE user SET username = ?, password = ?, first_name = ?, last_name = ?, email = ? WHERE id = ?";
		Connection con = DbAccessImpl.connect();
		PreparedStatement ps;
		int rowsModified;
		
		try //prepare and execute the sql query
		{
			// test is object is already in database to determine insert or update
			if(user.getId() >= 0)
				ps = con.prepareStatement(updateSQL);
			else
				ps = con.prepareStatement(insertSQL);
			
			// set the PreparedStatement parameters to values given from User or to sql null values if nullable	
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
			if(user.getFirstName() != null) ps.setString(3, user.getFirstName());
			else ps.setNull(3, java.sql.Types.VARCHAR);
		
			if(user.getLastName() != null) ps.setString(4, user.getLastName());
			else ps.setNull(4, java.sql.Types.VARCHAR);
			
			if(user.getEmail() != null) ps.setString(5, user.getEmail());
			else ps.setNull(5, java.sql.Types.VARCHAR);
			
			// set id if query is an update
			if( user.getId() >= 0 )
				ps.setInt( 10, user.getId() );
			
			//execute the query
			rowsModified = DbAccessImpl.update(con, ps);
			
			// set the id value assigned by the database to the user object
			if( rowsModified >= 1) 
			{
				if(user.getId() < 0)
				{
				    String sql = "select last_insert_id()";
				    if( ps.execute( sql ) ) // statement returned a result
				    { 
					    // retrieve the result
					    ResultSet r = ps.getResultSet();
					 
					    while( r.next() )
					    {
						    // retrieve the last insert auto_increment value
						    int userId = r.getInt( 1 );
						    if( userId > 0 )
							   user.setId( userId ); // set the user Id for this object 
					    }
			     	 }
				}
		     }else { 
		    	 throw new CCException("UserManager.store: failed to save a user");
			 }
		}
		catch (SQLException e) {
			throw new CCException("UserManager.store: failed to save a cabin: " + e );
		}
		
		DbAccessImpl.disconnect(con);
	} //end of store
	
	public static List<User> restore(User modelUser) throws CCException
	{
		String  selectUserSql = "select id, username, password, first_name, last_name, email from user"; 
		Statement    statement = null;
		StringBuffer query = new StringBuffer( 250 );
		StringBuffer condition = new StringBuffer( 250 );
		List<User> users = new LinkedList<User>();
		Connection con = DbAccessImpl.connect();

		condition.setLength( 0 );
		query.append( selectUserSql );
		
		// append where clauses to query for each specified field of the modelUser
		if( modelUser != null ) 
		{
			if( modelUser.getId() >= 0 ) // id is unique, so it is sufficient to get a User
				query.append( " where id = " + modelUser.getId() );
			else {	// no id is given		
				if( modelUser.getUsername() != null )
					condition.append( " username = '" + modelUser.getUsername() + "'");
				if( modelUser.getPassword() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" password = '" + modelUser.getPassword() + "'");
				}
				if( modelUser.getFirstName() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" first_name = '" + modelUser.getFirstName() + "'");
				}
				if( modelUser.getLastName() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" last_name = '" + modelUser.getLastName() + "'");
				}
				if( modelUser.getEmail() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" email = " + modelUser.getEmail() );
				}
			}
		} //end of if
		
		try {			
			statement = con.createStatement();
			
			if( statement.execute( query.toString() ) ) { // statement returned a result
				
				// fields of a user object
			    int 	id;
			    String	username;
			    String	password;
				String	firstName;
				String	lastName;
				String	email;
				
				// retrieve the ResultSet generated by the execution of the query
				ResultSet rs = statement.getResultSet();

				while( rs.next() ) { // process the result set

					// retrieve the values from an entry in the result set
					id = rs.getInt(1);
					username = rs.getString(2);
					password = rs.getString(3);
					firstName = rs.getString(4);
					lastName = rs.getString(5);
					email = rs.getString(6);

					// create a proxy object
					User user = new User( username, password, firstName, lastName, email);
					user.setId( id );
					
					users.add( user );
				}
				
				return users;
				
			} else {
				return null;
			}
		}
		catch( SQLException e ) {      
			throw new CCException("UserManager.restore: Could not restore persistent User objects: " + e );
		}		

	} //end of restore
	
	public static List<Cabin> restoreCabinsFromUser( User user ) throws CCException
	{
		String sqlQuery = "SELECT c.id, c.address, c.city, c.state, c.description, c.bedroom_count, c.bath_count, c.max_occupancy FROM cabin c"
						+ "	WHERE user_id = ?";
		
		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		List<Cabin> cabins = new LinkedList<Cabin>();
		
		try {
			// prepare and execute the query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt( 1, user.getId() );
			rs = ps.executeQuery();
			
			while( rs.next() ) { // there is a next entry in the result set
				
				// retrieve the values from the result set
				int id = rs.getInt(1);
				String address = rs.getString(2);
				String city = rs.getString(3);
				String state = rs.getString(4);
				String description = rs.getString(5);
				int bedroomCount = rs.getInt(6);
				float bathCount = rs.getFloat(7);
				int maxOccupancy = rs.getInt(8);
				
				// create the proxy object
				Cabin cabin = new Cabin( address, city, state, description, bedroomCount, bathCount, maxOccupancy );
				cabin.setId(id);
				cabin.setUser(null);
				cabin.setAmenities(null);
				
				cabins.add( cabin );
			}
			
			return cabins;
			
		} catch( SQLException e ) {
			throw new CCException("UserManager.restoreCabinsFromUser: could not restore persistent Cabin objects: " + e );
		}
		
	}
	
	public static List<RentRecord> restoreRentRecordsFromUser( User user ) throws CCException
	{
		String sqlQuery = "SELECT id, total_price, start_date, end_date FROM rent_record"
						+ "	WHERE user_id = ?";
		
		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		List<RentRecord> rentRecords = new LinkedList<RentRecord>();
		
		try {
			// prepare and execute the query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt( 1, user.getId() );
			rs = ps.executeQuery();
			
			while( rs.next() ) { // there is a next entry in the result set
				
				// retrieve the values from the result set
				int id = rs.getInt(1);
				float totalPrice = rs.getFloat(2);
				java.sql.Date startDate = rs.getDate(3);
				java.sql.Date endDate = rs.getDate(4);
				
				// convert the sql Dates to Calendar objects
				Calendar startCal = Calendar.getInstance();
				Calendar endCal = Calendar.getInstance();
				startCal.setTime( startDate );
				endCal.setTime( endDate );
				
				// create the proxy object
				RentRecord rentRecord = new RentRecord( totalPrice, startCal, endCal );
				rentRecord.setId(id);
				rentRecord.setCabin( null );
				rentRecord.setUser( null );
				rentRecords.add( rentRecord );
			}
			
			return rentRecords;
			
		} catch( SQLException e ) {
			throw new CCException("UserManager.restoreRentRecordsFromUser: could not restore persistent RentRecord objects: " + e );
		}
		
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
	
	//SIGN-UP & LOGIN METHODS -------------------------
	
	public boolean signup(User user) throws CCException
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
