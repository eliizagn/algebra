package root.Rings;

import root.Monoids.Monoid;
import root.Groups.Group;

// Класс для реализации кольца над логическими значениями
public class BooleanRing implements Ring<Boolean> {
    @Override
    public Group<Boolean> GroupOperation() {
        return new Group<Boolean>() {
            @Override
            public Boolean inverse(Boolean element) {
                return element;
            }

            @Override
            public Boolean neutral() {
                return false;
            }

            @Override
            public Boolean operate(Boolean a, Boolean b) {
                return a ^ b;
            }
        };
    }

    @Override
    public Monoid<Boolean> MonoidOperation() {
        return new Monoid<>() {
            @Override
            public Boolean neutral() {
                return true;
            }

            @Override
            public Boolean operate(Boolean a, Boolean b) {
                return a && b;
            }
        };
    }
}