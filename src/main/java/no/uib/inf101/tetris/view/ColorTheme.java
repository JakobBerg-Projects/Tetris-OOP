package no.uib.inf101.tetris.view;

import java.awt.Color;


public interface ColorTheme {
      /**
  * Sets the value of a position in the grid. A subsequent call to {@link #get}
  * with an equal position as argument will return the value which was set. The
  * method will overwrite any previous value that was stored at the location.
  * 
  * @param ch the character 
  * @return color 
  * @throws IndexOutOfBoundsException if the return is null
  */
    public Color getCellColor(char ch);

      /**
  * Sets the value of a position in the grid. A subsequent call to {@link #get}
  * with an equal position as argument will return the value which was set. The
  * method will overwrite any previous value that was stored at the location.
  * 
  * @param pos the position in which to store the value
  * @param value the new value
  * @throws IndexOutOfBoundsException if the return value is null
  */

    public Color getFrameColor();

      /**
  * Sets the value of a position in the grid. A subsequent call to {@link #get}
  * with an equal position as argument will return the value which was set. The
  * method will overwrite any previous value that was stored at the location.
  * 
  * @param pos the position in which to store the value
  * @param value the new value
  * @throws IndexOutOfBoundsException if the position does not exist in the grid
  */
    public Color getBackgroundColor();
    }

