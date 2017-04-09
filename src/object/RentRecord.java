package object;

import java.util.Calendar;

public class RentRecord {

	private int			id;
	private float		totalPrice;
	private Calendar	startDate;
	private Calendar	endDate;
	private Cabin		cabin;
	private User		user;

	public RentRecord()
	{
		id = -1;
		totalPrice = -1;
		startDate = null;
		endDate = null;
		cabin = null;
		user = null;
	}

	/**
	 * @param id
	 * @param totalPrice
	 * @param startDate
	 * @param endDate
	 * @param cabin
	 * @param user
	 */
	public RentRecord(int id, float totalPrice, Calendar startDate, Calendar endDate, Cabin cabin, User user) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cabin = cabin;
		this.user = user;
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
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the cabin
	 */
	public Cabin getCabin() {
		return cabin;
	}

	/**
	 * @param cabin the cabin to set
	 */
	public void setCabinId(Cabin cabin) {
		this.cabin = cabin;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser( User user) {
		this.user = user;
	}
	
	

}