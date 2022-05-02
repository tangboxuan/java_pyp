package collections;

import collections.exceptions.InvalidWordException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleCompactWordTree implements CompactWordsSet {

  private final Character c;
  private boolean isWord = false;
  private final SimpleCompactWordTree[] children = new SimpleCompactWordTree[26];
  private final AtomicInteger size = new AtomicInteger(0);
  private final Lock lock = new ReentrantLock();

  public SimpleCompactWordTree() {
    c = null;
  }

  public SimpleCompactWordTree(char c) {
    this.c = c;
  }

  // for fine sync, we use hand over hand locking to ensure that the operations do not overtake each other traversing down the tree.
  // Even though remove is only a logical remove and there is no chance of jumping to a deleted tree, we still need it to prevent overtaking

  @Override
  public boolean add(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    char first = word.charAt(0);
    int index = first - 'a';
    lock.lock();
    try {
      if (children[index] == null) {
        children[index] = new SimpleCompactWordTree(first);
      }
      if (word.length() == 1) {
        if (children[index].isWord) {
          return false;
        } else {
          children[index].isWord = true;
          size.incrementAndGet();
          return true;
        }
      } else {
        if (children[index].add(word.substring(1))) {
          size.incrementAndGet();
          return true;
        } else {
          return false;
        }
      }
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean remove(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    lock.lock();
    try {
      char first = word.charAt(0);
      int index = first - 'a';
      if (children[index] == null) {
        return false;
      }
      if (word.length() == 1) {
        if (children[index].isWord) {
          children[index].isWord = false;
          size.decrementAndGet();
          return true;
        } else {
          return false;
        }
      } else {
        if (children[index].remove(word.substring(1))) {
          size.decrementAndGet();
          return true;
        } else {
          return false;
        }
      }
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean contains(String word) throws InvalidWordException {
    CompactWordsSet.checkIfWordIsValid(word);
    lock.lock();
    try {
      char first = word.charAt(0);
      int index = first - 'a';
      if (children[index] == null) {
        return false;
      }
      if (word.length() == 1) {
        return children[index].isWord;
      } else {
        return children[index].contains(word.substring(1));
      }
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int size() {
    return size.get();
  }

  @Override
  public List<String> uniqueWordsInAlphabeticOrder() {
    List<String> result = new ArrayList<>();
    if (isWord && c != null) {
      result.add("");
    }
    for (SimpleCompactWordTree tree : children) {
      if (tree != null) {
        result.addAll(tree.uniqueWordsInAlphabeticOrder());
      }
    }
    if (c != null) {
      result.replaceAll(str -> c + str);
    }
    return result;
  }
}
