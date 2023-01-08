/**
 * Class to handle each grouping of equal pixels (nodes).
 * @author Diego Zegarra
 * @param <Pixel> Generic data extends to Number class.
 */
public class ImageBlob<Pixel extends Number> {
    /////////////////////////////////////////////////
    /*    DO NOT CHANGE/ALTER/REMOVE THESE FIELDS  */
    /*    DO NOT ADD ANY OTHER FIELDS EITHER       *//**
	 * Child nodes of the current node, if it has them, if they are not null.
	 */
	ImageBlob<Pixel> NW, NE, SE, SW;
	/**
     * Current node value.
     */
	Pixel value;
 /////////////////////////////////////////////////
    	
 //**  YOU MAY ADD ANY METHODS YOU WANT BELOW THIS LINE   **//
	
	/**
	 * Get the child node in NW.
	 * @return One child node
	 */
	public ImageBlob<Pixel> getNW() {
		return NW;
	}
	
	/**
	 * Set the child node in NW.
	 * @param nW The child node.
	 */
	public void setNW(ImageBlob<Pixel> nW) {
		NW = nW;
	}
	
	/**
	 * Get the child node in NE.
	 * @return One child node.
	 */
	public ImageBlob<Pixel> getNE() {
		return NE;
	}
	
	/**
	 * Set the child node in NE.
	 * @param nE The child node.
	 */
	public void setNE(ImageBlob<Pixel> nE) {
		NE = nE;
	}
	
	/**
	 * Get the child node in SE.
	 * @return One child node.
	 */
	public ImageBlob<Pixel> getSE() {
		return SE;
	}
	
	/**
	 * Set the child node in SE.
	 * @param sE The child node.
	 */
	public void setSE(ImageBlob<Pixel> sE) {
		SE = sE;
	}
	
	/**
	 * Get the child node in SW.
	 * @return One child node.
	 */
	public ImageBlob<Pixel> getSW() {
		return SW;
	}
	
	/**
	 * Set the child node in SE.
	 * @param sW The child node.
	 */
	public void setSW(ImageBlob<Pixel> sW) {
		SW = sW;
	}
	
	/**
	 * Get the current node value.
	 * @return The current node value.
	 */
	public Pixel getValue() {
		return value;
	}
	
	/**
	 * Set the current node value.
	 * @param value of the current node.
	 */
	public void setValue(Pixel value) {
		this.value = value;
	}
}