//TODO:
//  (1) Update this code to meet the style and JavaDoc requirements.
//			Why? So that you get experience with the code for a heap!
//			Also, this happens a lot in industry (updating old code
//			to meet your new standards). We've done this for you in
//			WeissCollection and WeissAbstractCollection.
//  (2) Implement update() method -- see project description

import java.util.Iterator;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * PriorityQueue class implemented via the binary heap.
 * From your textbook (Weiss)
 */
public class WeissPriorityQueue<AnyType> extends WeissAbstractCollection<AnyType>
{
	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------
	
	public static void main(String[] args) {
		class Student {
			String gnum;
			String name;
			Student(String gnum, String name) { this.gnum = gnum; this.name = name; }
			public boolean equals(Object o) {
				if(o instanceof Student) return this.gnum.equals(((Student)o).gnum);
				return false;
			}
			public String toString() { return name + "(" + gnum + ")"; }
			public int hashCode() { return gnum.hashCode(); }
		}
		
		Comparator<Student> comp = new Comparator<Student>() {
			public int compare(Student s1, Student s2) {
				return s1.name.compareTo(s2.name);
			}
		};
		
		WeissPriorityQueue<Student> q = new WeissPriorityQueue<>(comp);
		q.add(new Student("G00000000", "Robert"));
		q.add(new Student("G00000001", "Cindi"));
		
		for(Student s : q) System.out.print(s.name + " ");
		System.out.println(); //Cindi Robert
		
		Student bobby = new Student("G00000000", "Bobby");
		q.update(bobby);
		
		for(Student s : q) System.out.print(s.name + " ");
		System.out.println(); //Bobby Cindi
		
		bobby.name = "Diego";
		q.update(bobby);
		
		for(Student s : q) System.out.print(s.name + " ");
		System.out.println(); //Cindi Diego
		
		bobby.name = "Bart";
		q.update(bobby);
		
		for(Student s : q) System.out.print(s.name + " ");
		System.out.println(); //Bart Cindi
		
		//you'll need more testing...
		
		class GraphP {
			String node;
			String weight;
			GraphP(String node, String weight) { this.node = node; this.weight = weight; }
			public boolean equals(Object o) {
				if(o instanceof GraphP) return this.node.equals(((GraphP)o).node);
				return false;
			}
			public String toString() { return node+"("+weight+")"; }
			public int hashCode() { return node.hashCode(); }
		}
		
		Comparator<GraphP> comp2 = new Comparator<GraphP>() {
			public int compare(GraphP s1, GraphP s2) {
				System.out.println(s1.weight+" <-> "+s2.weight+" "+s1.weight.compareTo(s2.weight));
				return s1.weight.compareTo(s2.weight);
			}
		};
		
		System.out.println("#############################");
		WeissPriorityQueue<GraphP> q2 = new WeissPriorityQueue<>(comp2);
		q2.add(new GraphP("A", "11"));
		q2.add(new GraphP("B", "17"));
		q2.add(new GraphP("C", "14"));
		for(GraphP g : q2) System.out.println("\t"+g.node + " , " + g.weight);
		System.out.println("-------");
		
		q2.add(new GraphP("F", "09"));
		for(GraphP g : q2) System.out.println("\t"+g.node + " , " + g.weight);
		System.out.println("-------");
		
		q2.add(new GraphP("H", "14"));
		for(GraphP g : q2) System.out.println("\t"+g.node + " , " + g.weight);
		System.out.println("-------");
		
		q2.update(new GraphP("F", "12"));
		for(GraphP g : q2) System.out.println("\t"+g.node + " , " + g.weight);
		System.out.println("-------");
		
	}	
	
	public boolean update(AnyType x) {
		
		array[ 0 ] = null;
		
		int initIndex = 0;
		boolean present = false;
		
		for(int i=1; i<=currentSize; i++) {
			if(array[i] != null) {
				if(x.hashCode() == array[i].hashCode()) {
					present = true;
				}
			}
		}
		
		if(present == true) {
			for(int i=1; i<=currentSize; i++) {
				if(array[i] != null) {
					if(x.hashCode() == array[i].hashCode()) {
						array[i] = null;
						initIndex = i;
						break;
					}
				}
			}
			for(int i=initIndex; i<=currentSize-1; i++) {
				array[i] = array[i+1];
			}
			array[currentSize] = null;
			
			currentSize--;
		}
		add(x);
		
		//System.out.println(this.toString());
		
		return true;
	}
	
	/**
	 * Construct an empty PriorityQueue.
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue( )
	{
		currentSize = 0;
		cmp = null;
		array = (AnyType[]) new Object[ DEFAULT_CAPACITY + 1 ];
	}
	
	/**
	 * Construct an empty PriorityQueue with a specified comparator.
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue( Comparator<? super AnyType> c )
	{
		currentSize = 0;
		cmp = c;
		array = (AnyType[]) new Object[ DEFAULT_CAPACITY + 1 ];
	}
	
	 
	/**
	 * Construct a PriorityQueue from another Collection.
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue( WeissCollection<? extends AnyType> coll )
	{
		cmp = null;
		currentSize = coll.size( );
		array = (AnyType[]) new Object[ ( currentSize + 2 ) * 11 / 10 ];
		
		int i = 1;
		for( AnyType item : coll )
			array[ i++ ] = item;
		buildHeap( );
	}
	
	/**
	 * Compares lhs and rhs using comparator if
	 * provided by cmp, or the default comparator.
	 */
	@SuppressWarnings("unchecked")
	private int compare( AnyType lhs, AnyType rhs )
	{
		if( cmp == null )
			return ((Comparable)lhs).compareTo( rhs );
		else
			return cmp.compare( lhs, rhs );	
	}
	
	/**
	 * Adds an item to this PriorityQueue.
	 * @param x any object.
	 * @return true.
	 */
	public boolean add( AnyType x )
	{
		if( currentSize + 1 == array.length )
			doubleArray( );

			// Percolate up
		int hole = ++currentSize;
		array[ 0 ] = x;
		
		for( ; compare( x, array[ hole / 2 ] ) < 0; hole /= 2 )
			array[ hole ] = array[ hole / 2 ];
		array[ hole ] = x;
		
		return true;
	}
	
	/**
	 * Returns the number of items in this PriorityQueue.
	 * @return the number of items in this PriorityQueue.
	 */
	public int size( )
	{
		return currentSize;
	}
	
	/**
	 * Make this PriorityQueue empty.
	 */
	public void clear( )
	{
		currentSize = 0;
	}
	
	/**
	 * Returns an iterator over the elements in this PriorityQueue.
	 * The iterator does not view the elements in any particular order.
	 */
	public Iterator<AnyType> iterator( )
	{
		return new Iterator<AnyType>( )
		{
			int current = 0;
			
			public boolean hasNext( )
			{
				return current != size( );
			}
			
			@SuppressWarnings("unchecked")
			public AnyType next( )
			{
				if( hasNext( ) )
					return array[ ++current ];
				else
					throw new NoSuchElementException( );
			}
			
			public void remove( )
			{
				throw new UnsupportedOperationException( );
			}
		};
	}
	 
	/**
	 * Returns the smallest item in the priority queue.
	 * @return the smallest item.
	 * @throws NoSuchElementException if empty.
	 */
	public AnyType element( )
	{
		if( isEmpty( ) )
			throw new NoSuchElementException( );
		return array[ 1 ];
	}
	
	/**
	 * Removes the smallest item in the priority queue.
	 * @return the smallest item.
	 * @throws NoSuchElementException if empty.
	 */
	public AnyType remove( )
	{
		AnyType minItem = element( );
		array[ 1 ] = array[ currentSize-- ];
		//System.out.println("Remove element queue");
		percolateDown( 1 );
		//System.out.println("\tPrecolateDown");

		return minItem;
	}


	/**
	 * Establish heap order property from an arbitrary
	 * arrangement of items. Runs in linear time.
	 */
	private void buildHeap( )
	{
		for( int i = currentSize / 2; i > 0; i-- )
			percolateDown( i );
	}

	private static final int DEFAULT_CAPACITY = 100;

	private int currentSize;   // Number of elements in heap
	private AnyType [ ] array; // The heap array
	private Comparator<? super AnyType> cmp;

	/**
	 * Internal method to percolate down in the heap.
	 * @param hole the index at which the percolate begins.
	 */
	private void percolateDown( int hole )
	{
		int child;
		AnyType tmp = array[ hole ];

		for( ; hole * 2 <= currentSize; hole = child )
		{
			child = hole * 2;
			if( child != currentSize &&
					compare( array[ child + 1 ], array[ child ] ) < 0 )
				child++;
			if( compare( array[ child ], tmp ) < 0 )
				array[ hole ] = array[ child ];
			else
				break;
		}
		array[ hole ] = tmp;
	}
	
	/**
	 * Internal method to extend array.
	 */
	@SuppressWarnings("unchecked")
	private void doubleArray( )
	{
		AnyType [ ] newArray;

		newArray = (AnyType []) new Object[ array.length * 2 ];
		for( int i = 0; i < array.length; i++ )
			newArray[ i ] = array[ i ];
		array = newArray;
	}
}
