//TO DO: Complete this class, add JavaDocs

//Do not add any more imports!
import java.util.Iterator;
import java.util.Set;

public class MemMan implements Iterable<MemBlock> {

	//******************************************************
	//****    IMPORTANT: DO NOT CHANGE/ALTER/REMOVE     ****
	//****    ANYTHING PROVIDED LIKE THESE INTS HERE... ****
	//******************************************************
	
	public static final int FIRST_FIT = 0;
	public static final int BEST_FIT = 1;
	public static final int WORST_FIT = 2;
	public static final int NEXT_FIT = 3;
	
	private static BareNode lastUsed;
	 public static BareNode head;
	 int type;
	 int size;
	 BlockDoubly  list = new BlockDoubly();
	 /**
	  * 
	  * @param type  a type policy that is passed by factory
	  * @param factoryHead the new node with new policy passed by factory
	  */
	 
	 public MemMan(int type, BareNode factoryHead) {
		 
		 this.size=factoryHead.block.size;
		 
		 head = factoryHead;
		 
		 this.type=type;
		  
		 list.add(factoryHead);
		 
	 }
	
	/**
	 * 
	 * @param type accepts a type policy entered by the user
	 * @param head accepts a new node
	 * @return an instance of MemMan
	 */
	static MemMan factory(int type, BareNode head) {
		
		return new MemMan(type,head);		
		
	}
	
	/**
	 * 
	 * @return the head of the doubly linked list
	 */
	public BareNode getHead() {
		
		return list.head;
		
	}
	
	/**
	 * 
	 * @param size the size of the new MemBlock which will be added to the list based on its policy
	 * @return
	 */
	public BareNode malloc (int size) {
		
		
		BareNode bnt= list.head;
		int freeSpace = 0;
		int totalSpace=0;
		while(bnt!=null) {
			
			if(bnt.block.isFree) {
				freeSpace+=bnt.block.size;
			}
			totalSpace+=bnt.block.size;
			bnt=bnt.getNext();
		}
		if(size>freeSpace)return null;
		if(size>totalSpace)return null;
		
		if(type<0 || type>3)return null;

		if(size<1)return null;
				
		
		if(this.type==0) {
		
			BareNode h = list.head;
			
			
			if(h.block.isFree && h.block.size>=size) {
			
				BareNode bn = new BareNode(new MemBlock(0,size,false));
				MemBlock newBlock = new MemBlock(bn.block.size+bn.block.addr, list.head.block.size-size,true);
				list.head.block=newBlock;
				list.head.setPrev(bn);
				
				
				
				bn.setNext(list.head);
				list.head=bn;
				
				if(bn.getNext().block.isFree) {
				lastUsed=bn.getNext();
				}
				
				return list.head;
			}
			else {
				
				while(h != null) {
					
					if(h.block.isFree && h.block.size>=size) {
						BareNode bn = new BareNode(new MemBlock(h.getPrev().block.addr+h.getPrev().block.size,size,false));
						MemBlock newBlock = new MemBlock(bn.block.size+bn.block.addr, h.block.size-size,true);
						h.block=newBlock;
						
						h.getPrev().setNext(bn);
						bn.setPrev(h.getPrev());
						bn.setNext(h);
						h.setPrev(bn);
						if(bn.getNext().block.isFree) {
							lastUsed=bn.getNext();
							}
						return bn;
						
					}
					
					h=h.getNext();
					
				}
				
				return null;
			}
		

		}
		
	else if(this.type == 1) {
			
		
		
		
			int BestSize =0;
			BareNode y=list.head;
			BareNode NodeHolder=null;
			
			while(y!=null) {
				
					if(y.block.isFree && y.block.size>=size) {
						
						BestSize=y.block.size;
						NodeHolder=y;
						y=y.getNext();
						break;	
					}
					y=y.getNext();
			}
			while(y!=null) {
				
					if(y.block.isFree) {
						
						if(y.block.size<BestSize && y.block.size>=size) {
							
							BestSize=y.block.size;
							NodeHolder=y;
							
						}
							
					}
				
				y=y.getNext();
			}
		
		
			
		if(NodeHolder.getPrev()==null) {
			
			BareNode bn = new BareNode(new MemBlock(0,size,false));
			MemBlock newSize = new MemBlock(bn.block.size,list.head.block.size-size,true);
			list.head.block=newSize;
			
			list.head.setPrev(bn);
			bn.setNext(list.head);
			list.head=bn;
			if(bn.getNext().block.isFree) {
				lastUsed=bn.getNext();
				}
			return bn;
		}
		else if(NodeHolder.getPrev()!=null) {
			
			if(NodeHolder.block.size==size) {
				MemBlock newSize = new MemBlock(NodeHolder.block.addr,size,false);
				NodeHolder.block=newSize;
				return NodeHolder;
				
			}
			else {
			BareNode bn = new BareNode(new MemBlock(NodeHolder.block.addr,size,false));
			MemBlock newSize = new MemBlock(bn.block.addr+bn.block.size,NodeHolder.block.size-size,true);
			NodeHolder.block=newSize;
			
			NodeHolder.getPrev().setNext(bn);
			bn.setPrev(NodeHolder.getPrev());
			bn.setNext(NodeHolder);
			NodeHolder.setPrev(bn);
			if(bn.getNext().block.isFree) {
				lastUsed=bn.getNext();
				}
			return bn;
		}
		
		
	}
	}	
	
	else if(this.type == 2) {
			
		BareNode h = list.head;
		BareNode holder=null;
		
		int sizeh = 0;
		
					while(h!=null) {
						
							if(h.block.isFree) {
								
								sizeh=h.block.size;
								holder = h;
								h=h.getNext();
								break;
							}
						h=h.getNext();
					}
					while(h!=null) {
						
							if(h.block.isFree) {
								
								if(h.block.size>sizeh) {
									
										sizeh=h.block.size;
										holder=h;
									
								}
								
								
								
							}
						
						h=h.getNext();
					}
		
		
		if(holder.getPrev()==null) {
			
			BareNode bn = new BareNode(new MemBlock(holder.block.addr,size,false));
			MemBlock newBlock = new MemBlock(holder.block.addr+size,holder.block.size-size,true);
			holder.block=newBlock;
			bn.setNext(holder);
			holder.setPrev(bn);
			list.head=bn;
			if(bn.getNext().block.isFree) {
				lastUsed=bn.getNext();
				}
			return list.head;
		}
		else {
			
			BareNode bn = new BareNode(new MemBlock(holder.block.addr,size,false));
			holder.getPrev().setNext(bn);
			bn.setPrev(holder.getPrev());
			MemBlock newBlock = new MemBlock(holder.block.addr+size,holder.block.size-size,true);
			holder.block=newBlock;
			bn.setNext(holder);
			holder.setPrev(bn);
			if(bn.getNext().block.isFree) {
				lastUsed=bn.getNext();
				}
			return bn;
		}
		
	}

	
	else if(this.type==3) {
		
		BareNode bn = lastUsed;
		
		while(bn!=null) {
			
			if(bn.block.isFree && bn.block.size>=size) {
				
				BareNode newNode = new BareNode(new MemBlock(bn.block.addr,size,false));
				MemBlock newB = new MemBlock(bn.block.addr+size,bn.block.size-size,true);
				
				bn.getPrev().setNext(newNode);
				newNode.setPrev(bn.getPrev());
				bn.block=newB;
				newNode.setNext(bn);
				bn.setPrev(newNode);
				return newNode;
				
			}
			bn=bn.getPrev();
		}
	
		return null;
		
	}

		return null;
			
	}
	/**
	 * The method sweep checks if there are adjacent free space and coalesce them
	 */
	public void sweep() {
		
		BareNode c = list.head;
		int si=0;
		int add = 0;
		BareNode ref = null;
		while(c!=null) {
			
			if(c.block.isFree) {
				if(c.getPrev()==null||c.getPrev().block.isFree==false) {
					ref=c;
					 add=c.block.addr;
				}
				si+=c.block.size;
				if(c.getNext()==null) {
					break;
				}
				else if(c.getNext().block.isFree ) {
					c=c.getNext();
				}

				else {
					c=c.getNext();
					break;
				}
	
			}else	
			c=c.getNext();
		}
		BareNode newNode1 = new BareNode(new MemBlock(add,si,true));
		
		if(ref.getPrev()==null) {
			
			newNode1.setNext(c);
			c.setPrev(newNode1);
			list.head=newNode1;
			return;
		}
		
		if(ref.getPrev().block.isFree==false) {
			ref.getPrev().setNext(newNode1);
			newNode1.setPrev(ref.getPrev());
		}
		
		if(c.block.isFree==false) {
			c.setPrev(newNode1);
			newNode1.setNext(c);
		}
		
		
	}
	
	/**
	 * 
	 * @param node node to be freed
	 * @return true if the node passed in the parameter was successfully freed
	 */
	public boolean free(BareNode node) {
		if(node==null)return false;
		sweep();
		MemBlock newBlock = new MemBlock(node.block.addr,node.block.size,true);
		
		node.block=newBlock;

		if(node.getPrev()!=null && node.getPrev().block.isFree) {
			
			BareNode newNode = new BareNode(new MemBlock(node.getPrev().block.addr,node.block.size+node.getPrev().block.size,true));
			if(node.getPrev().getPrev()!=null) {
			node.getPrev().getPrev().setNext(newNode);
			newNode.setPrev(node.getPrev().getPrev());
			newNode.setNext(node.getNext());
			newNode.getNext().setPrev(newNode);
			}
			else {
				
				newNode.setNext(node.getNext());
				node.getNext().setPrev(newNode);
				list.head=newNode;
				if(list.head.getNext().block.isFree) {
					BareNode newNode2 = new BareNode(new MemBlock(0,list.head.block.size+list.head.getNext().block.size,true));
					list.head=newNode2;
				}
			}
		}
		BareNode x = node;
		int s=0;
		while(x!=null && x.block.isFree) {
			s+=x.block.size;
			x=x.getNext();
		}
		BareNode newNode = new BareNode(new MemBlock(node.block.addr,s,true));
		if(x==null) {
			if(node.getPrev()!=null) {
				node.getPrev().setNext(newNode);
				newNode.setPrev(node.getPrev());
				
			}
			if(node.getPrev()==null) {
				
				list.head=newNode;
				return true;
			}
		}
		
		
		
		return true;
	}
	
	
	/**
	 * 
	 * @param node node to be resized 
	 * @param size new size of the MemBlock of the node passed in to the function
	 * @return the node which contains the new resized MemBlock
	 */
	public BareNode realloc(BareNode node, int size) {
		
		BareNode temp = node;
		int freeSpace=0;
		while(temp!=null) {
			
			if(temp.block.isFree) {
				freeSpace+=temp.block.size;
			}
			temp=temp.getNext();
		}
		
		if(size>freeSpace)return null;
		
		
		
		if(size<node.block.size) {	
			
				MemBlock newBlock = new MemBlock(node.block.addr,size,node.block.isFree);
				BareNode newEmptyBlock = new BareNode(new MemBlock(newBlock.addr+newBlock.size,node.block.size-newBlock.size,true));
	
				
					node.block=newBlock;
					newEmptyBlock.setPrev(node);
					newEmptyBlock.setNext(node.getNext());
					node.getNext().setPrev(newEmptyBlock);
					node.setNext(newEmptyBlock);
					
					if(node.getPrev()==null) {
					list.head=node;
					}
					sweep();
					return node;
				
					
		}
	
		else if(size>node.block.size) {
			
			
			MemBlock mb=new MemBlock(node.block.addr,size,false);
			int tempVar = size-node.block.size;
			
			
						if(node.getNext().block.isFree && node.getNext().block.size>=tempVar) {
							
							if(node.getNext().block.size==size) {
		
								node.block=mb;
								node.getNext().getNext().setPrev(node);
								node.setNext(node.getNext().getNext());
								
								return node;
							}
								
							MemBlock Resize = new MemBlock(node.getNext().block.addr+tempVar,node.getNext().block.size-tempVar,true);
							
							node.block=mb;
							node.getNext().block=Resize;
							return node;
							
						}
						
			MemBlock tailResize = new MemBlock(list.tail.block.addr+size,list.tail.block.size-tempVar,true);
			node.block=mb;
			list.tail.block=tailResize;
			BareNode bn = list.head;
			
					while(bn!=null) {
						
						if(bn.getNext()==null) {
							break;
						}
						
						else {
							MemBlock tmb = new MemBlock(bn.block.addr+bn.block.size,bn.getNext().block.size,bn.getNext().block.isFree);
							bn.getNext().block=tmb;	
						}
						bn=bn.getNext();
					}
		
				sweep();
				return node;
		}
	
	
		
		
		return null;
	}
	
	

	/**
	 * 
	 * @param addrs a set of MemBlock addresses
	 * @return the number of bytes that were recovered
	 */
	public int garbageCollect(Set<Integer> addrs) {
	
		BareNode bn = getHead();
		int sum=0;
		int sizeh=0;
		sweep();
		
		if(addrs.size()==0)return sum;
		
		while(bn!=null) {
			
				if(bn.block.isFree==false) {
					bn.setMarked(true);
				}
				bn=bn.getNext();
		}
		bn=getHead();
		

		bn=list.head;
		
		while(bn!=null) {
			
				if(bn.block.addr==list.tail.block.addr) {
					
						MemBlock tb = new MemBlock(sizeh,bn.block.size+sum,bn.block.isFree);
					System.out.println(list);
						bn.block=tb;	
					
						return sum;
				}
			
			
				if(bn.isMarked()) {
						sizeh+=bn.block.size;
						bn=bn.getNext();
						
						continue;
				}
				else {
	
					if(bn.getPrev()!=null) {
							sum+=bn.block.size;

							MemBlock tb = new MemBlock(bn.getPrev().block.addr+bn.getPrev().block.size,bn.getNext().block.size,bn.getNext().block.isFree);
							bn.getNext().block=tb;
							bn.getPrev().setNext(bn.getNext());
							bn.getNext().setPrev(bn.getPrev());
							
							bn=bn.getNext();
							
					}
					
			
					else if(list.head==bn && bn.block.isFree) {
						sum+=bn.block.size;
						
						MemBlock tb = new MemBlock(0,bn.getNext().block.size,bn.getNext().block.isFree);
						
						bn.getNext().block=tb;
						list.head=bn.getNext();
						bn=bn.getNext();
						
					}
					
					else
					bn=bn.getNext();
				}
		}
		
		return sum;
	}
	

	
	

	
	//******************************************************
	//****    IMPORTANT: DO NOT CHANGE/ALTER/REMOVE     ****
	//****    ANYTHING PROVIDED LIKE THE INSTANCE       ****
	//****    VARIABLES IN THIS NESTED CLASS. ALSO      ****
	//****    DON'T REMOVE THE CLASS ITSELF OR ANYTHING ****
	//****    STRANGE LIKE THAT.                        ****
	//******************************************************
	public static class BareNode {
		public MemBlock block;
		public BareNode next;
		public BareNode prev;
		public boolean marked;
		
		/**
		 * 
		 * @param block accepts a MemBlock so that a new Node can be created for the list
		 */
		public BareNode(MemBlock block) {
			this.block = block;
			
		}

		
		public MemBlock getBlock() {
			return block;
		}

		public void setBlock(MemBlock block) {
			this.block = block;
		}

		public BareNode getNext() {
			return next;
		}

		public void setNext(BareNode next) {
			this.next = next;
		}

		public BareNode getPrev() {
			return prev;
		}

		public void setPrev(BareNode prev) {
			this.prev = prev;
		}

		public boolean isMarked() {
			return marked;
		}

		public void setMarked(boolean marked) {
			
			this.marked=marked;
				
		}
		

	}
	
	/**
	 * 
	 * @author dazd1
	 *
	 */
	public class BlockDoubly{
		
			 	BareNode head;
			 	BareNode tail;
			public  void add(BareNode node) {			
				
				if(head==null) {
					head=node;
					tail=node;
				}
				else { 
					node.setNext(head);
					head.setPrev(node);
					head=node;
				}
			}
	}

/**
 * Iterator to traverse the list
 */
	@Override
	public Iterator<MemBlock> iterator() {
	
		
		return new Iterator<MemBlock>() {

		
			private BareNode mb = list.head;
			
				public MemBlock next() {
			
					MemBlock tmp = mb.block;
		
					mb = mb.next;

			
					return tmp;
					
				}
		
				public boolean hasNext() {
			
					return mb!=null;
				}
		
		
		};
		
	}

	
}