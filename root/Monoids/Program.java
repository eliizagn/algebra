package root.Monoids;

public class Program {
  public static void main(String[] args) {
    Integer[] array = { 1, 3, 2, 7, 9, 11 };
    MaxMonoid maxMonoid = new MaxMonoid();
    MinMonoid minMonoid = new MinMonoid();

    SegmentTree<Integer> maxSegmentTree = new SegmentTree<>(array, maxMonoid);
    System.out.println(maxSegmentTree.query(1, 4)); // Выведет максимум на отрезке [1, 4]

    SegmentTree<Integer> minSegmentTree = new SegmentTree<>(array, minMonoid);
    System.out.println(minSegmentTree.query(2, 5)); // Выведет минимум на отрезке [2, 5]
    Monoid<Integer> multiplicationMonoid = new Monoid<Integer>() {
      @Override
      public Integer operate(Integer x, Integer y) {
        return x * y;
      }

      @Override
      public Integer neutral() {
        return 1;
      }
    };

    FastPower<Integer> fastPower = new FastPower<>(multiplicationMonoid);

    int base = 2;
    int exponent = 0;

    Integer result = fastPower.pow(base, exponent);
    System.out.println(base + " raised to a power " + exponent + " equals " + result); // 32
  }
}