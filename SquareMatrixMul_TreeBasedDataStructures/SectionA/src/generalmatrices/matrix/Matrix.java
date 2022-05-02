package generalmatrices.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Matrix<T> {

  // TODO: populate as part of Question 1 and Question 3

  private final T[][] store;
  private final int order;

  public Matrix(List<T> items) {
    order = (int) Math.sqrt(items.size()); // assume perfect square
    if (order == 0) {
      throw new IllegalArgumentException("Empty List provided");
    }
    store = (T[][]) new Object[order][order];
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        store[i][j] = items.get(i * order + j);
      }
    }
  }

  public int getOrder() {
    return order;
  }

  public T get(int row, int col) {
    return store[row][col];
  }

  public Matrix<T> sum(Matrix<T> other, BinaryOperator<T> elementSum) {
    List<T> result = new ArrayList<>();
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        result.add(elementSum.apply(this.store[i][j], other.store[i][j]));
      }
    }
    return new Matrix<>(result);
  }

  public Matrix<T> product(Matrix<T> other, BinaryOperator<T> elementSum,
      BinaryOperator<T> elementProduct) {
    List<T> result = new ArrayList<>();
    for (int i = 0; i < order; i++) {
      for (int j = 0; j < order; j++) {
        T element = elementProduct.apply(this.store[i][0], other.store[0][j]);
        for (int k = 1; k < order; k++) {
          T addition = elementProduct.apply(this.store[i][k], other.store[k][j]);
          element = elementSum.apply(element, addition);
        }
        result.add(element);
      }
    }
    return new Matrix<>(result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (T[] row : store) {
      sb.append('[');
      for (T item : row) {
        sb.append(item);
        sb.append(' ');
      }
      sb.deleteCharAt(sb.length() - 1);
      sb.append(']');
    }
    sb.append(']');
    return sb.toString();
  }
}
