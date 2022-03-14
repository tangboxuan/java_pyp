import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class implements a min-heap abstract data type (as described by the
 * generic interface IMinHeap<T extends Comparable<T>>) using a fixed array of
 * size MinHeap.MAXIMUM_HEAP_SIZE.
 */
public class MinHeap<T extends Comparable<T>> implements IMinHeap<T> {

  private final int MAXIMUM_HEAP_SIZE = 52;
  private int size = 0;
  private final List<T> heap = new ArrayList<>(MAXIMUM_HEAP_SIZE);

  @Override
  public void add(T element) throws HeapException {
    if (size == MAXIMUM_HEAP_SIZE) {
      throw new HeapException("Max heap size reached");
    } else {
      heap.add(element);
      percolateup(size);
      size++;
    }
  }

  private void percolateup(int position) {
    if (position > 0) {
      int parent = (position - 1) / 2;
      if (heap.get(position).compareTo(heap.get(parent)) < 0) {
        swap(position, parent);
        percolateup(parent);
      }
    }
  }

  private void swap(int a, int b) {
    T temp = heap.get(a);
    heap.set(a, heap.get(b));
    heap.set(b, temp);
  }

  @Override
  public T removeMin() {
    size--;
    T min = heap.get(0);
    if (!isEmpty()) {
      heap.set(0, heap.get(size));
      heap.remove(size);
      fixMinHeap(0);
    } else {
      heap.remove(0);
    }
    return min;
  }

  private void fixMinHeap(int position) {
    int larger = findSmallerHeap(position);
    if (larger != -1) {
      if (heap.get(position).compareTo(heap.get(larger)) > 0) {
        swap(position, larger);
        fixMinHeap(larger);
      }
    }
  }

  private int findSmallerHeap(int position) {
    T leftHeap, rightHeap;
    int left = left(position);
    int right = right(position);
    try {
      leftHeap = heap.get(left);
    } catch (Exception e) {
      try {
        heap.get(right);
        return right;
      } catch (Exception e2) {
        return -1;
      }
    }
    try {
      rightHeap = heap.get(right);
    } catch (Exception e) {
      return left;
    }
    if (leftHeap.compareTo(rightHeap) < 0) {
      return left;
    } else {
      return right;
    }
  }

  private int left(int i) {
    return 2 * i + 1;
  }

  private int right(int i) {
    return 2 * i + 2;
  }

  @Override
  public T getMin() {
    return heap.get(0);
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

	
}