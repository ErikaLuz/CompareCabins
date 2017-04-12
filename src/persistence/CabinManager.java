package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import exception.CCException;
import object.Amenities;
import object.Cabin;
import object.User;

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
	
	public static List<Cabin> restore( Cabin modelCabin ) throws CCException
	{
		String  selectCabinSql = "select id, address, city, state, description, bedroom_count, bath_count, max_occupancy from cabin"; 
		Statement    statement = null;
		StringBuffer query = new StringBuffer( 250 );
		StringBuffer condition = new StringBuffer( 250 );
		List<Cabin> cabins = new LinkedList<Cabin>();
		Connection conn = DbAccessImpl.connect();

		condition.setLength( 0 );
		query.append( selectCabinSql );

		// append where clauses to query for each specified field of the modelCabin
		if( modelCabin != null ) {
			if( modelCabin.getId() >= 0 ) // id is unique, so it is sufficient to get a Cabin
				query.append( " where id = " + modelCabin.getId() );
			else {	// no id is given		
				if( modelCabin.getAddress() != null )
					condition.append( " address = '" + modelCabin.getAddress() + "'");
				if( modelCabin.getCity() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" city = '" + modelCabin.getCity() + "'");
				}
				if( modelCabin.getState() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" state = '" + modelCabin.getState() + "'");
				}
				if( modelCabin.getDescription() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" description = '" + modelCabin.getDescription() + "'");
				}
				if( modelCabin.getBedroomCount() >= 0 ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" bedroom_count = " + modelCabin.getBedroomCount() );
				}
				if( modelCabin.getBathCount() >= 0 ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" bath_count = " + modelCabin.getBathCount() );
				}
				if( modelCabin.getMaxOccupancy() >= 0 ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" max_Occupancy = " + modelCabin.getMaxOccupancy() );
				}
				if( condition.length() > 0 ) {
					query.append(  " where " );
					query.append( condition );
				}
			}
		}
		
		try {			
			statement = conn.createStatement();
			
			if( statement.execute( query.toString() ) ) { // statement returned a result
				
				// fields of a cabin object
				int   	id;
				String 	address;
				String 	city;
				String 	state;
				String 	description;
				int		bedroomCount;
				float	bathCount;
				int		maxOccupancy;
				
				// retrieve the ResultSet generated by the execution of the query
				ResultSet rs = statement.getResultSet();

				while( rs.next() ) { // process the result set

					// retrieve the values from an entry in the result set
					id = rs.getInt(1);
					address = rs.getString(2);
					city = rs.getString(3);
					state = rs.getString(4);
					description = rs.getString(5);
					bedroomCount = rs.getInt(6);
					bathCount = rs.getFloat(7);
					maxOccupancy = rs.getInt(8);

					// create a proxy object
					Cabin cabin = new Cabin( address, city, state, description, bedroomCount, bathCount, maxOccupancy );
					cabin.setId( id );
					cabin.setUser( null );	
					cabin.setAmenities( null );
					
					cabins.add( cabin );
				}
				
				return cabins;
				
			} else {
				return null;
			}
		}
		catch( SQLException e ) {      
			throw new CCException("CabinManager.restore: Could not restore persistent Cabin objects; Root cause: " + e );
		}		
	}
	
	public static User restoreUserFromCabin( Cabin cabin ) throws CCException
	{
		String sqlQuery = "SELECT user.id, username, password, first_name, last_name, email FROM user"
						+ "	JOIN cabin ON user.id = cabin.user_id"
						+ "	WHERE cabin.id = ?";
		
		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		User user;
		
		try {
			// prepare and execute the query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt( 1, cabin.getId() );
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
			throw new CCException("CabinManager.restoreUserFromCabin: could not restore persistent User object; Root casue: " + e );
		}
		
	}
	
	public static Amenities restoreAmenitiesFromCabin( Cabin cabin ) throws CCException
	{
		String sqlQuery = "SELECT amenities.id, has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer,"
						+ "	allows_pets, allows_smoking FROM amenities"
						+ "	JOIN cabin ON amenities.id = cabin.id"
						+ "	WHERE cabin.id = ?";

		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		Amenities amenities;
		
		try {
			// prepare and execute query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt(1, cabin.getId() );
			rs = ps.executeQuery();
			
			if( rs.next() ) { // There is an entry in the result set
				
				// retrieve the values from the result set
				int id = rs.getInt(1);
				boolean hasLake = rs.getBoolean(2);
				boolean hasRiver = rs.getBoolean(3);
				boolean hasPool = rs.getBoolean(4);
				boolean hasHotTub = rs.getBoolean(5);
				boolean hasWifi = rs.getBoolean(6);
				boolean hasAirConditioning = rs.getBoolean(7);
				boolean hasWasherDryer = rs.getBoolean(8);
				boolean allowsPets = rs.getBoolean(9);
				boolean allowsSmoking = rs.getBoolean(10);
				
				// create the proxy object
				amenities = new Amenities( hasLake, hasRiver, hasPool, hasHotTub, hasWifi, hasAirConditioning, hasWasherDryer, allowsPets, allowsSmoking );
				amenities.setId(id);
				
				return amenities;
			} else { // no matches found for the query
				return null;
			}
		} catch( SQLException e ) {
			throw new CCException("CabinManager.restoreAmenitiesFromCabin: could not restore persistent Amenities object; Root casue: " + e );
		}		
	}
}













