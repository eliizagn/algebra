package root.Monoids;

public class MaxMonoid implements Monoid<Integer> {
  public Integer neutral() {
    return Integer.MIN_VALUE; // Нейтральный элемент для максимума
  }

  public Integer operate(Integer a, Integer b) {
    return Math.max(a, b); // Операция максимума
  }
}