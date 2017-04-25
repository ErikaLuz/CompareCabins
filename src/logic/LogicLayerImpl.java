package logic;

import java.util.Calendar;
import java.util.List;
import java.util.LinkedList;
import freemarker.template.SimpleHash;

import exception.CCException;

import object.User;
import object.Cabin;
import object.Amenities;
import object.CabinPicture;
import object.Feature;
import object.Availability;
import object.RentRecord;
import object.Review;

import persistence.CabinManager;
import persistence.RentRecordManager;
import persistence.UserManager;

public class LogicLayerImpl {

	public static void register( User user ) throws CCException {
		UserManager.store(user);
	}
	
	public static User login( String username, String password ) throws CCException {
		User modelUser = new User();
		User user;
		List<User> users;
		
		modelUser.setUsername(username);
		modelUser.setPassword(password);
		
		users = UserManager.restore( modelUser );
		if( users == null || users.isEmpty() )
			return null;
		else
			user = users.get(0);
		
		return user;
		
	}
	
	public static void cabinListing ( SimpleHash root, Cabin modelCabin ) throws CCException 
	{	
		// Retrieve cabin
		
			List<Cabin> cabins = CabinManager.restore( modelCabin );
			Cabin cabin = new Cabin();
		
			if ( cabins.size() != 1) System.out.println("ERROR: incorrect cabin(s) found");
			else cabin = cabins.get(0);
			
		// Retrieve cabin info
		
			if(cabins.size() == 1)
			{
				// Retrieve cabin info
				
					User user = CabinManager.restoreUserFromCabin( modelCabin );
					Amenities amenities = CabinManager.restoreAmenitiesFromCabin( modelCabin );
					
					List<CabinPicture> cabinPictures = CabinManager.restoreCabinPicturesFromCabin(modelCabin);
					List<Feature> features = CabinManager.restoreFeaturesFromCabin(modelCabin);
					List<Availability> availabilities  = CabinManager.restoreAvailabilitiesFromCabin(modelCabin);
				
				// Retrieve reviews from cabin's rent records
				
					List<RentRecord> rentRecords = CabinManager.restoreRentRecordsFromCabin(modelCabin);
				    List<Review> reviews = new LinkedList<Review>();
					
					for(int i = 0; i < rentRecords.size(); i++)
					{
						reviews.add(RentRecordManager.restoreReviewFromRentRecord(rentRecords.get(i)));
					}
				
				// Place info in SimpleHash for ftl
				
					root.put("Cabin", cabin);
					root.put("User", user);
					root.put("Amenities", amenities);
					root.put("CabinPictures", cabinPictures);
					root.put("Features", features);
					root.put("Availabilities", availabilities);
					root.put("Reviews", reviews);	
				
			} //end of if
		
	} // end of cabinListing
	
	public static void userCabinListings (SimpleHash root, User user) throws CCException
	{
		// Retrieve cabins
		
			List<Cabin> userCabins = UserManager.restoreCabinsFromUser(user);
			List<CabinPicture> cps = new LinkedList<CabinPicture>();
			List<Amenities> amenities = new LinkedList<Amenities>();
			Amenities amenity = new Amenities();
			
			for(int i = 0; i < userCabins.size(); i++)
			{	
				cps = CabinManager.restoreCabinPicturesFromCabin(userCabins.get(i));
				amenity = CabinManager.restoreAmenitiesFromCabin(userCabins.get(i));
				amenities.add(amenity);
			}

		// Place cabins in SimpleHash
			
			root.put("userCabins", userCabins);
			root.put("cabinPictures", cps);
			root.put("cabinAmenities", amenities);
			
		// TODO: figure out how to link specific amenities and cabin pictures to their specific cabin
			
	} // end of userCabinListings
	
	public static void addCabin (Cabin cabin) throws CCException
	{
		// Add cabin in database
		
			CabinManager.store(cabin);
			
	} // end of addCabin
	
	public static void viewUserProfile (SimpleHash root, User user) throws CCException
	{
		// Restore user from database
		
			List<User> users = UserManager.restore(user);
		
			if(users.size() != 1 || users.isEmpty()) System.out.println("ERROR: wrong user found");
			else user = users.get(0);
			
		// Restore user's cabin's from database
			
			List<Cabin> cabins = UserManager.restoreCabinsFromUser(user);
			
		// Restore rent records from user's cabins
			
			List<RentRecord> rr = new LinkedList<RentRecord>();
			
			for(int i = 0; i < cabins.size(); i++)
			{
				rr = CabinManager.restoreRentRecordsFromCabin(cabins.get(i));
			}
		
		// Restore reviews from user's cabin's rent records
			
			List<Review> reviews = new LinkedList<Review>();
			Review review = new Review();
		
			for(int i = 0; i < rr.size(); i++)
			{
				review = RentRecordManager.restoreReviewFromRentRecord(rr.get(i));
				reviews.add(review);
			}
		
		// Place user and review objects in SimpleHash
		
			root.put("User", user);
			root.put("Reviews", reviews);
			
	} // end of viewUserProfile
	
	public static User updateUser(User modelUser) throws CCException
	{	
		
		// Restore user from database
			
			List<User> users = UserManager.restore(modelUser);
			User user = new User();
			
			if(users.size() != 1) return null;
			else user = users.get(0);
			
		// Compare model user to database user and change new values if needed
			
			if(!modelUser.getUsername().equals(user.getUsername()) && modelUser.getUsername() != null) 
				user.setUsername(modelUser.getUsername());
			
			if(!modelUser.getPassword().equals(user.getPassword()) && modelUser.getPassword() != null) 
				user.setPassword(modelUser.getPassword());
			
			if(!modelUser.getFirstName().equals(user.getFirstName()) && modelUser.getFirstName() != null) 
				user.setFirstName(modelUser.getFirstName());
			
			if(!modelUser.getLastName().equals(user.getLastName()) && modelUser.getLastName() != null) 
				user.setLastName(modelUser.getLastName());
			
			if(!modelUser.getEmail().equals(user.getEmail()) && modelUser.getEmail() != null)
				user.setEmail(modelUser.getEmail());
		
		// Update user in database	
			
			UserManager.store(user);
			
		return user;
			
	} // end of updateUser
	
	public static void rentcabin (SimpleHash root, Availability start, Availability end, Cabin modelCabin) throws CCException
	{
		// Variables
		
			float subtotal = -1, serviceFee = -1, totalPrice = -1;
			Calendar startDate = start.getDate(), endDate = end.getDate();
		
		// Retrieve cabin to rent
		
			List<Cabin> cabins = CabinManager.restore(modelCabin);
			Cabin cabin = new Cabin();
			
			if(cabins.size() != 1) System.out.println("Multiple cabins found");
			else cabin = cabins.get(0);
			
		// Get cabin priority picture
		
			List<CabinPicture> cp = CabinManager.restoreCabinPicturesFromCabin(modelCabin);
			CabinPicture cp1 = new CabinPicture();
			
			for(int i = 0; i < cp.size(); i++)
			{
				if(cp.get(i).getPriority() == 1)
				{
					cp1 = cp.get(i);
				}
			}
		
		// TODO: Calculate total price
			
			// pseudo-code: get prices for all availabilities between start and end date and add them all together
			// then calculate service fee + add those together to find total price
			
		// Place objects into SimpleHash
		
		root.put("Cabin", cabin);
		root.put("CabinPriorityPicture", cp1);
		root.put("StartDate", startDate);
		root.put("EndDate", endDate);
		root.put("Subtotal", subtotal);
		root.put("TotalPrice", totalPrice);
		root.put("ServiceFee", serviceFee);
		
	} // end of rentCabin
	
}
