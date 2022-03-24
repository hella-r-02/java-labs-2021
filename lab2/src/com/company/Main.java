package com.company;

import com.sun.nio.sctp.InvalidStreamException;
import exceptions.NotFoundFileException;
import matrix.Matrix;
import parse.Parse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {
  public static void main(String[] args) {
    try (Scanner s = new Scanner(System.in)) {
      System.out.println("Enter input file");
      String inputFileName = s.nextLine();
      File inputFile = getFile(inputFileName);

      System.out.println("Enter output file");
      String outputFileName = s.nextLine();
      File outputFile = getFile(outputFileName);

      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
           BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter((outputFile)))) {

        int length = Parse.parseLength(bufferedReader);

        Matrix matrix = new Matrix(length);
        matrix.rand();

        bufferedWriter.write("Matrix\n");
        matrix.outputMatrix(bufferedWriter);
        bufferedWriter.write("Rotate matrix by 90 degrees\n");

        Matrix copyMatrix1 = new Matrix(matrix);
        copyMatrix1.rotateAndDivideMatrix(1);
        copyMatrix1.outputMatrix(bufferedWriter);

        bufferedWriter.write("Rotate matrix by 180 degrees\n");
        Matrix copyMatrix2 = new Matrix(matrix);
        copyMatrix2.rotateAndDivideMatrix(2);
        copyMatrix2.outputMatrix(bufferedWriter);

        bufferedWriter.write("Rotate matrix by 270 degrees\n");
        Matrix copyMatrix3 = new Matrix(matrix);
        copyMatrix3.rotateAndDivideMatrix(3);
        copyMatrix3.outputMatrix(bufferedWriter);

      } catch (
        OutOfMemoryError | IOException | InvalidStreamException e) {
        e.printStackTrace();
      }
    } catch (NotFoundFileException e) {
      e.printStackTrace();
    }
  }

  public static File getFile(String fileName) throws NotFoundFileException {
    Path pathInput = Paths.get(fileName);
    File inputFile = new File(fileName);
    if (!inputFile.exists()) {
      throw new NotFoundFileException("File not found");
    } else if (!Files.isRegularFile(pathInput)) {
      throw new NotFoundFileException("File not found");
    }
    return inputFile;
  }
}
