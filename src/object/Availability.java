package object;

import java.util.Calendar;

public class Availability {

	private int			id;
	private float		price;
	private Calendar	date;
	private int			cabinId;
	
	public Availability()
	{
		id = 0;
		price = 0;
		date = null;
		cabinId = 0;
	}

	/**
	 * @param id
	 * @param price
	 * @param date
	 * @param cabinId
	 */
	public Availability(int id, float price, Calendar date, int cabinId) {
		this.id = id;
		this.price = price;
		this.date = date;
		this.cabinId = cabinId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @return the cabinId
	 */
	public int getCabinId() {
		return cabinId;
	}

	/**
	 * @param cabinId the cabinId to set
	 */
	public void setCabinId(int cabinId) {
		this.cabinId = cabinId;
	}
	
	
}
