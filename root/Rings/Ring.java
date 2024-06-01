package root.Rings;

import root.Monoids.Monoid;
import root.Groups.Group;

// Интерфейс для кольца
public interface Ring<T> {
    Group<T> GroupOperation();
    Monoid<T> MonoidOperation();
}