package exceptions;

public class ExceedingTheBorderException extends ArithmeticException {
  public ExceedingTheBorderException(String message) {
    super(message);
  }
}
