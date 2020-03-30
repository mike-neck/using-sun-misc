package com.example.agent;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;

public class Fixer implements ClassFileTransformer {

  public static void premain(String args, Instrumentation instrumentation) {
    System.out.println("premain called");
    Arrays.stream(instrumentation.getAllLoadedClasses())
        .filter(it -> it.getPackageName().contains("com.example"))
        .map(it -> String.format("loaded: %s", it.getCanonicalName()))
        .forEach(System.out::println);
    instrumentation.addTransformer(new Fixer(), true);
  }

  @Override
  public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
      ProtectionDomain protectionDomain, byte[] classfileBuffer) {
    try {
      if ("com/example/java8/StringOp".equals(className)) {
        System.out.println(
            "[" + Thread.currentThread().getName() + "]" + "redefine :" + className + " @domain: "
                + protectionDomain.getCodeSource().getLocation());
        System.out.println("start loading class: " + className);
        InputStream resourceAsStream = loader.getResourceAsStream("com/example/agent/Dec.class");
        if (resourceAsStream == null) {
          System.out.println("failed to load StringOp.class");
          throw new IllegalStateException("failed to load StringOp.class");
        }
        try (BufferedInputStream in = new BufferedInputStream(resourceAsStream)) {
          System.out.println("load : " + className);
          return in.readAllBytes();
        } catch (IOException e) {
          throw new UncheckedIOException(e);
        }
      }
      return null;
    } catch (NoClassDefFoundError e) {
      System.out.println("エラーだわー");
      throw e;
    }
  }
}
