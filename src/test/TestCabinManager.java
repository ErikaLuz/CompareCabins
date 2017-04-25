package test;

import java.util.Calendar;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CCException;

import object.Amenities;
import object.Availability;
import object.Cabin;
import object.CabinPicture;
import object.Feature;
import object.RentRecord;
import object.User;

import persistence.AmenitiesManager;
import persistence.AvailabilityManager;
import persistence.CabinManager;
import persistence.CabinPictureManager;
import persistence.DbAccessImpl;
import persistence.FeatureManager;
import persistence.RentRecordManager;
import persistence.UserManager;

public class TestCabinManager 
{
	public static void main() throws CCException
	{
		System.out.println("-----START TEST CABIN MANAGER-----");
		System.out.println();
		
		// Testing methods:
		
			Cabin cabin = new Cabin();
			Cabin cabin2 = new Cabin();
		
			cabin = testInsertStore();
			cabin2 = testInsertStore2();
			testUpdateStore(cabin);
			testRestore(cabin);
			testRestore2();
			testRestoreUserFromCabin(cabin);
			testRestoreAmenitiesFromCabin(cabin);
			testRestoreCabinPicturesFromCabin(cabin);
			testRestoreFeaturesFromCabin(cabin);
			testRestoreAvailabilitiesFromCabin(cabin);
			testRestoreRentRecordsFromCabin(cabin);
			testDelete(cabin);
			testDelete(cabin2);
		
		System.out.println("-----END TEST CABIN MANAGER-----");
		System.out.println();
		
	} // end of main
	
	public static Cabin testInsertStore() throws CCException
	{
		// INSERT COMPLETE CABIN OBJECT INTO DATABASE
		
		System.out.println("1: Enter testInsertStore method");
		
			// Create new cabin
		
				Cabin cabin = new Cabin("address", "city", "state", "description", "title", 2, 3, 5);
				
			// Call method to test
		
				CabinManager.store(cabin);
				
			// Test Variables
				
				boolean cabinExists = false;
				String dbAdd = null, dbCity = null, dbState = null, dbDescription = null;
				int dbBedCount = -2, dbBathCount = -2, dbMaxOcc = -2;
				
			// Check to see if cabin now exists in database
				
				Connection con = DbAccessImpl.connect();
				String query = "SELECT address, city, state, description, bedroom_count, bath_count, max_occupancy FROM cabin WHERE id = ?";
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, cabin.getId());
					ResultSet rs = ps.executeQuery();
				
					if(rs.next())
					{
						cabinExists = true;
						
						dbAdd = rs.getString("address");
						dbCity = rs.getString("city");
						dbState = rs.getString("state");
						dbDescription = rs.getString("description");
						dbBedCount = rs.getInt("bedroom_count");
						dbBathCount = rs.getInt("bath_count");
						dbMaxOcc = rs.getInt("max_occupancy");
						
					}
					else System.out.println("FAIL: cabin inserted not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				DbAccessImpl.disconnect(con);
				
			// Verify correct cabin was inserted
				
				if(cabinExists)
				{
					if(dbAdd.equals("address") && dbCity.equals("city") && dbState.equals("state") && dbDescription.equals("description")
					   && dbBedCount == 2 && dbBathCount == 3 && dbMaxOcc == 5)
					{
						System.out.println("PASS: cabin inserted found in database");
					}
					else System.out.println("FAIL: incorrect cabin found or cabin inserting into database incorrectly");
				}
				else System.out.println("FAIL: cabin was not inserted into database");
		
		System.out.println("1: Exit testInsertStore method");
		System.out.println();
		
		return cabin;
	} // end of testInsertStore
	
	public static Cabin testInsertStore2() throws CCException
	{
		// INSERT PARTIAL CABIN INTO DATABASE
		
		System.out.println("2: Enter testInsertStore2 method");
		
			// Create partial cabin 
		
				Cabin cabin2 = new Cabin();
				cabin2.setAddress("Address2");
				cabin2.setBedroomCount(19);
				
			// Call method to test
				
				CabinManager.store(cabin2);
				
			// Create test variables
				
				boolean cabinExists = false;
				String dbAdd = null, dbCity = null;
				int dbBedCount = -2, dbBathCount = -2;
				
			// Check to see if cabin now exists in database
				
				Connection con = DbAccessImpl.connect();
				String query = "SELECT address, city, state, description, bedroom_count, bath_count, max_occupancy FROM cabin WHERE id = ?";
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, cabin2.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next())
					{
						cabinExists = true;
						
						dbAdd = rs.getString("address");
						dbBedCount = rs.getInt("bedroom_count");
						
						dbCity = rs.getString("city");
						if(rs.wasNull()) dbCity = "testNull";
						
						dbBathCount = rs.getInt("bath_count");
						if(rs.wasNull()) dbBathCount = -1;
					}
					else System.out.println("FAIL: user inserted not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				DbAccessImpl.disconnect(con);
				
			// Verify correct cabin was inserted
				
				if(cabinExists)
				{
					if(dbAdd.equals("Address2") && dbCity.equals("testNull") && dbBedCount == 19 && dbBathCount == -1)
					{
						System.out.println("PASS: cabin inserted found in database");
					}
					else System.out.println("FAIL: incorrect cabin found or cabin inserted into database incorrectly");
				}
				else System.out.println("FAIL: cabin was not inserted into database"); 
					
		System.out.println("2: Exit testInsertStore2 method");
		System.out.println();
		
		return cabin2;
	} // end of testInsertStore2
	
	public static void testUpdateStore(Cabin cabin) throws CCException
	{
		// UPDATES CABIN VALUES IN DATABASE
		
		System.out.println("3: Enter testUpdateStore method");
		
			// Test Variables
		
				String oldAddress = null, oldCity = null, oldState = null, oldDescription = null;
				int oldBedroomCount = -2, oldBathCount = -2, oldMaxOcc = -2;
				
				String newAddress = null, newCity = null, newState = null, newDescription = null;
				int newBedroomCount = -2, newBathCount = -2, newMaxOcc = -2;
				
			// Checking old cabin values in database
				
				Connection con = DbAccessImpl.connect();
				String query = "SELECT address, city, state, description, bedroom_count, bath_count, max_occupancy FROM cabin WHERE id = ?";
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, cabin.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next()) 
					{
						oldAddress = rs.getString("address");
						oldCity = rs.getString("city");
						oldState = rs.getString("state");
						oldDescription = rs.getString("description");
						oldBedroomCount = rs.getInt("bedroom_count");
						oldBathCount = rs.getInt("bath_count");
						oldMaxOcc = rs.getInt("max_occupancy");
					}
					else 
						System.out.println("FAIL: cabin not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
			// Setting new values except for description and max occupancy
				
				cabin.setAddress("newAddress");
				cabin.setCity("newCity");
				cabin.setState("newState");
				cabin.setBedroomCount(100);
				cabin.setBathCount(1);
				
			// Call method to store
				
				CabinManager.store(cabin);
				
			// Checking new cabin values in database
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, cabin.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next()) 
					{
						newAddress = rs.getString("address");
						newCity = rs.getString("city");
						newState = rs.getString("state");
						newDescription = rs.getString("description");
						newBedroomCount = rs.getInt("bedroom_count");
						newBathCount = rs.getInt("bath_count");
						newMaxOcc = rs.getInt("max_occupancy");
					}
					else 
						System.out.println("FAIL: cabin not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				DbAccessImpl.disconnect(con);
				
			// Verify old values have changed to new ones
				
				if(!(oldAddress.equals(newAddress)) && !(oldCity.equals(newCity)) && !(oldState.equals(newState)) &&
					(oldDescription.equals(newDescription)) && oldBedroomCount != newBedroomCount && oldBathCount != 
					newBathCount && oldMaxOcc == newMaxOcc)
				{
					if(newAddress.equals("newAddress") && newCity.equals("newCity") && newState.equals("newState") &&
					   newDescription.equals("description") && newBedroomCount == 100 && newBathCount == 1 && newMaxOcc == 5)
					{
						System.out.println("PASS: cabin successfully updated");
					}
					else System.out.println("FAIL: cabin failed to update correctly");
				}
				else System.out.println("FAIL: cabin did not update");
				
		System.out.println("3: Exit testUpdateStore method");
		System.out.println();
	}
	
	public static void testRestore(Cabin cabin) throws CCException
	{
		// SEARCH CABIN TABLE WITH CABIN OBJECT WITH ID
		
		System.out.println("4: Enter testRestore method");
		
			// Variables
		
				String address = cabin.getAddress(), city = cabin.getCity(), state = cabin.getState(), description = cabin.getDescription();
				int bedroomCount = cabin.getBedroomCount(), maxOcc = cabin.getMaxOccupancy(); 
				float bathCount = cabin.getBathCount();
				
				String dbAdd = null, dbCity = null, dbState = null, dbDescription = null;
				int dbBedroomCount = -2, dbMaxOcc = -2;
				float dbBathCount = -2;
				
			// Call method to test
				
				List<Cabin> cabins = CabinManager.restore(cabin);
				
			// Get database column values
				
				if(cabins.size() == 1)
				{
					dbAdd = cabins.get(0).getAddress();
					dbCity = cabins.get(0).getCity();
					dbState = cabins.get(0).getState();
					dbDescription = cabins.get(0).getDescription();
					dbBedroomCount = cabins.get(0).getBedroomCount();
					dbBathCount = cabins.get(0).getBathCount();
					dbMaxOcc = cabins.get(0).getMaxOccupancy();
				}
				else System.out.println("FAIL: multiple cabins with same ID found");
				
			// Verify correct cabin found
				
				if(address.equals(dbAdd) && city.equals(dbCity) && state.equals(dbState) && description.equals(dbDescription)
				   && bedroomCount == dbBedroomCount && bathCount == dbBathCount && maxOcc == dbMaxOcc)
				{
					System.out.println("PASS: cabin matches one searched for");
				}
				else System.out.println("FAIL: incorrect cabin found");
				
		System.out.println("4: Exit testRestore method");
		System.out.println();
	}
	
	public static void testRestore2() throws CCException
	{
		// SEARCH CABIN TABLE WITH CABIN WITHOUT ID
		
		System.out.println("5: Enter testRestore2 method");
		
			// Create test cabins and store into database
		
				// Control cabin
				Cabin cabin = new Cabin();
				cabin.setAddress("restore2Address");
				cabin.setDescription("amazing cabin");
				cabin.setBedroomCount(200);
		
				// Test cabins
				Cabin cabin2 = new Cabin();
				cabin2.setAddress("restore2Address");
				cabin2.setDescription("amazing cabin");
				cabin2.setBedroomCount(200);
				cabin2.setCity("restore2City");
				CabinManager.store(cabin2);
				
				Cabin cabin3 = new Cabin();
				cabin3.setAddress("restore2Address");
				cabin3.setDescription("amazing cabin");
				cabin3.setBedroomCount(200);
				cabin3.setMaxOccupancy(2);
				CabinManager.store(cabin3);
				
				Cabin decoy1 = new Cabin();
				decoy1.setAddress("restore2Address");
				decoy1.setDescription("amazing cabin");
				decoy1.setBedroomCount(300);
				CabinManager.store(decoy1);
				
				Cabin decoy2 = new Cabin();
				decoy2.setAddress("wrongAddress");
				decoy2.setDescription("amazing cabin");
				decoy2.setBedroomCount(200);
				CabinManager.store(decoy2);
				
			// Call method to test
				
				List<Cabin> cabins = CabinManager.restore(cabin);
				
			// Verify correct cabins were found
				
				if(cabins.size() == 2)
				{
					if(cabins.get(0).getCity().equals("restore2City") && cabins.get(1).getMaxOccupancy() == 2)
					{
						System.out.println("PASS: correct cabin(s) found");
					}
					else System.out.println("FAIL: wrong cabin(s) found");
				}
				else System.out.println("FAIL: incorrect number of cabins found");
				
			// Delete test cabins
				
				CabinManager.delete(cabin2);
				CabinManager.delete(cabin3);
				CabinManager.delete(decoy1);
				CabinManager.delete(decoy2);
		
		System.out.println("5: Exit testRestore2 method");
		System.out.println();
	}
	
	public static void testRestoreUserFromCabin(Cabin cabin) throws CCException
	{
		// SEARCH FOR USERS BASED ON CABIN
		
		System.out.println("6: Enter testRestoreUserFromCabin method");
		
			// Create user for cabin and store
			
				User user = new User("cabinUsername", "cabinPassword", "cabinFirst", "cabinLast", "cabinEmail");
				UserManager.store(user);
				cabin.setUser(user);
				
				CabinManager.store(cabin);
				
			// Call method to test
				
				User users = CabinManager.restoreUserFromCabin(cabin);
				
			// Verify correct user was found
				
				if(users.getId() == cabin.getUser().getId())
				{
					System.out.println("PASS: correct cabin user found");
				}
				else System.out.println("FAIL: cabin's user was not found");
		
		System.out.println("6: Exit testRestoreUserFromCabin method");
		System.out.println();
	}
	
	public static void testRestoreAmenitiesFromCabin(Cabin cabin) throws CCException
	{
		// SEARCH FOR AMENITIES BASED ON CABIN
		
		System.out.println("7: Enter testRestoreAmenitiesFromCabin method");
		
			// Create amenities for cabin and store
			
				Amenities amenity = new Amenities(true, false, true, true, false, true, true, false, true);
				AmenitiesManager.store(amenity);
				cabin.setAmenities(amenity);
			
				CabinManager.store(cabin);
		
			// Call method to test
		
				Amenities amenities = CabinManager.restoreAmenitiesFromCabin(cabin);
		
			// Verify correct amenities was found
				
				if(amenities.getId() == cabin.getAmenities().getId())
				{
					System.out.println("PASS: correct cabin amenities were found");
				}
				else System.out.println("FAIL: cabin's amenities were not found");
		
		System.out.println("7: Exit testRestoreAmenitiesFromCabin method");
		System.out.println();
	}
	
	public static void testRestoreCabinPicturesFromCabin(Cabin cabin) throws CCException
	{
		// SEARCH FOR CABIN PICTURES BASED ON CABIN
		
		System.out.println("8: Enter testRestoreCabinPicturesFromCabin method");
		
			// Create test Cabin Pictures and store into database
		
				CabinPicture cp1 = new CabinPicture();
				CabinPicture cp2 = new CabinPicture();
				
				CabinPicture decoy = new CabinPicture();
				Cabin decoyCabin = new Cabin();
				CabinManager.store(decoyCabin);
				
				cp1.setCabin(cabin);
				cp2.setCabin(cabin);
				decoy.setCabin(decoyCabin);
				
				CabinPictureManager.store(cp1);
				CabinPictureManager.store(cp2);
				CabinPictureManager.store(decoy);
				
			// Call method to test
				
				List<CabinPicture> cps = CabinManager.restoreCabinPicturesFromCabin(cabin);
			
			// Because we don't know how cabin pictures work yet can't verify cabin picture values	
				
			// Verify correct cabin pictures were found
				
				if(cps.size() == 2)
				{
					System.out.println("PASS: correct number of cabin pictures found");
				}
				else System.out.println("cabin's pictures were not found");
				
			// Delete Cabin Pictures and Cabin
				
				CabinPictureManager.delete(cp1);
				CabinPictureManager.delete(cp2);
				CabinPictureManager.delete(decoy);

				CabinManager.delete(decoyCabin);
				
		System.out.println("8: Exist testRestoreCabinPicturesFromCabin method");
		System.out.println();
	}
	
	public static void testRestoreFeaturesFromCabin(Cabin cabin) throws CCException
	{
		// SEARCH FOR FEATURES BASED ON CABIN
		
		System.out.println("9: Enter testRestoreFeaturesFromCabin method");
		
			// Create test features and store into database
		
				Feature feature1 = new Feature("feature1");
				Feature feature2 = new Feature("feature2");
				
				Feature decoy = new Feature("decoyFeature");
				Cabin decoyCabin = new Cabin();
				CabinManager.store(decoyCabin);
				
				feature1.setCabin(cabin);
				feature2.setCabin(cabin);
				decoy.setCabin(decoyCabin);
				
				FeatureManager.store(feature1);
				FeatureManager.store(feature2);
				FeatureManager.store(decoy);
				
			// Call method to test
				
				List<Feature> features = CabinManager.restoreFeaturesFromCabin(cabin);
		
			// Create model features for testing
				
				Feature modelFeature1 = new Feature();
				Feature modelFeature2 = new Feature();
				
				if(features.size() == 2)
				{	
					modelFeature1.setId(features.get(0).getId());
					modelFeature1.setFeatureString(features.get(0).getFeatureString());
					modelFeature1.setCabin(features.get(0).getCabin());
					
					modelFeature2.setId(features.get(1).getId());
					modelFeature2.setFeatureString(features.get(1).getFeatureString());
					modelFeature2.setCabin(features.get(1).getCabin());
				}
				else System.out.println("FAIL: incorrect number of features found");
				
			// Verify correct features were found
				
				if(features.size() == 2)
				{
					if(feature1.getId() == modelFeature1.getId() && feature1.getFeatureString().equals(modelFeature1.getFeatureString()))
					{
						if(feature2.getId() == modelFeature2.getId() && feature2.getFeatureString().equals(modelFeature2.getFeatureString()))
						{
							System.out.println("PASS: correct cabin features found");
						}
						else
							System.out.println("FAIL: wrong feature 2 found");
					}
					else System.out.println("FAIL: wrong feature 1 found");
				}
				else System.out.println("FAIL: incorrect number of features found");
		
			// Delete features and cabin	
				
				FeatureManager.delete(feature1);
				FeatureManager.delete(feature2);
				FeatureManager.delete(decoy);
				CabinManager.delete(decoyCabin);
		
		System.out.println("9: Exit testRestoreFeaturesFromCabin method");
		System.out.println();
	}
	
	public static void testRestoreAvailabilitiesFromCabin(Cabin cabin) throws CCException
	{	
		// SEARCH FOR AVAILABILITIES BASED ON CABIN
		
		System.out.println("10: Enter testRestoreAvailabilitiesFromCabin method");
		
			// Create test availabilities and store into database
				
				float z = 2;
				Calendar date = Calendar.getInstance();
		
				Availability availability1 = new Availability(z, date);
				Availability availability2 = new Availability(z, date);
				
				Availability decoy = new Availability(z, date);
				Cabin decoyCabin = new Cabin();
				CabinManager.store(decoyCabin);
				
				availability1.setCabin(cabin);
				availability2.setCabin(cabin);
				decoy.setCabin(decoyCabin);
				
				AvailabilityManager.store(availability1);
				AvailabilityManager.store(availability2);
				AvailabilityManager.store(decoy);
		
			// Call method to test
				
				List<Availability> availabilities = CabinManager.restoreAvailabilitiesFromCabin(cabin);
		
			// Create model availabilities for testing
				
				Availability modelAvailability1 = new Availability();
				Availability modelAvailability2 = new Availability();
				
				if(availabilities.size() == 2)
				{
					modelAvailability1.setId(availabilities.get(0).getId());
					modelAvailability1.setPrice(availabilities.get(0).getPrice());
					modelAvailability1.setDate(availabilities.get(0).getDate());
					modelAvailability1.setCabin(availabilities.get(0).getCabin());
					
					
					modelAvailability2.setId(availabilities.get(1).getId());
					modelAvailability2.setPrice(availabilities.get(1).getPrice());
					modelAvailability2.setDate(availabilities.get(1).getDate());
					modelAvailability2.setCabin(availabilities.get(1).getCabin());
				}
				else System.out.println("FAIL: incorrect number of availabilities found");
		
			// Verify correct availabilities were found
				
				if(availabilities.size() == 2)
				{
					if(availability1.getId() == modelAvailability1.getId())
					{
						System.out.println("PASS: correct cabin's availabilities were found");
					}
				}
				else System.out.println("FAIL: incorrect number of cabins found");
		
			// Delete availabilities and cabin
			
				AvailabilityManager.delete(availability1);
				AvailabilityManager.delete(availability2);
				AvailabilityManager.delete(decoy);
				CabinManager.delete(decoyCabin);
		
		System.out.println("10: Exit testRestoreAvailabilitiesFromCabin method");
		System.out.println();		
	}
	
	public static void testRestoreRentRecordsFromCabin(Cabin cabin) throws CCException
	{
		// SEARCH FOR RENT RECORDS BASED ON CABIN
		
		System.out.println("11: Enter testRestoreRentRecordsFromCabin method");
		
			// Create test rent records and store into database
		
				float z = 2;
				Calendar date = Calendar.getInstance();
		
				RentRecord rr1 = new RentRecord(z, date, date);
				RentRecord rr2 = new RentRecord(z, date, date);
				
				RentRecord decoy = new RentRecord(z, date, date);
				Cabin decoyCabin = new Cabin();
				CabinManager.store(decoyCabin);
		
				rr1.setCabin(cabin);
				rr2.setCabin(cabin);
				decoy.setCabin(decoyCabin);
				
				RentRecordManager.store(rr1);
				RentRecordManager.store(rr2);
				RentRecordManager.store(decoy);
		
			// Call method to test
				
				List<RentRecord> rentRecords = CabinManager.restoreRentRecordsFromCabin(cabin);
				
			// Create model rent records for testing
				
				RentRecord modelrr1 = new RentRecord();
				RentRecord modelrr2 = new RentRecord();
				
				if(rentRecords.size() == 2)
				{	
					modelrr1.setId(rentRecords.get(0).getId());
					modelrr1.setCabin(rentRecords.get(0).getCabin());
					modelrr1.setTotalPrice(rentRecords.get(0).getTotalPrice());
					modelrr1.setStartDate(rentRecords.get(0).getStartDate());
					modelrr1.setEndDate(rentRecords.get(0).getEndDate());
					
					modelrr2.setId(rentRecords.get(1).getId());
					modelrr2.setCabin(rentRecords.get(1).getCabin());
					modelrr2.setTotalPrice(rentRecords.get(1).getTotalPrice());
					modelrr2.setStartDate(rentRecords.get(1).getStartDate());
					modelrr2.setEndDate(rentRecords.get(1).getEndDate());
				}
		
			// Verify correct rent records were found
				
				if(rentRecords.size() == 2)
				{
					if(rr1.getId() == modelrr1.getId() && rr2.getId() == modelrr2.getId())
					{
						System.out.println("PASS: correct cabin's rent records were found");
					}
				}
				else System.out.println("FAIL: incorrect number of rent records found");
		
			// Delete rent records and cabin
				
				RentRecordManager.delete(rr1);
				RentRecordManager.delete(rr2);
				RentRecordManager.delete(decoy);
				CabinManager.delete(decoyCabin);
		
		System.out.println("11: Exit testRestoreRentRecordsFromCabin method");
		System.out.println();
	}
	
	public static void testDelete(Cabin cabin) throws CCException
	{
		// DELETE CABIN FROM DATABASE
		
		System.out.println("12: Enter testDelete method");
		
			// Get user & amenities
		
				User user = cabin.getUser();
				Amenities amenities = cabin.getAmenities();
		
			// Create restraints
		
				Calendar calendar = Calendar.getInstance(); //objects with date cannot be null or cannot delete
				float z = 2;

				Availability availability = new Availability(z, calendar); 
				availability.setCabin(cabin);
				AvailabilityManager.store(availability);
				
				CabinPicture cabinPic = new CabinPicture();
				cabinPic.setCabin(cabin);
				CabinPictureManager.store(cabinPic);
				
				Feature feature = new Feature();
				feature.setCabin(cabin);
				FeatureManager.store(feature);
				
				RentRecord rr = new RentRecord(z, calendar, calendar);
				rr.setCabin(cabin);
				RentRecordManager.store(rr);
		
			// Check if cabin exists before deleting
		
				Connection con = DbAccessImpl.connect();
				String query = "SELECT * FROM cabin WHERE id = ?";
				
				try{
				
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, cabin.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(!(rs.next())) System.out.println("FAIL: cabin to delete not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
			// Call method to test
				 
//				System.out.println("Deleting the cabin: " + cabin.getDescription());
				CabinManager.delete(cabin);
				
			// Check to see if cabin still exists in database
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, cabin.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next()) System.out.println("FAIL: cabin deleted still exists");
					else System.out.println("PASS: cabin successfully deleted");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				DbAccessImpl.disconnect(con);

			// Delete user & amenities
				
				if(user != null) UserManager.delete(user);
				if(amenities != null) AmenitiesManager.delete(amenities);
			
		System.out.println("12: Exit testDelete method");
		System.out.println();
		
	} // end of testDelete
	
}
