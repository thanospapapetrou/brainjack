package com.github.thanospapapetrou.brainjack.parser.commands;

import com.github.thanospapapetrou.brainjack.parser.tokenizer.Token;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.TokenType;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.script.ScriptContext;

/**
 * Class representing a simple Brainfuck command.
 * 
 * @author thanos
 */
public class SimpleCommand implements Command {
  private static final String INVALID_TOKEN = String.format("Token must be %1$s",
      Stream
          .of(TokenType.MOVE_RIGHT, TokenType.MOVE_LEFT, TokenType.INCREASE, TokenType.DECREASE,
              TokenType.WRITE, TokenType.READ)
          .map(TokenType::toString).collect(Collectors.joining(", ")));
  private static final String NULL_TOKEN = "Token must not be null";

  private final Token token;

  private static void requireValidToken(final Token token) {
    Objects.requireNonNull(token, NULL_TOKEN);
    final TokenType type = token.getType();
    if ((type == TokenType.ITERATION_START) || (type == TokenType.ITERATION_END)
        || (type == TokenType.EOF)) {
      throw new IllegalArgumentException(INVALID_TOKEN);
    }
  }

  /**
   * Construct a new simple command.
   * 
   * @param token
   *          the token corresponding to this command
   */
  public SimpleCommand(final Token token) {
    requireValidToken(token);
    this.token = token;
  }

  @Override
  public void eval(final ScriptContext context) {
    switch (token.getType()) {
    case MOVE_RIGHT:
    case MOVE_LEFT:
    case INCREASE:
    case DECREASE:
    case WRITE:
    case READ:
    case ITERATION_START:
    case ITERATION_END:
    case EOF:
    }
  }

  @Override
  public String toString() {
    return token.toString();
  }
}
