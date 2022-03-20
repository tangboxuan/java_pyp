import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPriorityQueue {
  @Test
  public void test() {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    pq.add(1);
    assertEquals(1, pq.getSize());
    assertEquals(1, (int) pq.removeMin());
    pq.add(1);
    pq.add(2);
    pq.add(3);
    assertEquals(1, (int) pq.removeMin());
    assertEquals(2, (int) pq.removeMin());
    assertEquals(3, (int) pq.removeMin());
    pq.add(3);
    pq.add(2);
    pq.add(1);
    assertEquals(1, (int) pq.removeMin());
    assertEquals(2, (int) pq.removeMin());
    assertEquals(3, (int) pq.removeMin());
  }
}
