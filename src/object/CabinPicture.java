package object;

public class CabinPicture {

	private int		id;
	private String	filePath;
	private Cabin	cabin;
	
	public CabinPicture()
	{
		id = -1;
		filePath = null;
		cabin = null;
	}
	
	/**
	 * @param id
	 * @param filePath
	 * @param cabinId
	 */
	public CabinPicture(int id, String filePath, Cabin cabin) {
		this.id = id;
		this.filePath = filePath;
		this.cabin = cabin;
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
