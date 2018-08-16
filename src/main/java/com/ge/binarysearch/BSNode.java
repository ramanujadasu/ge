package com.ge.binarysearch;

public class BSNode {
	
	BSNode left, right;
	int data;

	public BSNode() {
		left = null;
		right = null;
		data = 0;
	}

	public BSNode(int n) {
		left = null;
		right = null;
		data = n;
	}

	public void setLeft(BSNode n) {
		left = n;
	}

	public void setRight(BSNode n) {
		right = n;
	}

	public BSNode getLeft() {
		return left;
	}

	public BSNode getRight() {
		return right;
	}

	public void setData(int d) {
		data = d;
	}

	public int getData() {
		return data;
	}
}
