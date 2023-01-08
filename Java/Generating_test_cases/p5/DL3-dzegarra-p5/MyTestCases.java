import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class MyTestCases {
	@Test(timeout=2000)
	public void bst_basic() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		assertEquals("Class BST", "BinarySearchTree", bst.getClass().getName());
	}
	
	@Test(timeout=2000)
	public void bst_compare1() {
		BinarySearchTree<Integer> bst1 = createBST();
		BinarySearchTree<Integer> bst2 = createBST();
		boolean c1 = true;
		if(bst1.size() == bst2.size()) {
			int bn1;
			int bn2;
			Iterator<Integer> it1 = bst1.iterator();
			Iterator<Integer> it2 = bst2.iterator();
			while((it1.hasNext() == true) && (it2.hasNext() == true)) {
				bn1 = it1.next();
				bn2 = it2.next();
				if(bn1 != bn2) {
					c1 = false;
				}
			}
		}else {
			c1 = false;
		}
		assertEquals("Class Compare BST", true, c1);
	}
	
	@Test(timeout=2000)
	public void bst_compare2() {
		BinarySearchTree<Integer> bst1 = createBST();
		BinarySearchTree<Integer> bst2 = new BinarySearchTree<Integer>();
		boolean c1 = true;
		if(bst1.size() == bst2.size()) {
			int bn1;
			int bn2;
			Iterator<Integer> it1 = bst1.iterator();
			Iterator<Integer> it2 = bst2.iterator();
			while((it1.hasNext() == true) && (it2.hasNext() == true)) {
				bn1 = it1.next();
				bn2 = it2.next();
				if(bn1 != bn2) {
					c1 = false;
				}
			}
		}else {
			c1 = false;
		}
		assertEquals("Class Compare BST", false, c1);
	}
	
	@Test(timeout=2000)
	public void testInitBST_notNull() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		assertNotNull("Tree is null", bst);
	}
	
	@Test(timeout=2000)
	public void testInitBST_size() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		assertEquals("New tree has elements into", 0, bst.size);
	}
	
	@Test(timeout=2000)
	public void testInitBST_root() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		assertNull("Object tree not null", bst.root);
	}
	
	@Test(timeout=2000)
	public void testInitBST_null() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst = null;
		assertNull("Object tree not null", bst);
	}
	
	@Test(timeout=2000)
	public void testBST_add1_notNull() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(2);
		assertNotNull("Root null after insert element", bst.root);
	}
	
	@Test(timeout=2000)
	public void testBST_add1_root() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(2);
		int bst_root_element = bst.root.element;
		assertEquals("Not insert 1 element", 2, bst_root_element);
	}
	
	@Test(timeout=2000)
	public void testBST_add1_size() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(2);
		assertEquals("Not insert 1 element", 1, bst.size());
	}
	
	@Test(timeout=2000)
	public void testBST_add_left() {
		BinarySearchTree<Integer> bst = createBST();
		int left = bst.root.left.element;
		assertEquals("No insert in left", -4, left);
	}
	
	@Test(timeout=2000)
	public void testBST_add_right() {
		BinarySearchTree<Integer> bst = createBST();
		int right = bst.root.right.element;
		assertEquals("No insert in right", 6, right);
	}
	
	@Test(timeout=2000)
	public void testBST_add2_leftright() {
		BinarySearchTree<Integer> bst = createBST();
		int right = bst.root.left.right.element;
		assertEquals("No insert in left->right", -2, right);
	}
	
	@Test(timeout=2000)
	public void testBST_add2_leftleft() {
		BinarySearchTree<Integer> bst = createBST();
		int left = bst.root.left.left.element;
		assertEquals("No insert in left->left", -8, left);
	}
	
	@Test(timeout=2000)
	public void testBST_add2_rightleft() {
		BinarySearchTree<Integer> bst = createBST();
		int left = bst.root.right.left.element;
		assertEquals("No insert in right->left", 3, left);
	}
	
	@Test(timeout=2000)
	public void testBST_add2_rightright() {
		BinarySearchTree<Integer> bst = createBST();
		int right = bst.root.right.right.element;
		assertEquals("No insert in right->left", 9, right);
	}
	
	@Test(timeout=2000)
	public void testBST_finMin() {
		BinarySearchTree<Integer> bst = createBST();
		int minimum = bst.findMin();
		assertEquals("No minimun finded", -8, minimum);
	}
	
	@Test(timeout=2000)
	public void testBST_finMax() {
		BinarySearchTree<Integer> bst = createBST();
		int maximum = bst.findMax();
		assertEquals("No maximun finded", 9, maximum);
	}
	
	@Test(timeout=2000)
	public void testBST_finEle1() {
		BinarySearchTree<Integer> bst = createBST();
		int element = bst.find(6);
		assertEquals("Element no finded", 6, element);
	}
	
	@Test(timeout=2000)
	public void testBST_finEle2() {
		BinarySearchTree<Integer> bst = createBST();
		assertEquals("Element finded ?? ", null, bst.find(5));
	}
	
	@Test(timeout=2000)
	public void testBST_finEle3() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		assertEquals("Element finded ?? Root null ", null, bst.find(6));
	}
	
	@Test(timeout=2000)
	public void testBST_finEle4() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		assertEquals("Element finded ?? Root null ", null, bst.findMin());
	}
	
	@Test(timeout=2000)
	public void testBST_finEle5() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		assertEquals("Element finded ?? Root null ", null, bst.findMax());
	}
	
	@Test(timeout=2000)
	public void testBST_empty1() {
		BinarySearchTree<Integer> bst = createBST();
		assertEquals("Tree empty", false, bst.isEmpty());
	}
	
	@Test(timeout=2000)
	public void testBST_empty2() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		assertEquals("Tree empty", true, bst.isEmpty());
	}
	
	@Test(timeout=2000)
	public void testBST_size1() {
		BinarySearchTree<Integer> bst = createBST();
		assertEquals("No size equals", 7, bst.size());
	}
	
	@Test(timeout=2000)
	public void testBST_size2() {
		BinarySearchTree<Integer> bst = createBST();
		assertEquals("No size equals", 7, bst.size);
	}
	
	@Test(timeout=2000)
	public void testBST_add1_empLeft() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		assertEquals("Tree empty", null, bst.root.left);
	}
	
	@Test(timeout=2000)
	public void testBST_add1_empRight() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		assertEquals("Tree empty", null, bst.root.right);
	}
	
	@Test(timeout=2000)
	public void testBST_makeEmpty1() {
		BinarySearchTree<Integer> bst = createBST();
		bst.makeEmpty();
		assertNull("No make null tree", bst.root);
	}
	
	@Test(timeout=2000)
	public void testBST_makeEmpty2() {
		BinarySearchTree<Integer> bst = createBST();
		bst.makeEmpty();
		assertEquals("No make null tree", 0, bst.size);
	}
	
	@Test(timeout=2000)
	public void testBST_makeEmpty3() {
		BinarySearchTree<Integer> bst = createBST();
		bst.makeEmpty();
		assertEquals("No make null tree", 0, bst.size());
	}
	
	@Test(timeout=2000)
	public void testBST_iter1() {
		BinarySearchTree<Integer> bst = createBST();
		int nS = 0;
		for(Integer bn : bst) {
			nS += 1;
		}
		assertEquals("Iterator", 7, nS);
	}
	
	@Test(timeout=2000)
	public void testBST_iter2() {
		BinarySearchTree<Integer> bst = createBST();
		boolean bbn;
		Iterator<Integer> it = bst.iterator();
		bbn = it.hasNext();
		assertEquals("Iterator", true, bbn);
		
	}
	
	@Test(timeout=2000)
	public void testBST_iter3() {
		BinarySearchTree<Integer> bst = createBST();
		int bn;
		Iterator<Integer> it = bst.iterator();
		bn = (int) it.next();
		assertEquals("Iterator", 0, bn);
	}
	
	@Test(timeout=2000)
	public void testBST_iter4() {
		BinarySearchTree<Integer> bst = createBST();
		int bn;
		boolean bbn;
		Iterator<Integer> it = bst.iterator();
		while(it.hasNext()) {
			bn = it.next();
		}
		bbn = it.hasNext();
		assertEquals("Iterator", false, bbn);
	}

	@Test(timeout=2000, expected = DuplicateItemException.class)
	public void testBST_addDuplicate1() {
		BinarySearchTree<Integer> bst = createBST();
		bst.insert(0);
	}

	@Test(timeout=2000, expected = DuplicateItemException.class)
	public void testBST_addDuplicate2() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(2);
		bst.insert(2);
	}

	@Test(timeout=2000, expected = java.util.NoSuchElementException.class)
	public void testBST_excep1() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		Iterator<Integer> it = bst.iterator();
		int bn;
		while(it.hasNext()) {
			bn = it.next();
		}
		bn = it.next();
	}
	
	@Test(timeout=2000, expected = ItemNotFoundException.class)
	public void testBST_excep2() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.removeMin();
	}
	
	@Test(timeout=2000, expected = ItemNotFoundException.class)
	public void testBST_excep3() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.remove(0);
	}
	
	@Test(timeout=2000)
	public void testBST_rm1() {
		BinarySearchTree<Integer> bst = createBST();
		bst.removeMin();
		assertEquals("No remove min", 6, bst.size());
	}

	@Test(timeout=2000)
	public void testBST_rm2() {
		BinarySearchTree<Integer> bst = createBST();
		bst.removeMin();
		int minimum = bst.findMin();
		assertEquals("No remove min", -4, minimum);
	}

	@Test(timeout=2000)
	public void testBST_rm3() {
		BinarySearchTree<Integer> bst = createBST();
		int minimum = bst.removeMin(bst.root).element;
		assertEquals("No remove min", 0, minimum);
	}

	@Test(timeout=2000)
	public void testBST_rm4() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(-8);
		int ns = bst.size();
		assertEquals("No remove -8", 6, ns);
	}

	@Test(timeout=2000)
	public void testBST_rm5() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(-8);
		int ns = bst.findMin();
		assertEquals("No remove -8", -4, ns);
	}

	@Test(timeout=2000)
	public void testBST_rm11() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(-8);
		assertEquals("No remove -8", null, bst.root.left.left);
	}

	@Test(timeout=2000)
	public void testBST_rm6() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(-4);
		int ns = bst.size();
		assertEquals("No remove -4", 6, ns);
	}

	@Test(timeout=2000)
	public void testBST_rm7() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(-4);
		assertEquals("No remove -4", null, bst.root.left.right);
	}

	@Test(timeout=2000)
	public void testBST_rm8() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(-4);
		int nn = bst.root.left.left.element;
		assertEquals("No remove -4", -8, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm9() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(-4);
		int nn = bst.root.left.element;
		assertEquals("No remove -4", -2, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm10() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(9);
		int nn = bst.size();
		assertEquals("No remove 9", 6, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm12() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(9);
		int nn = bst.findMax();
		assertEquals("No remove 9", 6, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm13() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(9);
		assertEquals("No remove 9", null, bst.root.right.right);
	}

	@Test(timeout=2000)
	public void testBST_rm17() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(6);
		int nn = bst.size();
		assertEquals("No remove 9", 6, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm14() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(6);
		int nn = bst.root.right.element;
		assertEquals("No remove 6", 9, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm15() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(6);
		assertEquals("No remove 6", null, bst.root.right.right);
	}

	@Test(timeout=2000)
	public void testBST_rm16() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(6);
		int nn = bst.root.right.left.element;
		assertEquals("No remove 6", 3, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm18() {
		BinarySearchTree<Integer> bst = createBST();
		bst.remove(0);
		int nn = bst.root.element;
		assertEquals("No remove 6", 3, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm19() {
		BinarySearchTree<Integer> bst = createBST();
		bst.insert(12);
		bst.remove(9);
		int nn = bst.root.right.right.element;
		assertEquals("Insert 12 No remove 9", 12, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm23() {
		BinarySearchTree<Integer> bst = createBST();
		bst.insert(12);
		bst.remove(9);
		int nn = bst.size();
		assertEquals("Insert 12 No remove 9", 7, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm20() {
		BinarySearchTree<Integer> bst = createBST();
		bst.insert(7);
		bst.remove(9);
		int nn = bst.root.right.right.element;
		assertEquals("Insert 7 No remove 9", 7, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm24() {
		BinarySearchTree<Integer> bst = createBST();
		bst.insert(7);
		bst.remove(9);
		int nn = bst.size();
		assertEquals("Insert 7 No remove 9", 7, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm21() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		bst.insert(6);
		bst.removeMin();
		int nn = bst.root.element;
		assertEquals("Insert 0,6 Remove min", 6, nn);
	}

	@Test(timeout=2000)
	public void testBST_rm22() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		bst.insert(5);
		bst.insert(3);
		bst.remove(3);
		int ns = bst.size();
		assertEquals("Insert 0, 5, 3 Remove 3", 2, ns);
	}

	@Test(timeout=2000)
	public void testBST_rm25() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		bst.insert(5);
		bst.insert(3);
		bst.remove(3);
		int ns = bst.root.right.element;
		assertEquals("Insert 0, 5, 3 Remove 3", 5, ns);
	}

	@Test(timeout=2000)
	public void testBST_rm26() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		bst.insert(5);
		bst.insert(3);
		bst.remove(3);
		assertEquals("Insert 0, 5, 3 Remove 3", null, bst.root.right.left);
	}

	@Test(timeout=2000)
	public void testBST_rm27() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		bst.insert(5);
		bst.insert(3);
		bst.remove(3);
		assertEquals("Insert 0, 5, 3 Remove 3", null, bst.root.right.right);
	}

	@Test(timeout=2000)
	public void testBST_rm28() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		bst.insert(5);
		bst.insert(3);
		bst.remove(5);
		int ns = bst.size();
		assertEquals("Insert 0, 5, 3 Remove 3", 2, ns);
	}

	@Test(timeout=2000)
	public void testBST_rm29() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		bst.insert(5);
		bst.insert(3);
		bst.remove(5);
		int ns = bst.root.right.element;
		assertEquals("Insert 0, 5, 3 Remove 5", 3, ns);
	}

	@Test(timeout=2000)
	public void testBST_rm30() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		bst.insert(5);
		bst.insert(7);
		bst.insert(3);
		bst.remove(0);
		int ns = bst.root.element;
		assertEquals("Insert 0, 5, 7, 3 Remove 0", 5, ns);
	}
	
	private BinarySearchTree<Integer> createBST(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(0);
		assertEquals("Problem size", 1, bst.size());
		bst.insert(6);
		assertEquals("Problem size", 2, bst.size());
		bst.insert(3);
		assertEquals("Problem size", 3, bst.size());
		bst.insert(9);
		assertEquals("Problem size", 4, bst.size());
		bst.insert(-4);
		assertEquals("Problem size", 5, bst.size());
		bst.insert(-2);
		assertEquals("Problem size", 6, bst.size());
		bst.insert(-8);
		assertEquals("Problem size", 7, bst.size());
		return bst;
	}
	
	
	//run the unit tests
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("MyTestCases");
	}
}