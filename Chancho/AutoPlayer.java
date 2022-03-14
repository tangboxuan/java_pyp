import java.util.HashMap;
import java.util.Map;

public class AutoPlayer extends AbstractPlayer {

  public AutoPlayer(CardPile left, CardPile right, String name) {
    super(left, right, name);
  }

  @Override
  protected int selectCard() {
    Map<Rank, Integer> count = new HashMap<>();
    for (Card card : cards) {
     if (count.containsKey(card.getRank())) {
       count.replace(card.getRank(), count.get(card.getRank()));
     } else {
       count.put(card.getRank(), 1);
     }
    }
    int highest = 0;
    Rank select = null;
    for (Rank rank : count.keySet()) {
      if (count.get(rank) > highest) {
        highest = count.get(rank);
        select = rank;
      }
    }
    for (int i = 0; i < cards.length; i++) {
      if (cards[i].getRank() == select) {
        return i;
      }
    }
    return -1;
  }

}