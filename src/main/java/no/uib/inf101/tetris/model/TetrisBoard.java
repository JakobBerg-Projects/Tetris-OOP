package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class TetrisBoard extends Grid<Character> {
  private int rows;
  private int cols;

  public TetrisBoard(int rows, int cols) {
    // Initializes a new Tetris board with the specified number of rows and columns, 
    // filling the grid with '-' to indicate empty cells.
    super(rows, cols, '-');
    this.rows = rows;
    this.cols = cols;

  }

  /**
   * Returns a string representation of the board for easier visualization and debugging.
   * Each row of the board is shown by a single string separated by newline characters.
   * @return String representation of board
   */
  public String prettyString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        stringBuilder.append(get(new CellPosition(row, col)));
      }
      stringBuilder.append("\n");
    }
    return stringBuilder.toString().trim();

  }
 /**
   * Removes all full rows from the board and returns the number of rows removed.
   * @return The number of rows removed from the board.
   */
  public int removeFullRows() {
    int rowsRemoved = 0; //Starts at the bottom of the grid and moves up
    int a = rows - 1; //Creates two pointers
    int b = rows - 1;
    while (a >= 0) {
      while (b >= 0 && isRowFull(b)) { //If the row is full, the pointer moves up one
        rowsRemoved++;
        b--;
      }
      if (b >= 0) {
        copyRow(b, a);
      } else {
        setRowToValue(a, '-'); // Clear the top row after all rows are moved down
      }
      a--;
      b--;
    }
    return rowsRemoved;
  }

  
  //Checks if a specific row is fully occupied (no empty cells).
  private boolean isRowFull(int row) {
    for (int col = 0; col < cols; col++) {
      if (get(new CellPosition(row, col)) == '-') {
        return false;
      }

    }
    return true;
  }
  
  /**
   * Sets a whole row to a given value
   * @param row The row where the value shall be set
   * @param ch the value to be given to that row
   */
  public void setRowToValue(int row, char ch) {
    for (int col = 0; col < cols; col++) {
      set(new CellPosition(row, col), ch);
    }
  }


  
  //Copies the content of one row into another row.
  private void copyRow(int sourceRow, int destinationRow) {
    for (int col = 0; col < cols; col++) {
      set(new CellPosition(destinationRow, col), get(new CellPosition(sourceRow, col)));
    }

  }

}