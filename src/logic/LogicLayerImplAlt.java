package logic;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import exception.CCException;
import object.Amenities;
import object.Availability;
import object.Cabin;
import object.RentRecord;
import object.User;
import persistence.AmenitiesManager;
import persistence.AvailabilityManager;
import persistence.CabinManager;
import persistence.RentRecordManager;

public class LogicLayerImplAlt {

	public static List<Availability> findAvailabilities(int calendarMonth, int year, int cabinId ) throws CCException
	{
		Cabin cabin =  new Cabin();
		cabin.setId(cabinId);
		List<Availability> availabilities = CabinManager.restoreAvailabilitiesFromCabin( cabin );
		
		for( int i = 0, j = 0; i < availabilities.size(); i++) 
		{
			Availability availability = availabilities.get( i - j );
			if( availability.getDate().get( Calendar.MONTH ) != calendarMonth || availability.getDate().get( Calendar.YEAR ) != year ) 
			{
				availabilities.remove( i - j );
				j++;
			}
		}
		return availabilities;
	}
	public static boolean testIfDatesAvailable( List<Availability> rentDates, int cabinId ) throws CCException
	{
		List<Availability> cabinAvailabilities;
		Cabin cabin = new Cabin();
		boolean rentDatesAvailable = true;
		boolean rentDateFound = false;
		
		cabin.setId( cabinId ); 
		
		cabinAvailabilities = CabinManager.restoreAvailabilitiesFromCabin( cabin );
		
		for( int i = 0; i < rentDates.size(); i++)
		{
			Calendar rentDate = rentDates.get( i ).getDate();
			int j = 0;
			while( rentDateFound == false && j < cabinAvailabilities.size() )
			{
				if( rentDate.equals( cabinAvailabilities.get( j ).getDate() ) )
				{
					rentDateFound = true;					
				}
				j++;
			}
			if( rentDateFound == false )
			{
				rentDatesAvailable = false;
				break;
			}			
		}
		
		return rentDatesAvailable;
	}
	public static boolean testIfDatesAvailableFillAvailabilityList( List<Availability> rentDates, int cabinId, 
			List<Availability> cabinAvailabilitiesInRentPeriod ) throws CCException
	{
		System.out.println("---------------------------" + Calendar.APRIL);
		List<Availability> cabinAvailabilities;
		Cabin cabin = new Cabin();
		boolean rentDatesAvailable = true;
		boolean rentDateFound = false;
		
		cabin.setId( cabinId ); 
		
		cabinAvailabilities = CabinManager.restoreAvailabilitiesFromCabin( cabin );
		
		for( int i = 0; i < rentDates.size(); i++)
		{
			Calendar rentDate = rentDates.get( i ).getDate();
			int j = 0;
			rentDateFound = false;
			System.out.println("cabinAvailabilities size: " + cabinAvailabilities.size() );
			while( rentDateFound == false && j < cabinAvailabilities.size() )
			{
				Calendar cabinDate = cabinAvailabilities.get(j).getDate();
				Calendar a = cabinDate;
				System.out.println("rentDate: " + rentDate.get(Calendar.MONTH) + "  " + rentDate.get(Calendar.DAY_OF_MONTH)  + "  " + rentDate.get(Calendar.YEAR)
				+ "   cabAvail: " + a.get(Calendar.MONTH) + "  " + a.get(Calendar.DAY_OF_MONTH) + "  " + a.get(Calendar.YEAR) );
				if( checkDatesEqual( rentDate, cabinDate )  )
				{
					System.out.println("testIFDatesAvail: " + i );
					rentDateFound = true;
					Availability temp = cabinAvailabilities.get(j);
					temp.setCabin( cabin );
					cabinAvailabilitiesInRentPeriod.add( temp );
				}
				j++;
			}
			if( rentDateFound == false )
			{
				rentDatesAvailable = false;
				break;
			}			
		}
		
		return rentDatesAvailable;
	}
	
	public static float getTotalPrice( Availability start, Availability end, int cabinId ) throws CCException
	{
		if( start == null || end == null )
			return -1;
		List<Availability> availabilities = getDatesBetween( start.getDate(), end.getDate() );
		System.out.println( "getTotalPrice: size:" + availabilities.size() );
		Cabin model = new Cabin();
		float totalPrice = 0;
		model.setId( cabinId );
		
		LinkedList<Availability> rentDates = new LinkedList<>();
		
		if( testIfDatesAvailableFillAvailabilityList( availabilities, cabinId, rentDates) )
		{
			System.out.println("getTotalPrice: rentDates size: " + rentDates.size() );
			for( int i = 0; i < rentDates.size(); i++)
			{
				totalPrice += rentDates.get( i ).getPrice();
			}
			return totalPrice;
		}
		else
			return -1;
	}
	public static void rentCabin( Calendar startCal, Calendar endCal, int cabinId, User user ) throws CCException
	{
		List<Availability> rentDates = getDatesBetween( startCal, endCal );
		LinkedList<Availability> cabinAvailabilities = new LinkedList<>();
		boolean rentDatesAvailable = testIfDatesAvailableFillAvailabilityList( rentDates, cabinId, cabinAvailabilities );
		
		if( rentDatesAvailable )
		{
			float totalPrice = 0;
			Cabin cabin = new Cabin();
			
			cabin.setId( cabinId );
			
			for( int i = 0; i < cabinAvailabilities.size(); i++)
			{
				Availability cabinAvailability = cabinAvailabilities.get( i );

				AvailabilityManager.delete( cabinAvailability );
				
				totalPrice += cabinAvailability.getPrice();				
			}
			
			RentRecord rentRecord = new RentRecord();
			rentRecord.setTotalPrice(totalPrice);
			rentRecord.setStartDate( startCal );
			rentRecord.setEndDate( endCal );
			rentRecord.setCabin( cabin );
			rentRecord.setUser( user );
			
			RentRecordManager.store( rentRecord );
			
		}
	}
	public static LinkedList<Availability> getDatesBetween( Calendar startCal, Calendar endCal )
	{
		LinkedList<Availability> availabilities = new LinkedList<>();
		Calendar temp = Calendar.getInstance();
		temp.setTime( startCal.getTime() );
		
		while( compareCalendars( temp, endCal) <= 0 )
		{
			Availability availability = new Availability();
			Calendar cal = Calendar.getInstance();
			
			cal.setTime( temp.getTime() );
			availability.setDate( cal );
			
			availabilities.add( availability );
			
			temp.add( Calendar.DAY_OF_MONTH, 1);
		}
		
		return availabilities;
	}
	public static boolean checkDatesEqual( Calendar cal1, Calendar cal2 )
	{
		if( cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH) )
			if( cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) )
				if( cal2.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) )
					return true;
		return false;
	}
	public static int compareCalendars( Calendar cal1, Calendar cal2 )
	{
		if( cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR) )
			return -1;
		if( cal1.get(Calendar.MONTH) < cal2.get(Calendar.MONTH) )
			return -1;
		if( cal1.get( Calendar.DAY_OF_MONTH ) < cal2.get( Calendar.DAY_OF_MONTH ) )
			return -1;
		if( checkDatesEqual( cal1, cal2 ) )
			return 0;
		return 1;
	}
	public static List<Cabin> search ( Amenities amenities, Calendar startCal, Calendar endCal ) throws CCException
	{
		List<Amenities> matchedAmenities;
		List<Cabin> cabins = new LinkedList<Cabin>();
		LinkedList<Cabin> matchedCabins = new LinkedList<Cabin>();
		List<Availability> rentDates;
		LinkedList<List<Availability>> allAvailabilitiesPerRentDay = new LinkedList<List<Availability>>();
		boolean cabinFound = false;
		boolean cabinMatched = true;
		
		if( amenities == null )
		{
			cabins = CabinManager.restore(null);
		} else {					
			matchedAmenities = AmenitiesManager.restore( amenities );
			
			// fill list of cabins that match the amenities
			for( int i = 0; i < matchedAmenities.size(); i++)
			{
				Cabin cabin = AmenitiesManager.restoreCabinFromAmenities( matchedAmenities.get(i) );
				cabins.add( cabin );
			}
		}
		
		if( startCal == null || endCal == null)
			return cabins;
		
		// get list of Availabilities of each day between start and end
		rentDates = getDatesBetween( startCal, endCal );
		

		// make list of lists of Availabilities for each rent day
		for( int i = 0; i < rentDates.size(); i++ )
		{
			List<Availability> allAvailabilitiesInRentDay = AvailabilityManager.restore( rentDates.get(i) );
			for( int j = 0; j < allAvailabilitiesInRentDay.size(); j++)
			{
				Availability availability = allAvailabilitiesInRentDay.get(j);
				availability.setCabin( AvailabilityManager.restoreCabinFromAvailability( availability )); // set cabin for proxy object
			}

			allAvailabilitiesPerRentDay.add( allAvailabilitiesInRentDay );
		}

		int j = 0; // outer list counter
		int k = 0; // inner list counter
		int outerListSize;
		int innerListSize;
		
		// test each cabin is available during each rent day
		for( int i = 0; i < cabins.size(); i++) // for all cabins to consider after matching by amenities
		{
			j = 0; // reset counter
			outerListSize = allAvailabilitiesPerRentDay.size();
			while( cabinMatched == true && j < outerListSize ) // while cabin has not been ruled out and outer list testing is not done
			{
				k = 0; // reset counter
				innerListSize = allAvailabilitiesPerRentDay.get(j).size();
				while( cabinFound == false && k < innerListSize ) // while cabin has not been found in the inner list and testing is not done
				{
					if( cabins.get(i).getId() == allAvailabilitiesPerRentDay.get(j).get(k).getCabin().getId() )
					{
						cabinFound = true;
					}
					k++;
				}
				if( cabinFound == false) // cabin not available during one of the desired rent days
				{
					cabinMatched = false;
				}
				j++;
				cabinFound = false; // reset sentinel
			}
			if( cabinMatched == true ) // cabin is available during all of the desired rent days
			{
				matchedCabins.add( cabins.get(i) ); // return matched cabin
			}
			
			cabinMatched = true; // reset sentinel
		}
		
		return matchedCabins;
	}
}






