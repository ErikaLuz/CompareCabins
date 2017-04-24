package test;

import java.util.Calendar;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.CCException;

import object.User;
import object.Cabin;
import object.RentRecord;

import persistence.DbAccessImpl;
import persistence.UserManager;
import persistence.CabinManager;
import persistence.RentRecordManager;

public class TestUserManager 
{
	public static void main() throws CCException
	{
		System.out.println();
		System.out.println("-----START TEST USER MANAGER-----");
		System.out.println();
		
		// Testing methods:
		
			User user = new User();
			User user2 = new User();
		
			user = testInsertStore(); 
			user2 = testInsertStore2();  
			testUpdateStore(user);		
			testRestore(user);	
			testRestore2();
			testRestoreCabinsFromUser(user);			
			testRestoreRentRecordsFromUser(user);
			testDelete(user);
			testDelete(user2);
		
		System.out.println("-----END TEST USER MANAGER-----");
		System.out.println();
			
	} // end of main
	
	public static User testInsertStore () throws CCException
	{
		// INSERT COMPLETE USER OBJECT INTO DATABASE
		
		System.out.println("1: Enter testInsertStore method");
		
			// Create new user
			
				User user = new User("testUsername", "testPassword","testFirstName", "testLastName", "testEmail");	
			
			// Call method to test
				
				UserManager.store(user);
				
			// Test Variables
				
				boolean userExists = false;
				String dbUser = null, dbPass = null, dbFN = null, dbLN = null, dbEmail = null;
			
			// Check to see if user now exists in database 
			
				Connection con = DbAccessImpl.connect();
				String query = "SELECT username, password, first_name, last_name, email FROM user WHERE id = ?";
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, user.getId());
					ResultSet rs = ps.executeQuery();
				
					if(rs.next())
					{
						userExists = true;
						
						dbUser = rs.getString("username");
						dbPass = rs.getString("password");
						dbFN = rs.getString("first_name");
						dbLN = rs.getString("last_name");
						dbEmail = rs.getString("email");
						
					}
					else System.out.println("FAIL: user inserted not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				DbAccessImpl.disconnect(con);
				
			// Verify correct user was inserted
				
				if(userExists)
				{
					if(dbUser.equals("testUsername") && dbPass.equals("testPassword") && dbFN.equals("testFirstName") &&
					   dbLN.equals("testLastName") && dbEmail.equals("testEmail"))
					{
						System.out.println("PASS: user inserted found in database");
					}
					else System.out.println("FAIL: incorrect user found or user inserting into database incorrectly");
				}
			
		System.out.println("1: Exit testInsertStore method");
		System.out.println();
		
		return user;
		
	} //end of testInsertStore
	
	public static User testInsertStore2() throws CCException
	{
		// INSERT USER WITH ONLY USERNAME AND PASSWORD
		
		System.out.println("2: Enter testInsertStore2 method");
		
			// Create new user with only username and password
		
				User user2 = new User();
				user2.setUsername("testUsername2");
				user2.setPassword("testPassword2");
				
			// Call method to test
				
				UserManager.store(user2);
				
			// Test Variables
				
				boolean userExists = false;
				String dbUser = null, dbPass = null, dbFN = null, dbLN = null, dbEmail = null;
		
			// Check to see if user now exists in database
		
				Connection con = DbAccessImpl.connect();
				String query = "SELECT username, password, first_name, last_name, email FROM user WHERE id = ?";
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, user2.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next())
					{
						userExists = true;
						
						dbUser = rs.getString("username");
						dbPass = rs.getString("password");
						
						dbFN = rs.getString("first_name");
						if(rs.wasNull()) dbFN = "testNull";
						
						dbLN = rs.getString("last_name");
						if(rs.wasNull()) dbLN = "testNull";
						
						dbEmail = rs.getString("email");
						if(rs.wasNull()) dbEmail = "testNull";
					}
					else System.out.println("FAIL: user inserted not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				DbAccessImpl.disconnect(con);
			
			// Verify correct user was found
				
				if(userExists)
				{
					if(dbUser.equals("testUsername2") && (dbPass.equals("testPassword2")) && (dbFN.equals("testNull")) &&
					   dbLN.equals("testNull") && dbEmail.equals("testNull"))
					{
						System.out.println("PASS: user inserted found in database");
					}
					else System.out.println("FAIL: incorrect user found in database");
				}
		
		System.out.println("2: Exit testInsertStore2 method");
		System.out.println();
		
		return user2;
		
	} //end testInsertStore2
	
	public static void testUpdateStore(User user) throws CCException
	{
		// UPDATES USER VALUES IN DATABASE
		
		System.out.println("3: Enter testUpdateStore method");
		
			// Test Variables
			
				String oldUsername = null, oldPassword = null, oldFirstName = null, oldLastName = null, oldEmail= null;
				String newUsername = null, newPassword = null, newFirstName = null, newLastName = null, newEmail = null;
			
			// Checking old user values in database
			
				Connection con = DbAccessImpl.connect();
				String query = "SELECT username, password, first_name, last_name, email FROM user WHERE id = ?";
				
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, user.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next()) 
					{
						oldUsername = rs.getString("username");
						oldPassword = rs.getString("password");
						oldFirstName = rs.getString("first_name");
						oldLastName = rs.getString("last_name");
						oldEmail = rs.getString("email");
					}
					else 
						System.out.println("FAIL: user not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
					
			// Setting new values (except for email)		
			
				user.setUsername("newUsername");
				user.setPassword("newPassword");
				user.setFirstName("newFirstName");
				user.setLastName("newLastName");
				
			// Call method to test
				
				UserManager.store(user);
			
			// Checking new user values in database
			
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, user.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next()) 
					{
						newUsername = rs.getString("username");
						newPassword = rs.getString("password");
						newFirstName = rs.getString("first_name");
						newLastName = rs.getString("last_name");
						newEmail = rs.getString("email");
					}
					else 
						System.out.println("FAIL: user not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				DbAccessImpl.disconnect(con);
				
			// Verify old values have changed to new ones
				
				if(!(oldUsername.equals(newUsername)) && !(oldPassword.equals(newPassword)) && !(oldFirstName.equals(newFirstName)) &&
				   !(oldLastName.equals(newLastName)) && (oldEmail.equals(newEmail))) 
				{
					if(newUsername.equals("newUsername") && newPassword.equals("newPassword") && 
					   newFirstName.equals("newFirstName") && (newLastName.equals("newLastName")))
					{
						System.out.println("PASS: user successfully updated");
					}
					else System.out.println("FAIL: user failed to update correctly");
					
				}
				else
					System.out.println("FAIL: user failed to update");
		
		System.out.println("3: Exit testUpdateStore method");
		System.out.println();
		
	} //end of testUpdateStore
	
	public static void testRestore(User user) throws CCException
	{
		// SEARCH USER TABLE WITH USER OBJECT WITH ID
		
		System.out.println("4: Enter testRestore method");
		
			// Variables
			
				String username = user.getUsername(), pass = user.getPassword(), FN = user.getFirstName(), LN = user.getLastName(), email = user.getEmail();
				String dbUser = null, dbPass = null, dbFN = null, dbLN = null, dbEmail = null;
				
			// Call method to test
			
				List<User> users = UserManager.restore(user);
			
			// Get database column values
			
				if(users.size() == 1)
				{
					dbUser = users.get(0).getUsername();
					dbPass = users.get(0).getPassword();
					dbFN = users.get(0).getFirstName();
					dbLN = users.get(0).getLastName();
					dbEmail = users.get(0).getEmail();
				}
				else System.out.println("FAIL: multiple users with same ID found");
				
			// Verify correct user found
				
				if((username.equals(dbUser)) && (pass.equals(dbPass)) && (FN.equals(dbFN)) && 
				   (LN.equals(dbLN)) && email.equals(dbEmail))
					System.out.println("PASS: user matches one searched for");
				else
					System.out.println("FAIL: incorrect user found");
		
		System.out.println("4: Exit testRestore method");
		System.out.println();
		
	} //end of testRestore
	
	public static void testRestore2() throws CCException
	{
		// SEARCH USER TABLE WITH USER OBJECT WITHOUT ID
		
		System.out.println("5: Enter testRestore2 method");
		
			// Create test users and store into database
			
				// Control User
				User user = new User();
				user.setUsername("restore2User");
				user.setPassword("restore2Pass");
				user.setFirstName("CompareCabin");
				
				// Test Users
				User user2 = new User();
				user2.setUsername("restore2User");
				user2.setPassword("restore2Pass");
				user2.setFirstName("CompareCabin");
				user2.setEmail("coolEmail");
				UserManager.store(user2);
				
				User user3 = new User();
				user3.setUsername("restore2User");
				user3.setPassword("restore2Pass");
				user3.setFirstName("CompareCabin");
				user3.setLastName("coolLastName");
				UserManager.store(user3);
				
				User decoy1 = new User();
				decoy1.setUsername("restore2User");
				decoy1.setPassword("restore2Pass");
				decoy1.setFirstName("decoy1FN");
				UserManager.store(decoy1);
				
				User decoy2 = new User();
				decoy2.setUsername("decoyUsername");
				decoy2.setPassword("restore2Pass");
				decoy2.setFirstName("decoyFN");
				UserManager.store(decoy2);
			
			// Call method to test
			
				List<User> users = UserManager.restore(user);
			
			// Print out list of User objects
/*			
			for(int i = 0; i < users.size(); i++)
			{
				System.out.println("Username [" + i + "] " + users.get(i).getUsername());
				System.out.println("Password [" + i + "] " + users.get(i).getPassword());
				System.out.println("First Name [" + i + "] " + users.get(i).getFirstName());
				System.out.println("Last Name [" + i + "] " + users.get(i).getLastName());
				System.out.println("Email [" + i + "] " + users.get(i).getEmail());
	
				System.out.println();
			}
*/			
			// Verify correct users were found
			
				if(users.size() == 2)
				{
					if(users.get(0).getEmail().equals("coolEmail") && users.get(1).getLastName().equals("coolLastName"))
					{
						System.out.println("PASS: correct user(s) found");
					}
					else System.out.println("FAIL: wrong user(s) found");
				}
				else System.out.println("FAIL: wrong user(s) found");
			
			// Delete test users
			
				UserManager.delete(user2);
				UserManager.delete(user3);
				UserManager.delete(decoy1);
				UserManager.delete(decoy2);
		
		System.out.println("5: Exit testRestore2 method");
		System.out.println();
		
	} //end testRestore2
	
	public static void testRestoreCabinsFromUser(User user) throws CCException
	{
		// SEARCH FOR CABINS BASED ON USER
		
		System.out.println("6: Enter testRestoreCabinsFromUser method");
		
			// Create test cabins and store into database
			
				Cabin cabin1 = new Cabin("address1", "city1", "state1", "description1", 1, 2, 5);
				Cabin cabin2 = new Cabin("address2", "city2", "state2", "description2", 4, 5, 6);
				
				Cabin decoyCabin = new Cabin("address3", "city3", "state3", "description3", 0, 0, 0);
				User decoyUser = new User("User", "Pass", "FN", "LN", "Email");
				UserManager.store(decoyUser);
				
				cabin1.setUser(user);
				cabin2.setUser(user);
				decoyCabin.setUser(decoyUser);
				
				CabinManager.store(cabin1);
				CabinManager.store(cabin2);
				CabinManager.store(decoyCabin);
			
			// Call method to test
			
				List<Cabin> cabins = UserManager.restoreCabinsFromUser(user);
				
			// Create model cabins for testing
				
				Cabin modelCabin1 = new Cabin();
				Cabin modelCabin2 = new Cabin();
		
				if(cabins.size() == 2)
				{
					// Cabin1
					modelCabin1.setId(cabins.get(0).getId());
					modelCabin1.setAddress(cabins.get(0).getAddress());
					modelCabin1.setCity(cabins.get(0).getCity());	
					modelCabin1.setState(cabins.get(0).getState());
					modelCabin1.setDescription(cabins.get(0).getDescription());
					modelCabin1.setBedroomCount(cabins.get(0).getBedroomCount());
					modelCabin1.setBathCount(cabins.get(0).getBathCount());
					modelCabin1.setMaxOccupancy(cabins.get(0).getMaxOccupancy());
					
					// Cabin2
					modelCabin2.setId(cabins.get(1).getId());
					modelCabin2.setAddress(cabins.get(1).getAddress());
					modelCabin2.setCity(cabins.get(1).getCity());	
					modelCabin2.setState(cabins.get(1).getState());
					modelCabin2.setDescription(cabins.get(1).getDescription());
					modelCabin2.setBedroomCount(cabins.get(1).getBedroomCount());
					modelCabin2.setBathCount(cabins.get(1).getBathCount());
					modelCabin2.setMaxOccupancy(cabins.get(1).getMaxOccupancy());
				
				} else System.out.println("FAIL: incorrect number of cabins found");
		
			// Verify correct cabins were found
			
				if(cabins.size() == 2)
				{
					if(cabin1.getId() == modelCabin1.getId() && cabin2.getId() == modelCabin2.getId())
					{
						System.out.println("PASS: user's correct cabins found");
						
					}else System.out.println("FAIL: wrong cabins found");
				}
				else System.out.println("FAIL: wrong cabins found");
				
			// Delete Cabins & User
				
				CabinManager.delete(cabin1);
				CabinManager.delete(cabin2);
				CabinManager.delete(decoyCabin);
				UserManager.delete(decoyUser);
		
		System.out.println("6: Exit testRestoreCabinsFromUser method");
		System.out.println();
	
	} //end of testRestoreCabinsFromUser
	
	public static void testRestoreRentRecordsFromUser(User user) throws CCException
	{	
		// SEARCH FOR RENT RECORDS BASED ON USER
		
		System.out.println("7: Enter testRestoreRentRecordsFromUser method");
		
			// Creating and storing rent records in database 
			
				Calendar calendar = Calendar.getInstance();
				float z = 2;
				float y = 3;
				
				RentRecord rr1 = new RentRecord(z, calendar, calendar);
				RentRecord rr2 = new RentRecord(y, calendar, calendar);
				
				RentRecord decoyRentRecord = new RentRecord(z, calendar, calendar);
				User decoyUser = new User("decoyUser", "decoyPass", "FN", "LN", "Email");
				UserManager.store(decoyUser);
				
				rr1.setUser(user);
				rr2.setUser(user);
				decoyRentRecord.setUser(decoyUser);
				
				RentRecordManager.store(rr1);
				RentRecordManager.store(rr2);
				RentRecordManager.store(decoyRentRecord);
			
			// Call method to test
			
				List<RentRecord> rentRecords = UserManager.restoreRentRecordsFromUser(user);
			
			//Print out list of Rent Record 
/*			
				System.out.println("Rent Record Objects:");
				
				for(int i = 0; i < rentRecords.size(); i++)
				{
					System.out.println("Id [" + i + "] " + rentRecords.get(i).getId());
					System.out.println("Price [" + i + "] " + rentRecords.get(i).getTotalPrice());
					System.out.println("Start Date [" + i + "] " + rentRecords.get(i).getStartDate());
					System.out.println("End Date [" + i + "] " + rentRecords.get(i).getEndDate());
			
					System.out.println();
				}
*/		
				
			// Verify correct rent records were found
				
				if(rentRecords.size() == 2)
				{
					if(rr1.getTotalPrice() == rentRecords.get(0).getTotalPrice() && rr2.getTotalPrice() == rentRecords.get(1).getTotalPrice())
					{
						System.out.println("PASS: user's correct rent records were found");
					} else System.out.println("FAIL: incorrect rent records found");
				}
				else System.out.println("FAIL: incorrect number of rent records found");
				
			// Delete rent records and users
				
				RentRecordManager.delete(rr1);
				RentRecordManager.delete(rr2);
				RentRecordManager.delete(decoyRentRecord);
				UserManager.delete(decoyUser);
			
		System.out.println("7: Exit testRestoreRentRecordsFromUser method");
		System.out.println();
		
	} //end of testRestoreRentRecordsFromUser
	
	public static void testDelete(User user) throws CCException
	{	
		// DELETE USER FROM DATABASE
		
		System.out.println("8: Enter testDelete method");
		
			// Create restraints
		
				Cabin cabin = new Cabin("add", "city", "state", "great cabin", 3 ,4, 5);
				cabin.setUser(user);
				
				Calendar calendar = Calendar.getInstance();
				float z = 2;
				RentRecord rentRecord = new RentRecord(z, calendar, calendar);
				rentRecord.setUser(user);
				
				CabinManager.store(cabin);
				RentRecordManager.store(rentRecord);
		
			// Check if user exists before deleting
			
				Connection con = DbAccessImpl.connect();
				String query = "SELECT * FROM user WHERE id = ?";
				
				try{
				
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, user.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(!(rs.next())) System.out.println("FAIL: user to delete not found in database");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
			
			// Call method to test
			 
//				System.out.println("Deleting the user: " + user.getUsername());
				UserManager.delete(user);
			
			// Check to see if user still exists in the database
			
				try{
					
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, user.getId());
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next()) System.out.println("FAIL: user deleted still exists");
					else System.out.println("PASS: user successfully deleted");
				
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				DbAccessImpl.disconnect(con);
		
		System.out.println("8: Exit testDelete method");
		System.out.println();
			
	} //end of testDelete
}
