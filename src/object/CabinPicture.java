package object;

public class CabinPicture {

	private int		id;
	private String	filePath;
	private int		priority;
	private Cabin	cabin;
	
	public CabinPicture()
	{
		id = -1;
		filePath = null;
		priority = -1;
		cabin = null;
	}
	
	/**
	 * @param id
	 * @param filePath
	 * @param cabinId
	 */
	public CabinPicture( String filePath, int priority ) {
		this.id = -1;
		this.filePath = filePath;
		this.priority = priority;
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
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
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
	public void setCabin(Cabin cabin) {
		this.cabin = cabin;
	}
	
}
