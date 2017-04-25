package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
			
			feature = testInsertStore();
			testUpdateStore(feature);
			testRestore(feature);
			testRestore2();
			testRestoreCabinFromFeature(feature);
			testDelete(feature);
		
		System.out.println("-----END TEST FEATURE MANAGER-----");
		System.out.println();
	}
	
	public static Feature testInsertStore() throws CCException
	{
		// INSERT FEATURE OBJECT INTO DATABASE
		
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
				else System.out.println("FAIL: feature was not inserted into database");
				
		
		System.out.println("1: Exit testInsertStore method");
		System.out.println();
		
		return feature;
	}
	
	public static void testUpdateStore(Feature feature) throws CCException
	{
		// UPDATE FEATURE VALUES IN DATABASE
		
		System.out.println("2: Enter testUpdateStore method");
		
			// Test Variables
		
				String oldFeatureString = null, newFeatureString = null;
				
			// Checking old feature values in database
				
				Connection con = DbAccessImpl.connect();
				String query = "SELECT feature_string from features WHERE id = ?";
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, feature.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next()) 
					{
						oldFeatureString = rs.getString("feature_string");
					}
					else 
						System.out.println("FAIL: feature not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
			// Setting new values
				
				feature.setFeatureString("has 100 jacuzzis");
				
			// Call method to store
				
				FeatureManager.store(feature);
		
			// Checking new feature values in database
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, feature.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next()) 
					{
						newFeatureString = rs.getString("feature_string");
					}
					else 
						System.out.println("FAIL: feature not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
			// Verify old values have changed to new ones
				
				if(!oldFeatureString.equals(newFeatureString))
				{
					if(newFeatureString.equals("has 100 jacuzzis"))
						System.out.println("PASS: feature correctly updated");
				}
				else System.out.println("FAIL: feature was not updated");
				
		System.out.println("2: Exit testUpdateStore method");
		System.out.println();
	}
	
	public static void testRestore(Feature feature) throws CCException
	{
		// SEARCH FEATURE TABLE WITH FEATURE OBJECT WITH ID
		
		System.out.println("3: Enter testRestore method");
		
			// Variables
		
				String featureString = feature.getFeatureString();
				String dbFeatureString = null;
		
			// Call method to test
				
				List<Feature> features = FeatureManager.restore(feature);
		
			// Get database column values
				
				if(features.size() == 1)
				{
					
				}
				else System.out.println("FAIL: multiple features with same ID found");
		
			// Verify correct feature found
				
				if(features.size() == 1)
				{
					
				}
		
		System.out.println("3: Exit testRestore method");
		System.out.println();
	}
	
	public static void testRestore2() throws CCException
	{
		System.out.println("4: Enter testRestore2 method");
	
		System.out.println("4: Exit testRestore2 method");
		System.out.println();
	}
	
	public static void testRestoreCabinFromFeature(Feature feature) throws CCException
	{
		System.out.println("5: Enter testRestoreCabinFromFeature method");
	
		System.out.println("5: Exit testRestoreCabinFromFeature method");
		System.out.println();
	}
	
	public static void testDelete(Feature feature) throws CCException
	{
		System.out.println("6: Enter testDelete method");
		
		System.out.println("6: Exit testDelete method");
		System.out.println();
	}
	
}
