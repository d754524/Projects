public class Project3
{
    public static void main(String[] args)
    {
        // load an image from a file
        Short[][] array = Utilities.loadFile("image1.pgm");

        // construct the quadtree
        QuadTreeImage<Short> img1 = new QuadTreeImage<>(array);

        // invoke toString()
        System.out.println(img1);

        // invoke the iterator
        for(ImageBlob<Short> im : img1)
            System.out.print(im.value+" ");

        // measure the storage benefit from using quadtrees
        System.out.println("\nsizeRatio: "+img1.sizeRatio(10,4));

        // load a second image from another file
        array = Utilities.loadFile("image2.pgm");

        // construct a second quadtree
        QuadTreeImage<Short> img2 = new QuadTreeImage<>(array);

        // compare the two images, i.e. which one is brighter
        System.out.println(img1.compareTo(img2));

        // change the color of a single pixel in image1
        img1.setColor(2,6,(short)255);

        // save the modified image in a file to see how it looks like
        Utilities.exportImage(img1,"image1_after_setColor.pgm");

        // invoke the toString to see how the tree looks like after the modification
        System.out.println(img1);

        // inspect the values of all the nodes after the modification
        for(ImageBlob<Short> im : img1)
            System.out.print(im.value+" ");

    }

}
