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
import object.Availability;
import object.Cabin;


public class AvailabilityManager {
	
	public static void store(Availability availability) throws CCException
	{
		int rowsModified;
		String insertSql 	= "INSERT INTO availability (price, date, cabin_id) VALUES (?,?,?)";	
		String updateSql 	= "UPDATE availability SET price = ?, city = ?, description = ?, bedroom_count = ?, max_occupancy = ?, user_id = ?,"
							+ "		amenities_id = ? WHERE id = ?";
		Connection conn = DbAccessImpl.connect();
		PreparedStatement ps;
		
		try 
		{
			// test is object is already in database to determine insert or update
			if( availability.getId() >= 0 )
				ps = conn.prepareStatement( updateSql );
			else
				ps = conn.prepareStatement( insertSql );
			
			// set the PreparedStatement parameters to values given from availability or to sql null values if nullable				
			if( availability.getPrice() >= 0 ) 
				ps.setFloat(1, availability.getPrice());
			else 
				ps.setNull(1, java.sql.Types.FLOAT);			
			if( availability.getDate() != null ) 
				ps.setDate(2, new java.sql.Date (availability.getDate().getTime().getTime() ) );
			else 
				ps.setNull(2, java.sql.Types.DATE);
			if(availability.getCabin() != null) 
				ps.setInt(3, availability.getCabin().getId());
			else 
				ps.setNull(3, java.sql.Types.INTEGER);
			
			//execute the query
			rowsModified = DbAccessImpl.update(conn, ps);
			
			// set the id value assigned by the database to the cabin object
			if( rowsModified >= 1 ) 
			{
				if( availability.getId() < 0 ) 
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
		                        availability.setId( id ); // set the availability Id for this object 
		                }
		            }
				}
	        }
	        else { 
	            throw new CCException("AvailabilityManager.store: failed to save an availability ");
	        }
	    } catch( SQLException e) {
			throw new CCException("AvailabilityManager.store: failed to save an availability: " + e );
		}
	}
	
	public static List<Availability> restore( Availability modelAvailability ) throws CCException
	{
		String  selectAmenitiesSql 	= "SELECT id, price, date FROM availability";
		Statement    statement = null;
		StringBuffer query = new StringBuffer( 250 );
		StringBuffer condition = new StringBuffer( 250 );
		List<Availability> availabilities = new LinkedList<Availability>();
		Connection conn = DbAccessImpl.connect();

		condition.setLength( 0 );
		query.append( selectAmenitiesSql );

		// append where clauses to query for each specified field of the modelCabin
		if( modelAvailability != null ) {
			if( modelAvailability.getId() >= 0 ) // id is unique, so it is sufficient to get a Cabin
				query.append( " where id = " + modelAvailability.getId() );
			else {	// no id is given		
				if( modelAvailability.getPrice() >= 0 )
					condition.append( " price = " + modelAvailability.getPrice() );
				if( modelAvailability.getDate() != null ) {
					if ( condition.length() > 0 )
						condition.append( " and");
					Calendar cal = modelAvailability.getDate();
					int day = cal.get( Calendar.DAY_OF_MONTH );
					int month = cal.get( Calendar.MONTH );
					int year = cal.get( Calendar.YEAR );
					String dateString = year + "-" + month + "-" + day;
					condition.append(" date = '" + dateString + "'");
				}
				
				// append condition clause to where clause
				if( condition.length() > 0 ) {
					query.append(  " where " );
					query.append( condition );
				}
			}
		}
		
		try {			
			statement = conn.createStatement();
			
			if( statement.execute( query.toString() ) ) { // statement returned a result
				
				// fields of an availability object
				int   		id;
				float		price;
				Calendar 	calendar;
				
				// retrieve the ResultSet generated by the execution of the query
				ResultSet rs = statement.getResultSet();

				while( rs.next() ) { // process the result set

					// retrieve the values from an entry in the result set
					id = rs.getInt(1);
					price = rs.getFloat(2);
					java.sql.Date sqlDate = rs.getDate(3);
					
					// set calendar value from sql date
					calendar = Calendar.getInstance();
					calendar.setTime(sqlDate);


					// create a proxy object
					Availability availability = new Availability( price, calendar );
					availability.setId(id);
					availability.setCabin( null );
					
					availabilities.add( availability );
				}
				
				return availabilities;
				
			} else {
				return null;
			}
		}
		catch( SQLException e ) {      
			throw new CCException("AmenitiesManager.restore: Could not restore persistent Amenities objects: " + e );
		}		
	}
	
	public static Cabin restoreCabinFromAvailability( Availability availability ) throws CCException
	{
		String sqlQuery = "SELECT c.id, c.address, c.city, c.state, c.description, c.bedroom_count, c.bath_count, c.max_occupancy FROM cabin c"
						+ "	JOIN availability ON cabin.id = availability.cabin_id"
						+ "	WHERE availability.id = ?";
		
		Connection conn = DbAccessImpl.connect();
		ResultSet rs;
		Cabin cabin;
		
		try {
			// prepare and execute the query
			PreparedStatement ps = conn.prepareStatement( sqlQuery );
			ps.setInt( 1, availability.getId() );
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
	
	public static void delete(Availability availability) throws CCException
	{
		String query = "DELETE FROM availability WHERE id = ?";
		PreparedStatement ps;
		Connection con = DbAccessImpl.connect();
		int rowsModified;
		
		if(availability.getId() < 0) //object no in database
			return;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,  availability.getId());
			rowsModified = ps.executeUpdate();
			
			if(rowsModified != 1)
				throw new CCException("AvailabilityManager.delete: failed to delete availability");
		}catch(SQLException e) {
			throw new CCException("AvailabilityManager.delte: failed to delete availability: " + e);
		}
		
		DbAccessImpl.disconnect(con);
	}

}
