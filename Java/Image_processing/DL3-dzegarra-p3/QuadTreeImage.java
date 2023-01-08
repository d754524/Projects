import java.lang.Number;
import java.util.Iterator;
/**
 * This class represents an image using a quadree.
 * @author Diego Zegarra
 * @param <Pixel> Generic data type. Extends to Number class.
 */
public class QuadTreeImage<Pixel extends Number> implements Iterable<ImageBlob<Short>>{

	/**
	 * Image length of one side.
	 */
	private int size; 
	/**
	 * Seed or root node.
	 */
	private ImageBlob<Short> root;
	/**
	 * Number of tree levels.
	 */
	private int levels;
	/**
	 * Number of pixels in the original image. It is size*size.
	 */
	private int nPx;
	/**
	 * Counter for the number of nodes in the current tree.
	 */
	private int nodes = 1;
	/**
	 * Helper node used to query.
	 */
	private ImageBlob<Short> imgQuery =  new ImageBlob<Short>();
	
	/**
	 * Constructor: Enter the arrangement with the image information and generate a QuadTree.
	 * @param array a 2D array containing the image pixel values.
	 */
	QuadTreeImage(Pixel[][] array){
		
		this.size = array.length;

		nPx = this.size*this.size;
		levels = (int)log_base(4, nPx);
		root = new ImageBlob<Short>();
		root.setValue(null);
		
		divideArray((Short[][]) array, root, 0, 0, 0);
	}
	
	/**
	 * Constructor: Enter the arrangement with the root node, more information and generate a QuadTree.
	 * @param qt3root a node root.
	 * @param levels number of levels this tree.
	 * @param nodes number of nodes this tree.
	 * @param size number of pixels per side.
	 */
	QuadTreeImage(ImageBlob<Short> qt3root, int levels, int nodes, int size) {
		this.root = qt3root;
		this.levels = levels;
		this.nodes = nodes;
		this.size = size;
		this.nPx = size*size;
	}

	/**
	 * Get the color of a specific pixel by having its coordinate.
	 * @param w the row of the image.
	 * @param h the column of the image.
	 * @return the value of the pixel(w,h).
	 */
	Short getColor(int w, int h) { 
		int ns = this.size; 
		int tX = 0, tY = 0; 
		imgQuery = this.root;
		for(int i = 0; i <= this.levels; i++) {
			if(imgQuery.getValue() == null) { 
				ns = ns/2; 
				if(h < (ns+tX)){ 
					if(w < (ns+tY)) { 
						imgQuery = imgQuery.getNW();
					}else { 
						tY += ns;
						imgQuery = imgQuery.getSW();
					}
				}else { 
					tX += ns; 
					if(w < (ns+tY)) { 
						imgQuery = imgQuery.getNE();
					}else {
						tY += ns;
						imgQuery = imgQuery.getSE();
					}
				}
			}else {
				
				return imgQuery.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Set the color of a specific pixel having its coordinate.
	 * @param w the row of the image.
	 * @param h the column of the image.
	 * @param value the new value to be assigned to the pixel(w,h).
	 */
	void setColor(int w, int h, Pixel value) {
		int ns = this.size;
		int tX = 0, tY = 0;
		this.imgQuery = this.root;
		for(int i = 0; i <= this.levels; i++) {
			if((this.imgQuery.getValue() == null) && (i < this.levels)) { // Asignar color a una hoja del ultimo nivel
				ns = ns/2;
				if(h < (ns+tX)){ 
					if(w < (ns+tY)) { 
						this.imgQuery = this.imgQuery.getNW();
					}else { 
						tY += ns;
						this.imgQuery = this.imgQuery.getSW();
					}
				}else {
					tX += ns;
					if(w < (ns+tY)) { 
						this.imgQuery = this.imgQuery.getNE();
					}else { 
						tY += ns;
						this.imgQuery = this.imgQuery.getSE();
					}
				}
			}else { 
				if((this.imgQuery.getValue() != null) && (i < this.levels)){ 
					ImageBlob<Short> tmpNode = new ImageBlob<Short>();
					tmpNode.setValue(this.imgQuery.getValue());
					tmpNode.setNW(null);
					tmpNode.setNE(null);
					tmpNode.setSE(null);
					tmpNode.setSW(null);
					this.imgQuery.setValue(null);
					this.imgQuery.setNW(tmpNode);
					this.imgQuery.setNE(tmpNode);
					this.imgQuery.setSE(tmpNode);
					this.imgQuery.setSW(tmpNode);
					this.nodes += 4;
					i -= 1;
				}else {
					if(i == this.levels) { 
						this.imgQuery.setValue((Short)value);
					}
				}
			}
		}
		restructure(this.root);
	}
	
	/**
	 * Get an estimate of the image file size using QuadTree.
	 * @param bytesPerPixel value in bytes assigned to each pixel.
	 * @param bytesPerPointer value in bytes assigned to NW,SW,SE,NE.
	 * @return an approximation of bytes to be stored.
	 */
	float sizeRatio(int bytesPerPixel, int bytesPerPointer) {
		float bytesPerQuadTree = (this.nodes * bytesPerPixel) + (this.nodes * (4 * bytesPerPointer));		
		return bytesPerQuadTree;
	}
	
	/**
	 * Convert the QuadTree to an informational string.
	 * @return A written representation containing the coordinates, sizes and their values
	 */
	public String toString() {
		int tX = 0;
		int tY = 0;
		int ns = this.size;
		int cnt = 0;
		int cntLevel = 1;
		int cntLeafs = 0;
		int lvl = 0;
		int coor = 0;
		
		ImageBlob<Short> leaf = new ImageBlob<Short>();
		String answer = "";
		
		QuadTreeImageIterator i = new QuadTreeImageIterator(this.root);
		while(i.hasNext() == true) {
			leaf = i.next();
			cnt += 1;
			coor += ns;
			if(leaf.getValue() != null) {
				answer += "{"+Integer.toString(tY)+" "+Integer.toString(tX)+" "+Integer.toString(ns)+" "+Short.toString(leaf.getValue())+"}";
				cntLeafs += (ns*lvl);
			}
			if(cnt == cntLevel) {
				cnt = 0;
				cnt += cntLeafs;
				cntLevel *= 4;
				ns /= 2;
				lvl += 1;
				coor = 0;
				cntLeafs *= 4;
				tX = 0;
				tY = 0;
			}
			if((i.hasNext() == true) && (leaf.getValue() != null)) {
				answer += ",";
			}
		}
		return answer;
		
	}
	
	/**
	 * Compare two QuadTree and give the difference between these 
	 * in brightness.
	 * The temporal complexity is m1 + m2; where m1 are the nodes of 
	 * tree 1 and m2 are the nodes of tree 2 (to be able to compare 
	 * both trees, they must go through the two)
	 * @param other A second image to be compared to the image we are currently working with.
	 * @return The difference in bright between both images.
	 */
	int compareTo(QuadTreeImage<Short> other) {
		short levels = 0;
		short countLeafForLevel = 0;
		short maxLeafForLevel = 1;
		short nextMaxLeafForLevel = 4;
		short [] leafs = new short[100];
		int brightImage1 = 0;
		int brightImage2 = 0;
		int multiplier = 1;
		ImageBlob<Short> leaf = new ImageBlob<Short>();
		
		
		for(int j=0; j<100; j++) {
			leafs[j] = 0;
		}
		Iterator<ImageBlob<Short>> i = other.iterator();
		while(i.hasNext() == true) {
			leaf = i.next();
			if((leaf.getValue() != null) && (levels == 0)) {
				leafs[0] = leaf.getValue();
			}
			if(leaf.getValue() == null) {
				if(levels != 0) {
					nextMaxLeafForLevel += 4;
				}
				countLeafForLevel += 1;
			}else {
				leafs[levels] += leaf.getValue();
				countLeafForLevel += 1;
			}
			if(countLeafForLevel == maxLeafForLevel) {
				countLeafForLevel = 0;
				maxLeafForLevel = nextMaxLeafForLevel;
				nextMaxLeafForLevel = 0;
				levels += 1;
			}
		}
		levels -= 1;

		for(int j=0; j<=levels; j++) {
			multiplier = 1;
			for(int k=1; k<=(levels-j); k++) {
				multiplier *= 2;
			}
			brightImage2 += (leafs[j]*multiplier*multiplier);
		}

		levels = 0;
		maxLeafForLevel = 1;
		nextMaxLeafForLevel = 4;
		for(int j=0; j<100; j++) {
			leafs[j] = 0;
		}
		i = this.iterator();
		while(i.hasNext() == true) {
			leaf = i.next();
			if((leaf.getValue() != null) && (levels == 0)) {
				leafs[0] = leaf.getValue();
			}
			if(leaf.getValue() == null) {
				if(levels != 0) {
					nextMaxLeafForLevel += 4;
				}
				countLeafForLevel += 1;
			}else {
				leafs[levels] += leaf.getValue();
				countLeafForLevel += 1;
			}
			if(countLeafForLevel == maxLeafForLevel) {
				countLeafForLevel = 0;
				maxLeafForLevel = nextMaxLeafForLevel;
				nextMaxLeafForLevel = 0;
				levels += 1;
			}
		}
		levels -= 1;
	
		for(int j=0; j<=levels; j++) {
			multiplier = 1;
			for(int k=1; k<=(levels-j); k++) {
				multiplier *= 2;
			}
			brightImage1 += (leafs[j]*multiplier*multiplier);
		}
	
		return (brightImage1-brightImage2);
	}	
	
	/**
	 * Generate another QuadTree from the intersection of two QuadTree.
	 * The temporal complexity is m1 + m2; where m1 are the nodes of 
	 * tree 1 and m2 are the nodes of tree 2 (to be able to compare 
	 * both trees, they must go through the two)
	 * @param other A second image.
	 * @return the intersection between both images.
	 */
	QuadTreeImage<Short> intersectionMask(QuadTreeImage<Short> other) {
		short [] leafsTree1 = new short[100];
		short [] leafsTree2 = new short[100];
		short [] leafsPerLevelTree1 = new short[100];
		short [] leafsPerLevelTree2 = new short[100];
		int levels = 0;
		short countLeafForLevel = 0;
		short maxLeafForLevel = 1;
		short nextMaxLeafForLevel = 4;
		short cntArray = 0;
		short maxNodes = 0;
		for(int j=0; j<100; j++) {
			leafsTree1[j] = -1;
			leafsTree2[j] = -1;
			leafsPerLevelTree1[j] = 0;
			leafsPerLevelTree2[j] = 0;
		}
		leafsPerLevelTree1[0] = 1;
		leafsPerLevelTree2[0] = 1;
		Iterator<ImageBlob<Short>> i = other.iterator();
		ImageBlob<Short> leaf = new ImageBlob<Short>();
		while(i.hasNext() == true) {
			leaf = i.next();
			if((leaf.getValue() != null) && (levels == 0)) {
				leafsTree2[0] = leaf.getValue();
			}
			if(leaf.getValue() == null) {
				if(levels != 0) {
					nextMaxLeafForLevel += 4;
				}
				countLeafForLevel += 1;
				cntArray += 1;
			}else {
				leafsTree2[cntArray] = leaf.getValue();
				countLeafForLevel += 1;
				cntArray += 1;
			}
			if(countLeafForLevel == maxLeafForLevel) {
				countLeafForLevel = 0;
				maxLeafForLevel = nextMaxLeafForLevel;
				nextMaxLeafForLevel = 0;
				levels += 1;
				leafsPerLevelTree2[levels] = maxLeafForLevel;
			}
		}
		levels -= 1;
		if(cntArray > maxNodes) {
			maxNodes = cntArray;
		}

		countLeafForLevel = 0;
		maxLeafForLevel = 1;
		nextMaxLeafForLevel = 4;
		cntArray = 0;
		levels = 0;
		i = this.iterator();
		while(i.hasNext() == true) {
			leaf = i.next();
			if((leaf.getValue() != null) && (levels == 0)) {
				leafsTree1[0] = leaf.getValue();
			}
			if(leaf.getValue() == null) {
				if(levels != 0) {
					nextMaxLeafForLevel += 4;
				}
				countLeafForLevel += 1;
				cntArray += 1;
			}else {
				leafsTree1[cntArray] = leaf.getValue();
				countLeafForLevel += 1;
				cntArray += 1;
			}
			if(countLeafForLevel == maxLeafForLevel) {
				countLeafForLevel = 0;
				maxLeafForLevel = nextMaxLeafForLevel;
				nextMaxLeafForLevel = 0;
				levels += 1;
				leafsPerLevelTree1[levels] = maxLeafForLevel;
			}
		}
		if(cntArray > maxNodes) {
			maxNodes = cntArray;
		}
		levels -= 1;
		
		int sizeqt3 = 1;
		for(int j=0; j<levels; j++) {
			sizeqt3 *= 2;
		}
		
		ImageBlob<Short> qt3root = new ImageBlob<Short>();
		
		if((leafsTree2[0] == leafsTree1[0]) && (leafsTree2[0] != -1)) {
			qt3root.setValue(leafsTree2[0]);
			qt3root.setNW(null);
			qt3root.setNE(null);
			qt3root.setSE(null);
			qt3root.setSW(null);
		}else {
			qt3root.setValue(null);
			generateChildsNodes(qt3root, leafsTree1, leafsTree2, (short)1, (short)1, leafsPerLevelTree1,leafsPerLevelTree2, 1);
		}
		
		QuadTreeImage<Short> qt3 = new QuadTreeImage<Short>(qt3root, levels, maxNodes, sizeqt3);
		
		return qt3;
	}
	
	/**
	 * Iterator to traverse the QuadTree by levels.
	 * @return an iterator to traverse the nodes in the tree.
	 */
	@Override
	public Iterator<ImageBlob<Short>> iterator(){
		Queue<ImageBlob<Short>> queue = new Queue<ImageBlob<Short>>();
		Queue<ImageBlob<Short>> aux = new Queue<ImageBlob<Short>>();
		ImageBlob<Short> tmpNode = new ImageBlob<Short>();
		
		queue.enqueue(root);
		aux.enqueue(root);
		while(aux.isEmpty() == false) {
			tmpNode = aux.dequeue();
				if (tmpNode.getValue() == null) {
				queue.enqueue(tmpNode.getNW());
				queue.enqueue(tmpNode.getNE());
				queue.enqueue(tmpNode.getSE());
				queue.enqueue(tmpNode.getSW());
				aux.enqueue(tmpNode.getNW());
				aux.enqueue(tmpNode.getNE());
				aux.enqueue(tmpNode.getSE());
				aux.enqueue(tmpNode.getSW());
			}
		}
		
		Iterator<ImageBlob<Short>> i = new Iterator<ImageBlob<Short>>() {
				/**
				 * Ask if there are more items in the QuadTree.
				 * @return true if there's a node in the queue, false otherwise
				 */
				public boolean hasNext() {
					if(queue.isEmpty() == true) {
						return false;
					}else {
						return true;
					}		
				}
				
				/**
				 * Get the next node in the QuadTree.
				 * @return the next node
				 */
				public ImageBlob<Short> next(){
					return queue.dequeue();
				}
			};
			return i;
	}
	
	/**
	 * Function used to calculate the depth level of the QuadTree.
	 * @param base the size of one side(square shape) of the image.
	 * @param result total number of pixels in the image.
	 * @return the max level of deepness in the tree.
	 */
	private double log_base(double base, double result) {
    	return Math.log(result) / Math.log(base);
    }
	
	/**
	 * Function used to divide the initial array into 4 to form the QuadTree.
	 * @param array contains the original image
	 * @param father the root of the tree
	 * @param l	current level of the tree we are working on
	 * @param x	the x coordinate on the original image
	 * @param y	the y coordinate on the original image
	 */
	private void divideArray(Short[][] array, ImageBlob<Short> father, int l, int x, int y) {
		int len = array.length;
		if(len <= 1) {
			father.setValue(array[0][0]);
			father.setNW(null);
			father.setNE(null);
			father.setSE(null);
			father.setSW(null);
		}else {
			ImageBlob<Short> ib1 = new ImageBlob<Short>();
			ImageBlob<Short> ib2 = new ImageBlob<Short>();
			ImageBlob<Short> ib3 = new ImageBlob<Short>();
			ImageBlob<Short> ib4 = new ImageBlob<Short>();
			father.setNW(ib1);
			father.setNE(ib2);
			father.setSE(ib3);
			father.setSW(ib4);
			father.setValue(null);
			Short[][] NW = new Short[len/2][len/2];
			Short[][] NE = new Short[len/2][len/2];
			Short[][] SW = new Short[len/2][len/2];
			Short[][] SE = new Short[len/2][len/2];
			for(int i = 0; i < (len/2); i++) {
				for(int j = 0; j < (len/2); j++) {
					NW[i][j] = array[i][j];
					NE[i][j] = array[i][j+(len/2)];
					SW[i][j] = array[i+(len/2)][j];
					SE[i][j] = array[i+(len/2)][j+(len/2)];
				}
			}
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 2; j++) {
					int nQuadrant = (i*2)+(j)+1;
					switch(nQuadrant) {
					case 1:
						divideArray(NW, father.getNW(), l+1, 0+x, 0+y);
						this.nodes += 1;
						break;
					case 2:
						divideArray(NE, father.getNE(), l+1, (len/2)+x, 0+y);
						this.nodes += 1;
						break;
					case 3:
						divideArray(SE, father.getSE(), l+1, (len/2)+x, (len/2)+y);
						this.nodes += 1;
						break;
					case 4:
						divideArray(SW, father.getSW(), l+1, 0+x, (len/2)+y);
						this.nodes += 1;
						break;
					}
				}
			}
			if((father.getNW().getValue() != null) && (father.getNE().getValue() != null) && (father.getSE().getValue() != null) && (father.getSW().getValue() != null)) {
				if((((short)father.getNW().getValue() == (short)father.getNE().getValue()) && ((short)father.getNE().getValue() == (short)father.getSE().getValue()) && ((short)father.getSE().getValue() == (short)father.getSW().getValue()))) {
					father.setValue(father.getNW().getValue());
					father.setNW(null);
					father.setNE(null);
					father.setSE(null);
					father.setSW(null);
					this.nodes -= 4;
				}
			}
		}
	}
	
	/**
	 * The function restructure rebuilds the tree after the tree has changed a pixel's value.
	 * @param father Takes the root of the tree.
	 */
	private void restructure(ImageBlob<Short> father) {
		if(father.getValue() == null) { 
			if((father.getNW().getValue() != null) && (father.getNE().getValue() != null) && (father.getSE().getValue() != null) && (father.getSW().getValue() != null)) { 
				
				if((((short)father.getNW().getValue() == (short)father.getNE().getValue()) && ((short)father.getNE().getValue() == (short)father.getSE().getValue()) && ((short)father.getSE().getValue() == (short)father.getSW().getValue()))) {
					
					father.setValue(father.getNW().getValue());
					father.setNW(null);
					father.setNE(null);
					father.setSE(null);
					father.setSW(null);
					this.nodes -= 4;
				}
			}else { 
				restructure(father.getNW());
				restructure(father.getNE());
				restructure(father.getSE());
				restructure(father.getSW());
			}
		}
	}
	
	/**
	 * When the IntersectionMask method is applied, it is necessary 
	 * to create a new tree from the root and start generating the 
	 * children that are the same and different between the two 
	 * intercepted trees. This function will allow taking the parent 
	 * node and assigning its corresponding children.
	 * @param father parent node.
	 * @param arrayLeafs1 array whit information about the leaf of tree 1.
	 * @param arrayLeafs2 array whit information about the leaf of tree 2.
	 * @param indexInit1 position from which to analyze array 1.
	 * @param indexInit2 position from which to analyze array 2.
	 * @param leafsPerLevel1 array whit number of total leafs per level in tree1.
	 * @param leafsPerLevel2 array whit number of total leafs per level in tree1.
	 * @param level number of current level.
	 */
	private void generateChildsNodes(ImageBlob<Short> father, short[] arrayLeafs1, short[] arrayLeafs2, short indexInit1, short indexInit2, short[] leafsPerLevel1, short[] leafsPerLevel2, int level) {
		short cntNulls = 0;
		for(int i=0; i<4; i++) {
			if(arrayLeafs1[i+indexInit1] == arrayLeafs2[i+indexInit2]){
				if(arrayLeafs1[i+indexInit1] != -1){
					ImageBlob<Short> tmpNode;
					tmpNode = new ImageBlob<Short>();
					tmpNode.setValue((short)255);
					tmpNode.setNW(null);
					tmpNode.setNE(null);
					tmpNode.setSE(null);
					tmpNode.setSW(null);
					switch(i) {
						case 0:
							father.setNW(tmpNode);
						break;
						case 1:
							father.setNE(tmpNode);
						break;
						case 2:
							father.setSE(tmpNode);
						break;
						case 3:
							father.setSW(tmpNode);
						break;
					}
				}else {
					ImageBlob<Short> tmpNode;
					tmpNode = new ImageBlob<Short>();
					tmpNode.setValue(null);
					tmpNode.setNW(null);
					tmpNode.setNE(null);
					tmpNode.setSE(null);
					tmpNode.setSW(null);
					generateChildsNodes(tmpNode, arrayLeafs1, arrayLeafs2, (short)(indexInit1+leafsPerLevel1[level]+(cntNulls*4)), (short)(indexInit2+leafsPerLevel2[level]+(cntNulls*4)), leafsPerLevel1, leafsPerLevel2, level+1);
					cntNulls+=1;
					switch(i) {
						case 0:
							father.setNW(tmpNode);
						break;
						case 1:
							father.setNE(tmpNode);
						break;
						case 2:
							father.setSE(tmpNode);
						break;
						case 3:
							father.setSW(tmpNode);
						break;
					}
				}
			}else {
				if((arrayLeafs1[i+indexInit1] == -1) || (arrayLeafs2[i+indexInit2] == -1)) {
					ImageBlob<Short> tmpNode;
					tmpNode = new ImageBlob<Short>();
					tmpNode.setValue(null);
					tmpNode.setNW(null);
					tmpNode.setNE(null);
					tmpNode.setSE(null);
					tmpNode.setSW(null);
					if(arrayLeafs1[i+indexInit1] == -1) {
						generateChildsNodesDifferents(tmpNode, arrayLeafs1, (short)(indexInit1+leafsPerLevel1[level]+(cntNulls*4)), leafsPerLevel1, level+1, arrayLeafs2[i+indexInit2]);
					}else {
						generateChildsNodesDifferents(tmpNode, arrayLeafs2, (short)(indexInit2+leafsPerLevel2[level]+(cntNulls*4)), leafsPerLevel2, level+1, arrayLeafs1[i+indexInit1]);
					}
					switch(i) {
					case 0:
						father.setNW(tmpNode);
					break;
					case 1:
						father.setNE(tmpNode);
					break;
					case 2:
						father.setSE(tmpNode);
					break;
					case 3:
						father.setSW(tmpNode);
					break;
				}
				}else {
					ImageBlob<Short> tmpNode;
					tmpNode = new ImageBlob<Short>();
					tmpNode.setValue((short)0);
					tmpNode.setNW(null);
					tmpNode.setNE(null);
					tmpNode.setSE(null);
					tmpNode.setSW(null);
					switch(i) {
						case 0:
							father.setNW(tmpNode);
						break;
						case 1:
							father.setNE(tmpNode);
						break;
						case 2:
							father.setSE(tmpNode);
						break;
						case 3:
							father.setSW(tmpNode);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * For intersection mask, create childs of the parent node, with differents values.
	 * @param father the parent node.
	 * @param arrayLeafs array with leafs to analyze.
	 * @param indexInit number index where the analysis begin.
	 * @param leafsPerLevel number the leafs per level into node.
	 * @param level level of the tree.
	 * @param value value to compare the leafs with.
	 */
	private void generateChildsNodesDifferents(ImageBlob<Short> father, short[] arrayLeafs, short indexInit, short[] leafsPerLevel, int level, short value) {
		short cntNulls = 0;
		for(int i=0; i<4; i++) {
			
			if(arrayLeafs[i+indexInit] == -1) {
				ImageBlob<Short> tmpNode;
				tmpNode = new ImageBlob<Short>();
				tmpNode.setValue(null);
				tmpNode.setNW(null);
				tmpNode.setNE(null);
				tmpNode.setSE(null);
				tmpNode.setSW(null);
				generateChildsNodesDifferents(tmpNode, arrayLeafs, (short)(indexInit+leafsPerLevel[level]+(cntNulls*4)), leafsPerLevel, level+1, value);
				cntNulls += 1;
				switch(i) {
					case 0:
						father.setNW(tmpNode);
					break;
					case 1:
						father.setNE(tmpNode);
					break;
					case 2:
						father.setSE(tmpNode);
					break;
					case 3:
						father.setSW(tmpNode);
					break;
				}
			}else {
				if(arrayLeafs[i+indexInit] == value) {
					ImageBlob<Short> tmpNode;
					tmpNode = new ImageBlob<Short>();
					tmpNode.setValue((short)255);
					tmpNode.setNW(null);
					tmpNode.setNE(null);
					tmpNode.setSE(null);
					tmpNode.setSW(null);
					switch(i) {
						case 0:
							father.setNW(tmpNode);
						break;
						case 1:
							father.setNE(tmpNode);
						break;
						case 2:
							father.setSE(tmpNode);
						break;
						case 3:
							father.setSW(tmpNode);
						break;
					}
				}else {
					ImageBlob<Short> tmpNode;
					tmpNode = new ImageBlob<Short>();
					tmpNode.setValue((short)0);
					tmpNode.setNW(null);
					tmpNode.setNE(null);
					tmpNode.setSE(null);
					tmpNode.setSW(null);
					switch(i) {
						case 0:
							father.setNW(tmpNode);
						break;
						case 1:
							father.setNE(tmpNode);
						break;
						case 2:
							father.setSE(tmpNode);
						break;
						case 3:
							father.setSW(tmpNode);
						break;
					}
				}
			}
		}
	}
}