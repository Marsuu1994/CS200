class Node<K extends Comparable<? super K>>{
	K key;
	Node<K> left, right;
	public Node(K key, Node<K> left, Node<K> right){
		this.key = key;
		this.left = left;
		this.right = right;
	}	
	public Node(K key){
		this(key, null, null);
	}	
	public boolean isLeaf(){
		return left == null && right == null;
	}
}

public class BinarySearchTree <K extends Comparable<? super K>>{
	Node<K> root;
	public BinarySearchTree(){
		this.root = null;
	}	
	void insert(K k){
		root = insert(root, k);
	}	
	Node<K> insert(Node<K> curr, K k){
		if(curr == null) return new Node<K>(k);
		int c = k.compareTo(curr.key);
		if(c!=0){
			if(c < 0){
				curr.left = insert(curr.left, k);
			} else {
				curr.right = insert(curr.right, k);
			}
		}
		return curr;
	}
	
	//we can also insert in a iterative way
	void traversing_insert(K k){
		if(root == null){
			root = new Node<K>(k);
			return;
		}
		Node<K> curr = root;
		while(true){
			int c = k.compareTo(curr.key);
			if(c == 0){
				return;
			}else if( c < 0){
				if(curr.left == null){
					curr.left = new Node<K>(k);
					return;
				}
				curr = curr.left;
			}else{
				if(curr.right == null){
					curr.right = new Node<K>(k);
					return;
				}
				curr = curr.right;
			}					
		}
	}
	
	boolean contains(K k){
		return contains(root, k);
	}
	
	boolean contains(Node<K> curr, K k){
		if(curr == null) return false;
		int c = k.compareTo(curr.key);
		if(c!=0){
			if(c < 0){
				return contains(curr.left, k);

			} else {
				return contains(curr.right, k);
			}
		}
		return true;
	}
	
	//helper function to start the recursion
	void remove(K k){
		root = remove(root, k);
	}
	
	//remove recursivly
	Node<K> remove(Node<K> curr, K k){
		if(curr == null){
			return null;
		}
		int c = k.compareTo(curr.key);
		if(c!=0){
			if(c < 0){
				curr.left = remove(curr.left, k);
			} else {
				curr.right = remove(curr.right, k);
			}
		}else{
			curr = removeNode(curr);
		}
		return curr;
	}
	
	//the actual removal is done here
	Node<K> removeNode(Node<K> node){
		if(node.left == null){
			node = node.right;
		}else if(node.right == null){
			node = node.left;
		}else{
			// this is the tricky part of removing a Node
			Node<K> temp = node.left;
			while(temp.right != null){
				temp = temp.right;
			}
			node.key = temp.key;
			node.left = remove(node.left,temp.key);
		}
		return node;
	}
	
	//Printing the tree
	public void preorderTraverse(Node<K> node, String indent){
		System.out.println(indent+node.key);		
		if(node.left!=null) {
			System.out.println(indent+"left");
			preorderTraverse(node.left,indent+" ");
		}

		if(node.right!=null) {
			System.out.println(indent+"right");
			preorderTraverse(node.right,indent+" ");
		}
	}
	
	
	//test the tree
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(5);
		tree.insert(2);		
		tree.insert(3);
		tree.insert(1);
		tree.insert(7);
		tree.insert(6);
		tree.insert(8);
		tree.preorderTraverse(tree.root, "");
		tree.remove(5);
		System.out.println();
		tree.preorderTraverse(tree.root, "");
	}
	
	
	
	
	
	
}
