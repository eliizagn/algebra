package root.Rings;

import java.util.ArrayList;
import java.util.List;

public class Polynomial<T> {
    private final Ring<T> ring;
    private final List<T> coefficients;

    public Polynomial(Ring<T> ring, List<T> coefficients) {
        this.ring = ring;
        this.coefficients = new ArrayList<>(coefficients);
    }
    
    public Matrix<T> getCoefficientMatrix() {
        int size = coefficients.size();
        T[][] matrixValues = (T[][]) new Object[size][1]; // Создаем матрицу размером size x 1

        // Заполняем матрицу коэффициентами полинома
        for (int i = 0; i < size; i++) {
            matrixValues[i][0] = coefficients.get(i);
        }

        // Создаем матрицу из массива значений и возвращаем ее
        return new Matrix<T>(matrixValues, ring, ring.GroupOperation().neutral());
    }

    public Polynomial<T> add(Polynomial<T> other) {
        List<T> resultCoefficients = new ArrayList<>();
        int maxSize = Math.max(this.coefficients.size(), other.coefficients.size());
        for (int i = 0; i < maxSize; i++) {
            T coeff1 = (i < this.coefficients.size()) ? this.coefficients.get(i) : ring.GroupOperation().neutral();
            T coeff2 = (i < other.coefficients.size()) ? other.coefficients.get(i) : ring.GroupOperation().neutral();
            resultCoefficients.add(ring.GroupOperation().operate(coeff1, coeff2));
        }
        return new Polynomial<>(ring, resultCoefficients);
    }

    public Polynomial<T> multiply(Polynomial<T> other) {
        List<T> resultCoefficients = new ArrayList<>();
        for (int i = 0; i < this.coefficients.size() + other.coefficients.size() - 1; i++) {
            T resultCoefficient = ring.GroupOperation().neutral();
            for (int j = 0; j <= i; j++) {
                T coeff1 = (j < this.coefficients.size()) ? this.coefficients.get(j) : ring.GroupOperation().neutral();
                T coeff2 = (i - j < other.coefficients.size()) ? other.coefficients.get(i - j) : ring.GroupOperation().neutral();
                resultCoefficient = ring.GroupOperation().operate(resultCoefficient, ring.MonoidOperation().operate(coeff1, coeff2));
            }
            resultCoefficients.add(resultCoefficient);
        }
        return new Polynomial<>(ring, resultCoefficients);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = coefficients.size() - 1; i >= 0; i--) {
            T coeff = coefficients.get(i);
            if (!ring.GroupOperation().neutral().equals(coeff)) {
                if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (i > 1) {
                    sb.append(coeff).append("x^").append(i);
                } else if (i == 1) {
                    sb.append(coeff).append("x");
                } else {
                    sb.append(coeff);
                }
            }
        }
        return sb.toString();
    }
}