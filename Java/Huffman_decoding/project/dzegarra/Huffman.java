//************************************************
//************************************************

//No additional imports, make do with these

//streams are for reading/writing bytes
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//for bits used provided BitInputStream and BitOutputStream

//readers/writers are for reading/writing characters
import java.io.FileReader;
import java.io.FileWriter;

//parent of all checked I/O exceptions
import java.io.IOException;

//pretty much all the data structures
import java.util.*;

//************************************************
//************************************************


//the class with the main method
class Huffman {
	
	public static void main(String[] args) {
		if(args.length != 1) System.out.println("Usage: java Huffman <inputFile>");
		
		//your code... feel free to make classes, methods, anything you want/need
		try{
		
		BitInputStream myFile = new BitInputStream(new FileInputStream(args[0].toString()));
		BitInputStream myFile2 = new BitInputStream(new FileInputStream(args[0].toString()));
		LinkedList<LinkedList<Integer>> treeNodesAsBits = new LinkedList<>();
		LinkedList<Byte> treeNodesAsBytes = new LinkedList<>();
		LinkedList<treeNode> nodes = new LinkedList<>();
		LinkedList<LinkedList<Integer>> encodedData = new LinkedList<>();
		LinkedList<Integer> encodedDataInSingleList = new LinkedList<>();
		byte[] readBytes = new byte[myFile.available()];
		

		System.out.println("\nFile exists!\n");
		System.out.println("Stimated bytes that can be read: "+myFile.available());


		/*
		 * READS BYTES
		 */
		myFile.read(readBytes);
		for(int i=0;i < readBytes.length; i++){
			if(readBytes[i]==-1) break;
		}
		if(readBytes.length<4){
			System.out.println("Not a valid binary file with Huffman encoding!");return;
		}
		else if(readBytes[0]+readBytes[1]+readBytes[2]+readBytes[3]!=0){
			System.out.println("Not a valid binary file with Huffman encoding!");return;
		}
		else{
			System.out.println("\n>>>>>>>>>It is a valid binary file!<<<<<<<<<<<");
		}
		FileWriter fileToWriteMessage = new FileWriter(args[0].toString().concat(".txt"));
		

		/*
		 * Find total number of nodes and store height of tree
		*/
		int height = readBytes[4];	//Height of tree
		int temp = height+1;
		int totalNodes = 1;
		while(temp!=0){
			totalNodes*=2;
			temp--;
		}
		totalNodes--;	//Total number of nodes
		System.out.println("================================================");
		System.out.println("\nHeight of the tree is: "+height);
		System.out.println("\nThe total number of nodes is: "+totalNodes);


		/*
		 * STORE NODES FROM TREE AS BYTES
		*/
		for(int i=5;i<totalNodes+5;i++){
			treeNodesAsBytes.add(readBytes[i]);
		}

		/*
		 * ADD NODES TO A LINKED LIST TO THEN ASSIGN CHILDREN TO THEIR RESPECTIVE PARENTS
		 */
		treeNode root = new treeNode((char)((int)treeNodesAsBytes.get(0)));
		nodes.add(root);
		for(int i=1;i<treeNodesAsBytes.size();i++){
			nodes.add(new treeNode((char)((int)treeNodesAsBytes.get(i))));
		}
		int l = 0;
		int r = 1;
		while(r<nodes.size()){
			nodes.get(l).setLeft(nodes.get(r++));
			nodes.get(l).setRight(nodes.get(r++));
			l++;
		}


		/*
		 * READ BITS
		 * STORE ALL NODES FROM THE TREE AS BITS																					
		 */
		myFile2.startBitMode();
		for(int i=0;i<40;i++){	//Skip first 40 bits
			myFile2.readNext();
		}
		for(int i=0;i<totalNodes;i++){
			treeNodesAsBits.add(new LinkedList<>());	
			for(int j=0;j<8;j++){
				treeNodesAsBits.get(i).add(0,myFile2.readNext());
			}
		}

		/*
		* STORE ENCODED FILE
		*/
		int t=0;
		for(int i=0;myFile2.hasNext();i++){
			encodedData.add(new LinkedList<>());
			for(int j=0;j<8;j++){
				t = myFile2.readNext();
				if(t==-1) break;
				encodedDataInSingleList.add(t);
				encodedData.get(i).add(0,t);							
			}
			if(t==-1) break;
		}

		while(encodedDataInSingleList.size()%8!=0){
			encodedDataInSingleList.add(0);
		}
		System.out.println("\nNum of Bytes of encoded file: "+encodedDataInSingleList.size()/8);
		System.out.println("================================================");

		/*
		 * DECODING FILE
		 */
		System.out.println("\nDecoding processing...\n");
							
			for(int i=0; i < encodedDataInSingleList.size(); i++){	
					if(encodedDataInSingleList.get(i)==0){
						if(root.getLeft().value==(char)0){
								continue;
						}
						else if(root.getLeft().value==(char)1){
							root = root.getLeft();
						}
						else if(root.getLeft().value==(char)3){
							break;
						}
						else{

							fileToWriteMessage.write(root.getLeft().value);
							root = nodes.get(0);
						}
					}
					else if(encodedDataInSingleList.get(i)==1){
						if(root.getRight().value==(char)0){
							continue;
						}
						else if(root.getRight().value==(char)1){
							root = root.getRight();
						}
						else if(root.getRight().value==(char)3){
							break;
						}
						else{
							fileToWriteMessage.write(root.getRight().value);
							root = nodes.get(0);
						}
					}

			}
			System.out.println("Decoding finished successfully!");

		myFile.close();
		myFile2.close();
		fileToWriteMessage.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File was not found!");
		}
		catch(IOException e){

		}

	}



}
class treeNode{

	treeNode left=null;
	treeNode right=null;
	char value;
	boolean root;

	public treeNode(char value){
		this.value=value;
	}

	public void setLeft(treeNode leftNode){
		left = leftNode;
	}
	public treeNode getLeft(){
		return left;
	}
	public void setRight(treeNode rightNode){
		right=rightNode;
	}
	public treeNode getRight(){
		return right;
	}

}
