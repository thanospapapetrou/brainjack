package com.github.thanospapapetrou.brainjack.parser;

import com.github.thanospapapetrou.brainjack.parser.tokenizer.Token;
import com.github.thanospapapetrou.brainjack.parser.tokenizer.TokenType;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.script.ScriptException;

/**
 * Class representing a parse exception.
 * 
 * @author thanos
 */
public class ParseException extends ScriptException {
  private static final String DELIMITER = ", ";
  private static final String EMPTY_EXPECTED = "Expected must not be empty";
  private static final String NULL_EXPECTED = "Expected must not be null";
  private static final String NULL_UNEXPECTED = "Unexpected must not be null";
  private static final String UNEXPECTED = "Unexpected %1$s, expected %2$s";
  private static final long serialVersionUID = 0L;

  private static TokenType[] requireValidExpected(final TokenType[] expected) {
    Objects.requireNonNull(expected, NULL_EXPECTED);
    if (expected.length == 0) {
      throw new IllegalArgumentException(EMPTY_EXPECTED);
    }
    return expected;
  }

  /**
   * Construct a new parse exception.
   * 
   * @param unexpected
   *          the unexpected token
   * @param expected
   *          the token types expected
   */
  public ParseException(final Token unexpected, final TokenType... expected) {
    super(String.format(UNEXPECTED, Objects.requireNonNull(unexpected, NULL_UNEXPECTED).getType(),
        Stream.of(requireValidExpected(expected)).map(TokenType::toString)
            .collect(Collectors.joining(DELIMITER)),
        unexpected.getFile(), unexpected.getLine(), unexpected.getColumn()));
  }
}
