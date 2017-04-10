package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


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
	
	public static List<Cabin> restore( Cabin modelCabin )
	{
		String  selectCabinSql = "select id, address, city, state, description, bedroom_count, bath_count, max_occupancy from cabin"; 
		Statement    statement = null;
		StringBuffer query = new StringBuffer( 100 );
		StringBuffer condition = new StringBuffer( 100 );
		List<Cabin> cabins = new LinkedList<Cabin>();
		Connection conn = DbAccessImpl.connect();


		condition.setLength( 0 );

		// form the query based on the given Election object instance
		query.append( selectCabinSql );
		//voteCount, office, isPartisan
		if( modelCabin != null ) {
			if( modelCabin.getId() >= 0 ) // id is unique, so it is sufficient to get a ballot
				query.append( " where id = " + modelCabin.getId() );
			else {				
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
			
			// retrieve the persistent Election objects
			if( statement.execute( query.toString() ) ) { // statement returned a result
				
				int   	id;
				String 	address;
				String 	city;
				String 	state;
				String 	description;
				int		bedroomCount;
				float	bathCount;
				int		maxOccupancy;
				
				
				ResultSet rs = statement.getResultSet();

				while( rs.next() ) {

					id = rs.getInt(1);
					address = rs.getString(2);
					city = rs.getString(3);
					state = rs.getString(4);
					description = rs.getString(5);
					bedroomCount = rs.getInt(6);
					bathCount = rs.getFloat(7);
					maxOccupancy = rs.getInt(8);

					Cabin cabin = new Cabin();
					cabin.setId( id );
					cabin.setAddress( address );
					cabin.setCity( city );
					cabin.setState( state );
					cabin.setDescription( description );
					cabin.setBedroomCount( bedroomCount );
					cabin.setBathCount( bathCount );
					cabin.setMaxOccupancy( maxOccupancy );
					cabin.setUser( null );	
					cabin.setAmenities( null );
					
					cabins.add( cabin );
				}
				return cabins;
			}
		}
		catch( SQLException e ) {      
			//throw new CompareCabinsException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
			e.printStackTrace();
		}

		// if this point is reached, it is an error
		//throw new CompareCabinsException( "ElectionManager.restore: Could not restore persistent Election objects" );
		
		// temporary, correctly handled by throwing exception at end of file like above
		cabins = null; 
		return cabins;
	}
}













