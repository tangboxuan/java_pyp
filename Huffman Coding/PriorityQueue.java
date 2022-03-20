public class PriorityQueue<E extends Comparable<E>> implements PriorityQueueInterface<E>{

	private E[] items;    //a heap of HuffmanTrees
	private final static int max_size = 256;
	private int size;    //number of HuffManTrees in the heap.
	
	
	public PriorityQueue( ) {
        // constructor which creates an empty heap
		items = (E[]) new Comparable[max_size];
		size = 0;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int getSize(){
		return size;
	}

	public E getMin(){
		E root = null;
		if (!isEmpty()) root = items[0];
		return root;
	}
	
	public void add(E newEntry) throws PriorityQueueException{
	// post: Adds a new entry to the priority queue according to 
        // the priority value.
    if (size == max_size) {
      throw new PriorityQueueException("Max size reached");
    }
	  items[size] = newEntry;
    percolateUp(size);
    size++;
	}

	private void percolateUp(int position) {
		int childPos = child(position);
		if (position != 0 && items[childPos].compareTo(items[position]) > 0) {
			swap(position, childPos);
			percolateUp(childPos);
		}
	}

	private void swap(int position, int childPos) {
		E temp = items[position];
		items[position] = items[childPos];
		items[childPos] = temp;
	}

	private int child(int size) {
		return (size - 1) / 2;
	}

	public E removeMin(){
	// post: Removes the minimum valued item from the PriorityQueue
		E root = null;
		if (!isEmpty()){
			root = items[0];
			items[0] = items[size-1];
			size--;
			heapRebuild(0);
		}
		return root;
	}
	
	private void heapRebuild(int root){
	// Rebuild heap to keep it ordered
	// ADD YOUR CODE HERE
		int leftPos =  left(root);
		int rightPos = right(root);
		int toSwap;
		if (leftPos < size) {
			if (rightPos < size) {
				toSwap = items[leftPos].compareTo(items[rightPos]) < 0 ? leftPos : rightPos;
			}	 else {
				toSwap = leftPos;
			}
			if (items[toSwap].compareTo(items[root]) < 0) {
				swap(root, toSwap);
				heapRebuild(toSwap);
			}
		}
	}

	private int right(int root) {
		return root * 2 + 2;
	}

	private int left(int root) {
		return root * 2 + 1;
	}
}
