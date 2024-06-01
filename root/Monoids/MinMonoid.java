package root.Monoids;

public class MinMonoid implements Monoid<Integer> {
  public Integer neutral() {
    return Integer.MAX_VALUE; // Нейтральный элемент для минимума
  }

  public Integer operate(Integer a, Integer b) {
    return Math.min(a, b); // Операция минимума
  }
}
