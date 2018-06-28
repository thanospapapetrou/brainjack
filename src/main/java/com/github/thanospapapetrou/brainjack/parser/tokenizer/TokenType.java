package com.github.thanospapapetrou.brainjack.parser.tokenizer;

/**
 * Enumeration representing Brainfuck token types.
 * 
 * @author thanos
 */
public enum TokenType {
  /**
   * Greater than (<code>&gt;</code>).
   */
  GREATER_THAN("greater than", '>'),

  /**
   * Less than (<code>&lt;</code>).
   */
  LESS_THAN("less than", '<'),

  /**
   * Plus (<code>+</code>).
   */
  PLUS("plus", '+'),

  /**
   * Minus (<code>-</code>).
   */
  MINUS("minus", '-'),

  /**
   * Full stop (<code>.</code>).
   */
  FULL_STOP("full stop", '.'),

  /**
   * Comma (<code>,</code>).
   */
  COMMA("comma", ','),

  /**
   * Left square bracket (<code>[</code>).
   */
  LEFT_SQUARE_BRACKET("left square bracket", '['),

  /**
   * Right square bracket (<code>]</code>).
   */
  RIGHT_SQUARE_BRACKET("right square bracket", ']'),

  /**
   * End of file (EOF).
   */
  EOF("end of file", -1);

  private final String name;
  private final int token;

  private TokenType(final String name, final int token) {
    this.name = name;
    this.token = token;
  }

  /**
   * Get token.
   * 
   * @return the token literal corresponding to this type
   */
  public int getToken() {
    return token;
  }

  @Override
  public String toString() {
    return name;
  }
}
