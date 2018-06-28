package com.github.thanospapapetrou.brainjack.parser.commands;

import com.github.thanospapapetrou.brainjack.parser.tokenizer.Token;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.TokenType;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.script.ScriptContext;

/**
 * Class representing a simple Brainfuck command.
 * 
 * @author thanos
 */
public class SimpleCommand implements Command {
  private static final String INVALID_TOKEN = String.format("Token must be %1$s",
      String.join(", ",
          Arrays
              .stream(new TokenType[] {TokenType.GREATER_THAN, TokenType.LESS_THAN, TokenType.PLUS,
                  TokenType.MINUS, TokenType.FULL_STOP, TokenType.COMMA})
              .map(TokenType::toString).collect(Collectors.toList()).toArray(new String[0])));
  private static final String NULL_TOKEN = "Token must not be null";

  private final Token token;

  private static void requireValidToken(final Token token) {
    Objects.requireNonNull(token, NULL_TOKEN);
    if ((token.getType() == TokenType.LEFT_SQUARE_BRACKET)
        || (token.getType() == TokenType.RIGHT_SQUARE_BRACKET)) {
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
    case GREATER_THAN:
    case LESS_THAN:
    case PLUS:
    case MINUS:
    case FULL_STOP:
    case COMMA:
    case LEFT_SQUARE_BRACKET:
    case RIGHT_SQUARE_BRACKET:
    case EOF:
    }
  }
}
