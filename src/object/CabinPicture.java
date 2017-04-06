package object;

public class CabinPicture {

	private int		id;
	private String	filePath;
	private int		cabinId;
	
	public CabinPicture()
	{
		id = 0;
		filePath = "";
		cabinId = 0;
	}
	
	/**
	 * @param id
	 * @param filePath
	 * @param cabinId
	 */
	public CabinPicture(int id, String filePath, int cabinId) {
		this.id = id;
		this.filePath = filePath;
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
