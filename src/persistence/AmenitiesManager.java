package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CCException;
import object.Amenities;
import object.Cabin;

public class AmenitiesManager {

	public static void store(Amenities amenities)
	{
		String insertSql	= "INSERT INTO amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning,"
							+ "	has_washer_dryer, allows_pets, allows_smoking) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String updateSql	= "UPDATE amenities SET has_lake = ?, has_river = ?, has_pool = ?, has_hot_tub = ?, has_wifi = ?, has_air_conditioning = ?,"
							+ "		has_washer_dryer = ?, allows_pets = ?, allows_smoking = ? WHERE id = ?";
		Connection conn = DbAccessImpl.connect();
		PreparedStatement ps;
		int rowsModified;
		try {
			
			// test is object is already in database to determine insert or update
			if( amenities.getId() >= 0 )
				ps = conn.prepareStatement( updateSql );
			else
				ps = conn.prepareStatement( insertSql );
			
			// set the PreparedStatement parameters to values given from User or to sql null values if nullable
		    ps.setBoolean(1, amenities.isHasLake()); 
		    ps.setBoolean(2, amenities.isHasRiver());
		    ps.setBoolean(3, amenities.isHasPool());
		    ps.setBoolean(4, amenities.isHasHotTub());
		    ps.setBoolean(5, amenities.isHasWifi());
		    ps.setBoolean(6, amenities.isHasAirConditioning());
		    ps.setBoolean(7, amenities.isHasWasherDryer());
		    ps.setBoolean(8, amenities.isAllowsPets());
		    ps.setBoolean(9, amenities.isAllowsSmoking());
		    
			// set id if query is an update
			if( amenities.getId() >= 0 )
				ps.setInt( 10, amenities.getId() );
			
			//execute the query
			rowsModified = DbAccessImpl.update( conn, ps );
			
			// set the id value assigned by the database to the amenities object if inserted
			if( amenities.getId() >= 0 && rowsModified >= 1 ) {
			    String sql = "select last_insert_id()";
			    if( ps.execute( sql ) ) { // statement returned a result
				
				   // retrieve the result
				   ResultSet r = ps.getResultSet();
				
				   while( r.next() ) {
				   // retrieve the last insert auto_increment value
				   int amenitiesId = r.getInt( 1 );
				   if( amenitiesId > 0 )
				   amenities.setId( amenitiesId ); // set the cabin Id for this object
				   }
		     	 }
			    else { 
		            throw new CCException("CabinManager.store: failed to save a cabin");
		        }
		    } catch( SQLException e) {
				throw new CCException("CAbinManager.store: failed to save a cabin: " + e );
			}
		
		DbAccessImpl.disconnect(con);

	} //end of store
	
	public static void delete(Amenities amenities) throws CCException
	{
		String query = "DELETE FROM amenities WHERE id = ?";
		PreparedStatement ps;
		Connection con = DbAccessImpl.connect();
		int rowsModified;
		
		if(amenities.getId() < 0) //object no in database
			return;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,  amenities.getId());
			rowsModified = ps.executeUpdate();
			
			if(rowsModified != 1)
				throw new CCException("AmenitiesManager.delete: failed to delete amenities");
		}catch(SQLException e) {
			throw new CCException("AmenitiesManager.delte: failed to delete amenities: " + e);
		}
		
		DbAccessImpl.disconnect(con);
	} //end of delete
	
}
