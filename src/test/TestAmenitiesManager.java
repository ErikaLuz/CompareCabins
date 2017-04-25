package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CCException;

import object.Amenities;
import persistence.AmenitiesManager;
import persistence.DbAccessImpl;

public class TestAmenitiesManager 
{
	public static void main() throws CCException
	{
		System.out.println("-----START TEST AMENITIES MANAGER-----");
		System.out.println();
		
		Amenities amenities = new Amenities();
		
		amenities = testStore();
		testDelete(amenities);
		
		System.out.println("-----END TEST AMENITIES MANAGER-----");
		System.out.println();
	}
	
	public static Amenities testStore() throws CCException
	{
		// INSERT AMENITIES OBJECT INTO DATABASE
		
			// Create new amenities
		
				Amenities amenities = new Amenities(true, false, true, false, true, false, true, false, true);
				AmenitiesManager.store(amenities);
				
				Connection con = DbAccessImpl.connect();
				String query = "SELECT has_lake, has_river, has_pool, has_hot_tub, has_wifi, has_air_conditioning, has_washer_dryer, allows_pets, allows_smoking FROM amenities WHERE id = ?";
				
				boolean hasAir = true;
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, amenities.getId());
					ResultSet rs = ps.executeQuery();
				
					if(rs.next())
					{
						System.out.println("has_lake: " + rs.getBoolean("has_lake"));
						System.out.println("has_river: " + rs.getBoolean("has_river"));
						System.out.println("has_pool: " + rs.getBoolean("has_pool"));
						System.out.println("has_hot_tob: " + rs.getBoolean("has_hot_tub"));
						hasAir = rs.getBoolean("has_air_conditioning");
					}
					else System.out.println("amenities not found");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				DbAccessImpl.disconnect(con);
				
				System.out.println("has_air_conditioning: " + hasAir);
		
				System.out.println("End of testInsert");
				System.out.println();
				
		return amenities;
	}
	
	public static void testDelete(Amenities amenities)
	{
		
	}

}
