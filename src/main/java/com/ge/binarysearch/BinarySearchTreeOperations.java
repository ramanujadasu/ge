package com.ge.binarysearch;

public class BinarySearchTreeOperations {

	private BSNode root;

	public BinarySearchTreeOperations() {
		root = null;
	}

	public void insert(int data) {
		root = insert(root, data);
	}

	public BSNode insert(BSNode node, int data) {
		if (node == null)
			node = new BSNode(data);
		else {
			if (data <= node.getData())
				node.left = insert(node.left, data);
			else
				node.right = insert(node.right, data);
		}
		return node;
	}

	public boolean search(int val) {
		return search(root, val);
	}

	public boolean search(BSNode r, int val) {
		boolean found = false;
		while ((r != null) && !found) {
			int rval = r.getData();
			if (val < rval)
				r = r.getLeft();
			else if (val > rval)
				r = r.getRight();
			else {
				found = true;
				break;
			}
			found = search(r, val);
		}
		return found;
	}
	
	 public void display()
     {
		 display(root);
     }

	private void display(BSNode r)
    {
        if (r != null)
        {
        	display(r.getLeft());
            System.out.print(r.getData() +" ");
            display(r.getRight());
        }
    }
}
