package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;

/**
 * @author A00913377 Tyler Wardle
 *
 */
public class Gesture {

	private long id;
	private String name;
	private String displayImage;	
		
	/**
	 * @param id
	 * @param name
	 * @param displayImage
	 */
	public Gesture(long id, String name, String displayImage) {
		super();
		this.id = id;
		this.name = name;
		this.displayImage = displayImage;
	}	
	
	public String toString(){
		return "";
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the displayImage
	 */
	public String getDisplayImage() {
		return displayImage;
	}
	/**
	 * @param displayImage the displayImage to set
	 */
	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	
	
}
