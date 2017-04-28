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

import persistence.AmenitiesManager;
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
	
	public static Group cabinListing ( Cabin modelCabin ) throws CCException 
	{	
		// Create group
		
			Group group = new Group();
		
		// Retrieve cabin
		
			List<Cabin> cabins = CabinManager.restore( modelCabin );
			Cabin cabin = new Cabin();
		
			if ( cabins.size() != 1) System.out.println("ERROR: incorrect cabin(s) found");
			else cabin = cabins.get(0);
			group.setCabin(cabin);
			
		// Retrieve cabin info
		
			if(cabins.size() == 1)
			{
				// Retrieve cabin info
				
					User user = CabinManager.restoreUserFromCabin( modelCabin );
					group.setUser(user);
					
					Amenities amenities = CabinManager.restoreAmenitiesFromCabin( modelCabin );
					group.setAmenities(amenities);
					
					List<Feature> features = CabinManager.restoreFeaturesFromCabin(modelCabin);
					group.setFeatureList(features);
					
					List<Availability> availabilities  = CabinManager.restoreAvailabilitiesFromCabin(modelCabin);
					group.setAvailabilityList(availabilities);
				
				// Retrieve reviews from cabin's rent records
				
					List<RentRecord> rentRecords = CabinManager.restoreRentRecordsFromCabin(modelCabin);
					group.setRentRecordList(rentRecords);
					
				    List<Review> reviews = new LinkedList<Review>();
					
					for(int i = 0; i < rentRecords.size(); i++)
					{
						reviews.add(RentRecordManager.restoreReviewFromRentRecord(rentRecords.get(i)));
					}
					
					group.setReviewList(reviews);
					
				// Retrieve cabin pictures and find cabin's priority picture
					
					List<CabinPicture> cabinPictures = CabinManager.restoreCabinPicturesFromCabin(modelCabin);
					
					
					CabinPicture priority = new CabinPicture();
					
					for(int i = 0; i < cabinPictures.size(); i++)
					{
						if(cabinPictures.get(i).getPriority() == 1) priority = cabinPictures.get(i);
					}
					
					group.setCabinPicture(priority);
					
					List<CabinPicture> cabinPicturesNoPriority = getCabinPicturesWithoutPriority(modelCabin);
					group.setCabinPictureList(cabinPicturesNoPriority);
		
						
			} //end of if
			
		// Return group
			
			return group;
		
	} // end of cabinListing
	
	public static List<Group> ownersCabins ( User user ) throws CCException
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
				
				if(cps.size() == 0 || cps == null) group.setHasCabinPictures(false);
				else group.setHasCabinPictures(true);
				
				group.setAmenities(CabinManager.restoreAmenitiesFromCabin(userCabins.get(i))); 
				
				
				groups.add(group);
			}

		// Return groups
			
			return groups;
			
	} // end of userCabinListings
	
	public static void addCabin (Cabin cabin) throws CCException
	{
		// Add cabin in database
		
			CabinManager.store(cabin);
			
	} // end of addCabin
	
	public static Group prepareEditCabin( Cabin modelCabin ) throws CCException
	{
		// Create group object 
		
			Group group = new Group();
		
		// Retrieve cabin from the database + add to group
		
			List<Cabin> cabins = CabinManager.restore(modelCabin);
			Cabin cabin = new Cabin();
			
			if(cabins.size() != 1) System.out.println("ERROR: wrong cabin(s) found");
			else cabin = cabins.get(0);
			
			group.setCabin(cabin);
			
		// Retrieve cabin amenities from database + add to group
			
			Amenities amenity = CabinManager.restoreAmenitiesFromCabin(cabin);
			group.setAmenities(amenity);
			
		// Retrieve cabin pictures from database + add to group
			
			List<CabinPicture> cp = CabinManager.restoreCabinPicturesFromCabin(cabin);
			System.out.println("cabin picture list size: " + cp.size());
			group.setCabinPictureList(cp);
			
		// Retrieve cabin priority picture + add to group
			
			CabinPicture priorityPicture = new CabinPicture();
			
			for(int i = 0; i < cp.size(); i++)
			{
				if(cp.get(i).getPriority() == 1) priorityPicture = cp.get(i);
			}
			
			group.setCabinPicture(priorityPicture);
			
		// Retrieve cabin features from database + add to group
			
			List<Feature> feature = CabinManager.restoreFeaturesFromCabin(cabin);
			System.out.println("Features: " + feature.size());
			group.setFeatureList(feature);
			
		// Retrieve cabin availabilities + add to group
			
			List<Availability> availability = CabinManager.restoreAvailabilitiesFromCabin(cabin);
			group.setAvailabilityList(availability);
			
		// Return group
			
			return group;
			
	} // end of prepareEditCabin

	public static Group updateCabin(Cabin modelCabin, Amenities modelAmenities) throws CCException
	{
		// Create Group 
		
			Group group = new Group();
			
		// Restore cabin from database
		
			List<Cabin> cabins = CabinManager.restore(modelCabin);
			Cabin cabin = new Cabin();
			
			if(cabins.size() != 1) System.out.println("ERROR: wrong cabin(s) found");
			else cabin = cabins.get(0);
			
			// Restore amenities from database
			
			Amenities amenities = CabinManager.restoreAmenitiesFromCabin(cabin);
			
		if(modelAmenities != null) 
		{	
			// Compare model amenities to database amenities and change to new values if needed
				
				if(modelAmenities.isHasLake() != amenities.isHasLake())
					amenities.setHasLake(modelAmenities.isHasLake());
				
				if(modelAmenities.isHasRiver() != amenities.isHasRiver())
					amenities.setHasRiver(modelAmenities.isHasRiver());
				
				if(modelAmenities.isHasPool() != amenities.isHasPool())
					amenities.setHasPool(modelAmenities.isHasPool());
				
				if(modelAmenities.isHasHotTub() != amenities.isHasHotTub())
					amenities.setHasHotTub(modelAmenities.isHasHotTub());
				
				if(modelAmenities.isHasWifi() != amenities.isHasWifi())
					amenities.setHasWifi(modelAmenities.isHasWifi());
				
				if(modelAmenities.isHasAirConditioning() != amenities.isHasAirConditioning())
					amenities.setHasAirConditioning(modelAmenities.isHasAirConditioning());
				
				if(modelAmenities.isHasWasherDryer() != amenities.isHasWasherDryer())
					amenities.setHasWasherDryer(modelAmenities.isHasWasherDryer());
				
				if(modelAmenities.isAllowsPets() != amenities.isAllowsPets())
					amenities.setAllowsPets(modelAmenities.isAllowsPets());
				
				if(modelAmenities.isAllowsSmoking() != amenities.isAllowsSmoking())
					amenities.setAllowsSmoking(modelAmenities.isAllowsSmoking());
				
		} // end of amenities if statement
		
		// Update amenities in database
		
			AmenitiesManager.store(amenities);
			group.setAmenities(amenities);
			
		// Compare model cabin to database cabin and change to new values if needed
			
			if(!modelCabin.getAddress().equals(cabin.getAddress()) && modelCabin.getAddress() != null)
				cabin.setAddress(modelCabin.getAddress());
			
			if(!modelCabin.getCity().equals(cabin.getCity()) && modelCabin.getCity() != null)
				cabin.setCity(modelCabin.getCity());
			
			if(!modelCabin.getState().equals(cabin.getState()) && modelCabin.getState() != null)
				cabin.setState(modelCabin.getState());
			
			if(!modelCabin.getDescription().equals(cabin.getDescription()) && modelCabin.getDescription() != null)
				cabin.setDescription(modelCabin.getDescription());
			
			if(!modelCabin.getTitle().equals(cabin.getTitle()) && modelCabin.getTitle() != null)
				cabin.setTitle(modelCabin.getTitle());
			
			if(modelCabin.getBedroomCount() != cabin.getBedroomCount() && modelCabin.getBedroomCount() > -1)
				cabin.setBedroomCount(modelCabin.getBedroomCount());
			
			if(modelCabin.getBathCount() != cabin.getBathCount() && modelCabin.getBathCount() > -1)
				cabin.setBathCount(modelCabin.getBathCount());
			
			if(modelCabin.getMaxOccupancy() != cabin.getMaxOccupancy() && modelCabin.getMaxOccupancy() > -1)
				cabin.setMaxOccupancy(modelCabin.getMaxOccupancy());	
			
		// Update cabin in database
			
			CabinManager.store(cabin);
			group.setCabin(cabin);
			
		// Return group
			
			return group;
	}

	public static void deleteCabin(Cabin cabin) throws CCException
	{
		// Delete cabin from database
		
			CabinManager.delete(cabin);
	}

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
	
	
	/**
	 * Retrieves a list of the user's past stays
	 * 
	 * @param modelUser		user from which rent records are pulled
	 * @return				list of user's past stays 
	 * @throws CCException
	 */
	public static List<Group> pastStays( User modelUser ) throws CCException
	{	
		// Retrieve user from database
		
			List<User> users = UserManager.restore(modelUser);
			
			if(users.size() != 1) System.out.println("ERROR: wrong user(s) found");
			else modelUser = users.get(0);
			
		// Retrieve user's rent records from database
			
			List<RentRecord> rr = UserManager.restoreRentRecordsFromUser(modelUser);
			
		// Create Group List
		
			List<Group> groups = new LinkedList<Group>();
			
		// Restore cabins, cabin pictures, and rent records from database and store into group objects
			
			for(int i = 0; i < rr.size(); i++)
			{
				Group group = new Group();
				
				group.setUser(modelUser);
				group.setRentRecord(rr.get(i));
				
				// Get start + end dates, then format 
				
					Calendar start = rr.get(i).getStartDate();
					Calendar end = rr.get(i).getEndDate();
				
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
					
					String startDate = sdf.format(start.getTime()); 
					String endDate = sdf.format(end.getTime()); 
					
					group.setStartDate(startDate);
					group.setEndDate(endDate);
					
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
			
			return groups;
			
	} // end of pastStays
	
	/**
	 * Retrieves info on a user's specific rent record
	 * 
	 * @param modelRR	rent record from which info is pulled
	 * @return	group containing the rent record and the rent record's cabin
	 * @throws CCException
	 */
	public static Group goToReview( RentRecord modelRR ) throws CCException
	{
		// Create group object
		
			Group group = new Group();
		
		// Retrieve rent record from database
		
			List<RentRecord> rrs = RentRecordManager.restore(modelRR);
			RentRecord rr = new RentRecord();
			
			if(rrs.size() != 1) System.out.println("ERROR: wrong rent record(s) found");
			else rr = rrs.get(0);
			group.setRentRecord(rr);
			
		// Get start + end dates, then format 
			
			Calendar start = rr.getStartDate();
			Calendar end = rr.getEndDate();
		
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
			
			String startDate = sdf.format(start.getTime()); 
			String endDate = sdf.format(end.getTime()); 
			
			group.setStartDate(startDate);
			group.setEndDate(endDate);
			
		// Retrieve cabin from rent record
			
			Cabin cabin = RentRecordManager.restoreCabinFromRentRecord(rr);
			group.setCabin(cabin);
			
		// Retrieve cabin pictures from cabin
			
			List<CabinPicture> cps = CabinManager.restoreCabinPicturesFromCabin(cabin);
			List<CabinPicture> cps2 = getCabinPicturesWithoutPriority(cabin);
			
			group.setCabinPictureList(cps2);
			
			// Get Priority Picture
			
			for(int i = 0; i < cps.size(); i++)
			{
				if(cps.get(i).getPriority() == 1) group.setCabinPicture(cps.get(i));
			}
			
		// Return group
			
			return group;
	}
	
	/**
	 * Adds review to database
	 * 
	 * @param modelRentRecord	the rent record that review belongs to 
	 * @param modelReview		review to be added to the database
	 * @return
	 * @throws CCException
	 */
	public static Group addReview(RentRecord modelRentRecord, Review modelReview) throws CCException
	{
		// Create group object
		
			Group group = new Group();
		
		// Retrieve review's rent record from database
		
			List<RentRecord> rr = RentRecordManager.restore(modelRentRecord);
			
			if(rr.size() != 1) System.out.println("ERROR: wrong rent record(s) found");
			else modelRentRecord = rr.get(0);
			group.setRentRecord(modelRentRecord);
			
			// Format start and end date
			
				Calendar start = modelRentRecord.getStartDate();
				Calendar end = modelRentRecord.getEndDate();
			
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
				
				String startDate = sdf.format(start.getTime()); 
				String endDate = sdf.format(end.getTime()); 
				
				group.setStartDate(startDate);
				group.setEndDate(endDate);
			
		// Assign rent record found to modelReview
			
			modelReview.setRentRecord(modelRentRecord);
			group.setReview(modelReview);
		
		// Store review into database
		
			ReviewManager.store(modelReview);
			
		// Return group
			
			return group;
			
	} // end of addReview
	
	public static List<CabinPicture> getCabinPicturesWithoutPriority( Cabin cabin) throws CCException {
		List<CabinPicture> cabinPictures = CabinManager.restoreCabinPicturesFromCabin(cabin);
		
		for( int i = 0; i < cabinPictures.size(); i++)
		{
			if( cabinPictures.get(i).getPriority() == 1 )
			{
				cabinPictures.remove(i);
				break;
			}
		}
		
		return cabinPictures;
	}
	
}
