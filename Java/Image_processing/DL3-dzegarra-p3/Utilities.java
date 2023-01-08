import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class loads an image and exports it as a pgm image.
 * @author Diego Zegarra
 */
final class Utilities {
	
	/**
	 * Method to open the image file and upload it to the QuadTree.
	 * @param pgmFile image passed in as a text file.
	 * @return a 2D array with every pixel value from the image opened. 
	 */
	static Short[][] loadFile(String pgmFile){
		
		FileInputStream fileInputStream = null;
		
		try {
			fileInputStream = new FileInputStream(pgmFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Scanner scan = new Scanner(fileInputStream);
		scan.nextLine();
		
		int imgWidth = scan.nextInt();
		int imgHeight = scan.nextInt();
		scan.nextInt();
		
		Short ar[][] = new Short[imgHeight][imgWidth];
		
		for(int i = 0; i < imgHeight; i++) {
			for(int j = 0; j < imgWidth; j++) {
				ar[i][j] = scan.nextShort();
			}
		}
		
		try {
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scan.close();
		
        return ar;
    }
	
	
	/**
	 * Method to generate an image file from a QuadTree.
	 * @param image The new image to be exported.
	 * @param filename Name of the new image to be exported.
	 */
	static void exportImage(QuadTreeImage<Short> image, String filename) {
    	int side = 8;
    	FileWriter newFile = null;
    	String strPixel = "";
    	String strFile = "";
    	try {
    		newFile = new FileWriter(filename);
    	    newFile.write("P2\n");
	  		newFile.write(Integer.toString(side));
	  		newFile.write(" ");
	  		newFile.write(Integer.toString(side));
	  		newFile.write("\n");
	  		newFile.write("255\n");
	  		for(int i = 0; i < side; i++) {
	    		for(int j = 0; j < side; j++) {
	    			strFile = "";
	    			for(int k=0; k<3; k++) {
	    				strFile += " ";
	    			}
	    			strPixel = Short.toString((image.getColor(i, j)));
	    			strFile = strFile.substring(strPixel.length()) + strPixel;
	    			newFile.write(strFile);
	    			newFile.write(" ");
	    		}
	    		newFile.write("\n");
	    	}
	  		newFile.close();
    	} catch (IOException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	}
    	
    	for(int i = 0; i < side; i++) {
    		for(int j = 0; j < side; j++) {
    			
    		}
    	}
    }
	
	
	
}