package root.Groups;

import root.Monoids.Monoid;

public interface Group<T> extends Monoid<T> {
  T inverse(T element); // Операция нахождения обратного элемента
}