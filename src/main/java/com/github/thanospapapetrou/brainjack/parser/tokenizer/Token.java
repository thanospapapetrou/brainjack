package com.github.thanospapapetrou.brainjack.parser.tokenizer;

import java.util.Objects;

/**
 * Class representing a Brainfuck token.
 * 
 * @author thanos
 */
public class Token {
  private static final String NULL_TYPE = "Type must not be null";

  private final TokenType type;
  private final String file;
  private final int line;
  private final int column;

  /**
   * Construct a new token.
   * 
   * @param type
   *          the type of this token
   * @param file
   *          the file of this token
   * @param line
   *          the line of this token
   * @param column
   *          the column of this token
   */
  public Token(final TokenType type, final String file, final int line, final int column) {
    Objects.requireNonNull(type, NULL_TYPE);
    this.type = type;
    this.file = file;
    this.line = line;
    this.column = column;
  }

  /**
   * Get type.
   * 
   * @return the type of this token
   */
  public TokenType getType() {
    return type;
  }

  /**
   * Get file.
   * 
   * @return the file of this token
   */
  public String getFile() {
    return file;
  }

  /**
   * Get line.
   * 
   * @return the line of this token
   */
  public int getLine() {
    return line;
  }

  /**
   * Get column.
   * 
   * @return the column of this token
   */
  public int getColumn() {
    return column;
  }

  @Override
  public String toString() {
    return (type == TokenType.EOF) ? "" : Character.toString((char) type.getToken());
  }
}
