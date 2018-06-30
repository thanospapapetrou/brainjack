package com.github.thanospapapetrou.brainjack;

import com.github.thanospapapetrou.brainjack.parser.Parser;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.Tokenizer;

import java.io.Reader;
import java.io.StringReader;
import java.util.Map;
import java.util.Objects;

import javax.script.AbstractScriptEngine;
import javax.script.Compilable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * Class implementing Brainjack engine.
 * 
 * @author thanos
 */
public class BrainjackEngine extends AbstractScriptEngine implements Compilable, ScriptEngine {
  private static final String ARRAY = "array";
  private static final String NULL_CONTEXT = "Context must not be null";
  private static final String NULL_SCRIPT = "Script must not be null";
  private static final String POINTER = "pointer";

  private final BrainjackEngineFactory factory;

  BrainjackEngine(final BrainjackEngineFactory factory) {
    this.factory = factory;
    put(POINTER, 0);
    put(ARRAY, new byte[3000]); // TODO set size
  }

  @Override
  public BrainjackScript compile(final Reader script) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    final BrainjackScript result =
        new BrainjackScript(this, new Parser(new Tokenizer(script, null)).parse());
    System.out.println(result); // TODO remove
    return result;
  }

  @Override
  public BrainjackScript compile(final String script) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    return compile(new StringReader(script));
  }

  @Override
  public SimpleBindings createBindings() {
    return new SimpleBindings(); // TODO use custom bindings?
  }

  @Override
  public Void eval(final String script, final ScriptContext context) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    Objects.requireNonNull(context, NULL_CONTEXT);
    System.out.println("Context: " + context); // TODO remove
    for (final int scope : context.getScopes()) {
      System.out.println("Scope: " + scope);
      System.out.println("Bindings: " + context.getBindings(scope));
      for (Map.Entry<String, Object> entry : context.getBindings(scope).entrySet()) {
        System.out.println(entry.getKey() + " " + entry.getValue());
      }
    }
    return compile(script).eval(context);
  }

  @Override
  public Void eval(final Reader script, final ScriptContext context) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    Objects.requireNonNull(context, NULL_CONTEXT);
    return compile(script).eval(context);
  }

  @Override
  public BrainjackEngineFactory getFactory() {
    return factory;
  }

  public int getPointer() throws ScriptException { // TODO validate arguments and fix order in this
                                                   // method and the following
    Object pointer = getBindings(ScriptContext.ENGINE_SCOPE).get(POINTER);
    if (Integer.class.isAssignableFrom(pointer.getClass())) {
      return Integer.class.cast(pointer);
    }
    throw new ScriptException("Pointer is not an integer"); // TODO
  }

  public void setPointer(final int pointer) { // TODO check pointer range?
    getBindings(ScriptContext.ENGINE_SCOPE).put(POINTER, pointer);
  }

  public byte getValue() throws ScriptException {
    return getArray()[getPointer()];
  }

  public void setValue(final byte value) throws ScriptException {
    getArray()[getPointer()] = value;
  }

  private byte[] getArray() throws ScriptException {
    final Object array = getBindings(ScriptContext.ENGINE_SCOPE).get(ARRAY);
    if (byte[].class.isAssignableFrom(array.getClass())) {
      return byte[].class.cast(array);
    }
    throw new ScriptException("Array is not a byte array"); // TODO
  }

  // TODO add common implementation for getPointer and getArray
}
