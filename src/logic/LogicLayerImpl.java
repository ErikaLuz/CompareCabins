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
	
	public static void cabinListing ( SimpleHash cabinListing, Cabin modelCabin ) throws CCException {
		
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
	}
	
	public static void userCabinListings (SimpleHash userCabinListing, User user) throws CCException
	{
		// Retrieve cabins
		
			List<Cabin> userCabins = UserManager.restoreCabinsFromUser(user);
			
			for(int i = 0; i < userCabins.size(); i++)
			{
				List<CabinPicture> cps = CabinManager.restoreCabinPicturesFromCabin(userCabins.get(i));
			}

			
		// Place cabins in SimpleHash
			
			userCabinListing.put("userCabins", userCabins);
	}
	
}
