package logic;

import java.text.SimpleDateFormat;
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
import persistence.ReviewManager;
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
		// Create Group List
		
			List<Group> groups = new LinkedList<Group>();
		
		// Retrieve cabins
		
			List<Cabin> userCabins = UserManager.restoreCabinsFromUser(user);
			
			for(int i = 0; i < userCabins.size(); i++)
			{	
				Group group = new Group();
				group.setCabin(userCabins.get(i));
				
				List<CabinPicture> cps = CabinManager.restoreCabinPicturesFromCabin(userCabins.get(i));
				group.setCabinPictureList(cps);
				
				// Get cabin's priority picture
				
					for(int j = 0; j < cps.size(); j++)
					{
						if(cps.get(j).getPriority() == 1) group.setCabinPicture(cps.get(j));
					}
				
				group.setAmenities(CabinManager.restoreAmenitiesFromCabin(userCabins.get(i))); 
				
				groups.add(group);
			}

		// Place group of cabins in SimpleHash
			
			root.put("Groups", groups);
			
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
			if(reviews.size() > 0)
			{
				root.put("Reviews", reviews);
				root.put("ReviewsCheck", "notNull");
			}
			else root.put("ReviewsCheck", "null");
			
			
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
			
		// Restore cabins, cabin pictures, and rent records from database and store into group object
			
			for(int i = 0; i < rr.size(); i++)
			{
				Group group = new Group();
				group.setRentRecord(rr.get(i));
				
				Calendar start = rr.get(i).getStartDate();
				Calendar end = rr.get(i).getEndDate();
			
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
				
				String startDate = sdf.format(start.getTime()); 
				String endDate = sdf.format(end.getTime()); 
				
				root.put("StartDate", startDate);
				root.put("EndDate", endDate);
				
				Cabin cabin = RentRecordManager.restoreCabinFromRentRecord(rr.get(i));
				group.setCabin(cabin);
				
				List<CabinPicture> cp = CabinManager.restoreCabinPicturesFromCabin(cabin);
				group.setCabinPictureList(cp);
				
				// Retrieve cabin's priority picture
				
					for(int j = 0; j < cp.size(); j++)
					{
						if(cp.get(j).getPriority() == 1) group.setCabinPicture(cp.get(j));
					}
				
				groups.add(group);
			}
			
		// Place info into SimpleHash
			
			root.put("User", user);
			root.put("PastStays", groups);
 		
	}
	
	public static void goToReview(SimpleHash root, RentRecord modelRR) throws CCException
	{
		// Retrieve rent record from database and place in root
		
			List<RentRecord> rrs = RentRecordManager.restore(modelRR);
			RentRecord rr = new RentRecord();
			
			if(rrs.size() != 1) System.out.println("ERROR: wrong rent record(s) found");
			else rr = rrs.get(0);
			root.put("RentRecord", rr);
			
			Calendar start = rr.getStartDate();
			Calendar end = rr.getEndDate();
		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
			
			String startDate = sdf.format(start.getTime()); 
			String endDate = sdf.format(end.getTime()); 
			
			root.put("StartDate", startDate);
			root.put("EndDate", endDate);
			
		// Retrieve Cabin from RentRecord
			
			Cabin cabin = RentRecordManager.restoreCabinFromRentRecord(rr);
			
		// Get cabin info - places info in root
			
			cabinListing(root, cabin);
	}
	
	public static void addReview(SimpleHash root, RentRecord modelRentRecord, Review modelReview) throws CCException
	{
		// Retrieve review's rent record from database
		
			List<RentRecord> rr = RentRecordManager.restore(modelRentRecord);
			RentRecord rentRecord  = new RentRecord();
			
			if(rr.size() != 1) System.out.println("ERROR: wrong rent record(s) found");
			else rentRecord = rr.get(0);
			
			// Format start and end date
			
				Calendar start = rentRecord.getStartDate();
				Calendar end = rentRecord.getEndDate();
			
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
				
				String startDate = sdf.format(start.getTime()); 
				String endDate = sdf.format(end.getTime()); 
			
		// Assign rent record found to modelReview
			
			modelReview.setRentRecord(rentRecord);
		
		// Store review into database
		
			ReviewManager.store(modelReview);
			
		// Store rent record and review into SimpleHash
			
			root.put("RentRecord", rentRecord);
			root.put("StartDate", startDate);
			root.put("EndDate", endDate);
			root.put("Review", modelReview);
	}
	
}
