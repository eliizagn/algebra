package root.Rings;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Main {

        public static void main(String[] args) {
                // Пример использования кольца над логическими значениями
                Ring<Boolean> booleanRing = new BooleanRing();
                System.out.println("Inverse element for true with XOR operation: "
                                + booleanRing.GroupOperation().inverse(true));
                System.out.println("Multiplication of true and false: "
                                + booleanRing.MonoidOperation().operate(true, false));

                // Пример использования кольца над целыми числами
                Ring<Integer> integerRing = new IntegerRing();
                System.out.println("Inverse element for 5 with addition: "
                                + integerRing.GroupOperation().inverse(5));
                System.out.println("Multiplication of 2 and 3: "
                                + integerRing.MonoidOperation().operate(2, 3));

                Integer[][] valueA = { { 1, 2, 3 }, { 3, 4, 5 }, { 7, 1, 2 } };
                Integer[][] valueB = { { -1, 0, 3 }, { 0, 2, -3 }, { 2, 3, 1 } };

                Matrix<Integer> matrixA = new Matrix<>(valueA, integerRing, 0);
                Matrix<Integer> matrixB = new Matrix<>(valueB, integerRing, 0);
                Matrix<Integer> neutralMatrix = matrixA.neutral();
                System.out.println("Identity Matrix:");
                System.out.println(neutralMatrix);

                System.out.println("Matrix A:");
                System.out.println(matrixA);

                System.out.println("Matrix B:");
                System.out.println(matrixB);

                Matrix<Integer> sumResult = matrixA.add(matrixB);
                System.out.println("Matrix 1 + Matrix 2:");
                System.out.println(sumResult);

                Matrix<Integer> multiplying = matrixA.multiply(matrixB);
                System.out.println("multiplying of A and B:");
                System.out.println(multiplying);
                
                List<Integer> coefficients1 = Arrays.asList(1, 2, 3); 
        Polynomial<Integer> poly1 = new Polynomial<>(integerRing, coefficients1);

        List<Integer> coefficients2 = Arrays.asList(4, 5, 6); 
        Polynomial<Integer> poly2 = new Polynomial<>(integerRing, coefficients2);

        // Сложение полиномов
        Polynomial<Integer> sum = poly1.add(poly2);
        System.out.println("Sum of: " + sum);

        // Умножение полиномов
        Polynomial<Integer> product = poly1.multiply(poly2);
        System.out.println("Multiplying: " + product);

       
        }
}
