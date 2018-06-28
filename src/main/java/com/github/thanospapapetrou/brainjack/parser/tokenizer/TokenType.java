package com.github.thanospapapetrou.brainjack.parser.tokenizer;

/**
 * Enumeration representing Brainfuck token types.
 * 
 * @author thanos
 */
public enum TokenType {
  /**
   * Move right (<code>&gt;</code>).
   */
  MOVE_RIGHT("greater than", '>'),

  /**
   * Move left (<code>&lt;</code>).
   */
  MOVE_LEFT("less than", '<'),

  /**
   * Increase (<code>+</code>).
   */
  INCREASE("plus", '+'),

  /**
   * Decrease (<code>-</code>).
   */
  DECREASE("minus", '-'),

  /**
   * Write (<code>.</code>).
   */
  WRITE("full stop", '.'),

  /**
   * Read (<code>,</code>).
   */
  READ("comma", ','),

  /**
   * Iteration start (<code>[</code>).
   */
  ITERATION_START("left square bracket", '['),

  /**
   * Iteration end (<code>]</code>).
   */
  ITERATION_END("right square bracket", ']'),

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
