package com.github.thanospapapetrou.brainjack.parser.tokenizer;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

import javax.script.ScriptException;

/**
 * Class implementing a tokenizer.
 * 
 * @author thanos
 */
public class Tokenizer {
  private static final String NULL_READER = "Reader must not be null";

  private final Reader reader;
  private final String file;
  private int line;
  private int column;

  /**
   * Construct a new tokenizer.
   * 
   * @param reader
   *          the reader to read tokens from
   * @param file
   *          the file name corresponding to previous reader or <code>null</code> if unknown or
   *          reader does not correspond to any file
   */
  public Tokenizer(final Reader reader, final String file) {
    Objects.requireNonNull(reader, NULL_READER);
    this.reader = reader;
    this.file = file;
    line = 0;
    column = 0;
  }

  /**
   * Get next token.
   * 
   * @return the next available token
   * @throws ScriptException
   *           if any I/O errors occur
   */
  public Token getNextToken() throws ScriptException {
    while (true) {
      try {
        final int token = reader.read();
        for (final TokenType type : TokenType.values()) {
          if (type.getToken() == token) {
            return new Token(type, file, line, column); // TODO increase line, column
          }
        }
      } catch (final IOException e) {
        throw new ScriptException(e);
      }
    }
  }
}
