package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import object.Cabin;

public class CabinManager {

	public static void store( Cabin cabin )
	{
		int insertionCount;
		String query = "INSERT INTO cabin ( address, city, state, description, bedroom_count, bath_count, max_occupancy, user_id, amenities_id)"
						+	" VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		Connection conn = DbAccessImpl.connect();
		try {
			
			PreparedStatement ps = conn.prepareStatement( query );
			
			// set the PreparedStatement parameters to values given from Cabin or to sql null values if nullable
			if( cabin.getAddress() != null ) {
				ps.setString( 1, cabin.getAddress() );
			} else {
				ps.setNull(1, java.sql.Types.VARCHAR);
			}
			if( cabin.getCity() != null )
				ps.setString( 2, cabin.getCity() );
			else
				ps.setNull(2, java.sql.Types.VARCHAR);
			if( cabin.getState() != null )
				ps.setString( 3, cabin.getState() );
			else
				ps.setNull( 3, java.sql.Types.VARCHAR );
			if( cabin.getDescription() != null )
				ps.setString( 4, cabin.getDescription() );
			else
				ps.setNull( 4, java.sql.Types.VARCHAR );
			if( cabin.getBedroomCount() >= 0 )
				ps.setInt( 5, cabin.getBedroomCount() );
			else
				ps.setNull( 5, java.sql.Types.INTEGER );
			if( cabin.getBathCount() >= 0 )
				ps.setFloat( 6, cabin.getBathCount() );
			else
				ps.setNull( 6, java.sql.Types.INTEGER );
			if( cabin.getMaxOccupancy() >= 0 )
				ps.setInt( 7, cabin.getMaxOccupancy() );
			else
				ps.setNull( 7, java.sql.Types.INTEGER );
			if( cabin.getUser() != null )
				ps.setInt( 8, cabin.getUser().getId() );
			else
				ps.setInt( 8, java.sql.Types.INTEGER);
			if( cabin.getAmenities() != null )
				ps.setInt( 9, cabin.getAmenities().getId() );
			else
				ps.setInt( 9, java.sql.Types.INTEGER);
			
			// execute the query
			insertionCount = DbAccessImpl.update( conn, ps );
			
			// set the id value assigned by the database to the cabin object
			if( insertionCount >= 1 ) {
	            String sql = "select last_insert_id()";
	            if( ps.execute( sql ) ) { // statement returned a result
	
	                // retrieve the result
	                ResultSet r = ps.getResultSet();
	
	                while( r.next() ) {
	                    // retrieve the last insert auto_increment value
	                    int cabinId = r.getInt( 1 );
	                    if( cabinId > 0 )
	                        cabin.setId( cabinId ); // set the cabin Id for this object 
	                }
	            }
	        }
	        else { 
	            // throw new CompareCabinsException( "CabinManager.store: failed to save a Cabin to the database" );
	        }
	    } catch( SQLException e) {
			// throw CompareCabinsException
	    	e.printStackTrace();
		}
	}
}
