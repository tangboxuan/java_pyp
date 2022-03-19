import java.util.Iterator;

public class LinkedListPriorityQueue<T> implements PriorityQueue<T> {

  Node<T> root = new Node<>(0, null);

  @Override
  public void add(double priority, T element) {
    Node<T> current = root;
    while(current.getNext() != null && current.getNext().getPriority() < priority) {
      current = current.getNext();
    }
    Node<T> toAdd = new Node<>(priority, element);
    toAdd.setNext(current.getNext());
    current.setNext(toAdd);
  }

  @Override
  public T dequeue() {
    Node<T> next = root.getNext();
    if (next != null) {
      root.setNext(next.getNext());
      return next.getValue();
    } else {
      return null;
    }
  }

  @Override
  public boolean isEmpty() {
    return root.getNext() == null;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      Node<T> current = root.getNext();

      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public T next() {
        T value = current.getValue();
        current = current.getNext();
        return value;
      }
    };
  }

}
