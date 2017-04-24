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
			LLAvailability.add(sLAvailability);
			startAvailability.getDate().add(Calendar.DAY_OF_MONTH, 1);
		}
		for(int i = 0; i < LLAvailability.size(); i++) {
			boolean available = false;
			for(int k = 0; k < LLAvailability.get(i).size(); k++) {
				if(cabins.get(i).getId() == LLAvailability.get(i).get(k).getCabin().getId()) {
					available = true;
					break;
				}
			}
			if(!available)
					cabins.remove(i);
		}
		return cabins;
	}
}
