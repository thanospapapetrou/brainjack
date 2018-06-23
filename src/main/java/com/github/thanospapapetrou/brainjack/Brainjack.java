package com.github.thanospapapetrou.brainjack;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class Brainjack {
  public static void main(final String[] arguments) {
    final ScriptEngineManager manager = new ScriptEngineManager();
    for (final ScriptEngineFactory factory : manager.getEngineFactories()) {
      System.out.println("Language Name: " + factory.getLanguageName());
      System.out.println("Language Version: " + factory.getLanguageVersion());
      System.out.println("Engine Name: " + factory.getEngineName());
      System.out.println("Engigne Version: " + factory.getEngineVersion());
      System.out.println("Names: " + factory.getNames());
      System.out.println("MIME Types: " + factory.getMimeTypes());
      System.out.println("Extensions: " + factory.getExtensions());
      System.out.println();
    }
  }
}
