//import java.lang.Number;

public class Project3
{
    public static void main(String[] args)
    {
        // load an image from a file
    	System.out.println("#########################################################");
    	System.out.println("Reading the image / file image1.pgm");
    	System.out.println("#########################################################");
        Short[][] array = Utilities.loadFile("image1.pgm");

        System.out.println("Original Image 1:");
        int side1 = array.length;
        int side2 = array[0].length;
        
        for(int i = 0; i < side1; i++){
            for(int j = 0; j < side2; j++){
                System.out.print(array[i][j]);
                System.out.print("\t");
            }
            System.out.println("");
        }
        
    	// construct the quadtree
        QuadTreeImage<Short> img1 = new QuadTreeImage<Short>(array);
        QuadTreeImage<Short> originalImg1 = new QuadTreeImage<Short>(array);
        
        System.out.println("#########################################################");
    	System.out.println("Reading and changing one pixel");
    	System.out.println("#########################################################");
        
        System.out.print("Get Color (2,6)");
        System.out.println(img1.getColor(2, 6));
    	System.out.println("Set Color (2,6) -> 255");
        img1.setColor(2, 6, (short)255);
        System.out.print("Get Color (2,6)");
        System.out.println(img1.getColor(2, 6));
    	
//        
//        System.out.print("Get Color (2,5)");
//        System.out.println(img1.getColor(2, 5));
//        
//        System.out.println("Set Color (2,5) -> 255");
//        img1.setColor(2, 5, (short)255);
//        
//        System.out.print("Get Color (2,5)");
//        System.out.println(img1.getColor(2, 5));
        
        /*
        System.out.print("Get Color (0,0)");
        System.out.println(img1.getColor(0, 0));
        
        System.out.print("Get Color (0,1)");
        System.out.println(img1.getColor(0, 1));
        
        System.out.print("Get Color (1,0)");
        System.out.println(img1.getColor(1, 0));
        
        */
        
        System.out.println("\n#########################################################");
    	System.out.println("To String");
    	System.out.println("#########################################################");
        
        // invoke toString()
        System.out.println(originalImg1);
        
        // invoke the iterator;
        
        System.out.println("\n#########################################################");
    	System.out.println("Using the iterator of QueadTree");
    	System.out.println("#########################################################");

        System.out.print("Original Image:\n\t");  
        for(ImageBlob<Short> im : originalImg1)
            System.out.print(im.value+" "); 
    	
        System.out.print("\nChanged Image:\n\t");
        for(ImageBlob<Short> im : img1)
            System.out.print(im.value+" ");

        System.out.println("\n\n#########################################################");
    	System.out.println("Calculation Size of File QuadTree");
    	System.out.println("#########################################################");

        // measure the storage benefit from using quadtrees
        System.out.println("Size Ratio: "+img1.sizeRatio(10,4));
        
        System.out.println("\n#########################################################");
    	System.out.println("Export the QuadTree changed to file: image1_after_setColor.pgm");
    	System.out.println("#########################################################");
    	Utilities.exportImage(img1,"image1_after_setColor.pgm");
        System.out.println("Exported Image !!!");
        
        // load a second image from another file
        System.out.println("\n#########################################################");
    	System.out.println("Reading the image / file image2.pgm");
    	System.out.println("#########################################################");
        array = Utilities.loadFile("image2.pgm");

        // construct a second quadtree
        QuadTreeImage<Short> img2 = new QuadTreeImage<>(array);
        System.out.println("Original Image 2:");
        side1 = array.length;
        side2 = array[0].length;
        
        for(int i = 0; i < side1; i++){
            for(int j = 0; j < side2; j++){
                System.out.print(array[i][j]);
                System.out.print("\t");
            }
            System.out.println("");
        }

        System.out.println("\n#########################################################");
    	System.out.println("Compare brightness Original Image 1 whit Original Image 2");
    	System.out.println("#########################################################");
        // compare the two images, i.e. which one is brighter
        System.out.println("Diference: "+originalImg1.compareTo(img2));
        
        System.out.println("\n#########################################################");
    	System.out.println("Compare brightness Original Image 2 whit Original Image 1");
    	System.out.println("#########################################################");
        // compare the two images, i.e. which one is brighter
        System.out.println("Diference: "+img2.compareTo(originalImg1));

        System.out.println("\n#########################################################");
    	System.out.println("Using the iterator of QueadTree with Image 2");
    	System.out.println("#########################################################");
        System.out.print("Image 2:\n\t");
        for(ImageBlob<Short> im : img2)
            System.out.print(im.value+" ");
        System.out.println();

        System.out.println("\n#########################################################");
    	System.out.println("Intersection Mask");
    	System.out.println("#########################################################");

    	QuadTreeImage<Short> interMask = originalImg1.intersectionMask(img2);
    	System.out.print("Interator: ");
    	for(ImageBlob<Short> im : interMask)
            System.out.print(im.value+" ");
        System.out.println();
        Utilities.exportImage(interMask,"interMask.pgm");
        System.out.println("Generated File with image: interMask.pgm");
        /*
        // change the color of a single pixel in image1
        img1.setColor(2,6,(short)255);

        // save the modified image in a file to see how it looks like
        Utilities.exportImage(img1,"image1_after_setColor.pgm");

        // invoke the toString to see how the tree looks like after the modification
        System.out.println(img1);

        // inspect the values of all the nodes after the modification
        for(ImageBlob<Short> im : img1)
            System.out.print(im.value+" ");
            
         */

    }
}