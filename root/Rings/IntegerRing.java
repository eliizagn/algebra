package root.Rings;

import root.Monoids.Monoid;
import root.Groups.Group;

// Класс для реализации кольца над целыми числами
public class IntegerRing implements Ring<Integer> {
    //группа
    @Override
    public Group<Integer> GroupOperation() {
        return new Group<>() {
            @Override
            public Integer inverse(Integer element) {//существование обратного элемента
                return -element;
            }

            @Override
            public Integer neutral() {
                return 0;
            }

            @Override
            public Integer operate(Integer a, Integer b) { //коммутативность
                return a + b;
            }
            
        };
    }

    //Моноид по умножению
    @Override
    public Monoid<Integer> MonoidOperation() {
        return new Monoid<>() {
            @Override
            public Integer neutral() { //существование нейтрального элемента
                return 1;
            }

            @Override
            public Integer operate(Integer a, Integer b) { // ассоциативность
                return a * b;
            }
        };
    }
}