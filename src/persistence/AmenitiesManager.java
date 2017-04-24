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

public class AmenitiesManager {

	public static void store(Amenities amenities) throws CCException
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
			
			// set the id value assigned by the database to the amenities object 
			if( rowsModified >= 1 ) 
			{
				if( amenities.getId() < 0 ) 
				{
		            String sql = "select last_insert_id()";
		            if( ps.execute( sql ) ) // statement returned a result
		            { 		
		                // retrieve the result
		                ResultSet r = ps.getResultSet();
		
		                while( r.next() ) 
		                {
		                    // retrieve the last insert auto_increment value
		                    int id = r.getInt( 1 );
		                    if( id > 0 )
		                        amenities.setId( id ); // set the amenities Id for this object 
		                }
		            }
				}
	        }
	        else { 
	            throw new CCException("AmenitiesManager.store: failed to save an amenities");
	        }
	    } catch( SQLException e) {
			throw new CCException("AmenitiesManager.store: failed to save an amenities: " + e );
		}
	}
	
	public static List<Amenities> restore( Amenities modelAmenities ) throws CCException
	{
		String  selectAmenitiesSql 	= "select id, has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning,"
									+ "	has_washer_dryer, allows_pets, allows_smoking from amenities"; 
		Statement    statement = null;
		StringBuffer query = new StringBuffer( 250 );
		StringBuffer condition = new StringBuffer( 250 );
		List<Amenities> amenitiesList = new LinkedList<Amenities>();
		Connection conn = DbAccessImpl.connect();

		condition.setLength( 0 );
		query.append( selectAmenitiesSql );

		// append where clauses to query for each specified field of the modelCabin
		if( modelAmenities != null ) {
			if( modelAmenities.getId() >= 0 ) // id is unique, so it is sufficient to get a Cabin
				query.append( " where id = " + modelAmenities.getId() );
			else {	// no id is given		
				if( modelAmenities.isHasLake() != false )
					condition.append( " has_lake = " + modelAmenities.isHasLake() );
				if( modelAmenities.isHasRiver() != false ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" has_river = " + modelAmenities.isHasRiver() );
				}
				if( modelAmenities.isHasPool() != false ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" has_pool = " + modelAmenities.isHasPool() );
				}
				if( modelAmenities.isHasHotTub() != false ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" has_hot_tub = " + modelAmenities.isHasHotTub() );
				}
				if( modelAmenities.isHasWifi() != false ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" has_wifi = " + modelAmenities.isHasWifi() );
				}
				if( modelAmenities.isHasAirConditioning() != false ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" has_air_conditioning = " + modelAmenities.isHasAirConditioning() );
				}
				if( modelAmenities.isHasWasherDryer() != false ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" has_washer_dryer = " + modelAmenities.isHasWasherDryer() );
				}
				if( modelAmenities.isAllowsPets() != false ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" allows_pets = " + modelAmenities.isAllowsPets() );
				}
				if( modelAmenities.isAllowsSmoking() != false ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" allows_smoking = " + modelAmenities.isAllowsSmoking() );
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
				
				// fields of an amenities object
				int   	id;
				boolean hasLake;
				boolean hasRiver;
				boolean hasPool;
				boolean hasHotTub;
				boolean hasWifi;
				boolean hasAirConditioning;
				boolean hasWasherDryer;
				boolean allowsPets;
				boolean allowsSmoking;
				
				// retrieve the ResultSet generated by the execution of the query
				ResultSet rs = statement.getResultSet();

				while( rs.next() ) { // process the result set

					// retrieve the values from an entry in the result set
					id = rs.getInt(1);
					hasLake = rs.getBoolean(2);
					hasRiver = rs.getBoolean(3);
					hasPool = rs.getBoolean(4);
					hasHotTub = rs.getBoolean(5);
					hasWifi = rs.getBoolean(6);
					hasAirConditioning = rs.getBoolean(7);
					hasWasherDryer = rs.getBoolean(8);
					allowsPets = rs.getBoolean(9);
					allowsSmoking = rs.getBoolean(10);

					// create a proxy object
					Amenities amenities = new Amenities(hasLake,hasRiver,hasPool,hasHotTub,hasWifi,hasAirConditioning,hasWasherDryer,allowsPets,allowsSmoking);
					amenities.setId( id );
					
					amenitiesList.add( amenities );
				}
				
				return amenitiesList;
				
			} else {
				return null;
			}
		}
		catch( SQLException e ) {      
			throw new CCException("AmenitiesManager.restore: Could not restore persistent Amenities objects: " + e );
		}		
	}
	
	public static Cabin restoreCabinFromAmenities( Amenities amenities ) throws CCException
	{
		String sqlQuery = "SELECT id, address, city, state, description, title, bedroom_count, bath_count, max_occupancy FROM cabin"
						+ "	WHERE amenities_id = ?";

		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		Cabin cabin;
		
		try {
			// prepare and execute query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt(1, amenities.getId() );
			rs = ps.executeQuery();
			
			if( rs.next() ) { // There is an entry in the result set
				
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
				cabin.setUser( null );
				cabin.setAmenities( null );
				
				return cabin;
			} else { // no matches found for the query
				return null;
			}
		} catch( SQLException e ) {
			throw new CCException("AmenitiesManager.restoreCabinFromAmenities: could not restore persistent cabin object: " + e );
		}
	}
	
	public static void delete(Amenities amenities) throws CCException
	{
		String query = "DELETE FROM amenities WHERE id = ?";
		PreparedStatement ps;
		Connection con = DbAccessImpl.connect();
		int rowsModified;
		
		if(amenities.getId() < 0) { //object not in database
			DbAccessImpl.disconnect(con);
			return;
		}
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,  amenities.getId());
			rowsModified = ps.executeUpdate();
			
			if(rowsModified != 1)
				throw new CCException("AmenitiesManager.delete: failed to delete amenities");
		} catch(SQLException e) {
			throw new CCException("AmenitiesManager.delte: failed to delete amenities: " + e);
		}
		
		DbAccessImpl.disconnect(con);
	} 
	
}
