package matrix;

import exceptions.ZeroException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Matrix {
  private static final double EPSILON = 0.0000000001;
  final int length;
  final double[][] array;

  public Matrix(int length) {
    this.length = length;
    array = new double[length][length];
  }

  public Matrix(Matrix copyMatrix) {
    length = copyMatrix.length;
    array = new double[length][length];
    for (int i = 0; i < length; i++) {
      System.arraycopy(copyMatrix.array[i], 0, array[i], 0, length);
    }
  }

  private void divide() {
    double[][] copyArr = new double[length][length];
    for (int i = 0; i < length; i++) {
      System.arraycopy(array[i], 0, copyArr[i], 0, length);
    }
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        double sum = 0;
        if (i != 0) {
          if (!Double.isNaN(copyArr[i - 1][j])) {
            sum += copyArr[i - 1][j];
          }
        }
        if (i != length - 1) {
          if (!Double.isNaN(copyArr[i + 1][j])) {
            sum += copyArr[i + 1][j];
          }
        }
        if (j != 0) {
          if (!Double.isNaN(copyArr[i][j - 1])) {
            sum += copyArr[i][j - 1];
          }
        }
        if (j != length - 1) {
          if (!Double.isNaN(copyArr[i][j + 1])) {
            sum += copyArr[i][j + 1];
          }
        }
        try {
          if (Math.abs(sum) < EPSILON) {

            array[i][j] = Double.NaN;
            throw new ZeroException("Division by zero i = " + i + ", j = " + j);
          } else {
            array[i][j] = array[i][j] / sum;
          }
        } catch (ZeroException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  private void transportation() {
    for (int i = 0; i < length; i++) {
      for (int j = i; j < length; j++) {
        double temp = array[i][j];
        array[i][j] = array[j][i];
        array[j][i] = temp;

      }
    }
  }

  private void mirrorRelativeToHorizontalAxis() {
    int N = length;
    if (length % 2 != 0) {
      N = length - 1;
    }
    for (int i = 0; i < N / 2; i++) {
      for (int j = 0; j < length; j++) {
        double temp = array[i][j];
        array[i][j] = array[length - i - 1][j];
        array[length - i - 1][j] = temp;

      }
    }
  }

  private void rotateMatrixBy90degrees() {
    this.transportation();
    this.mirrorRelativeToHorizontalAxis();
  }

  public void rand() {
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        array[i][j] = (Math.random() * (2 * length)) - length;
      }
    }
  }

  public void rotateAndDivideMatrix(int numberOfTurns) {
    for (int i = 0; i < numberOfTurns; i++) {
      this.rotateMatrixBy90degrees();
    }
    this.divide();
  }

  public void outputMatrix(BufferedWriter bw) throws IOException {
    DecimalFormat decimalFormat = new DecimalFormat("#.###");
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        bw.write(decimalFormat.format(array[i][j]).replace(",", ".") + " ");
      }
      bw.write("\n");
    }
    bw.write("\n");
  }
}
