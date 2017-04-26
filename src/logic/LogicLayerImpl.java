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
import object.Group;

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
					
					List<Feature> features = CabinManager.restoreFeaturesFromCabin(modelCabin);
					List<Availability> availabilities  = CabinManager.restoreAvailabilitiesFromCabin(modelCabin);
				
				// Retrieve reviews from cabin's rent records
				
					List<RentRecord> rentRecords = CabinManager.restoreRentRecordsFromCabin(modelCabin);
				    List<Review> reviews = new LinkedList<Review>();
					
					for(int i = 0; i < rentRecords.size(); i++)
					{
						reviews.add(RentRecordManager.restoreReviewFromRentRecord(rentRecords.get(i)));
					}
					
				// Retrieve cabin pictures and find cabin's priority picture
					
					List<CabinPicture> cabinPictures = CabinManager.restoreCabinPicturesFromCabin(modelCabin);
					CabinPicture priority = new CabinPicture();
					
					for(int i = 0; i < cabinPictures.size(); i++)
					{
						if(cabinPictures.get(i).getPriority() == 1) priority = cabinPictures.get(i);
					}
				
				// Place info in SimpleHash for ftl
				
					root.put("Cabin", cabin); // cannot be null
					
					if(user != null) 
					{
						root.put("User", user);
						root.put("Usercheck", "notNull");
					}
					else root.put("UserCheck", "null");
					
					if(amenities != null) 
					{
						root.put("Amenities", amenities);
						root.put("AmenitiesCheck", "notNull");
					}
					else root.put("AmenitiesCheck", "null");
				
					if(cabinPictures.size() > 0)
					{
						root.put("CabinPictures", cabinPictures);
						root.put("CPCheck", "notNull");
						
						if(priority.getFilePath() != null) 
						{
							root.put("PriorityPicture", priority);
							root.put("PriorityCheck", "notNull");
						}
						else root.put("PriorityCheck", "null");
					}
					else root.put("CPCheck", "null");
					
					if(features.size() > 0)
					{
						root.put("Features", features);
						root.put("FeaturesCheck", "notNull");
					}
					else root.put("FeaturesCheck", "null");
					
					if(availabilities.size() > 0)
					{
						root.put("Availabilities", availabilities);
						root.put("AvailabilitiesCheck", "notNull");
					}
					else root.put("AvailabilitiesCheck", "null");
					
					if(reviews.size() > 0)
					{
						root.put("Reviews", reviews);
						root.put("ReviewsCheck", "notNull");
					}
					else root.put("ReviewsCheck", "null");
						
			} //end of if
		
	} // end of cabinListing
	
	public static void userCabinListings (SimpleHash root, User user) throws CCException
	{
		// Retrieve cabins
		
			List<Cabin> userCabins = UserManager.restoreCabinsFromUser(user);
			List<CabinPicture> cps = new LinkedList<CabinPicture>();
			List<Amenities> amenities = new LinkedList<Amenities>();		
			
			for(int i = 0; i < userCabins.size(); i++)
			{	
				cps = CabinManager.restoreCabinPicturesFromCabin(userCabins.get(i));	
				amenities.add(CabinManager.restoreAmenitiesFromCabin(userCabins.get(i)));
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
		
			for(int i = 0; i < rr.size(); i++)
			{
				reviews.add(RentRecordManager.restoreReviewFromRentRecord(rr.get(i)));
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
	
	public static void rentCabin (SimpleHash root, Availability start, Availability end, Cabin modelCabin) throws CCException
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
				if(cp.get(i).getPriority() == 1) cp1 = cp.get(i);
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
	
	public static void pastStays(SimpleHash root, User user) throws CCException
	{
		
		// Retrieve user from database
		
			List<User> users = UserManager.restore(user);
			
			if(users.size() != 1) System.out.println("ERROR: wrong user(s) found");
			else user = users.get(0);
			
		// Retrieve user's rent records from database
			
			List<RentRecord> rr = UserManager.restoreRentRecordsFromUser(user);
			
		// Create Group Object + List
		
			List<Group> groups = new LinkedList<Group>();
			
		// Restore cabins from rent records from database and store both into group object
			
			for(int i = 0; i < rr.size(); i++)
			{
				Group group = new Group();
				group.setRentRecord(rr.get(i));
				group.setCabin(RentRecordManager.restoreCabinFromRentRecord(rr.get(i)));
				groups.add(group);
			}
			
		// Place info into SimpleHash
			
			root.put("User", user);
			root.put("PastStays", groups);
 		
	}
	
	public static void addReview(SimpleHash root, User user) throws CCException
	{
		
	}
	
}
