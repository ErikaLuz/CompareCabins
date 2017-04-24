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
import object.Amenities;
import object.Cabin;
import object.RentRecord;
import object.Review;
import object.User;

public class RentRecordManager {

	public static void store(RentRecord rentRecord) throws CCException
	{
		
		String insertSQL = "INSERT INTO rent_record (total_price, start_date, end_date, cabin_id, user_id) VALUES"
				+" (?,?,?,?,?)";
		String updateSQL = "UDATE rent_record SET total_price = ?, start_date = ?, end_date = ?, rentRecord_id = ?, user_id = ? WHERE id = ?";
		Connection con = DbAccessImpl.connect();
		PreparedStatement ps;
		int rowsModified;
		
		try //prepare and execute SQL query
		{
			// test is object is already in database to determine insert or update
			if( rentRecord.getId() >= 0 )
				ps = con.prepareStatement( updateSQL );
			else
				ps = con.prepareStatement( insertSQL );
			
			// set the PreparedStatement parameters to values given from RentRecord or to sql null values if nullable	
			if(rentRecord.getTotalPrice() != -1) ps.setFloat(1, rentRecord.getTotalPrice());
			else ps.setNull(1, java.sql.Types.FLOAT);
			
			if(rentRecord.getStartDate() != null) ps.setDate(2, new java.sql.Date (rentRecord.getStartDate().getTime().getTime()));
			else ps.setNull(2, java.sql.Types.DATE);	
			
			if(rentRecord.getEndDate() != null) ps.setDate(3, new java.sql.Date (rentRecord.getEndDate().getTime().getTime()));
			else ps.setNull(3, java.sql.Types.DATE);
			
			if(rentRecord.getCabin() != null) ps.setInt(4, rentRecord.getCabin().getId());
			else ps.setNull(4, java.sql.Types.INTEGER);
			
			if(rentRecord.getUser() != null) ps.setInt(5, rentRecord.getUser().getId());
			else ps.setNull(5, java.sql.Types.INTEGER);
			
			// set id if query is an update
			if( rentRecord.getId() >= 0 )
				ps.setInt( 6, rentRecord.getId() );
			
			//execute the query
			rowsModified = DbAccessImpl.update(con, ps);
			
			// set the id value assigned by the database to the user object
			if( rowsModified >= 1 ) 
			{
				if( rentRecord.getId() < 0)
				{
				    String sql = "select last_insert_id()";
				    if( ps.execute( sql ) ) // statement returned a result
					{ 
					    // retrieve the result
					    ResultSet r = ps.getResultSet();
					
					    while( r.next() ) 
					    {
					 	   // retrieve the last insert auto_increment value
					 	   int rentRecordId = r.getInt( 1 );
						   if( rentRecordId > 0 )
							   rentRecord.setId( rentRecordId ); // set the user Id for this object 
					    }
			     	 }
				}
		     }
			 else { 
				 throw new CCException("RentRecordManager.store: failed to save a Rent Record");
			 }
		}catch(SQLException e){
			throw new CCException("RentRecordManager.store: failed to save a cabin: " + e );	
		}
	} //end of store

	public static List<RentRecord> restore( RentRecord modelRentRecord ) throws CCException
	{
		String  selectRentRecordSql = "select id, total_price, start_date, end_date from rent_record"; 
		Statement    statement = null;
		StringBuffer query = new StringBuffer( 250 );
		StringBuffer condition = new StringBuffer( 250 );
		List<RentRecord> rentRecords = new LinkedList<RentRecord>();
		Connection con = DbAccessImpl.connect();

		condition.setLength( 0 );
		query.append( selectRentRecordSql );

		// append where clauses to query for each specified field of the modelRentRecord
		if( modelRentRecord != null ) {
			if( modelRentRecord.getId() >= 0 ) // id is unique, so it is sufficient to get a Cabin
				query.append( " where id = " + modelRentRecord.getId() );
			else {	// no id is given		
				if( modelRentRecord.getTotalPrice() >= 0)
					condition.append( " total_price = '" + modelRentRecord.getTotalPrice() + "'");
				if( modelRentRecord.getStartDate() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					Calendar cal = modelRentRecord.getStartDate();
					int day = cal.get( Calendar.DAY_OF_MONTH );
					int month = cal.get( Calendar.MONTH );
					int year = cal.get( Calendar.YEAR );
					String dateString = year + "-" + month + "-" + day;
					condition.append(" start_date = '" + dateString + "'");
				}
				if( modelRentRecord.getEndDate() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					Calendar cal = modelRentRecord.getEndDate();
					int day = cal.get( Calendar.DAY_OF_MONTH );
					int month = cal.get( Calendar.MONTH );
					int year = cal.get( Calendar.YEAR );
					String dateString = year + "-" + month + "-" + day;
					condition.append(" date = '" + dateString + "'");
				}
				if( condition.length() > 0 ) {
					query.append(  " where " );
					query.append( condition );
				}
			}
		}
		
		try {			
			statement = con.createStatement();
			
			if( statement.execute( query.toString() ) ) { // statement returned a result
				
				// fields of a rent record object
				int   		id;
				float		totalPrice;
				Calendar	start = Calendar.getInstance();
				Calendar	end = Calendar.getInstance();
				
				// retrieve the ResultSet generated by the execution of the query
				ResultSet rs = statement.getResultSet();

				while( rs.next() ) { // process the result set

					// retrieve the values from an entry in the result set
					id = rs.getInt(1);
					totalPrice = rs.getFloat(2); 
					java.sql.Date start_date = rs.getDate(3);
					java.sql.Date end_date = rs.getDate(4);
					
					start.setTime(start_date);
					end.setTime(end_date);
					
					// create a proxy object
					RentRecord rentRecord = new RentRecord(totalPrice, start, end);
					rentRecord.setId( id );
					rentRecord.setCabin( null );	
					rentRecord.setUser( null );
					
					rentRecords.add( rentRecord );
				}
				
				return rentRecords;
				
			} else {
				return null;
			}
		}
		catch( SQLException e ) {      
			throw new CCException("RentRecordManager.restore: Could not restore persistent RentRecord objects: " + e );
		}		
	}//end of restore
	
	public static Cabin restoreCabinFromRentRecord( RentRecord rentRecord ) throws CCException
	{
		String sqlQuery = "SELECT cabin.id, address, city, state, description, title, bedroom_count, bath_count, maxOccupancy FROM cabin"
						+ "	JOIN rent_record ON cabin.id = rent_record.cabin_id"
						+ "	WHERE rent_record.id = ?";
		
		Connection con = DbAccessImpl.connect();
		ResultSet rs;
		Cabin cabin;
		
		try {
			// prepare and execute the query
			PreparedStatement ps = con.prepareStatement( sqlQuery );
			ps.setInt( 1, rentRecord.getId() );
			rs = ps.executeQuery();
			
			if( rs.next() ) { // there is an entry in the result set
				
				// retrieve the values from the result set
				int id = rs.getInt(1);
				String address = rs.getString(2);
				String city = rs.getString(3);
				String state = rs.getString(4);
				String description = rs.getString(5);
				String title = rs.getString(6);
				int	bedroomCount = rs.getInt(7);
				float bathCount = rs.getFloat(8);
				int	maxOccupancy = rs.getInt(9);
				
				// create the proxy object
				cabin = new Cabin( address, city, state, description, title, bedroomCount, bathCount, maxOccupancy );
				cabin.setId( id );
				cabin.setUser( null );
				cabin.setAmenities( null );
				
				return cabin;
			} else { // no matches found for the query
				return null;
			}
		} catch( SQLException e ) {
			throw new CCException("RentRecordManager.restoreCabinFromRentRecord: could not restore persistent Cabin object: " + e );
		}
	} //end of restoreCabinFromRentRecord
	
	public static User restoreUserFromRentRecord( RentRecord rentRecord ) throws CCException
	{
		String sqlQuery = "SELECT user.id, username, password, first_name, last_name, email FROM user"
						+ "	JOIN rent_record ON user.id = rent_record.user_id"
						+ "	WHERE rent_record.id = ?";
		
		Connection con = DbAccessImpl.connect();
		ResultSet rs;
		User user;
		
		try {
			// prepare and execute the query
			PreparedStatement ps = con.prepareStatement( sqlQuery );
			ps.setInt( 1, rentRecord.getId() );
			rs = ps.executeQuery();
			
			if( rs.next() ) { // there is an entry in the result set
				
				// retrieve the values from the result set
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String email = rs.getString(6);
				
				// create the proxy object
				user = new User( username, password, firstName, lastName, email );
				user.setId(id);
				
				return user;
			} else { // no matches found for the query
				return null;
			}
		} catch( SQLException e ) {
			throw new CCException("RentRecordManager.restoreUserFromRentRecord: could not restore persistent User object: " + e );
		}
	} //end of restoreUserFromRentRecord
	
	public static Review restoreReviewFromRentRecord( RentRecord rentRecord ) throws CCException
	{
		String sqlQuery = "SELECT id, num_stars, title, description FROM review"
						+ "	WHERE rent_record_id = ?";

		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		Review review;
		
		try {
			// prepare and execute query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt(1, rentRecord.getId() );
			rs = ps.executeQuery();
			
			if( rs.next() ) { // There is an entry in the result set
				
				// retrieve the values from the result set
				int id = rs.getInt(1);
				int numStars = rs.getInt(2);
				String title = rs.getString(3);
				String description = rs.getString(4);
				
				// create the proxy object
				review = new Review( numStars, title, description );
				review.setId(id);
				review.setRentRecord( null );
				
				return review;
				
			} else { // no matches found for the query
				return null;
			}
		} catch( SQLException e ) {
			throw new CCException("RentRecordManager.restoreReviewFromRentRecord: could not restore persistent Reveiw object: " + e );
		}
	}
	
	public static void delete(RentRecord rentRecord) throws CCException
	{
		String query = "DELETE FROM rent_record WHERE id = ?";
		PreparedStatement ps;
		Connection con = DbAccessImpl.connect();
		int rowsModified;
		
		if(rentRecord.getId() < 0) //object no in database
			return;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,  rentRecord.getId());
			rowsModified = ps.executeUpdate();
			
			if(rowsModified != 1)
				throw new CCException("RentRecordManager.delete: failed to delete rent record");
		}catch(SQLException e) {
			throw new CCException("RentRecordManager.delte: failed to delete rent record: " + e);
		}
		
		DbAccessImpl.disconnect(con);
		
	} //end of delete
	
}
