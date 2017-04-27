package logic;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import exception.CCException;
import object.Availability;
import object.Cabin;
import object.RentRecord;
import object.User;
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
		List<Availability> availabilities = getDatesBetween( start.getDate(), end.getDate() );
		Cabin model = new Cabin();
		float totalPrice = 0;
		model.setId( cabinId );
		
		LinkedList<Availability> rentDates = new LinkedList<>();
		
		if( testIfDatesAvailableFillAvailabilityList( availabilities, cabinId, rentDates) )
		{
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
		
		while( startCal.compareTo( endCal ) <= 0 )
		{
			Availability availability = new Availability();
			availability.setDate( startCal );
			
			availabilities.add( availability );
			
			startCal.add( Calendar.DAY_OF_MONTH, 1);
		}
		
		return availabilities;
	}
}






