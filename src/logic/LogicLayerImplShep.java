package logic;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import exception.CCException;
import object.Amenities;
import object.Availability;
import object.Cabin;
import persistence.AmenitiesManager;
import persistence.AvailabilityManager;

public class LogicLayerImplShep {
	
	public static List<Cabin> search(Amenities amenities, Availability startAvailability, Availability endAvailability) throws CCException{
		List<Cabin> cabins = new LinkedList<Cabin>();
		List<Amenities> listAmenities = AmenitiesManager.restore(amenities);
		for(int i = 0; i < listAmenities.size(); i++) {
			Cabin cabin = AmenitiesManager.restoreCabinFromAmenities(listAmenities.get(i));	
			cabins.add(cabin);
		}
		List<List<Availability>> LLAvailability = new LinkedList<List<Availability>>();
		while(startAvailability.getDate().compareTo(endAvailability.getDate()) <= 0) {
			List<Availability> sLAvailability = AvailabilityManager.restore(startAvailability);
			for(int i = 0; i < sLAvailability.size(); i++) {
				Availability availability = sLAvailability.get( i );
				Cabin cabin = AvailabilityManager.restoreCabinFromAvailability( availability );
				availability.setCabin( cabin );				
			}
			LLAvailability.add(sLAvailability);
			startAvailability.getDate().add(Calendar.DAY_OF_MONTH, 1);
		}
		for(int c = 0; c < cabins.size(); c++){
			System.out.println(cabins.get(c).getId());
			boolean available = false;
			for(int i = 0; i < LLAvailability.size(); i++) {
				for(int k = 0; k < LLAvailability.get(i).size(); k++) {
					System.out.println(LLAvailability.get(i).get(k).getCabin());
					if(cabins.get(c) == LLAvailability.get(i).get(k).getCabin()) {
						available = true;
						break;
					}
				}
			}
			if(!available)
				cabins.remove(c);
		}
		return cabins;
	}
}
