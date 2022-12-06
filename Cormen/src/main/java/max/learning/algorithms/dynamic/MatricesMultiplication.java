package max.learning.algorithms.dynamic;

public class MatricesMultiplication {

    public static void main(String[] args) {
        int[][] m1 = new int[][] {
                {1, 2, 3, 4},
                {2, 1, 2, 1},
                {1, 3, 1, 1}
        };

        int[][] m2 = new int[][] {
                {1, 2, 2},
                {1, 1, 1},
                {2, 3, 2},
                {2, 1, 1}
        };

        var matrix1 = new Matrix(m1);
        var matrix2 = new Matrix(m2);

        matrix1.print();
        System.out.println("-------");
        matrix2.print();

        System.out.println("--- Multiplication ---");
        matrix1.multiply(matrix2).print();
    }
}
