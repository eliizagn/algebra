package root.Rings;

public class MatrixPolynomial<T> {
    private final Matrix<T>[] coefficients;

    public MatrixPolynomial(Matrix<T>[] coefficients) {
        this.coefficients = coefficients;
    }

    // Метод для сложения полиномов
    public MatrixPolynomial<T> add(MatrixPolynomial<T> other) {
        if (coefficients.length != other.coefficients.length) {
            throw new IllegalArgumentException("Polynomials must have the same degree");
        }

        Matrix<T>[] resultCoefficients = new Matrix[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            resultCoefficients[i] = coefficients[i].add(other.coefficients[i]);
        }

        return new MatrixPolynomial<>(resultCoefficients);
    }

    // Метод для умножения полиномов
    public MatrixPolynomial<T> multiply(MatrixPolynomial<T> other) {
        Matrix<T>[] resultCoefficients = new Matrix[coefficients.length + other.coefficients.length - 1];
        for (int i = 0; i < resultCoefficients.length; i++) {
            resultCoefficients[i] = coefficients[0].multiply(other.coefficients[0]); // Здесь можно выбрать начальное значение
        }

        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                resultCoefficients[i + j] = resultCoefficients[i + j].add(coefficients[i].multiply(other.coefficients[j]));
            }
        }

        return new MatrixPolynomial<>(resultCoefficients);
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            builder.append("Coefficient ").append(i).append(": ").append(coefficients[i]).append("\n");
        }
        return builder.toString();
    }
}