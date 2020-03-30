package com.example.agent;

import java.util.Base64;

public class Dec {

  public byte[] decode(String base) {
    return Base64.getDecoder().decode(base);
  }
}
