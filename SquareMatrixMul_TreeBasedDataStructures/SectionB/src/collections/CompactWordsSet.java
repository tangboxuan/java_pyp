package collections;

import collections.exceptions.InvalidWordException;
import java.util.List;

public interface CompactWordsSet {

  static void checkIfWordIsValid(String word) throws InvalidWordException {
    if (word == null || word.isEmpty()) {
      throw new InvalidWordException("Empty word");
    }
    for (int c : word.toCharArray()) {
      if (c < 'a' || c > 'z') {
        throw new InvalidWordException("Invalid character");
      }
    }
  }

  boolean add(String word) throws InvalidWordException;

  boolean remove(String word) throws InvalidWordException;

  boolean contains(String word) throws InvalidWordException;

  int size();

  List<String> uniqueWordsInAlphabeticOrder();

}
