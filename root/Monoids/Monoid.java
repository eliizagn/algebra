package root.Monoids;

public interface Monoid<T> {
  T neutral(); // Нейтральный элемент моноида

  T operate(T a, T b); // Бинарная операция моноида

}
