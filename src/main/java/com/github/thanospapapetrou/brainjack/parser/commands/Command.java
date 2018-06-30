package com.github.thanospapapetrou.brainjack.parser.commands;

import com.github.thanospapapetrou.brainjack.BrainjackEngine;

import javax.script.ScriptException;

/**
 * Interface defining a Brainfuck command.
 * 
 * @author thanos
 */
public interface Command {
  /**
   * Execute.
   * 
   * @param engine
   *          the Brainjack engine to use to execute this command
   * @throws ScriptException
   *           if any errors occur while execution
   */
  public void execute(final BrainjackEngine engine) throws ScriptException;
}
