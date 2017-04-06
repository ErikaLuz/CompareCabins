package object;

import java.util.Calendar;

public class RentRecord {

	private int			id;
	private float		totalPrice;
	private Calendar	startDate;
	private Calendar	endDate;
	private int			cabinId;
	private int			userId;

	public RentRecord()
	{
		id = 0;
		totalPrice = 0;
		startDate = null;
		endDate = null;
		cabinId = 0;
		userId = 0;
	}

	/**
	 * @param id
	 * @param totalPrice
	 * @param startDate
	 * @param endDate
	 * @param cabinId
	 * @param userId
	 */
	public RentRecord(int id, float totalPrice, Calendar startDate, Calendar endDate, int cabinId, int userId) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cabinId = cabinId;
		this.userId = userId;
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

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}