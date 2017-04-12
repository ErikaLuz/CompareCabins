package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import object.Amenities;

public class AmenitiesManager {

	public static void store(Amenities amenities)
	{
		int rowsModified;
		String query = "INSERT INTO amenities (has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning,"
					   +" has_washer_dryer, allows_pets, allows_smoking) VALUES(?,?,?,?,?,?,?,?,?)";
		
		Connection con = DbAccessImpl.connect();
		try {
			
			PreparedStatement ps = con.prepareStatement( query );
			
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
		    
			//execute the query
			rowsModified = DbAccessImpl.update(con, ps);
			
			// set the id value assigned by the database to the cabin object
			if( rowsModified >= 1 ) {
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
		     }else { 
		    	 // throw new CompareCabinsException( "AmenitiesManager.store: failed to save a Amenities to the database" );
			 }
		    
		}  catch( SQLException e) {
			// throw CompareCabinsException
	    	e.printStackTrace();
		}

	} //end of store
	
}
