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
  private static final String NULL_CONTEXT = "Context must not be null";
  private static final String NULL_SCRIPT = "Script must not be null";

  private final BrainjackEngineFactory factory;

  BrainjackEngine(final BrainjackEngineFactory factory) {
    this.factory = factory;
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
}
