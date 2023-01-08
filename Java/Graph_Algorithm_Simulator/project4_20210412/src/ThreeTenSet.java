//TODO: JavaDocs

//Don't forget, use inheritDoc to save yourself
//a lot of time for inherited methods from Set!!!

import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

class ThreeTenSet<E> implements Set<E> {
	//****************************
	//   YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// These are some methods we didn't write for you, but you could write.
	// if you need/want them for building your graph. We will not test
	// (or grade) these methods.
	//****************************
	
	/**
	 * Gets rid of the elements in the c list, passed as a parameter, from the hash table
	 * @param c list with elements to be removed
	 * @return true if elements were able to be removed, false otherwise.
	 * @deprecated not implemented
	 */
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Keep elements in the c list and remove the ones that are not in the c list
	 * @param c list with elements to be kept
	 * @return true if the c list could be kept and the rest of elements in the table were removed
	 * @deprecated not implemented
	 */
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/** 
	 * @param a array with data of generic type 
	 * @return an array with data of generic type
	 * @deprecated not implemented
	 */
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	/** 
	 * @param c list of elements 
	 * @return true if all elements in c are in the list
	 * @deprecated not implemented
	 */
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/** 
	 * @param o object to be compared
	 * @return true if the list "o" is equal to the current table
	 * @deprecated not implemented
	 */
	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @return a hashcode
	 */
	public int hashCode() {
		throw new UnsupportedOperationException();
	}
	
	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------
	
	public static void main(String[] args) {
		ThreeTenSet<Integer> set = new ThreeTenSet<>();
		
		if(set.add(1) && set.add(2) && set.add(-51) && !set.add(1) && set.size() == 3) {
			System.out.println("yay 1");
		}
		
		if(set.remove(1) && !set.remove(1) && set.size() == 2 && set.remove(-51) && set.toArray()[0].equals(2)) {
			System.out.println("yay 2");
		}
		
		set.clear();
		ThreeTenSet<Integer> set2 = new ThreeTenSet<>();
		for(int i = -100; i < 100; i += 17) set2.add(i);
		
		if(set.size() == 0 && set.addAll(set2) && set.size() == 12) {
			System.out.println("yay 3");
		}
		
		System.out.println();
		System.out.println(set);
		/*
		[0]: <-32,-32>
		[2]: <-66,-66>
		[3]: <2,2>
		[4]: <-100,-100>
		[5]: <36,36>
		[6]: <70,70>
		[15]: <-15,-15>
		[17]: <-49,-49>
		[19]: <-83,-83>
		[20]: <19,19>
		[21]: <53,53>
		[23]: <87,87>
		*/
	}
	
	//****************************
	//   DO NOT EDIT ANYTHING BELOW THIS LINE (except to add the JavaDocs)
	// We will grade these methods to make sure they still work, so don't break them.
	//****************************
	
	/** 
	 * Hash table where all elements from the current list will be stored
	 */
	private ThreeTenMap<E,E> storage = new ThreeTenMap<>(7);
	
	/**
	 * 
	 * @param e elements to be added to the list
	 * @return true if the element was successfully added, false otherwise
	 */
	public boolean add(E e) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		if(e == null) throw new NullPointerException();
		return storage.put(e, e) == null;
	}
	
	/**
	 * This method adds multiple elements to the table
	 * @param c list with elements to be added 
	 * @return true if all elements in c could be added
	 */
	public boolean addAll(Collection<? extends E> c) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		boolean changedSomething = false;
		
		for(E e : c) {
			if(e != null) {
				changedSomething = add(e) || changedSomething;
			}
		}
		
		return changedSomething;
	}

	/** 
	 * Removed all elements from the current list
	 */
	public void clear() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		storage = new ThreeTenMap<>(7);
	}

	/**
	 * 
	 * @param o object to look for in the list
	 * @return true if object o is in the list, false otherwise
	 */
	public boolean contains(Object o) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.get(o) != null;
	}

	/**
	 * Checks if the list is empty
	 * @return true is empty, false otherwise
	 */
	public boolean isEmpty() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return size() == 0;
	}

	/** 
	 * Removes the element o from the list
	 * @param o element to be removed from the list
	 * @return true if element o was successfully removed, false otherwise.
	 */
	public boolean remove(Object o) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.remove(o) != null;
	}

	/**
	 * 
	 * @return An integer number representing the size of the list
	 */
	public int size() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.size();
	}

	/**
	 * Converts the list to an array
	 * @return A 1D array with the elements from the list
	 */
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		ThreeTenMap<E,E>.Pair<E,E>[] arr = (ThreeTenMap<E,E>.Pair<E,E>[]) storage.toArray();

		Object[] ret = new Object[arr.length];
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[i].key;
		}
		
		return ret;
	}

	/**
	 * This method generated a string with information from the elements in the list
	 * @return a string with information from all elements in the list
	 */
	public String toString(){
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.toString();
	}

	/**
	 * This method generated a string with information from the elements in the list
	 * @param showEmpty is used to know if whitespaces from the list are printed
	 * @return a string with information from elements in the list
	 */
	public String toString(boolean showEmpty) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.toString(showEmpty);
	}

	/**
	 * This method is used to iterate over the list
	 * @return An object of type Iterator to traverse the list
	 */
	public Iterator<E> iterator() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return new Iterator<E>() {
			private Object[] values = toArray();
			private int currentLoc = 0;
			
			@SuppressWarnings("unchecked")
			public E next() {
				return (E) values[currentLoc++];
			}
			
			public boolean hasNext() {
				return currentLoc != values.length;
			}
		};
	}
}