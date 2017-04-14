package object;

import java.util.Calendar;

public class Availability {

	private int			id;
	private float		price;
	private Calendar	date;
	private Cabin		cabin;
	
	public Availability()
	{
		id = -1;
		price = -1;
		date = null;
		cabin = null;
	}

	/**
	 * @param price
	 * @param date
	 */
	public Availability( float price, Calendar date ) {
		this.id = -1;
		this.price = price;
		this.date = date;
		this.cabin = null;
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
	public Cabin getCabin() {
		return cabin;
	}

	/**
	 * @param cabinId the cabinId to set
	 */
	public void setCabin( Cabin cabin) {
		this.cabin = cabin;
	}
	
	
}
