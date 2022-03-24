package parse;

import com.sun.nio.sctp.InvalidStreamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ParseTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final String LENGTH_EQUAL_ZERO = "The dimension of the matrix is equal zero\nThe default value is set (5)\n";
  private static final String LENGTH_TOO_LARGE = "The dimension of the matrix is too large (length > 1000000)\nThe default value is set (5)\n";
  private static final String STR_WITH_NOT_NUMBERS = "Error in the dimension of the matrix (There are not just numbers present)\nThe default value is set (5)\n";

  @Test
  void correctParseLength() throws IOException {
    StringWriter stringWriter = new StringWriter();
    stringWriter.write("5");

    BufferedReader bufferedReader = new BufferedReader(new StringReader(stringWriter.toString()));
    bufferedReader.mark(100);
    Assertions.assertEquals(Parse.parseLength(bufferedReader), 5);
    bufferedReader.reset();

    assertDoesNotThrow(() -> Parse.parseLength(bufferedReader));
  }

  @Test()
  void incorrectParseLengthEqualZero() {
    StringWriter stringWriter = new StringWriter();
    stringWriter.write("0");

    BufferedReader bufferedReader = new BufferedReader(new StringReader(stringWriter.toString()));

    System.setOut(new PrintStream(outContent));
    Parse.parseLength(bufferedReader);
    assertEquals(LENGTH_EQUAL_ZERO, outContent.toString());
  }

  @Test()
  void incorrectParseLengthValueIsLessThanZero() {

    StringWriter stringWriter = new StringWriter();
    stringWriter.write("-100");

    BufferedReader bufferedReader = new BufferedReader(new StringReader(stringWriter.toString()));
    System.setOut(new PrintStream(outContent));
    assertEquals(5, Parse.parseLength(bufferedReader));
    assertEquals(STR_WITH_NOT_NUMBERS, outContent.toString());
  }

  @Test()
  void incorrectParseLengthTheValueIsGreaterThan1000000() {
    StringWriter stringWriter = new StringWriter();
    stringWriter.write("10000001");

    BufferedReader bufferedReader = new BufferedReader(new StringReader(stringWriter.toString()));
    System.setOut(new PrintStream(outContent));
    assertEquals(5, Parse.parseLength(bufferedReader));
    assertEquals(LENGTH_TOO_LARGE, outContent.toString());
  }

  @Test()
  void incorrectParseLengthTheValueIsGreaterThanMaxInteger() {
    StringWriter stringWriter = new StringWriter();
    stringWriter.write("273828191991929929911991");

    BufferedReader br = new BufferedReader(new StringReader(stringWriter.toString()));
    System.setOut(new PrintStream(outContent));
    assertEquals(5, Parse.parseLength(br));
    assertEquals(LENGTH_TOO_LARGE, outContent.toString());
  }

  @Test()
  void incorrectParseLengthNotInteger() {
    StringWriter stringWriter = new StringWriter();
    stringWriter.write("AAAAAAAAAAAAAAAAAAAAAAA");

    BufferedReader bufferedReader = new BufferedReader(new StringReader(stringWriter.toString()));
    System.setOut(new PrintStream(outContent));
    assertEquals(5, Parse.parseLength(bufferedReader));
    assertEquals(STR_WITH_NOT_NUMBERS, outContent.toString());
  }

  @Test()
  void incorrectParseLengthErrorRead() {
    StringWriter stringWriter1 = new StringWriter();
    stringWriter1.write("1 \n a");

    BufferedReader bufferedReader1 = new BufferedReader(new StringReader(stringWriter1.toString()));
    System.setOut(new PrintStream(outContent));
    assertEquals(5, Parse.parseLength(bufferedReader1));
    assertEquals(STR_WITH_NOT_NUMBERS, outContent.toString());

    StringWriter stringWriter2 = new StringWriter();
    stringWriter2.write("");
    BufferedReader bufferedReader2 = new BufferedReader(new StringReader(stringWriter2.toString()));
    assertThrows(InvalidStreamException.class, () -> Parse.parseLength(bufferedReader2));
  }
}
