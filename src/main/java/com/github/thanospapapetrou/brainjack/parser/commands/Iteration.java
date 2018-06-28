package com.github.thanospapapetrou.brainjack.parser.commands;

import com.github.thanospapapetrou.brainjack.parser.tokenizer.Token;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.TokenType;

import java.util.List;
import java.util.Objects;

import javax.script.ScriptContext;

/**
 * Class representing a Brainfuck iteration.
 * 
 * @author thanos
 */
public class Iteration implements Command {
  private static final String INVALID_END_TOKEN =
      String.format("End token must be %1$s", TokenType.ITERATION_END);
  private static final String INVALID_START_TOKEN =
      String.format("Start token must be %1$s", TokenType.ITERATION_START);
  private static final String NULL_COMMANDS = "Commands must not be null";
  private static final String NULL_END_TOKEN = "End token must not be null";
  private static final String NULL_START_TOKEN = "Start token must not be null";

  private final Token startToken;
  private final Token endToken;
  private final List<Command> commands;

  private static void requireValidToken(final Token token, final String nullMessage,
      final TokenType type, final String invalidMessage) {
    Objects.requireNonNull(token, nullMessage);
    if (token.getType() != type) {
      throw new IllegalArgumentException(invalidMessage);
    }
  }

  /**
   * Construct a new iteration.
   * 
   * @param startToken
   *          the token corresponding to the start of this iteration
   * @param endToken
   *          the token corresponding to the end of this iteration
   * @param commands
   *          the commands contained in this iteration
   */
  public Iteration(final Token startToken, final Token endToken, final List<Command> commands) {
    requireValidToken(startToken, NULL_START_TOKEN, TokenType.ITERATION_START,
        INVALID_START_TOKEN);
    requireValidToken(endToken, NULL_END_TOKEN, TokenType.ITERATION_END, INVALID_END_TOKEN);
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
