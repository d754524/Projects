
// TO DO: add your implementation and JavaDocs.

import java.util.Collection;
import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {
	private int NumElements=0;
	private boolean tf=false;
	private int nulIndex;
	private int counter=0;
	private T hold;
	private static final int INITCAP = 2; 
	private T[] storage; 
	private T[] ar;
	
	@SuppressWarnings("unchecked")
	public DynamicArray(){
		 
		storage = (T[]) new Object[INITCAP]; 
		
	}
	
	/** 
	 * @param initCapacity this will be the initital capacity of the DynamicArray
	 * @throws IllegalArgumentException if initCapacity is zero or a negative number
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray(int initCapacity) throws IllegalArgumentException {
		//constructor
		if(initCapacity<1) {
			throw new IllegalArgumentException("Capacity cannot be zero or negative.");
		}
		storage = (T[]) new Object[initCapacity]; 
	
	}
	
    /**
	* 
	* @return the method size returns the current number of elements in the DynamicArray
	*/
	public int size() {	
		NumElements=0;
		for(int i=0;i<storage.length;i++) {
			if(storage[i]==null) {
				continue;
			}
			else if(storage[i]!=null) {
				NumElements++;
			}
		}
		// Report the current number of elements.
		// O(1)
		
		return NumElements;
	}  
	
	/**
	* @return the method capacity return the max number of elements the DynamicArray can hold
	*/
	public int capacity() {
		
		return storage.length;
	}
	
	/**
	 * replace the item at the given index with the value entered by the user
	 * @param index location where the new value will be inserted
	 * @param value value to be inserted at the index location
	 * @return the old item at the index where the value was inserted
	 */
	@SuppressWarnings("unchecked")
	public T set(int index, T value) {
		
	if(index>storage.length) {
		throw new IndexOutOfBoundsException("Index "+index+" out of bounds!");
	}
		
		ar = (T[]) new Object[storage.length];
		hold = storage[index];
		for(int i=0; i<storage.length;i++) {
			
			if(i==index) {
				
				ar[i]=value;
				
			}
			else
			ar[i] = storage[i];
		}
		
		storage = (T[]) new Object[ar.length];
		for(int i=0;i<ar.length;i++) {
			
			storage[i]=ar[i];		
		}
		
		
		
		return hold;
	}
	
	/**
	 * 
	 * @param index location of the item to be returned
	 * @return the item at the index location
	 * @throws Exception if index location is out of bounds of the DynamicArray
	 */
	public T get(int index)  {
		
		
		if(index>storage.length) {
			try {
				if(storage[index]!=null);
			}catch(Exception e) {
				System.out.println("Index "+index+" out of bounds!");
			}
		}

		return storage[index];
	}
	
	/**
	 * This add method appends a new item to the end of the list. The capacity if doubled if there is
	 * not enough space in the DynamicArray 
	 * @param value item to be added into the DynamicArray 
	 * @return true if item was added successfully, false otherwise. 
	 */
	@SuppressWarnings("unchecked")
	public boolean add(T value) {
		tf=false;
		counter=0;
		for(int i=0;i<storage.length;i++) {	
			if(storage[i]==null) {
				break;
			}
			else if(storage[i]!=null) {
				counter++;
				
				if(counter==storage.length) {
					
					ar = (T[]) new Object[storage.length];
					for(int j=0;j<storage.length;j++) {
						ar[j]=storage[j];
					}
					
					storage = (T[]) new Object[storage.length*2]; 
				
					for(int k=0;k<ar.length;k++) {
						storage[k]=ar[k];
					}
					i=-1;
					counter=0;
				}
				
			}
		}

	if(counter!=storage.length || counter==ar.length) {	

		for(int i=0;i<storage.length;i++) {
					if(storage[i]==null) {

						storage[i]=value;
						break;
					}
	
			}
				
				tf=true;
	}
		
		return tf;
	}
	
	/**
	 * This overloaded add method with two parameters adds an item at a certain location shifting items
	 * if neccessary. Doubles capacity if there is not enough space available and it can also append an 
	 * item at the end of the list.
	 * @param index location where new value will be added
	 * @param value Element to be added at the index location
	 * @throws Exception if index is out of the range of capacity
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value) {
		counter=0;
		
//		if(index>storage.length) {
//			throw new Exception("Index "+ index +" out of bounds!");
//		}
	try {	
			if(index>storage.length) {
				throw new Exception();
			}
		
		for(int i=0;i<storage.length;i++) {
			if (storage[i]!=null) {
				counter++;
			}
		}
		
		if(counter==storage.length && index==storage.length) {
			ar = (T[]) new Object[storage.length];
			for(int j=0;j<storage.length;j++) {
				ar[j]=storage[j];
			}
			
			storage = (T[]) new Object[storage.length*2]; 
			for(int j=0;j<ar.length;j++) {
				storage[j]=ar[j];
			}
			
		}
			
		counter=0;	
			
			
		if(storage[index]==null) {
			storage[index]=value;
		}
		else if(storage[index]!=null){
			
			for(int i=0;i<storage.length;i++) {
				if(storage[i]!=null) {
					counter++;
				}
			}
			if(counter==storage.length) {
				ar = (T[]) new Object[storage.length];
				for(int j=0;j<storage.length;j++) {
					ar[j]=storage[j];
				}
				
				storage = (T[]) new Object[storage.length*2]; 
			
				for(int k=0;k<ar.length;k++) {
					storage[k]=ar[k];
				}
				for(int i=index;i<storage.length;i++) {
					if(storage[i]==null) {
						nulIndex=i;
						break;
					}
				}
				
				for(int i=nulIndex;i>=index;i--) {
					storage[i]=storage[i-1];
					
					
				}
				storage[index]=value;
				
			}
			
			else if(counter!=storage.length) {
				
				for(int i=index;i<storage.length;i++) {
					if(storage[i]==null) {
						nulIndex=i;
						break;
					}
				}
				
				for(int i=nulIndex;i>=index;i--) {
					storage[i]=storage[i-1];
					
					
				}
				storage[index]=value;
	
			}
			

		}
	}catch(Exception e) {
		
		System.out.println("Index "+ index +" out of bounds!");
	}
		

	}
	
	/**
	 * removes the item at the given index and shift elements to remove the gap after the removal.
	 * @param index location of the item to be removed
	 * @return removed item
	 * @throws Exception if invalid index is passed
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		
	try {
		if(index>storage.length-1) {
			throw new Exception();
		}
		
		hold = storage[index];
		ar = (T[]) new Object[storage.length];
		
		storage[index]=null;
		
		for(int i=0;i<storage.length;i++) {
			
			if(storage[i]==null) {
				
				for(int j=i;j<storage.length-1;j++) {
					storage[j]=storage[j+1];
					storage[j+1]=null;
				}
				break;
			}
			else
				continue;
			
		}
		
		int counter=0;
		
		double thereshold = storage.length*0.33;
		
		for(int i=0;i<storage.length;i++) {
			

			if(storage[i]!=null) {
				counter++;
			}
			
		}
		int increase=0;
		
		if(counter<thereshold) {
			
			ar = (T[]) new Object[storage.length/2];
			
				for(int i =0;i<storage.length;i++) {
				
					if(storage[i]!=null) {
					ar[increase++]=storage[i];
					}
				}
				
			storage = (T[]) new Object[ar.length]; 
			for(int i =0;i<ar.length;i++) {
				storage[i]=ar[i];
			}
			
			for(int i=storage.length-2;i>=0;i--) {
				
				if(storage[i+1]==null ) {
					storage[i+1]=storage[i];
					storage[i]=null;
				}
				else
					continue;
				
			}
			
		}
		
		
	}catch(Exception e) {
		System.out.println("Index "+ index +" out of bounds!");
	}
	return hold;
	
}
	
	
	public Iterator<T> iterator() {
		

		return new Iterator<T>() {
			
			private int indx=0;
			
			
			/**
			 * @return the next item in the DynamicArray
			 */
			public T next() {
				
				return storage[indx++];
				
			}
			
			/**
			 * @return true if there is an item in the next index. Returns false otherwise
			 */
			public boolean hasNext() {
				

				return indx<storage.length;
			}
		};
	}
	
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//******************************************************
	
//	public String toString() {
//		//This method is provided for debugging purposes
//		//(use/modify as much as you'd like), it just prints
//		//out the list ifor easy viewing.
//		StringBuilder s = new StringBuilder("Dynamic array with " + size()
//			+ " items and a capacity of " + capacity() + ":");
//		for (int i = 0; i < size(); i++) {
//			s.append("\n  ["+i+"]: " + get(i));
//		}
//		return s.toString();
//		
//	}
//	
//	JavaDoc note: How do you document a main? See Simulation.java for an example
	public static void main(String args[]) throws Exception{
		//These are _sample_ tests. If you're seeing all the "yays" that's
		//an excellend first step! But it might not mean your code is 100%
		//working... You may edit this as much as you want, so you can add
		//own tests here, modify these tests, or whatever you need!
		
		DynamicArray<Integer> ida = new DynamicArray<>();
		if ((ida.size() == 0) && (ida.capacity() == 2)){
			System.out.println("Yay 1");
		}

		boolean ok = true;
		for (int i=0; i<3;i++)
			ok = ok && ida.add(i*5);
		
		if (ok && ida.size()==3 && ida.get(2) == 10 && ida.capacity() == 4 ){
			System.out.println("Yay 2");
		}
		
		ida.add(1,-10);
		ida.add(4,100);
		if (ida.set(1,-20)==-10 && ida.get(2) == 5 && ida.size() == 5 
			&& ida.capacity() == 8 ){
			System.out.println("Yay 3");
		}
		
		if (ida.remove(0) == 0 && ida.remove(0) == -20 && ida.remove(2) == 100 
			&& ida.size() == 2 && ida.capacity() == 4 ){
			System.out.println("Yay 4");
		}
		
		//Uncomment this after doing the iterator for testing
		
		System.out.print("Printing values: ");
		for(Integer i : ida) {
			System.out.print(i);
			System.out.print(" ");
		}
		
	}
}