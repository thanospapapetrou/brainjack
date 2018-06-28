package com.github.thanospapapetrou.brainjack.parser.commands;

import javax.script.ScriptContext;

/**
 * Interface defining a Brainjack command.
 * 
 * @author thanos
 */
public interface Command {
  /**
   * Evaluate.
   * 
   * @param context
   *          the script context in which to evaluate this command
   */
  public void eval(final ScriptContext context);
}
