import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Iterator;
import org.junit.Test;

public class TestLinkedListPriorityQueue {
  @Test
  public void test() {
    PriorityQueue<Integer> pq = new LinkedListPriorityQueue<>();
    assertTrue(pq.isEmpty());
    pq.add(3,3);
    assertFalse(pq.isEmpty());
    pq.add(2,2);
    pq.add(1,1);
    assertEquals((int) pq.dequeue(), 1);
    assertEquals((int) pq.dequeue(), 2);
    assertEquals((int) pq.dequeue(), 3);
    assertTrue(pq.isEmpty());
    pq.add(1,1);
    pq.add(2,2);
    pq.add(3,3);
    assertEquals((int) pq.dequeue(), 1);
    assertEquals((int) pq.dequeue(), 2);
    assertEquals((int) pq.dequeue(), 3);
    assertTrue(pq.isEmpty());
    pq.add(3,3);
    pq.add(2,2);
    pq.add(1,1);
    Iterator<Integer> iterator = pq.iterator();
    assertTrue(iterator.hasNext());
    assertEquals((int) iterator.next(), 1);
    assertEquals((int) iterator.next(), 2);
    assertEquals((int) iterator.next(), 3);
  }
}