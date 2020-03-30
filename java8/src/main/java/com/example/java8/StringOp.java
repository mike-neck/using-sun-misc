package com.example.java8;

import java.io.IOException;
import java.io.UncheckedIOException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringOp {

  public String operate(byte[] bytes) {
    return new BASE64Encoder().encode(bytes);
  }

  public byte[] operate(String base64) {
    try {
      return new BASE64Decoder().decodeBuffer(base64);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
