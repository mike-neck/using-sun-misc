package com.example.agent;

import java.util.Base64;

public class Enc {

  public String decode(byte[] bytes) {
    return Base64.getEncoder().encodeToString(bytes);
  }
}
