/**
 * This class helps us traverse the tree in a breadth-first traversal.
 * @author Diego Zegarra
 *
 */
public class QuadTreeImageIterator{
	
	/**
	 * ImageBlob array where objects will be stored to queue.
	 */
	Queue<ImageBlob<Short>> queue = new Queue<ImageBlob<Short>>();
	/**
	 * Temporary and auxiliary fix to create the queue.
	 */
	Queue<ImageBlob<Short>> aux = new Queue<ImageBlob<Short>>();
	/**
	 * Help to create the queue.
	 */
	ImageBlob<Short> tmpNode = new ImageBlob<Short>();
	
	/**
	 * Creates a queue to traverse the tree.
	 * @param root of the tree.
	 */
	QuadTreeImageIterator(ImageBlob<Short> root){
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
	}
	
	/**
	 * Ask if there are more items in the queue.
	 * @return true if there's a node in the queue, false otherwise.
	 */
	public boolean hasNext() {
		if(queue.isEmpty() == true) {
			return false;
		}else {
			return true;
		}		
	}
	
	/**
	 * Get the next object in the queue.
	 * @return the next node
	 */
	public ImageBlob<Short> next(){
		return queue.dequeue();
	}
	
	
}