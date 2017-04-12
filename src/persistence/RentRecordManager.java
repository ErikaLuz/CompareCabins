package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CCException;
import object.rentRecord;
import object.RentRecord;

public class RentRecordManager {

	public static void store(RentRecord rentRecord)
	{
		int rowsModified;
		String query = "INSERT INTO rent_record (total_price, start_date, end_date, cabin_id, user_id) VALUES"
				+" (?,?,?,?,?)";
		
		Connection con = DbAccessImpl.connect();
		
		try 
		{
			PreparedStatement ps = con.prepareStatement(query);
			
			// set the PreparedStatement parameters to values given from User or to sql null values if nullable	
			if(rentRecord.getTotalPrice() != -1) ps.setFloat(1, rentRecord.getTotalPrice());
			else ps.setNull(1, java.sql.Types.FLOAT);
			
			if(rentRecord.getStartDate() != null) ps.setDate(2, new java.sql.Date (rentRecord.getStartDate().getTime().getTime()));
			else ps.setNull(2, java.sql.Types.DATE);	
			
			if(rentRecord.getEndDate() != null) ps.setDate(3, new java.sql.Date (rentRecord.getEndDate().getTime().getTime()));
			else ps.setNull(3, java.sql.Types.DATE);
			
			if(rentRecord.getCabin() != null) ps.setInt(4, rentRecord.getCabin().getId());
			else ps.setNull(4, java.sql.Types.INTEGER);
			
			if(rentRecord.getUser() != null) ps.setInt(5, rentRecord.getUser().getId());
			else ps.setNull(5, java.sql.Types.INTEGER);
			
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
				   int rentRecordId = r.getInt( 1 );
				   if( rentRecordId > 0 )
				   rentRecord.setId( rentRecordId ); // set the user Id for this object 
				   }
		     	 }
		     }else { 
		    	 // throw new CompareCabinsException( "RentRecordManager.store: failed to save a rentRecord to the database" );
			 }
		
		}catch(SQLException e)
		{
			e.printStackTrace();	
		}
	} //end of store

	public static void delete(RentRecord rentRecord) throws CCException
	{
		String query = "DELETE FROM rent_record WHERE id = ?";
		PreparedStatement ps;
		Connection con = DbAccessImpl.connect();
		int rowsModified;
		
		if(rentRecord.getId() < 0) //object no in database
			return;
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1,  rentRecord.getId());
			rowsModified = ps.executeUpdate();
			
			if(rowsModified != 1)
				throw new CCException("RentRecordManager.delete: failed to delete rent record");
		}catch(SQLException e) {
			throw new CCException("RentRecordManager.delte: failed to delete rent record: " + e);
		}
		
		DbAccessImpl.disconnect(con);
		
	} //end of delete
	
}
