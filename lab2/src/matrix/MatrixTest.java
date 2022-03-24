package matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final int MATRIX_LENGTH = 2;
  Matrix matrix;
  private static final String DIVISION_BY_ZERO = "Division by zero i = 0, j = 1\nDivision by zero i = 1, j = 0\n";

  @Test
  void rotateAdeDivideMatrix() {
    matrix = new Matrix(MATRIX_LENGTH);
    matrix.array[0][0] = 1;
    matrix.array[0][1] = 2;
    matrix.array[1][0] = 3;
    matrix.array[1][1] = 4;
    double[][] rotatedArr = {{0.4, 0.8}, {0.2, 0.6}};
    matrix.rotateAndDivideMatrix(1);
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        Assertions.assertEquals(matrix.array[i][j], rotatedArr[i][j]);
      }
    }
  }

  @Test
  void rand() {
    matrix = new Matrix(MATRIX_LENGTH);
    matrix.rand();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {

        Assertions.assertTrue((matrix.array[i][j] <= matrix.length && matrix.array[i][j] >= -matrix.length));
      }
    }
  }

  @Test
  void errorRotateAndDivideMatrix() {
    matrix = new Matrix(MATRIX_LENGTH);
    matrix.array[0][0] = 1;
    matrix.array[0][1] = 0;
    matrix.array[1][0] = 0;
    matrix.array[1][1] = 0;
    System.setOut(new PrintStream(outContent));
    assertDoesNotThrow(() -> matrix.rotateAndDivideMatrix(1));
    assertEquals(DIVISION_BY_ZERO, outContent.toString());
  }
}
