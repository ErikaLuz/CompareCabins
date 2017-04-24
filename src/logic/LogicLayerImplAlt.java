package logic;

import java.util.Calendar;
import java.util.List;

import exception.CCException;
import object.Availability;
import object.Cabin;
import persistence.AvailabilityManager;
import persistence.CabinManager;

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
}
