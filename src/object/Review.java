package object;

public class Review {

	private int		id;
	private int		numStars;
	private String	title;
	private String	description;
	private int		rentRecordId;
	
	public Review()
	{
		id = 0;
		numStars = 0;
		title = "";
		description = "";
		rentRecordId = 0;
	}

	/**
	 * @param id
	 * @param numStars
	 * @param title
	 * @param description
	 * @param rentRecordId
	 */
	public Review(int id, int numStars, String title, String description, int rentRecordId) {
		this.id = id;
		this.numStars = numStars;
		this.title = title;
		this.description = description;
		this.rentRecordId = rentRecordId;
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
	 * @return the numStars
	 */
	public int getNumStars() {
		return numStars;
	}

	/**
	 * @param numStars the numStars to set
	 */
	public void setNumStars(int numStars) {
		this.numStars = numStars;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the rentRecordId
	 */
	public int getRentRecordId() {
		return rentRecordId;
	}

	/**
	 * @param rentRecordId the rentRecordId to set
	 */
	public void setRentRecordId(int rentRecordId) {
		this.rentRecordId = rentRecordId;
	}
	
	
}
