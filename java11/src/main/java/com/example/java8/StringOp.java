package com.example.java8;

import java.util.Base64;

public class StringOp {

  public String operate(byte[] bytes) {
    return Base64.getEncoder().encodeToString(bytes);
  }

  public byte[] operate(String base64) {
    return Base64.getDecoder().decode(base64);
  }
}
