package parse;

import com.sun.nio.sctp.InvalidStreamException;
import exceptions.ExceedingTheBorderException;

import java.io.BufferedReader;
import java.io.IOException;


public class Parse {
  private static final int DEFAULT_SIZE = 5;
  private static final int ZERO_ASCII = 48;
  private static final int NINE_ASCII = 57;
  private static final int MAX_SIZE = 1000000;

  public static int parseLength(BufferedReader br){
    String str;
    try {
      if (!br.ready()) {
        throw new InvalidStreamException("Error in the file");
      }
      str = br.readLine();
      if (str == null) {
        throw new InvalidStreamException("file is empty");
      }
    } catch (IOException e) {
      throw new InvalidStreamException("Error in the file");
    }
    int length = -1;
    for (int i = 0; i < str.length(); i++) {
      try {
        if (str.charAt(i) < ZERO_ASCII || str.charAt(i) > NINE_ASCII) {
          throw new IllegalArgumentException("Error in the dimension of the matrix (There are not just numbers present)");
        }
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        length = DEFAULT_SIZE;
        System.out.println("The default value is set (5)");
        break;
      }
    }
    if (length != DEFAULT_SIZE) {
      try {
        length = Integer.parseInt(str);
      } catch (NumberFormatException e) {
        length = DEFAULT_SIZE;
        System.out.println("The dimension of the matrix is too large (length > 1000000)\nThe default value is set (5)");
      }
      try {
        if (br.readLine() != null) {
          length = DEFAULT_SIZE;
          throw new IllegalArgumentException("Error in the dimension of the matrix (There are not just numbers present)");
        }
      } catch (IOException | IllegalStateException e) {
        System.out.println(e.getMessage());
        System.out.println("The default value is set (5)");
      }
      try {
        if (length == 0) {
          throw new IllegalArgumentException("The dimension of the matrix is equal zero");
        }

        if (length > MAX_SIZE) {
          throw new ExceedingTheBorderException("The dimension of the matrix is too large (length > 1000000)");
        }
        return length;
      } catch (ExceedingTheBorderException | IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println("The default value is set (5)");
        return DEFAULT_SIZE;
      }
    }
    return length;
  }
}
