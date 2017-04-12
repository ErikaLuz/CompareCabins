package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import object.Availability;

public class AvailabilityManager {
	
	public static void store(Availability availability)
	{
		int rowsModified;
		String query = "INSERT INTO availability (price, date, cabin_id) VALUES (?,?,?)";
	
		Connection con = DbAccessImpl.connect();
		
		try 
		{
			PreparedStatement ps = con.prepareStatement(query);
			
			// set the PreparedStatement parameters to values given from User or to sql null values if nullable	
			
			if(availability.getPrice() != -1) ps.setFloat(1, availability.getPrice());
			else ps.setNull(1, java.sql.Types.FLOAT);
			
			if(availability.getDate() != null) ps.setDate(2, new java.sql.Date (availability.getDate().getTime().getTime()));
			else ps.setNull(2, java.sql.Types.DATE);
		
			if(availability.getCabin() != null) ps.setInt(3, availability.getCabin().getId());
			else ps.setNull(3, java.sql.Types.INTEGER);
			
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
				   int availabilityId = r.getInt( 1 );
				   if( availabilityId > 0 )
				   availability.setId( availabilityId ); // set the availability Id for this object 
				   }
		     	 }
		     }else { 
		    	 // throw new CompareCabinsException( "AvailabilityManager.store: failed to save an Availability to the database" );
			 }
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		DbAccessImpl.disconnect(con);	
	} //end of store

}
