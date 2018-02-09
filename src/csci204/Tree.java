package csci204;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The tree class performs the add and find functions for the tree as well as
 * implements toString
 * 
 * @author Tobin Greenwald
 *
 * @param <E>
 *            parameter of type E holds each node in the tree
 */
public class Tree<E extends Comparable<E>> {
	Node<E> root; // creates node for root of tree
	int numItems; // number of items

	int maxNumChild = 3; // maximum number of children for each node

	private ArrayList strList = new ArrayList(); // array that holds string
													// values for printing tree
													// in toString
	private ArrayList nodeList = new ArrayList(); // holds values of nodes to be
													// seen in toString

	// Constructor is called when program begins, creates empty tree with a root
	// of null
	public Tree(int maxNumOfChild) {

		numItems = 0;

		maxNumChild = maxNumOfChild;
		root = null;
	}

	/**
	 * add method appends a new node to the tree, wrapper method for addAux
	 * 
	 * @param key
	 *            value to be placed at node being added to tree
	 * @return method returns true if node was successfully added to tree
	 */
	public boolean add(E key) {

		if (root == null) {
			root = new Node<E>(maxNumChild, key);
			numItems++;
			return true;
		}
		return addAux(root, key);
	}

	/**
	 * addAux method recurses through the tree to find the location to add the
	 * new node
	 * 
	 * @param currNode
	 *            keeps track of the node that is being added to
	 * @param key
	 *            value to be added to the new node in the tree
	 * @return returns true once the value is added at the next logical location
	 *         in the tree
	 * @throws IllegalArgumentException
	 *             thrown if the location to add the new node is greater than
	 *             the max number of children
	 */
	private boolean addAux(Node<E> currNode, E key) throws IllegalArgumentException {
		// checks to see if the value of the current node is the same as the key
		// value,
		// this will return false because this tree cannot have duplicates
		if (currNode.getValue().compareTo(key) == 0) {
			return false;
		}
		// for loop iterates through the children of the current node
		for (int i = 0; i < currNode.getNumChild(); i++) {
			// if statement compares key value to children of the current node,
			// if the key is less than the current child, recurse to that nodes
			// child
			if (currNode.getChild(i).getValue().compareTo(key) >= 0) {
				return addAux(currNode.getChild(i), key);
			}
		}
		// if statement checks if we are at the last child of the node, if
		// empty, place there,
		// otherwise recurse down to the right of the tree
		if (currNode.getNumChild() == maxNumChild) {
			return addAux(currNode.getChild(currNode.getNumChild() - 1), key);
		} else {
			// if node's children are full append a new child to the node
			currNode.addChild(new Node<E>(maxNumChild, key), currNode.getNumChild());
			// increment number of items in the tree
			numItems++;
			return true;

		}
	}

	/**
	 * size method
	 * 
	 * @return returns number of nodes in the tree
	 */
	public int size() {
		return numItems;
	}

	/**
	 * find method works similarly to add, is a wrapper method for findAux which
	 * compares each of the nodes to the key until it finds the node that
	 * matches the key value
	 * 
	 * @param key
	 *            value to be searched for
	 * @return returns node of type E that is found
	 */
	public E find(E key) {
		// if root does not equal null recurse through the tree
		if (root != null) {
			return findAux(root, key);
			// otherwise the tree is empty and return null
		} else {
			return null;
		}
	}

	/**
	 * findAux recurses through the tree to find the node that matches the key
	 * value
	 * 
	 * @param currNode
	 * @param key
	 * @return
	 */
	private E findAux(Node<E> currNode, E key) {
		// base case for the method, if value is equal to the key, return value
		if (currNode.getValue().compareTo(key) == 0) {
			return currNode.getValue();
		}
		// checks to see if the node has any children, if not, return null
		if (currNode.getNumChild() == 0) {
			return null;
		}
		// for loop iterates through the nodes children comparing their values
		// to the key
		for (int i = 0; i < currNode.getNumChild(); i++) {
			// if the child value is greater than the key, recurse down to that
			// node's child,
			// otherwise continue and compare value to next child
			if (currNode.getChild(i).getValue().compareTo(key) >= 0) {
				return findAux(currNode.getChild(i), key);
			}
		}
		// if the for loop finishes and the key value is greater than all the
		// children,
		// recurse down the node's right-most child
		return findAux(currNode.getChild(maxNumChild - 1), key);
	}


	@SuppressWarnings("unchecked")
	@Override
	/**
	 * toString is the wrapper method for the stringRec method and takes no
	 * arguments and prints the tree in level order
	 */
	public String toString() {

		// root is printed on its own line at the top of the tree
		strList.add(root.toString() + "\n");
		// for loop iterates through the root's children, adding them to the
		// nodeList arraylist
		// to be used in the stringRec method

		for (int i = 0; i < root.getNumChild(); i++) {

			nodeList.add(root.getChild(i));

		}
		// calls stringRec
		stringRec();
		String output = "";
		// while loop looks at the string list and while it is not empty
		// the loop pops off the item at index 0 and adds it the the string
		// output
		while (!strList.isEmpty()) {
			output += strList.remove(0);
		}
		// returns the string of nodes in the tree
		return output;
	}

	/**
	 * method stringRec recurses through the levels of the tree and adds all of
	 * the nodes on one line to a string to be printed
	 */
	private void stringRec() {
		// size records number of items in the nodeList
		int size = nodeList.size();
		String out = "";
		// for loop looks at all of the children of a node represented by
		// nodeList
		for (int i = 0; i < size; i++) {
			// each time the loop iterates, the child at index 0 of nodeList is
			// popped off the array
			// in order to print the tree in level order
			Node<E> node = (Node<E>) nodeList.remove(0);
			// if statement checks to see if there are no more nodes in the
			// list, if so,
			// places delimiter between it and the adjacent set of nodes that
			// are children of a separate node
			if (node.getValue() != null) {
				// if we are not at the last child, add the value to the output
				// string
				out += "<" + node.getValue() + "> ";
			}

			// for loop looks at each of the children of the nodes that were
			// added on the previous row
			// and adds those children to a new nodeList where they are
			// temporarily held
			for (int j = 0; j < node.getNumChild(); j++) {
				nodeList.add(node.getChild(j));
				if (j == node.getNumChild()) {
					nodeList.add(new Node<E>(maxNumChild, null));
				}
			}
		}
		// adds new line of nodes to string array followed by a new line
		strList.add(out + "\n");
		// recurses through the tree until there are no more items in the
		// nodeList to be printed
		if (!nodeList.isEmpty()) {
			stringRec();
		}
	}
}
