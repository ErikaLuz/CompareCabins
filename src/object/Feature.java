/**
 * 
 */
package object;


public class Feature {

	private int 	id;
	private String 	featureString;
	private int		cabinId;
	
	public Feature()
	{
		id = 0;
		featureString = "";
		cabinId = 0;
	}
	/**
	 * @param id
	 * @param featureString
	 * @param cabinID
	 */
	public Feature(int id, String featureString, int cabinId) {
		this.id = id;
		this.featureString = featureString;
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
	 * @return the featureString
	 */
	public String getFeatureString() {
		return featureString;
	}
	/**
	 * @param featureString the featureString to set
	 */
	public void setFeatureString(String featureString) {
		this.featureString = featureString;
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
