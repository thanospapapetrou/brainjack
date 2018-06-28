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
      case MOVE_RIGHT:
      case MOVE_LEFT:
      case INCREASE:
      case DECREASE:
      case WRITE:
      case READ:
        commands.add(new SimpleCommand(token));
        break;
      case ITERATION_START:
        commands.add(parse(token));
        break;
      case ITERATION_END:
        return (iterationStart == null)
            ? throwParseException(token, TokenType.MOVE_RIGHT, TokenType.MOVE_LEFT,
                TokenType.INCREASE, TokenType.DECREASE, TokenType.WRITE, TokenType.READ,
                TokenType.ITERATION_START, TokenType.EOF)
            : (T) new Iteration(iterationStart, token, commands);
      case EOF:
        return (iterationStart == null) ? (T) commands
            : throwParseException(token, TokenType.MOVE_RIGHT, TokenType.MOVE_LEFT,
                TokenType.INCREASE, TokenType.DECREASE, TokenType.WRITE, TokenType.READ,
                TokenType.ITERATION_START, TokenType.ITERATION_END);
      }
    }
  }

  private <T> T throwParseException(final Token unexpected, final TokenType... expected)
      throws ScriptException {
    throw new ParseException(unexpected, expected);
  }
}
