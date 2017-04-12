package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CCException;
import object.feature;
import object.Feature;

public class FeatureManager {

	public static void store(Feature feature)
	{
		int rowsModified;
		String query = "INSERT INTO user (username, password, first_name, last_name, email) VALUES"
				+" (?,?,?,?,?)";
		
		Connection con = DbAccessImpl.connect();
		
		try 
		{
			PreparedStatement ps = con.prepareStatement(query);
			
			// set the PreparedStatement parameters to values given from User or to sql null values if nullable
			if(feature.getFeatureString() != null) ps.setString(1, feature.getFeatureString());
			else ps.setNull(1, java.sql.Types.VARCHAR);
			
			if(feature.getCabin() != null) ps.setInt(2, feature.getCabin().getId());
			else ps.setNull(1, java.sql.Types.INTEGER);
			
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
				   int featureId = r.getInt( 1 );
				   if( featureId > 0 )
				   feature.setId( featureId ); // set the user Id for this object 
				   }
		     	 }
		     }else { 
		    	 // throw new CompareCabinsException( "FeatureManager.store: failed to save a Feature to the database" );
			 }
				
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		DbAccessImpl.disconnect(con);
	} //end of store
	
	public static void delete(Feature feature) throws CCException
	{
		String query = "DELETE FROM feature WHERE id = ?";
		PreparedStatement ps;
		Connection con = DbAccessImpl.connect();
		int rowsModified;
		
		if(feature.getId() < 0) //object no in database
			return;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,  feature.getId());
			rowsModified = ps.executeUpdate();
			
			if(rowsModified != 1)
				throw new CCException("FeatureManager.delete: failed to delete feature");
		}catch(SQLException e) {
			throw new CCException("FeatureManager.delte: failed to delete feature: " + e);
		}
		
		DbAccessImpl.disconnect(con);
		
	} //end of delete
	
}