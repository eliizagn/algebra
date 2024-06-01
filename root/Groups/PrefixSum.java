package root.Groups;

import java.util.ArrayList;

public class PrefixSum<T> {
  private final ArrayList<T> prefixSum;
  private final Group<T> group;

  public PrefixSum(ArrayList<T> array, Group<T> group) {
    this.group = group; //для использования операций из группы
    int n = array.size();
    prefixSum = new ArrayList<>(n + 1);
    prefixSum.add(group.neutral());
    for (int i = 1; i <= n; i++) {
      T previousSum = prefixSum.get(i-1);
      T currentElement = array.get(i-1);
      prefixSum.add(group.operate(previousSum, currentElement));
    }
  }

  public T query(int start, int end) {
    // Находим обратный элемент для prefixSum[start] с помощью inverse()
    T inversePrefixStart = group.inverse(prefixSum.get(start));
    // Выполняем операцию group.operate(inversePrefixStart, prefixSum[end + 1])
    return group.operate(inversePrefixStart, prefixSum.get(end + 1));
  }
}