import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Tester {
  @org.junit.Test
  public void minHeapTest() {
    MinHeap<HeapEntry<Card>> heap = new MinHeap<>();
    Card card = new Card(Rank.ACE, Suit.SPADES);
    heap.add(new HeapEntry<>(card, 7));
    assertEquals(heap.size(), 1);
    heap.add(new HeapEntry<>(card, 6));
    heap.add(new HeapEntry<>(card, 5));
    heap.add(new HeapEntry<>(card, 4));
    heap.add(new HeapEntry<>(card, 3));
    heap.add(new HeapEntry<>(card, 2));
    heap.add(new HeapEntry<>(card, 1));
    assertEquals(heap.size(), 7);
    assertEquals(heap.removeMin().getPosition(), 1);
    assertEquals(heap.removeMin().getPosition(), 2);
    assertEquals(heap.removeMin().getPosition(), 3);
    assertEquals(heap.removeMin().getPosition(), 4);
    assertEquals(heap.removeMin().getPosition(), 5);
    assertEquals(heap.removeMin().getPosition(), 6);
    assertEquals(heap.removeMin().getPosition(), 7);
  }

  @Test
  public void minHeapDeckTest() {
    Deck deck = new MinHeapDeck();
    Card card = new Card(Rank.ACE, Suit.SPADES);
    deck.addToBottom(card);
    deck.addToBottom(card);
    deck.addToBottom(card);
    deck.addToBottom(card);
    assertEquals(deck.size(), 4);
    Deck top = deck.cut();
    assertEquals(deck.size(), 2);
    assertEquals(top.size(), 2);
  }
}
