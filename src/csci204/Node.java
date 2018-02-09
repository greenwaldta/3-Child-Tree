
package csci204;


public class Node<E extends Comparable<E>> {

	Object[] childArray; // array of children for each node
	private int numChild; // number of children at each node
	private E value; // value/key for each node
	// constructor sets the node's number of children to 0 and
	// creates array for each node's of size maxChild

	public Node(int maxChild, E val) {
		numChild = 0;
		value = val;
		childArray = new Object[maxChild];
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public int getNumChild() {
		return numChild;
	}

	public void setNumChild(int numChild) {
		this.numChild = numChild;
	}

	public Node<E> getChild(int index) {
		return (Node<E>) childArray[index];
	}

	public void setChild(Node<E> child, int index) {
		childArray[index] = child;
	}

	public void addChild(Node<E> child, int index) {
		setChild(child, index);
		numChild++;
	}


	public Object[] getChildArray() {
		return childArray;
	}

	@Override
	public String toString() {
		return ("<" + value + ">");

	}
}
