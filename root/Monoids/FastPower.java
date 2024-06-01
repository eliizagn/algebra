package root.Monoids;

public class FastPower<T> {

    private final Monoid<T> monoid;

    public FastPower(Monoid<T> monoid) {
        this.monoid = monoid;
    }

    public T pow(T base, int exponent) {
        if (exponent == 0) {
            return monoid.neutral(); // Любое число в степени 0 равно единице
        }

        boolean isEven = (exponent % 2) == 0;
        T result = pow(base, exponent / 2);
        result = monoid.operate(result, result); // Возведение в квадрат

        if (isEven) {
            return result;
        } else {
            return monoid.operate(result, base); // Умножение на основание, если экспонента нечетная
        }
    }
}