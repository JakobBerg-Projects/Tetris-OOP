package no.uib.inf101.tetris.view;

import java.awt.Color;


public interface ColorTheme {
  /**
  * Gets the color of any value in the grid
  * @param ch the character in the cell
  * @return color 
  */
  public Color getCellColor(char ch);
  
  /**
  * Gets the color of the frame
  * @return Frame color
  */
  
  public Color getFrameColor();
  
  /**
  * Gets the color of the background
  * @return Color of the background
  */
  public Color getBackgroundColor();
  
    /**
  * Gets the Color value of a string letter.
  * 
  * @param ch the letter
  * @return Color of that letter
  */
  public Color getLetterColor(char ch);
}
