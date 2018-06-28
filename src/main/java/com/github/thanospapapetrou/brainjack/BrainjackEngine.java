package com.github.thanospapapetrou.brainjack;

import com.github.thanospapapetrou.brainjack.parser.Parser;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.Tokenizer;

import java.io.Reader;
import java.io.StringReader;
import java.util.Objects;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
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
  private static final String NULL_CONTEXT = "Context must not be null";
  private static final String NULL_SCRIPT = "Script must not be null";

  private final BrainjackEngineFactory factory;

  BrainjackEngine(final BrainjackEngineFactory factory) {
    this.factory = factory;
  }

  @Override
  public CompiledScript compile(final Reader script) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    return new BrainjackScript(this, new Parser(new Tokenizer(script, null)).parse());
  }

  @Override
  public CompiledScript compile(final String script) throws ScriptException {
    Objects.requireNonNull(script, NULL_SCRIPT);
    return compile(new StringReader(script));
  }

  @Override
  public Bindings createBindings() {
    return new SimpleBindings(); // TODO use custom bindings?
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
