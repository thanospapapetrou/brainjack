package com.github.thanospapapetrou.brainjack.parser;

import com.github.thanospapapetrou.brainjack.parser.commands.Command;
import com.github.thanospapapetrou.brainjack.parser.commands.Iterate;
import com.github.thanospapapetrou.brainjack.parser.commands.SimpleCommand;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.Token;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.TokenType;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.script.ScriptException;

public class Parser {
  private static final String DELIMITER = ", ";
  private static final String NULL_TOKENIZER = "Tokenizer must not be null";
  private static final String UNEXPECTED = "Unexpected %1$s, expected %2$s";

  private final Tokenizer tokenizer;

  public Parser(final Tokenizer tokenizer) {
    Objects.requireNonNull(tokenizer, NULL_TOKENIZER);
    this.tokenizer = tokenizer;
  }

  public List<Command> parse() throws ScriptException {
    return parse(null);
  }

  @SuppressWarnings("unchecked")
  private <T> T parse(final Token iterationStart) throws ScriptException {
    final List<Command> commands = new ArrayList<>();
    while (true) {
      final Token token = tokenizer.getNextToken();
      switch (token.getType()) {
      case GREATER_THAN:
      case LESS_THAN:
      case PLUS:
      case MINUS:
      case FULL_STOP:
      case COMMA:
        commands.add(new SimpleCommand(token));
        break;
      case LEFT_SQUARE_BRACKET:
        commands.add(parse(token));
        break;
      case RIGHT_SQUARE_BRACKET:
        return (iterationStart == null)
            ? unexpected(token, TokenType.GREATER_THAN, TokenType.LESS_THAN, TokenType.PLUS,
                TokenType.MINUS, TokenType.FULL_STOP, TokenType.COMMA,
                TokenType.LEFT_SQUARE_BRACKET, TokenType.EOF)
            : (T) new Iterate(iterationStart, token, commands);
      case EOF:
        return (iterationStart == null) ? (T) commands
            : unexpected(token, TokenType.GREATER_THAN, TokenType.LESS_THAN, TokenType.PLUS,
                TokenType.MINUS, TokenType.FULL_STOP, TokenType.COMMA,
                TokenType.LEFT_SQUARE_BRACKET, TokenType.RIGHT_SQUARE_BRACKET);
      }
    }
  }

  private <T> T unexpected(final Token unexpected, final TokenType... expected)
      throws ScriptException { // TODO create custom exception
    throw new ScriptException(
        String.format(UNEXPECTED, unexpected.getType(),
            String.join(DELIMITER,
                Arrays.stream(expected).map(TokenType::toString).collect(Collectors.toList()))),
        unexpected.getFile(), unexpected.getLine(), unexpected.getColumn());
  }
}
