package ca.bcit.comp2613.rockpaperscissorslizardspocksim.model;

public class Gesture {

	private String name;
	private String displayImage;	
		
	/**
	 * @param name
	 * @param displayImage
	 */
	public Gesture(String name, String displayImage) {
		super();
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
	
	
	
}
