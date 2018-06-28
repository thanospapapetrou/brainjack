package com.github.thanospapapetrou.brainjack.parser.tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

import javax.script.ScriptException;

/**
 * Class implementing a Brainjack tokenizer.
 * 
 * @author thanos
 */
public class Tokenizer {
  private static final String NULL_READER = "Reader must not be null";

  private final Reader reader;

  public Tokenizer(final Reader reader) {
    Objects.requireNonNull(reader, NULL_READER);
    this.reader = reader;
  }

  public Token getNextToken() throws ScriptException {
    while (true) {
      try {
        final int token = reader.read();
        for (final TokenType type : TokenType.values()) {
          if (type.getToken() == token) {
            return new Token(type, null, -1, -1); // TODO
          }
        }
      } catch (final IOException e) {
        throw new ScriptException(e);
      }
    }
  }
}
