package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import object.CabinPicture;

public class CabinPictureManager {

	public static void store(CabinPicture cabinPicture)
	{
		int rowsModified;
		String query = "INSERT INTO cabin_picture (file_path, cabin_id) VALUES (?,?)";
		
		Connection con = DbAccessImpl.connect();
		
		try 
		{
			PreparedStatement ps = con.prepareStatement(query);
			
			// set the PreparedStatement parameters to values given from User or to sql null values if nullable
			if(cabinPicture.getFilePath() != null) ps.setString(1, cabinPicture.getFilePath());
			else ps.setNull(1, java.sql.Types.VARCHAR); 
				
			if(cabinPicture.getCabin() != null) ps.setInt(2, cabinPicture.getCabin().getId());
			else ps.setNull(2, java.sql.Types.INTEGER);
			
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
				   int cabinPictureId = r.getInt( 1 );
				   if( cabinPictureId > 0 )
				   cabinPicture.setId( cabinPictureId ); // set the cabinPicture Id for this object 
				   }
		     	 }
		     }else { 
		    	 // throw new CompareCabinsException( "CabinPictureManager.store: failed to save a CabinPicture to the database" );
			 }
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		DbAccessImpl.disconnect(con);
	} //end of store
}
