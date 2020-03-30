package com.example;

import com.example.java8.StringOp;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class App {

  private static final String TEXT = "foo-bar-baz";

  public static void main(String[] args) {
    StringOp stringOp = new StringOp();
    String operate = stringOp.operate(TEXT.getBytes(StandardCharsets.UTF_8));
    String result = String.format("%s -> %s", TEXT, operate);
    System.out.println(result);

    byte[] bytes = stringOp.operate(operate);
    System.out.printf("%s -> %s(%s)\n", operate, Arrays.toString(bytes), new String(bytes, StandardCharsets.UTF_8));
  }
}
