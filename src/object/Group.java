package object;

import java.util.List;

public class Group {
	
	// VARIABLES
	
		// Objects

		private Amenities amenities;
		private Availability availability;
		private Cabin cabin;
		private CabinPicture cabinPicture;
		private Feature feature;
		private RentRecord rentRecord;
		private Review review;
		private User user;
	
		// List of objects
		
		private List<Amenities> amenitiesList;
		private List<Availability> availabilityList;
		private List<Cabin> cabinList;
		private List<CabinPicture> cabinPictureList;
		private List<Feature> featureList;
		private List<RentRecord> rentRecordList;
		private List<Review> reviewList;
		private List<User> userList;
	
	// CONSTRUCTOR	
		
		public Group()
		{
			amenities = null;
			availability = null;
			cabin = null;
			cabinPicture = null;
			feature = null;
			rentRecord = null;
			review = null;
			user = null;
			
			amenitiesList = null;
			availabilityList = null;
			cabinList = null;
			cabinPictureList = null;
			featureList = null;
			rentRecordList = null;
			reviewList = null;
			userList = null;
		}
	
	// OBJECT GETTER AND SETTERS
		
		// Amenities getter and setter
	
			public Amenities getAmenities()
			{
				return amenities;
			}
			
			public void setAmenities(Amenities amenities)
			{
				this.amenities = amenities;
			}
		
		// Availability getter and setter
			
			public Availability getAvailability()
			{
				return availability;
			}
			
			public void setAvailability(Availability availability)
			{
				this.availability = availability;
			}
			
		// Cabin getter and setter
			
			public Cabin getCabin()
			{
				return cabin;
			}
			
			public void setCabin(Cabin cabin)
			{
				this.cabin = cabin;
			}
			
		// Cabin Picture getter and setter
			
			public CabinPicture getCabinPicture()
			{
				return cabinPicture;
			}
			
			public void setCabinPicture(CabinPicture cabinPicture)
			{
				this.cabinPicture = cabinPicture;
			}
			
		// Feature getter and setter
			
			public Feature getFeature()
			{
				return feature;
			}
			
			public void setFeature(Feature feature)
			{
				this.feature = feature;
			}			 
			
		// Rent Record getter and setter
		
			public RentRecord getRentRecord()
			{
				return rentRecord;
			}
			
			public void setRentRecord(RentRecord rentRecord)
			{
				this.rentRecord = rentRecord;
			}		
			
		// Review getter and setter
			
			public Review getReview()
			{
				return review;
			}
			
			public void setReview(Review review)
			{
				this.review = review;
			}
			
		// User getter and setter
			
			public User getUser()
			{
				return user;
			}
			
			public void setUser(User user)
			{
				this.user = user;
			}
	
	// OBJECT LIST GETTERS AND SETTERS
		
		// Amenities List getter and setter	
			
			public List<Amenities> getAmenitiesList()
			{
				return amenitiesList;
			}
			
			public void setAmenitiesList(List<Amenities> amenitiesList)
			{
				this.amenitiesList = amenitiesList;
			}
			
		// Availability List getter and setter
			
			public List<Availability> getAvailabilityList()
			{
				return availabilityList;
			}
			
			public void setAvailabilityList(List<Availability> availabilityList)
			{
				this.availabilityList = availabilityList;
			}
		
		// Cabin getter List and setter
			
			public List<Cabin> getCabinList()
			{
				return cabinList;
			}
			
			public void setCabinList(List<Cabin> cabinList)
			{
				this.cabinList = cabinList;
			}
			
		// Cabin Picture List getter and setter
			
			public List<CabinPicture> getCabinPictureList()
			{
				return cabinPictureList;
			}
			
			public void setCabinPictureList(List<CabinPicture> cabinPictureList)
			{
				this.cabinPictureList = cabinPictureList;
			}
			
		// Feature getter List and setter
			
			public List<Feature> getFeatureList()
			{
				return featureList;
			}
			
			public void setFeatureList(List<Feature> featureList)
			{
				this.featureList = featureList;
			}
			
		// Rent Record List getter and setter
			
			public List<RentRecord> getRentRecordList()
			{
				return rentRecordList;
			}
			
			public void setRentRecordList(List<RentRecord> rentRecordList)
			{
				this.rentRecordList = rentRecordList;
			}
			
		// Review getter List and setter
			
			public List<Review> getReviewList()
			{
				return reviewList;
			}
			
			public void setReviewList(List<Review> reviewList)
			{
				this.reviewList = reviewList;
			}
			
		// User getter List and setter
		
			public List<User> getUserList()
			{
				return userList;
			}
			
			public void setUserList(List<User> userList)
			{
				this.userList = userList;
			}	
}
