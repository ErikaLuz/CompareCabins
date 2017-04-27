package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CCException;
import object.Availability;
import object.Cabin;
import object.CabinPicture;

public class CabinPictureManager {

	public static void store(CabinPicture cabinPicture) throws CCException
	{
		int rowsModified;
		String insertSQL = "INSERT INTO cabin_picture (file_path, priority, cabin_id) VALUES (?,?,?)";
		String updateSQL 	= "UPDATE cabin SET address = ?, city = ?, state = ?, description = ?, title = ?, bedroom_count = ?, bath_count = ?, max_occupancy = ?, "
				+ "user_id = ?,	amenities_id = ? WHERE id = ?";
		
		Connection conn = DbAccessImpl.connect();
		PreparedStatement ps;
		try 
		{			
			if( cabinPicture.getId() >= 0 )
				ps = conn.prepareStatement( updateSQL );
			else
				ps = conn.prepareStatement( insertSQL );
			
			// set the PreparedStatement parameters to values given from User or to sql null values if nullable
			if(cabinPicture.getFilePath() != null) 
				ps.setString(1, cabinPicture.getFilePath());
			else 
				ps.setNull(1, java.sql.Types.VARCHAR); 

			if( cabinPicture.getPriority() >= 0 )
				ps.setInt( 2, cabinPicture.getPriority() );
			else
				ps.setNull( 2, java.sql.Types.INTEGER );
			
			if(cabinPicture.getCabin() != null) 
				ps.setInt(3, cabinPicture.getCabin().getId());
			else 
				ps.setNull(3, java.sql.Types.INTEGER);
			
			// set id if query is an update
			if( cabinPicture.getId() >= 0 )
				ps.setInt( 4, cabinPicture.getId() );
			
			//execute the query
			rowsModified = DbAccessImpl.update(conn, ps);
			
			// set the id value assigned by the database to the user object
			if( rowsModified >= 1 ) {
			    String sql = "select last_insert_id()";
			    if( ps.execute( sql ) ) { // statement returned a result
				
				   // retrieve the result
				   ResultSet r = ps.getResultSet();
				
				   while( r.next() ) {
				   // retrieve the last insert auto_increment value
				   int cabinPictureId = r.getInt( 1 );
				   if( cabinPictureId > 0 )
				   cabinPicture.setId( cabinPictureId ); // set the cabinPicture Id for this object 
				   }
		     	 }
		     }else { 
		 		DbAccessImpl.disconnect(conn);
		    	 throw new CCException( "CabinPictureManager.store: failed to save a CabinPicture to the database" );
			 }
			
		}catch(SQLException e)
		{
			DbAccessImpl.disconnect(conn);
			throw new CCException( "CabinPictureManager.store: failed to save a CabinPicture to the database" );
		}
		
		DbAccessImpl.disconnect(conn);
	} //end of store
	
	public static Cabin restoreCabinFromCabinPicture( CabinPicture cabinPicture ) throws CCException
	{
		String sqlQuery = "SELECT c.id, c.address, c.city, c.state, c.description, c.title, c.bedroom_count, c.bath_count, c.max_occupancy FROM cabin"
						+ "	JOIN cabin_picture ON cabin.id = cabin_picture.cabin_id"
						+ "	WHERE cabin_picture.id = ?";
		
		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		Cabin cabin;
		
		try {
			// prepare and execute the query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt( 1, cabinPicture.getId() );
			rs = ps.executeQuery();
			
			if( rs.next() ) { // there is an entry in the result set
				
				// retrieve the values from the result set
				int id = rs.getInt(1);
				String address = rs.getString(2);
				String city = rs.getString(3);
				String state = rs.getString(4);
				String description = rs.getString(5);
				String title = rs.getString(6);
				int bedroomCount = rs.getInt(7);
				float bathCount = rs.getFloat(8);
				int maxOccupancy = rs.getInt(9);
				
				// create the proxy object
				cabin = new Cabin( address, city, state, description, title, bedroomCount, bathCount, maxOccupancy );
				cabin.setId(id);
				cabin.setUser(null);
				cabin.setAmenities(null);
				
				DbAccessImpl.disconnect(conn);
				return cabin;
			} else { // no matches found for the query
				DbAccessImpl.disconnect(conn);
				return null;
			}
		} catch( SQLException e ) {
			DbAccessImpl.disconnect(conn);
			throw new CCException("AvailabilityManager.restoreCabinFromAvailabilty: could not restore persistent Cabin object: " + e );
		}
		
	}
	
	public static void delete(CabinPicture cabinPicture) throws CCException
	{
		String query = "DELETE FROM cabin_picture WHERE id = ?";
		PreparedStatement ps;
		Connection conn = DbAccessImpl.connect();
		int rowsModified;
		
		if(cabinPicture.getId() < 0) //object no in database
			return;
		
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1,  cabinPicture.getId());
			rowsModified = ps.executeUpdate();
			
			if(rowsModified != 1) {
				DbAccessImpl.disconnect(conn);
			
				throw new CCException("CabinPictureManager.delete: failed to delete cabin picture");
			}
		}catch(SQLException e) {
			DbAccessImpl.disconnect(conn);
		
			throw new CCException("CabinPictureManager.delte: failed to delete cabin picture: " + e);
		}
		
		DbAccessImpl.disconnect(conn);
		
	} //end of delete
}
