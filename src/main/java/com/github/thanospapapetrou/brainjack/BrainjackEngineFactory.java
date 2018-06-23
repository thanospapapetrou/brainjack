package com.github.thanospapapetrou.brainjack;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

public class BrainjackEngineFactory implements ScriptEngineFactory {
  private static final String DELIMITER = ",\\s*";
  private static final String ENGINE_NAME = "ScriptEngine.ENGINE";
  private static final String ENGINE_VERSION = "ScriptEngine.ENGINE_VERSION";
  private static final String EXTENSION = "ScriptEngine.EXTENSION";
  private static final String LANGUAGE_NAME = "ScriptEngine.LANGUAGE";
  private static final String LANGUAGE_VERSION = "ScriptEngine.LANGUAGE_VERSION";
  private static final String MIME_TYPE = "ScriptEngine.MIME_TYPE";
  private static final String NAME = "ScriptEngine.NAME";
  private static final String PROPERTIES = "/brainjack.properties";

  private final Properties properties;

  private static Properties loadProperties() throws IOException {
    final Properties properties = new Properties();
    properties.load(BrainjackEngineFactory.class.getResourceAsStream(PROPERTIES));
    return properties;
  }

  public BrainjackEngineFactory() throws IOException { // TODO do not throw exception?
    this(loadProperties());
  }

  private BrainjackEngineFactory(final Properties properties) {
    this.properties = properties;
  }

  @Override
  public String getEngineName() {
    return getStringProperty(ENGINE_NAME);
  }

  @Override
  public String getEngineVersion() {
    return getStringProperty(ENGINE_VERSION);
  }

  @Override
  public List<String> getExtensions() {
    return getListProperty(EXTENSION);
  }

  @Override
  public List<String> getMimeTypes() {
    return getListProperty(MIME_TYPE);
  }

  @Override
  public List<String> getNames() {
    return getListProperty(NAME);
  }

  @Override
  public String getLanguageName() {
    return getStringProperty(LANGUAGE_NAME);
  }

  @Override
  public String getLanguageVersion() {
    return getStringProperty(LANGUAGE_VERSION);
  }

  @Override
  public Object getParameter(final String key) {
    if (EXTENSION.equals(key) || MIME_TYPE.equals(key)) {
      return getListProperty(key);
    } else if (NAME.equals(key)) {
      return getListProperty(key).get(0);
    } else {
      return getStringProperty(key);
    }
  }

  @Override
  public String getMethodCallSyntax(final String object, final String method,
      final String... arguments) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getOutputStatement(final String message) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getProgram(final String... statements) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ScriptEngine getScriptEngine() {
    // TODO Auto-generated method stub
    return null;
  }

  private String getStringProperty(final String key) {
    return properties.getProperty(key);
  }

  private List<String> getListProperty(final String key) {
    return Arrays.asList(properties.getProperty(key).split(DELIMITER));
  }
}
