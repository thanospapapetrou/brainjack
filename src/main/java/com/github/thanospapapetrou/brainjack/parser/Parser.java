package com.github.thanospapapetrou.brainjack.parser;

import com.github.thanospapapetrou.brainjack.parser.commands.Command;
import com.github.thanospapapetrou.brainjack.parser.commands.Iteration;
import com.github.thanospapapetrou.brainjack.parser.commands.SimpleCommand;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.Token;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.TokenType;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.script.ScriptException;

/**
 * Class implementing a parser.
 * 
 * @author thanos
 */
public class Parser {
  private static final String NULL_TOKENIZER = "Tokenizer must not be null";

  private final Tokenizer tokenizer;

  /**
   * Construct a new parser.
   * 
   * @param tokenizer
   *          the tokenizer to use for reading tokens
   */
  public Parser(final Tokenizer tokenizer) {
    Objects.requireNonNull(tokenizer, NULL_TOKENIZER);
    this.tokenizer = tokenizer;
  }

  /**
   * Parse Brainfuck commands.
   * 
   * @return a list of Brainfuck commands
   * @throws ScriptException
   *           if any I/O or parse errors occur
   */
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
            ? throwParseException(token, TokenType.GREATER_THAN, TokenType.LESS_THAN,
                TokenType.PLUS, TokenType.MINUS, TokenType.FULL_STOP, TokenType.COMMA,
                TokenType.LEFT_SQUARE_BRACKET, TokenType.EOF)
            : (T) new Iteration(iterationStart, token, commands);
      case EOF:
        return (iterationStart == null) ? (T) commands
            : throwParseException(token, TokenType.GREATER_THAN, TokenType.LESS_THAN,
                TokenType.PLUS, TokenType.MINUS, TokenType.FULL_STOP, TokenType.COMMA,
                TokenType.LEFT_SQUARE_BRACKET, TokenType.RIGHT_SQUARE_BRACKET);
      }
    }
  }

  private <T> T throwParseException(final Token unexpected, final TokenType... expected)
      throws ScriptException {
    throw new ParseException(unexpected, expected);
  }
}
