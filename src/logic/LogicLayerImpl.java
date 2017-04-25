package logic;

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
	
	public static void cabinListing ( SimpleHash cabinListing, Cabin modelCabin ) throws CCException 
	{
	
		// Retrieve cabin
		
		Cabin cabin = new Cabin();
		List<Cabin> cabins = CabinManager.restore( modelCabin );
		
		if( cabins == null || cabins.isEmpty() )
			System.out.println("No cabins found");
		else if (cabins.size() > 1)
			System.out.println("Multiple cabins found");
		else if (cabins.size() == 1)
			cabin = cabins.get(0);
		
		if(cabins.size() == 1)
		{
			// Retrieve cabin listing info
			
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
			
			// Place info in SimpleHash
			
				cabinListing.put("Cabin", cabin);
				cabinListing.put("User", user);
				cabinListing.put("Amenities", amenities);
				cabinListing.put("CabinPictures", cabinPictures);
				cabinListing.put("Features", features);
				cabinListing.put("Availabilities", availabilities);
				cabinListing.put("Reviews", reviews);	
			
		} //end of if
		
	} // end of cabinListing
	
	public static void userCabinListings (SimpleHash userCabinListing, User user) throws CCException
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
			
			userCabinListing.put("userCabins", userCabins);
			userCabinListing.put("cabinPictures", cps);
			userCabinListing.put("cabinAmenities", amenities);
			
		// TODO: figure out how to link specific amenities and cabin pictures to their specific cabin
			
	} // end of userCabinListings
	
	public static void addCabin (Cabin cabin) throws CCException
	{
		CabinManager.store(cabin);
	}
	
	public static void viewUserProfile (SimpleHash userProfile, User user) throws CCException
	{
		List<User> users = UserManager.restore(user);
		
		if(users.size() != 1 || users.isEmpty()) System.out.println("wrong user found");
		else user = users.get(0);
		
		List<RentRecord> rr = UserManager.restoreRentRecordsFromUser(user);
		System.out.println("Number of rent records found: " + rr.size());
		List<Review> reviews = new LinkedList<Review>();
		Review review = new Review();
		
		for(int i = 0; i < rr.size(); i++)
		{
			review = RentRecordManager.restoreReviewFromRentRecord(rr.get(i));
			reviews.add(review);
		}
		
		System.out.println("Number of reviews found: " + reviews.size());
		
		userProfile.put("User", user);
		userProfile.put("Reviews", reviews);
	}
	
}
