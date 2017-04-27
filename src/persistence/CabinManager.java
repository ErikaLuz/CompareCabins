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
import object.Availability;
import object.Cabin;
import object.CabinPicture;
import object.Feature;
import object.RentRecord;
import object.User;

public class CabinManager {

	public static void store( Cabin cabin ) throws CCException
	{
		String insertSQL 	= "INSERT INTO cabin ( address, city, state, description, title, bedroom_count, bath_count, max_occupancy, user_id, amenities_id)"
							+ "		VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		String updateSQL 	= "UPDATE cabin SET address = ?, city = ?, state = ?, description = ?, title = ?, bedroom_count = ?, bath_count = ?, max_occupancy = ?, "
							+ "user_id = ?,	amenities_id = ? WHERE id = ?";
		Connection conn = DbAccessImpl.connect();
		PreparedStatement ps;
		int rowsModified;
		
		try { // prepare and execute SQL query
			
			// test is object is already in database to determine insert or update
			if( cabin.getId() >= 0 )
				ps = conn.prepareStatement( updateSQL );
			else
				ps = conn.prepareStatement( insertSQL );
			
			// set the PreparedStatement parameters to values given from Cabin or to sql null values if nullable
			if( cabin.getAddress() != null ) 
				ps.setString( 1, cabin.getAddress() );
			else 
				ps.setNull(1, java.sql.Types.VARCHAR);
			
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
			
			if( cabin.getTitle() != null )
				ps.setString( 5, cabin.getTitle() );
			else
				ps.setNull( 5, java.sql.Types.VARCHAR );
			
			if( cabin.getBedroomCount() >= 0 )
				ps.setInt( 6, cabin.getBedroomCount() );
			else
				ps.setNull( 6, java.sql.Types.INTEGER );
			
			if( cabin.getBathCount() >= 0 )
				ps.setFloat( 7, cabin.getBathCount() );
			else
				ps.setNull( 7, java.sql.Types.INTEGER );
			
			if( cabin.getMaxOccupancy() >= 0 )
				ps.setInt( 8, cabin.getMaxOccupancy() );
			else
				ps.setNull( 8, java.sql.Types.INTEGER );
			
			if( cabin.getUser() != null )
				ps.setInt( 9, cabin.getUser().getId() );
			else
				ps.setNull( 9, java.sql.Types.INTEGER);
			if( cabin.getAmenities() != null )
				
				ps.setInt( 10, cabin.getAmenities().getId() );
			else
				ps.setNull( 10, java.sql.Types.INTEGER);
			
			// set id if query is an update
			if( cabin.getId() >= 0 )
				ps.setInt( 11, cabin.getId() );
			
			// execute the query
			rowsModified = DbAccessImpl.update( conn, ps );
			
			// set the id value assigned by the database to the cabin object
			if( rowsModified >= 1 ) 
			{
				if( cabin.getId() < 0 ) 
				{
		            String sql = "select last_insert_id()";
		            if( ps.execute( sql ) ) // statement returned a result
		            { 		
		                // retrieve the result
		                ResultSet r = ps.getResultSet();
		
		                while( r.next() ) 
		                {
		                    // retrieve the last insert auto_increment value
		                    int cabinId = r.getInt( 1 );
		                    if( cabinId > 0 )
		                        cabin.setId( cabinId ); // set the cabin Id for this object 
		                }
		            }
				}
	        }
	        else { 
	            throw new CCException("CabinManager.store: failed to save a cabin");
	        }
	    } catch( SQLException e) {
			throw new CCException("CabinManager.store: failed to save a cabin: " + e );
		}
	}
	
	public static List<Cabin> restore( Cabin modelCabin ) throws CCException
	{
		String  selectCabinSql = "SELECT id, address, city, state, description, title, bedroom_count, bath_count, max_occupancy from cabin"; 
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
				if( modelCabin.getTitle() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					condition.append(" title = '" + modelCabin.getTitle() + "'");
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
					condition.append(" max_occupancy = " + modelCabin.getMaxOccupancy() );
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
				String	title;
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
					title = rs.getString(6);
					bedroomCount = rs.getInt(7);
					bathCount = rs.getFloat(8);
					maxOccupancy = rs.getInt(9);

					// create a proxy object
					Cabin cabin = new Cabin( address, city, state, description, title, bedroomCount, bathCount, maxOccupancy );
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
			throw new CCException("CabinManager.restore: Could not restore persistent Cabin objects: " + e );
		}		
	}//end of restore
	
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
			throw new CCException("CabinManager.restoreUserFromCabin: could not restore persistent User object: " + e );
		}
		
	}
	
	public static Amenities restoreAmenitiesFromCabin( Cabin cabin ) throws CCException
	{
		String sqlQuery = "SELECT amenities.id, has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer,"
						+ "	allows_pets, allows_smoking FROM amenities"
						+ "	JOIN cabin ON amenities.id = cabin.amenities_id"
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
			throw new CCException("CabinManager.restoreAmenitiesFromCabin: could not restore persistent Amenities object: " + e );
		}		
	}
	
	public static List<CabinPicture> restoreCabinPicturesFromCabin( Cabin cabin ) throws CCException
	{
		String sqlQuery = "SELECT id, file_path, priority FROM cabin_picture"
						+ "	WHERE cabin_id = ?";
		
		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		List<CabinPicture> cabinPictures = new LinkedList<CabinPicture>();
		
		try {
			// prepare and execute the query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt( 1, cabin.getId() );
			rs = ps.executeQuery();
			
			while( rs.next() ) { // there is a next entry in the result set
				
				// retrieve the values from the result set
				int id = rs.getInt(1);
				String filePath = rs.getString(2);
				int priority = rs.getInt(3);
				
				// create the proxy object
				CabinPicture cabinPicture = new CabinPicture( filePath, priority );
				cabinPicture.setId(id);
				cabinPicture.setCabin(null);
				
				cabinPictures.add( cabinPicture );
			}
			
			return cabinPictures;
			
		} catch( SQLException e ) {
			throw new CCException("CabinManager.restoreCabinPicturesFromCabin: could not restore persistent CabinPicture objects: " + e );
		}
		
	}
	
	public static List<Feature> restoreFeaturesFromCabin( Cabin cabin ) throws CCException
	{
		String sqlQuery = "SELECT id, feature_string FROM feature"
						+ "	WHERE cabin_id = ?";
		
		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		List<Feature> features = new LinkedList<Feature>();
		
		try {
			// prepare and execute the query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt( 1, cabin.getId() );
			rs = ps.executeQuery();
			
			while( rs.next() ) { // there is a next entry in the result set
				
				// retrieve the values from the result set
				int id = rs.getInt(1);
				String featureString = rs.getString(2);
				
				// create the proxy object
				Feature feature = new Feature( featureString );
				feature.setId(id);
				feature.setCabin(null);
				
				features.add( feature );
			}
			
			return features;
			
		} catch( SQLException e ) {
			throw new CCException("CabinManager.restoreFeaturesFromCabin: could not restore persistent Feature objects: " + e );
		}
		
	}
	
	public static List<Availability> restoreAvailabilitiesFromCabin( Cabin cabin ) throws CCException
	{
		String sqlQuery = "SELECT id, price, date FROM availability"
						+ "	WHERE cabin_id = ?";
		
		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		List<Availability> availabilities = new LinkedList<Availability>();
		
		try {
			// prepare and execute the query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt( 1, cabin.getId() );
			rs = ps.executeQuery();
			
			while( rs.next() ) { // there is a next entry in the result set
				
				// retrieve the values from the result set
				int id = rs.getInt(1);
				float price = rs.getFloat(2);
				java.sql.Date date = rs.getDate(3);
				
				// convert Date to Calendar
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				
				// create the proxy object
				Availability availability = new Availability( price, cal );
				availability.setId(id);
				availability.setCabin(null);
				
				availabilities.add( availability );
			}
			
			return availabilities;
			
		} catch( SQLException e ) {
			throw new CCException("CabinManager.restoreAvailabilitiesFromCabin: could not restore persistent Availabilities objects: " + e );
		}
		
	}
	
	public static List<RentRecord> restoreRentRecordsFromCabin( Cabin cabin ) throws CCException
	{
		String sqlQuery = "SELECT id, total_price, start_date, end_date FROM rent_record"
						+ "	WHERE cabin_id = ?";
		
		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		List<RentRecord> rentRecords = new LinkedList<RentRecord>();
		
		try {
			// prepare and execute the query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt( 1, cabin.getId() );
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
			throw new CCException("CabinManager.restoreRentRecordsFromCabin: could not restore persistent RentRecord objects: " + e );
		}
		
	}
	
	public static void delete( Cabin cabin ) throws CCException
    {	
		String deleteAmenities = "DELETE FROM amenities WHERE id = ?";
        String deleteCabin = "DELETE FROM cabin WHERE id = ?"; 
        String deleteAvailabiliy = "DELETE FROM availability WHERE id = ?";
        String deleteCabinPicture = "DELETE from cabin_picture WHERE id = ?";
        String deleteFeature = "DELETE FROM feature WHERE id = ?";
        String deleteRentRecord = "DELETE FROM rent_record WHERE id = ?";
        
        PreparedStatement ps;
        Connection con = DbAccessImpl.connect();
        int rowsModified;
        
        // Delete cabin availabilities
        
        List<Availability> availability = restoreAvailabilitiesFromCabin(cabin);
        
        for(int i = 0; i < availability.size(); i++)
		{
			try{
				ps = con.prepareStatement(deleteAvailabiliy);
				ps.setInt(1, availability.get(i).getId());
				rowsModified = ps.executeUpdate();
				
				if(rowsModified != 1)
					throw new CCException("CabinManager.delete: failed to delete cabin's availabilities");
				
			}catch(SQLException e){
				throw new CCException("CabinManager.delete: failed to delete cabin's availabilities" + e);
			}
		}
        
        // Delete cabin pictures
        
        List<CabinPicture> cabinPicture = restoreCabinPicturesFromCabin(cabin);
        
        for(int i = 0; i < cabinPicture.size(); i++)
		{
			try{
				ps = con.prepareStatement(deleteCabinPicture);
				ps.setInt(1, cabinPicture.get(i).getId());
				rowsModified = ps.executeUpdate();
				
				if(rowsModified != 1)
					throw new CCException("CabinManager.delete: failed to delete cabin's cabin pictures");
				
			}catch(SQLException e){
				throw new CCException("CabinManager.delete: failed to delete cabin's cabin pictures" + e);
			}
		}
        
        // Delete cabin features
        
        List<Feature> feature = restoreFeaturesFromCabin(cabin);
        
        for(int i = 0; i < feature.size(); i++)
		{
			try{
				ps = con.prepareStatement(deleteFeature);
				ps.setInt(1, feature.get(i).getId());
				rowsModified = ps.executeUpdate();
				
				if(rowsModified != 1)
					throw new CCException("CabinManager.delete: failed to delete cabin's features");
				
			}catch(SQLException e){
				throw new CCException("CabinManager.delete: failed to delete cabin's features" + e);
			}
		}
        
        // Delete cabin rent records
        
        List<RentRecord> rentRecords = restoreRentRecordsFromCabin(cabin);
        
        for(int i = 0; i < rentRecords.size(); i++)
		{
			try{
				ps = con.prepareStatement(deleteRentRecord);
				ps.setInt(1, rentRecords.get(i).getId());
				rowsModified = ps.executeUpdate();
				
				if(rowsModified != 1)
					throw new CCException("CabinManager.delete: failed to delete cabin's rent records");
				
			}catch(SQLException e){
				throw new CCException("CabinManager.delete: failed to delete cabin's rent records" + e);
			}
		}
        
        // Deleting cabin
             
        if( cabin.getId() < 0 ) // object not in database
            return;
        
        try {
            ps = con.prepareStatement( deleteCabin );         
            ps.setInt( 1, cabin.getId() );
            rowsModified = ps.executeUpdate();          
            
            if( rowsModified != 1 ) 
                throw new CCException("CabinManager.delete: failed to delete a cabin" );
        }
        catch( SQLException e ) {
            throw new CCException( "CabinManager.delete: failed to delete a cabin: " + e );   
        }
        
        // Deleting cabin amenities
        
        Amenities amenity = restoreAmenitiesFromCabin(cabin);
        
        try {
        	ps = con.prepareStatement(deleteAmenities);
        	ps.setInt(1, amenity.getId());
        	rowsModified = ps.executeUpdate();
        	
        	if( rowsModified != 1)
        		throw new CCException("CabinManager.delete: failed to delete cabin's amenities");
        }
        catch( SQLException e ) {
            throw new CCException( "CabinManager.delete: failed to delete cabin's amenities" + e );   
        }
    }
}