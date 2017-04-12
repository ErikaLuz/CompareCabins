package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import object.Review;


public class ReviewManager {

	public static void store(Review review)
	{
		int rowsModified;
		String query = "INSERT INTO review (num_stars, title, description, rent_record_id) VALUES"
				+" (?,?,?,?)";
		
		Connection con = DbAccessImpl.connect();
		
		try 
		{
			PreparedStatement ps = con.prepareStatement(query);
			
			// set the PreparedStatement parameters to values given from User or to sql null values if nullable
			if(review.getNumStars() != -1) ps.setInt(1, review.getNumStars());
			else ps.setNull(1, java.sql.Types.INTEGER);
			
			if(review.getTitle() != null) ps.setString(2, review.getTitle());
			else ps.setNull(2, java.sql.Types.VARCHAR);
			
			if(review.getDescription() != null) ps.setString(3, review.getDescription());
			else ps.setNull(3, java.sql.Types.VARCHAR);
			
			if(review.getRentRecord() != null) ps.setInt(4, review.getRentRecord().getId());
			else ps.setNull(4, java.sql.Types.INTEGER);
			
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
				   int reviewId = r.getInt( 1 );
				   if( reviewId > 0 )
				   review.setId( reviewId ); // set the user Id for this object 
				   }
		     	 }
		     }else { 
		    	 // throw new CompareCabinsException( "ReviewManager.store: failed to save a Review to the database" );
			 }
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}