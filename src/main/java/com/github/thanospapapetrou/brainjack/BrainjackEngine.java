package com.github.thanospapapetrou.brainjack;

import java.io.Reader;
import java.util.Objects;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * Class implementing Brainjack engine.
 * 
 * @author thanos
 */
public class BrainjackEngine extends AbstractScriptEngine implements Compilable, ScriptEngine {
  private static final String NULL_CONTEXT = "Context must not be null";
  private static final String NULL_FACTORY = "Factory must not be null";
  private static final String NULL_SCRIPT = "Script must not be null";

  private final BrainjackEngineFactory factory;

  /**
   * Construct a new Brainjack engine.
   * 
   * @param factory
   *          the Brainjack engine factory of this engine
   */
  public BrainjackEngine(final BrainjackEngineFactory factory) {
    Objects.requireNonNull(factory, NULL_FACTORY);
    this.factory = factory;
  }

  @Override
  public CompiledScript compile(final Reader script) throws ScriptException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompiledScript compile(final String script) throws ScriptException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Bindings createBindings() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object eval(final String script, final ScriptContext context) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    Objects.requireNonNull(context, NULL_CONTEXT);
    return compile(script).eval(context);
  }

  @Override
  public Object eval(final Reader script, final ScriptContext context) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    Objects.requireNonNull(context, NULL_CONTEXT);
    return compile(script).eval(context);
  }

  @Override
  public BrainjackEngineFactory getFactory() {
    return factory;
  }
}
