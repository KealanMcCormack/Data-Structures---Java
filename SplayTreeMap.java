import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;

//Extends TreeMap which contain important functions for using binary trees
//Makes use of a balanced binary tree and the rotate methods from the class
public class SplayTreeMap<K,V> extends TreeMap<K,V> {

    protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();
	
	public static void main(String[] args) {
		SplayTreeMap<Integer, String> map = new SplayTreeMap<>();
		//java.util.TreeMap<Integer, String> map = new java.util.TreeMap<>();
		Integer[] arr = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};
		
		for(Integer i : arr) {
			System.out.println("i");
			map.put(i, Integer.toString(i));
			System.out.println("j");
		}
		
		System.out.println(map.lastEntry().getKey());
	}

    
	  /** Constructs an empty map using the natural ordering of keys. */
	  public SplayTreeMap() { super(); }

	  /**
	   * Constructs an empty map using the given comparator to order keys.
	   * @param comp comparator defining the order of keys in the map
	   */
	  public SplayTreeMap(Comparator<K> comp) { super(comp); }

	  /** Utility used to rebalance after a map operation. */
	  private void splay(Position<Entry<K,V>> p) {
		  while(!isRoot(p)) { 
			  
			  Position<Entry<K, V>> parent = parent(p); //gets row of nodes
			  Position<Entry<K, V>> grandParent = parent(parent);
			  
			  if(grandParent == null) { //Uses balance tree rotate methods
				  tree.rotate(p);
			  }else if((parent == left(grandParent)) == (p == left(parent))) {
				  tree.rotate(parent);
				  tree.rotate(p);
			  }else {
				  tree.rotate(p);
				  tree.rotate(p);
			  }
			  
		  }
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after a node access. */
	  @Override
	  protected void rebalanceAccess(Position<Entry<K,V>> p) {
		 if(isExternal(p)) {
			 p = parent(p);
		 }
		 if(p != null) {
			 splay(p);
		 }
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
	  @Override
	  protected void rebalanceInsert(Position<Entry<K,V>> p) {
		  splay(p);
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
	  @Override
	  protected void rebalanceDelete(Position<Entry<K,V>> p) {
		  if(!isRoot(p)){
			  splay(parent(p));
		  }
	  }
	}