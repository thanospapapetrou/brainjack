package com.github.thanospapapetrou.brainjack;

import com.github.thanospapapetrou.brainjack.parser.commands.Command;

import java.util.List;
import java.util.Objects;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptException;

/**
 * Class representing a Brainjack script.
 * 
 * @author thanos
 */
public class BrainjackScript extends CompiledScript {
  /**
   * Constant identifying script scope, which contains attributes visible only to a single script.
   */
  public static final int SCRIPT_SCOPE =
      2 * ScriptContext.ENGINE_SCOPE - ScriptContext.GLOBAL_SCOPE; // TODO should this be part of
                                                                   // engine?

  private static final String NULL_CONTEXT = "Context must not be null";

  private final BrainjackEngine engine;
  private final List<Command> commands;

  BrainjackScript(final BrainjackEngine engine, final List<Command> commands) {
    this.engine = engine;
    this.commands = commands;
  }

  @Override
  public Void eval(final ScriptContext context) throws ScriptException {
    Objects.requireNonNull(context, NULL_CONTEXT);
    for (final Command command : commands) {
      command.eval(context);
    }
    return null;
  }

  @Override
  public BrainjackEngine getEngine() {
    return engine;
  }
}
