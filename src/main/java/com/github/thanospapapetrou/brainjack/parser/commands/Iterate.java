package com.github.thanospapapetrou.brainjack.parser.commands;

import com.github.thanospapapetrou.brainjack.parser.tokenizer.Token;

import java.util.List;
import java.util.Objects;

import javax.script.ScriptContext;

public class Iterate implements Command {
  private static final String NULL_COMMANDS = "Commands must not be null";
  private static final String NULL_END_TOKEN = "End token must not be null";
  private static final String NULL_START_TOKEN = "Start token must not be null";

  private final Token startToken;
  private final Token endToken;
  private final List<Command> commands;

  public Iterate(final Token startToken, final Token endToken, final List<Command> commands) {
    Objects.requireNonNull(startToken, NULL_START_TOKEN);
    Objects.requireNonNull(endToken, NULL_END_TOKEN);
    Objects.requireNonNull(commands, NULL_COMMANDS);
    this.startToken = startToken;
    this.endToken = endToken;
    this.commands = commands;
  }

  @Override
  public void eval(ScriptContext context) {
    // TODO Auto-generated method stub
    
  }
}
