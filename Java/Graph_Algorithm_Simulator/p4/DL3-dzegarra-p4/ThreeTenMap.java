//TODO:
//  (1) Implement the hash table!
//  (2) JavaDocs

import java.util.Map;
import java.util.Set;

import java.util.Collection; //for returning in the values() function only

//If you uncomment the import to ArrayList below, the grader will manually
//look to make sure you are not using it anywhere else... if you use it
//somewhere else you will get 0pts on the entire project (not a joke).

//uncomment the line below ONLY if you are implementing values().
//import java.util.ArrayList; //for returning in the values() function only

/**
 * Clase para crear una tabla tipo hash
 * @author 
 *
 * @param <K> Elemento que actua como llave de la pareja a ingresarse en la tabla hash
 * @param <V> Elemento que actua como valor de la pareja a ingresarse en la tabla hash
 */
class ThreeTenMap<K,V> implements Map<K,V> {
	//********************************************************************************
	//   DO NOT EDIT ANYTHING IN THIS SECTION (except to add the JavaDocs)
	//********************************************************************************
	
	//you must use this storage for the hash table
	//and you may not alter this variable's name, type, etc.
	/**
	 * Arreglo donde será almacenadas todas las parejas de la tabla hash
	 */
	private Pair<K,V>[] storage;
	
	//you must use to track the current number of elements
	//and you may not alter this variable's name, type, etc.
	/**
	 * Cantidad de elementos que contiene la tabla hash
	 */
	private int numElements = 0;
	
	//provided class, do not edit!
	/**
	 * Clase que consiste en cada pareja que será ingresada a la tabla hash
	 * @author
	 *
	 * @param <K> Elemento que será la llave de la pareja
	 * @param <V> Elemento que será el valor de la pareja
	 */
	public class Pair<K,V> {
		K key;
		V value;
		Pair(K key, V value) { this.key = key; this.value = value; }
		public String toString() { return "<" + key + "," + value + ">"; }
	}
	
	//********************************************************************************
	//   YOUR CODE GOES IN THIS SECTION
	//********************************************************************************
	
	
	//IMPORTANT NOTES FOR STUDENTS:
	
	//Remember... if you want an array of ClassWithGeneric<V>, the following format ___SHOULD NOT___ be used:
	//		 ClassWithGeneric<V>[] items = (ClassWithGeneric<V>[]) new Object[10];
	//instead, use this format:
	//		 ClassWithGeneric<V>[] items = (ClassWithGeneric<V>[]) new ClassWithGeneric[10];
	
	//If the hash of something is Integer.MIN_VALUE, you need to manually change it
	//to Integer.MAX_VALUE. Math.abs() won't work on Integer.MIN_VALUE!
	
	//This table does not accept null keys. Why is that important? Think happy little
	//bunnies... (or think tombstones if you don't know about the much cooler bunny thing).
	
	/**
	 * Cantidad de espacios en total en la tabla hash
	 */
	private int slots;
	/**
	 * Cantidad de elementos máxima, en proporción, que puede tener la tabla hash. Si pasa este valor se hace rehash
	 */
	private double maxLoad = 0.5;
	/**
	 * Variable para guardar el valor actual de carga de la tabla hash. Se recalcula al ingresar un nuevo elemento
	 */
	private double loadNow = 0;
	
	@SuppressWarnings("unchecked")
	/**
	 * Constructor de la clase
	 * @param size Tamaño que será tenido en cuenta para crear el arreglo que construirá la tabla hash
	 */
	public ThreeTenMap(int size) {
		int hashSize = 1;
		do {
			hashSize *= 2;
		}while(hashSize < size);
		storage = (Pair<K, V>[])new Pair[hashSize];
		
		this.maxLoad=0.5;
		
		this.slots=hashSize;
		
		
		//Create a hash table where the size of the storage is
		//the smallest power of two larged than the requested size.
		//storage size = # slots in the hash table
		//You may assume the requested size is >= 1
		
		//This will use the default maxLoad of 0.5.
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Constructor de la clase
	 * @param size Tamaño que será tenido en cuenta para crear el arreglo que construirá la tabla hash
	 * @param maxLoad Carga máxima, en proporción, que soporta la tabla hash
	 */
	public ThreeTenMap(int size, double maxLoad) {
		int hashSize = 1;
		do {
			hashSize *= 2;
		}while(hashSize < size);
		storage = (Pair<K, V>[])new Pair[hashSize];
		
		this.maxLoad=0.5;
		
		this.slots=hashSize;
		this.maxLoad = maxLoad;
		
		//Create a hash table where the size of the storage is
		//the smallest power of two larged than the requested size.
		//storage size = # slots in the hash table
		//You may assume the requested size is >= 1
		
		//The max load should also be set by this function. You may
		//assume that this will be > 0 and <= 1
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Limpiar la tabla hash, eliminar todos los elementos
	 */
	public void clear() {
		
		storage =null;
		storage=(Pair<K,V>[])new Pair[this.slots];
		
		numElements=0;
		//the table should return to the original size it had
		//when constructed
		//O(1)
	}
	
	public boolean isEmpty() {
		
		if(numElements==0) {
			return true;
		}
		
		return false;
	}
	
	public int capacity() {
		
		return this.slots;
		
		//return how many "slots" are in the table
		//O(1)	
	}
	
	public int size() {
		
		return numElements;
		
		//return the number of elements in the table
		//O(1)
	}
	
	private boolean slotContainsValue(int index) {
		
		if(storage[index]!=null) {
			return true;
		}
		return false;
		
		//Private helper method for toString()
		//O(1)
		
		//Returns true if a "real" value is at the index given
		//Tombstones (and bunnies) are not "real" values in the map.
	}
	
	
	public V get(Object key) { 
		
		for(int i=0;i<storage.length;i++) {
			if(slotContainsValue(i) == true) {
				if(storage[i].key.equals(key)) {
					return storage[i].value;
				}
			}
		}
		
		return null;
		//Worst case: O(n), Average case: O(1)
	}
	
	public Set<K> keySet() {
		
		ThreeTenSet<K> set = new ThreeTenSet<>();
		
		for(int i=0; i<this.slots; i++) {
			if(storage[i] != null) {
				set.add(storage[i].key);
			}
		}
		
		return set;
		
		
		//a ThreeTenSet is a Set, so return one of those
		//max of O(m) where m = number of slots in the table
	}
	
	public V remove(Object key) {
		for(int i=0;i<storage.length;i++) {
			if(storage[i] != null) {
				if(storage[i].key.equals(key)) {
					V value = storage[i].value;
					storage[i]=null;
					numElements--;
					return value;
				}
			}
		}
		return null;
		
		//Worst case: O(n), Average case: O(1)
	}
	
	public V put(K key, V value) throws NullPointerException{
		if(key==null) {
			throw new NullPointerException("Warning: Key is null.");
		}
		
		if(maxLoad==1) {
			if(slots==numElements) {
				rehash(slots*2);
			}
		}
		
		//System.out.println("Key "+key+" Value"+value);
		
		K otherKey;
		int index = 0;
		int adition = 0;
		
		//System.out.println("Metiendo: <"+key+","+value+">");
		
		String varClass = key.getClass().getSimpleName();
		String strKey = key.toString();
		int intKey = 0;
		if(varClass.equals("Integer")){
			intKey = Integer.parseInt(strKey);
			if(intKey < 0) {
				intKey = intKey*(-1);
			}
			index = intKey%slots;
		}else if(varClass.equals("String")) {
			for(int i=0; i<strKey.length(); i++) {
				index = (index*128+strKey.charAt(i));
				if(index < 0) {
					index+=((Integer.MIN_VALUE)*(-1));
				}
			}
			index = index%slots;
		}
		//System.out.println("Inicial Index: "+index);
		int i = 0;
		int tIndex = index;
		tIndex += (int)((0.5*i) + (0.5*(i*i)));
		//System.out.println("tIndex: "+tIndex);
		while(slotContainsValue(tIndex) == true) {
			//System.out.println("\tSlot ocupado");
			otherKey = storage[tIndex].key;
			if(otherKey.equals(key)) {
				//System.out.println("\t## Ocupado por misma llave -> Cambia valor");
				V oldValue = storage[tIndex].value;
				storage[tIndex].value = value;
				return oldValue;
			}
			i++;
			adition = (int)((0.5*i) + (0.5*(i*i)));
			tIndex += adition;
			//System.out.print("\t// i: "+i+" | adition: "+adition+" | Nuevo tIndex: "+tIndex);
			while(tIndex >= slots) {
				tIndex -= slots;
				//System.out.print(" -> "+tIndex);
			}
			//System.out.println();
		}
		index = tIndex;
		storage[index] = new Pair<>(key,value);
		numElements++;
		
		loadNow = (double)numElements/(double)slots;
		if(loadNow > maxLoad){
			rehash(slots*2);
		}
		
		return null;
	
		//Place value v at the location of key k.
		//This table does not accept null keys and you
		//should throw a NullPointerException if k
		//is null (it _is_ ok for the value to be null!).
		
		//If the key already exists in the table
		//replace the current value with v.
		
		//If _after_ adding, the load on the table is 
		//greater than the max load, expand the table 
		//to twice the table size and rehash. Note ">" != ">=".
		
		//If there is _absolutely no space_ in the table
		//_before_ adding (this can happen with max loads
		//close to 1), then rehash to twice the table size
		//before adding.
		
		//Worst case: O(n), Average case: O(1) if no rehashing
		//O(m) if rehashing is needed.
	}
	
	@SuppressWarnings("unchecked")
	public boolean rehash(int size) {
		
		int hashSize = 1;
		do {
			hashSize *= 2;
		}while(hashSize < size);
		if(numElements > hashSize) {
			return false;
		}
		Pair<K,V>[] storageCopy;
		
		storageCopy = (Pair<K, V>[])new Pair[slots];
		storageCopy = storage;
		
		storage = null;
		slots = hashSize;
		storage = (Pair<K, V>[])new Pair[slots];
		
		numElements = 0;
		
		for(int i=0; i<storageCopy.length; i++) {
			if(storageCopy[i] != null) {
				put(storageCopy[i].key, storageCopy[i].value);
			}
		}
	
		return true;
		
		//Increase or decrease the size of the storage,
		//to the smallest power of two larged than the
		//requested size (and rehashing all values).
		//storage size = # slots in the hash table
		
		//Note: you should start at the beginning of the
		//old table and go through each index in order
		//to move items into the new table. 
		
		//If you go backwards, etc. your elements will end up 
		//out of order compared to the expected order.
		
		//If the new size would result in a load greater
		//than the max load, return false. Note ">" != ">=".
		//Return true if you were able to rehash.
	}
	
	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------
	
	public static void main(String[] args) {
		//main method for testing, edit as much as you want
		ThreeTenMap<Integer,Integer> t1 = new ThreeTenMap<>(3);
		ThreeTenMap<String,String> t2 = new ThreeTenMap<>(4,1);
		
		t1.put(0,1);
		t1.put(4,1);
		
		if(t1.toString().equals("[0]: <0,1>\n[1]: <4,1>") && t1.toString(true).equals("[0]: <0,1>\n[1]: <4,1>\n[2]: null\n[3]: null")) {
			System.out.println("Yay 1");
		}
		
		t1.put(8,2);		
		t1.put(20,2);
		
		if(t1.toString().equals("[0]: <0,1>\n[1]: <8,2>\n[4]: <4,1>\n[5]: <20,2>") && t1.toString(true).equals("[0]: <0,1>\n[1]: <8,2>\n[2]: null\n[3]: null\n[4]: <4,1>\n[5]: <20,2>\n[6]: null\n[7]: null")) {
			System.out.println("Yay 2");
		}
		
		if(t1.put(4,10) == 1 && t1.size() == 4 && t1.capacity() == 8 && t1.get(4) == 10) {
			System.out.println("Yay 3");
		}

		t2.put("a","apple");	
		t2.put("b","banana");
		t2.put("banana","b");
		t2.put("b","butter");
		t2.put("apple","a");
		
		if(t2.toString().equals("[0]: <apple,a>\n[1]: <a,apple>\n[2]: <b,butter>\n[3]: <banana,b>") && t2.toString(true).equals("[0]: <apple,a>\n[1]: <a,apple>\n[2]: <b,butter>\n[3]: <banana,b>")) {
			System.out.println("Yay 4");
		}
		
		// t2.put("c","carrot") == null -> no puede ser null porque regresa el valor V = "carrot"
		if(t2.put("c","carrot") == null && t2.size() == 5 && t2.capacity() == 8 && t2.rehash(4) == false && t2.rehash(5) == true && t2.size() == 5 && t2.capacity() == 8) {
			System.out.println("Yay 5");
		}
		
		System.out.println("#####################");
		System.out.println("Print all t2");
		System.out.println(t2.toString(true));
		System.out.println("#####################");
		
		System.out.println("#####################");
		System.out.println("Print all keys of t2");
		for (Object key : t2.keySet()) {
            System.out.println("key: " + key);
		}
		
		System.out.println("#####################");
		System.out.println("Print all values of t2");
		for (Object value : t2.values()) {
            System.out.println("value: " + value);
		}
		
		System.out.println("#####################");
		System.out.println("Is the value of 'banana' in t2?: "+t2.containsValue("banana"));
		System.out.println("Is the value of 'apple' in t2?: "+t2.containsValue("apple"));
		System.out.println("Is the value of 'carrot' in t2?: "+t2.containsValue("carrot"));
		
		System.out.println("#####################");
		System.out.println("Is the key of 'banana' in t2?: "+t2.containsKey("banana"));
		System.out.println("Is the key of 'apple' in t2?: "+t2.containsKey("apple"));
		System.out.println("Is the key of 'carrot' in t2?: "+t2.containsKey("carrot"));
		//This is NOT all the testing you need... several methods are not
		//being tested here! Some method return types aren't being tested
		//either. You need to write your own tests!
		
	}
	
	//********************************************************************************
	//   YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// These are some methods we didn't write for you, but you could write,
	// if you need/want them for building your graph. We will not test
	// (or grade) these methods.
	//********************************************************************************
	
	public Collection<V> values() {
		
		ThreeTenSet<V> values = new ThreeTenSet<>();
		
		for(int i=0; i<this.slots; i++) {
			if(storage[i] != null) {
				values.add(storage[i].value);
			}
		}
		
		return values;
		//throw new UnsupportedOperationException();
	}
	
	public void	putAll(Map<? extends K,? extends V> m) {
		for(K k : m.keySet()) {
			put(k, m.get(k));
		}
		//throw new UnsupportedOperationException();
	}
	
	public boolean containsValue(Object value) {
		for(int i=0; i<this.slots; i++) {
			if(storage[i] != null) {
				if(value.equals(storage[i].value)) {
					return true;
				}
			}
		}
		return false;
		//throw new UnsupportedOperationException();
	}
	
	public Set<Map.Entry<K,V>> entrySet() {
		throw new UnsupportedOperationException();
	}
	
	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}
	
	public int hashCode() {
		throw new UnsupportedOperationException();
	}
	
	public boolean containsKey(Object key) {
		for(int i=0; i<this.slots; i++) {
			if(storage[i] != null) {
				if(key.equals(storage[i].key)) {
					return true;
				}
			}
		}
		return false;
		//throw new UnsupportedOperationException();
	}
	
	//********************************************************************************
	//   DO NOT EDIT ANYTHING BELOW THIS LINE (except to add the JavaDocs)
	// We will check these things to make sure they still work, so don't break them.
	//********************************************************************************
	
	public String toString() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return toString(false);
	}
	
	public String toString(boolean showEmpty) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < storage.length; i++) {
			if(showEmpty || slotContainsValue(i))  {
				s.append("[");
				s.append(i);
				s.append("]: ");
				s.append(storage[i]);
				s.append("\n");
			}
		}
		s.deleteCharAt(s.length()-1);
		return s.toString();
	}

	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		//System.out.println("Num elements: "+numElements);
		Pair<K,V>[] ret = (Pair<K,V>[]) new Pair[numElements];
		for(int i = 0, j = 0; i < storage.length; i++) {
			if(slotContainsValue(i)) {
				ret[j++] = new Pair<>(storage[i].key, storage[i].value);
			}
		}
		
		return (Object[]) ret;
	}
}