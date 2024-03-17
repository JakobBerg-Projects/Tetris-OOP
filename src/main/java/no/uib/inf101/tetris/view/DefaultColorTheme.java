package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

  @Override
  public Color getCellColor(char ch) {
    switch (ch) {
    case 'L':
      return Color.GREEN;
    case 'J':
      return Color.YELLOW;
    case 'S':
      return Color.RED;
    case 'Z':
      return Color.BLUE;
    case 'T':
      return Color.PINK;
    case 'I':
      return Color.ORANGE;
    case 'O':
      return Color.CYAN;
    default:
      return Color.BLACK;
    }
  }

  
  @Override
  public Color getFrameColor() {
    return Color.LIGHT_GRAY;
  }

  
  @Override
  public Color getBackgroundColor() {
    return Color.BLACK;
  }

  @Override
  public Color getLetterColor(char ch) {
    switch (ch) {
    case 'T':
      return Color.RED;
    case 'e':
      return Color.ORANGE;
    case 't':
      return Color.YELLOW;
    case 'r':
      return Color.GREEN;
    case 'i':
      return Color.BLUE;
    case 's':
      return Color.PINK;
    default:
      return Color.WHITE;

    }
  }
}
