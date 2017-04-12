package object;

public class Amenities {

	private int id;
	private boolean hasLake;
	private boolean hasRiver;
	private boolean hasPool;
	private boolean hasHotTub;
	private boolean hasWifi;
	private boolean hasAirConditioning;
	private boolean hasWasherDryer;
	private boolean allowsPets;
	private boolean allowsSmoking;
	
	
	/**
	 * 
	 */
	public Amenities() 
	{
		id = -1;
		hasLake = false;
		hasRiver = false;
		hasPool = false;
		hasHotTub = false;
		hasWifi = false;
		hasAirConditioning = false;
		hasWasherDryer = false;
		allowsPets = false;
		allowsSmoking = false;		
	}

	/**
	 * @param hasLake
	 * @param hasRiver
	 * @param hasPool
	 * @param hasHotTub
	 * @param hasWifi
	 * @param hasAirConditioning
	 * @param hasWasherDryer
	 * @param allowsPets
	 * @param allowsSmoking
	 */
	public Amenities( boolean hasLake, boolean hasRiver, boolean hasPool, boolean hasHotTub, boolean hasWifi,
			boolean hasAirConditioning, boolean hasWasherDryer, boolean allowsPets, boolean allowsSmoking ) 
	{
		this.id = -1;
		this.hasLake = hasLake;
		this.hasRiver = hasRiver;
		this.hasPool = hasPool;
		this.hasHotTub = hasHotTub;
		this.hasWifi = hasWifi;
		this.hasAirConditioning = hasAirConditioning;
		this.hasWasherDryer = hasWasherDryer;
		this.allowsPets = allowsPets;
		this.allowsSmoking = allowsSmoking;
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
	 * @return the hasLake
	 */
	public boolean isHasLake() {
		return hasLake;
	}

	/**
	 * @param hasLake the hasLake to set
	 */
	public void setHasLake(boolean hasLake) {
		this.hasLake = hasLake;
	}

	/**
	 * @return the hasRiver
	 */
	public boolean isHasRiver() {
		return hasRiver;
	}

	/**
	 * @param hasRiver the hasRiver to set
	 */
	public void setHasRiver(boolean hasRiver) {
		this.hasRiver = hasRiver;
	}

	/**
	 * @return the hasPool
	 */
	public boolean isHasPool() {
		return hasPool;
	}

	/**
	 * @param hasPool the hasPool to set
	 */
	public void setHasPool(boolean hasPool) {
		this.hasPool = hasPool;
	}

	/**
	 * @return the hasHotTub
	 */
	public boolean isHasHotTub() {
		return hasHotTub;
	}

	/**
	 * @param hasHotTub the hasHotTub to set
	 */
	public void setHasHotTub(boolean hasHotTub) {
		this.hasHotTub = hasHotTub;
	}

	/**
	 * @return the hasWifi
	 */
	public boolean isHasWifi() {
		return hasWifi;
	}

	/**
	 * @param hasWifi the hasWifi to set
	 */
	public void setHasWifi(boolean hasWifi) {
		this.hasWifi = hasWifi;
	}

	/**
	 * @return the hasAirConditioning
	 */
	public boolean isHasAirConditioning() {
		return hasAirConditioning;
	}

	/**
	 * @param hasAirConditioning the hasAirConditioning to set
	 */
	public void setHasAirConditioning(boolean hasAirConditioning) {
		this.hasAirConditioning = hasAirConditioning;
	}

	/**
	 * @return the hasWasherDryer
	 */
	public boolean isHasWasherDryer() {
		return hasWasherDryer;
	}

	/**
	 * @param hasWasherDryer the hasWasherDryer to set
	 */
	public void setHasWasherDryer(boolean hasWasherDryer) {
		this.hasWasherDryer = hasWasherDryer;
	}

	/**
	 * @return the allowsPets
	 */
	public boolean isAllowsPets() {
		return allowsPets;
	}

	/**
	 * @param allowsPets the allowsPets to set
	 */
	public void setAllowsPets(boolean allowsPets) {
		this.allowsPets = allowsPets;
	}

	/**
	 * @return the allowsSmoking
	 */
	public boolean isAllowsSmoking() {
		return allowsSmoking;
	}

	/**
	 * @param allowsSmoking the allowsSmoking to set
	 */
	public void setAllowsSmoking(boolean allowsSmoking) {
		this.allowsSmoking = allowsSmoking;
	}
	
	
}
