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

	public static void store(CabinPicture cabinPicture)
	{
		int rowsModified;
		String query = "INSERT INTO cabin_picture (file_path, cabin_id) VALUES (?,?)";
		
		Connection con = DbAccessImpl.connect();
		
		try 
		{
			PreparedStatement ps = con.prepareStatement(query);
			
			// set the PreparedStatement parameters to values given from User or to sql null values if nullable
			if(cabinPicture.getFilePath() != null) ps.setString(1, cabinPicture.getFilePath());
			else ps.setNull(1, java.sql.Types.VARCHAR); 
				
			if(cabinPicture.getCabin() != null) ps.setInt(2, cabinPicture.getCabin().getId());
			else ps.setNull(2, java.sql.Types.INTEGER);
			
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
				   int cabinPictureId = r.getInt( 1 );
				   if( cabinPictureId > 0 )
				   cabinPicture.setId( cabinPictureId ); // set the cabinPicture Id for this object 
				   }
		     	 }
		     }else { 
		    	 // throw new CompareCabinsException( "CabinPictureManager.store: failed to save a CabinPicture to the database" );
			 }
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		DbAccessImpl.disconnect(con);
	} //end of store
	
	public static Cabin restoreCabinFromCabinPicture( CabinPicture cabinPicture ) throws CCException
	{
		String sqlQuery = "SELECT c.id, c.address, c.city, c.state, c.description, c.bedroom_count, c.bath_count, c.max_occupancy FROM cabin"
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
				int bedroomCount = rs.getInt(6);
				float bathCount = rs.getFloat(7);
				int maxOccupancy = rs.getInt(8);
				
				// create the proxy object
				cabin = new Cabin( address, city, state, description, bedroomCount, bathCount, maxOccupancy );
				cabin.setId(id);
				cabin.setUser(null);
				cabin.setAmenities(null);
				
				return cabin;
			} else { // no matches found for the query
				return null;
			}
		} catch( SQLException e ) {
			throw new CCException("AvailabilityManager.restoreCabinFromAvailabilty: could not restore persistent Cabin object: " + e );
		}
		
	}
	
	public static void delete(CabinPicture cabinPicture) throws CCException
	{
		String query = "DELETE FROM cabin_picture WHERE id = ?";
		PreparedStatement ps;
		Connection con = DbAccessImpl.connect();
		int rowsModified;
		
		if(cabinPicture.getId() < 0) //object no in database
			return;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,  cabinPicture.getId());
			rowsModified = ps.executeUpdate();
			
			if(rowsModified != 1)
				throw new CCException("CabinPictureManager.delete: failed to delete cabin picture");
		}catch(SQLException e) {
			throw new CCException("CabinPictureManager.delte: failed to delete cabin picture: " + e);
		}
		
		DbAccessImpl.disconnect(con);
		
	} //end of delete
}
