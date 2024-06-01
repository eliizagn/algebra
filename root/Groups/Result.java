package root.Groups;

import java.util.ArrayList;
import java.util.List;

public class Result {
  public static void main(String[] args) {
    // Пример использования группы со сложением
    Group<Integer> additionGroup = new Addition();
    System.out.println(additionGroup.operate(2, 3)); // Выведет 5
    System.out.println(additionGroup.inverse(5)); // Выведет -5

    // Пример использования группы с умножением
    Group<Double> multiplicatioGroup = new Multiplication();
    System.out.println(multiplicatioGroup.operate(5.0, 4.0)); // Выведет 20
    System.out.println(multiplicatioGroup.inverse(10.0)); // Выведет 0.1

    // Пример использования префиксной суммы для запросов на отрезке
    ArrayList<Integer> array = new ArrayList<>(List.of(1, 3, 5, 7, 9));
    PrefixSum<Integer> prefixSum = new PrefixSum<>(array, additionGroup);

    // Выполнение запроса на сумму элементов на отрезке [1, 3]
    Integer result = prefixSum.query(1, 3);
    System.out.println(result);
  }
}
