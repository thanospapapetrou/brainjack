package com.github.thanospapapetrou.brainjack.parser.commands;

import com.github.thanospapapetrou.brainjack.parser.tokenizer.Token;

import java.util.Objects;

import javax.script.ScriptContext;

public class SimpleCommand implements Command {
  private static final String NULL_TOKEN = "Token must not be null";

  private final Token token;

  public SimpleCommand(final Token token) {
    Objects.requireNonNull(token, NULL_TOKEN);
    // TODO validate token type; do the same in iterate
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
