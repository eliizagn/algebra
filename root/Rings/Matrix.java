package root.Rings;

import java.util.Arrays;

// Класс для матрицы
public class Matrix<T> {
    private final Ring<T> ring;
    protected final T zero;
    private final int size;
    private final T[][] value;

    // конструктор для матрицы 
    public Matrix(T[][] value, Ring<T> ring, T zero) {
        this.value = value;
        this.size = value.length;
        this.ring = ring;
        this.zero = zero;
    }

    public Matrix<T> neutral() {
    

        T[][] result = (T[][]) new Object[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    result[i][j] = ring.MonoidOperation().neutral();
                } else {
                    result[i][j] = zero;
                }
            }
        }

        return new Matrix<>(result, ring, zero);
    }

    // суммирование матрицы
    public Matrix<T> add(Matrix<T> other) {
        T[][] otherValue = other.value;
        if (value.length != otherValue.length || value[0].length != otherValue[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
    
        T[][] result = (T[][]) new Object[size][size];
    
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = ring.GroupOperation().operate(value[i][j], otherValue[i][j]);
            }
        }
    
        return new Matrix<>(result, ring, zero);
    }

    public Matrix<T> identity() {
        T[][] result = (T[][]) new Object[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    result[i][i] = ring.MonoidOperation().neutral();
                } else {
                    result[i][j] = zero;
                }
            }
        }

        return new Matrix<>(result, ring, zero);
    }


    public Matrix<T> multiply(Matrix<T> other) {

        T[][] otherValue = other.value;
        if (value.length != otherValue[0].length) {
            throw new IllegalArgumentException("Matrices must have compatible dimensions for multiplication");
        }
        
        Thread matrThread[] = new Thread[size];
        
        T[][] result = (T[][]) new Object[size][size];

        
        for (int i = 0; i < size; i++) {
            int index = i;
            matrThread[i] = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < size; j++) {
                        T sum = ring.GroupOperation().neutral();
                        for (int k = 0; k < size; k++) {
                            sum = ring.GroupOperation().operate(sum, ring.MonoidOperation().operate(value[index][k], otherValue[k][j]));
                        }
                        result[index][j] = sum;
                    }
                }
                
            });
            matrThread[i].start();
            
        }
        for (Thread thread : matrThread) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                return new Matrix<>(result, ring, zero);
            }
        }
        
        return new Matrix<>(result, ring, zero);
    }

    public Matrix<T> multiplyByScalar(T scalar) {
        T[][] result = (T[][]) new Object[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = ring.GroupOperation().operate(value[i][j], scalar);
            }
        }

        return new Matrix<>(result, ring, zero);
    }
    // хелпер для матрицы
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T[] row : value) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }
}