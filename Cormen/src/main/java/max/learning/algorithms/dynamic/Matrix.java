package max.learning.algorithms.dynamic;

public class Matrix {

    private int[][] matrix;

    private int rows;

    private int cols;

    public Matrix(int[][] rows) {
        this.rows = rows.length;
        this.cols = rows[0].length;

        matrix = new int[this.rows][cols];
        fillRows(rows);
    }

    private void fillRows(int[][] rowsNumbers) {
        if (rowsNumbers.length != this.rows) {
            throw new IllegalArgumentException("The matrix has different number of rows");
        }

        // System.arraycopy(rowsNumbers, 0, matrix, 0, this.rows);
        for (int i = 0; i < this.rows; i++) {
            matrix[i] = rowsNumbers[i];
        }
    }

    public Matrix multiply(Matrix m) {
        if (this.cols != m.rows) {
            throw new IllegalArgumentException("The number of cols of Matrix A must be equal" +
                    " to the number of rows of Matrix B");
        }

        Matrix newM = new Matrix(new int[this.rows][m.cols]);
        for (int row = 0; row < newM.rows; row++) {
            for (int col = 0; col < newM.cols; col++) {
                int c = 0;
                for (int i = 0; i < cols; i++) {
                    c = c + matrix[row][i] * m.matrix[i][col];
                }
                newM.matrix[row][col] = c;
            }
        }
        return newM;
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 1; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(matrix[i][cols - 1] + " ");
        }
        System.out.println();
    }
}
