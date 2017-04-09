/**
 * 
 */
package object;


public class Feature {

	private int 	id;
	private String 	featureString;
	private Cabin	cabin;
	
	public Feature()
	{
		id = -1;
		featureString = null;
		cabin = null;
	}
	/**
	 * @param id
	 * @param featureString
	 * @param cabinID
	 */
	public Feature(int id, String featureString, Cabin cabin) {
		this.id = id;
		this.featureString = featureString;
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
