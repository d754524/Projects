/**
 * This class creates a queue where the nodes of the tree will be stored.
 * @author Diego Zegarra
 * @param <T> Generic data type.
 */
public class Queue<T> {
	
	/**
	 * Array where generic type data is stored.
	 */
	private T nodes[];
	/**
	 * Indicates where the final part of the queue (last data).
	 */
	private short tail = 1;
	/**
	 * Max capacity of the queue.
	 */
	public final short maxCap = 100;

	/**
	 * Constructor: Creates the queue as an array.
	 */
	@SuppressWarnings("unchecked")
	public Queue() {
		this.nodes = (T[]) new ImageBlob[maxCap];
		this.nodes[0] = null;
		this.nodes[1] = null;
	}
	
	/**
	 * Check if the queue is empty.
	 * @return true if queue is empty, false otherwise.
	 */
	boolean isEmpty() {
		if (this.nodes[1] == null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Add an object to the queue.
	 * @param value to be added to the queue.
	 */
	void enqueue(T value) {
		if(this.tail == (this.maxCap-1)) {
			System.out.println("Full Queue");
		}else {
			nodes[this.tail+1] = null;
			nodes[this.tail] = value;
			this.tail += 1;
		}
	}
	
	/**
	 * Take out the item at the front of the queue.
	 * @return the node at the front that was removed.
	 */
	T dequeue() {
		if(isEmpty() == false) {
			T tmpImg = nodes[1];

			for(int i = 1; i <= this.tail; i++){
				nodes[i] = nodes[i+1];
			}
			this.tail -= 1;
			return tmpImg;
		}else {
			return null;
		}
	}
	
	/**
	 * Get the object to the front of the queue. No remove this object.
	 * @return access the node at the front of the queue without removing it
	 */
	T peek() {
		return nodes[1];
	}
	

}