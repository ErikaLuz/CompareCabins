package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CCException;

import object.Feature;
import object.User;
import object.Cabin;
import persistence.DbAccessImpl;
import persistence.FeatureManager;

public class TestFeatureManager
{
	public static void main() throws CCException
	{
		System.out.println("-----START TEST FEATURE MANAGER-----");
		System.out.println();
		
		// Testing Methods
		
			Feature feature = new Feature();
			Feature feature2 = new Feature();
			
			feature = testInsertStore();
			feature2 = testInsertStore2();
			testUpdateStore(feature);
			testRestore(feature);
			testRestore2();
			testRestoreCabinFromFeature(feature);
			testDelete(feature);
			testDelete(feature2);
		
		System.out.println("-----END TEST FEATURE MANAGER-----");
	}
	
	public static Feature testInsertStore() throws CCException
	{
		// INSERT COMPLETE FEATURE OBJECT INTO DATABASE
		
		System.out.println("1: Enter testInsertStore method");
		
			// Create new feature
		
				Feature feature = new Feature("has 57 jacuzzis");
				
			// Call method to test
				
				FeatureManager.store(feature);
				
			// Test Variables 
				
				boolean featureExists = false;
				String dbFeatureString = null;
				
			// Check to see if feature is now in database
				
				Connection con = DbAccessImpl.connect();
				String query = "SELECT feature_string FROM feature WHERE id = ?";
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, feature.getId());
					ResultSet rs = ps.executeQuery();
				
					if(rs.next())
					{
						featureExists = true;
						
						dbFeatureString = rs.getString("feature_string");
						
					}
					else System.out.println("FAIL: feature inserted not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				DbAccessImpl.disconnect(con);
				
				System.out.println("Feature String: " + dbFeatureString);
				
			// Verify correct feature was found
				
				if(featureExists)
				{
					if(feature.getFeatureString().equals(dbFeatureString))
					{
						System.out.println("PASS: feature correctly inserted into database");
					}
				}
				else System.out.println("Fail: feature was not inserted into database");
				
		
		System.out.println("1: Exit testInsertStore method");
		System.out.println();
		
		return feature;
	}
	
	public static Feature testInsertStore2()
	{
		Feature feature = new Feature();
		
		return feature;
	}
	
	public static void testUpdateStore(Feature feature)
	{
		
	}
	
	public static void testRestore(Feature feature)
	{
		
	}
	
	public static void testRestore2()
	{
		
	}
	
	public static void testRestoreCabinFromFeature(Feature feature)
	{
		
	}
	
	public static void testDelete(Feature feature)
	{
		
	}
	
}
