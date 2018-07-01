package com.github.thanospapapetrou.brainjack;

import com.github.thanospapapetrou.brainjack.parser.Parser;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.Tokenizer;

import java.io.Reader;
import java.io.StringReader;
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
  private static final String NON_BYTE_ARRAY_ARRAY = "Array is not a byte array";
  private static final String NON_INTEGER_POINTER = "Pointer is not an integer";
  private static final String NULL_CONTEXT = "Context must not be null";
  private static final String NULL_SCRIPT = "Script must not be null";
  private static final String POINTER = "pointer";

  private final BrainjackEngineFactory factory;

  BrainjackEngine(final BrainjackEngineFactory factory) {
    this.factory = factory;
    put(POINTER, 0);
    put(ARRAY, new byte[3000]); // TODO set size
  }

  /**
   * Get pointer.
   * 
   * @return the pointer of this engine
   * @throws ScriptException
   *           if any errors occur while retrieving pointer
   */
  public int getPointer() throws ScriptException {
    return get(POINTER, Integer.class, NON_INTEGER_POINTER);
  }

  /**
   * Set pointer.
   * 
   * @param pointer
   *          the new pointer of this engine
   */
  public void setPointer(final int pointer) { // TODO check pointer range?
    getBindings(ScriptContext.ENGINE_SCOPE).put(POINTER, pointer);
  }

  /**
   * Get array.
   * 
   * @return the array of this engine
   * @throws ScriptException
   *           if any errors occur while retrieving array
   */
  public byte[] getArray() throws ScriptException {
    return get(ARRAY, byte[].class, NON_BYTE_ARRAY_ARRAY);
  }

  /**
   * Get value.
   * 
   * @return the value of the array at current pointer of this engine
   * @throws ScriptException
   *           if any errors occur while retrieving value
   */
  public byte getValue() throws ScriptException {
    return getArray()[getPointer()];
  }

  /**
   * Set value.
   * 
   * @param value
   *          the new value of the array at current pointer of this engine
   * @throws ScriptException
   *           if any errors occur while setting value
   */
  public void setValue(final byte value) throws ScriptException {
    getArray()[getPointer()] = value;
  }

  @Override
  public BrainjackScript compile(final Reader script) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    return new BrainjackScript(this, new Parser(new Tokenizer(script, null)).parse());
  }

  @Override
  public BrainjackScript compile(final String script) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    return compile(new StringReader(script));
  }

  @Override
  public SimpleBindings createBindings() {
    final SimpleBindings bindings = new SimpleBindings();
    bindings.put(POINTER, 0);
    bindings.put(ARRAY, new byte[3000]); // TODO use size
    return bindings;
  }

  @Override
  public Void eval(final String script, final ScriptContext context) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    Objects.requireNonNull(context, NULL_CONTEXT);
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

  private <T> T get(final String name, final Class<T> clazz, final String message)
      throws ScriptException {
    final Object object = getBindings(ScriptContext.ENGINE_SCOPE).get(name);
    if (clazz.isAssignableFrom(object.getClass())) {
      return clazz.cast(object);
    }
    throw new ScriptException(new IllegalStateException(message)); // TODO add context info?
  }
}
