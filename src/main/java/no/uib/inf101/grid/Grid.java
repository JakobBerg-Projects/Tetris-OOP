package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid<E> implements IGrid<E> {

  private int rows;
  private int cols;
  protected E[][] grid;


  /**
  * Constructs a Grid with a specified number of rows and columns, initializing all elements to a given initial value.
  * 
  * @param rows The number of rows in the grid.
  * @param cols The number of columns in the grid.
  * @param initialValue The initial value for each cell in the grid.
  */
  @SuppressWarnings("unchecked")
  public Grid(int rows, int cols, E initialValue) {
    this.rows = rows;
    this.cols = cols;
    this.grid = (E[][]) new Object[rows][cols];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        grid[row][col] = initialValue;
      }
    }
  }

  /**
  * Constructs a Grid with specified rows and columns, with all cells initialized to null.
  * 
  * @param rows The number of rows in the grid.
  * @param cols The number of columns in the grid.
  */
  public Grid(int rows, int cols) {
    this(rows, cols, null);
  }

  @Override
  public E get(CellPosition pos) {
    if (!positionIsOnGrid(pos)) {
      throw new IndexOutOfBoundsException("position is out of grid bounds");
    }
    int col = pos.col();
    int row = pos.row();
    return (E) grid[row][col];
  }

  @Override
  public void set(CellPosition pos, E value) {
    if (!positionIsOnGrid(pos)) {
      throw new IndexOutOfBoundsException("position is out of grid bounds");
    }
    int row = pos.row();
    int col = pos.col();
    grid[row][col] = value;
  }

  @Override
  public boolean positionIsOnGrid(CellPosition pos) {
    int row = pos.row();
    int col = pos.col();
    return row >= 0 && row < rows && col >= 0 && col < cols;
  }

  @Override
  public int rows() {
    return rows;
  }

  @Override
  public int cols() {
    return cols;

  }

  @Override
  public Iterator<GridCell<E>> iterator() {
    ArrayList<GridCell<E>> items = new ArrayList<>();
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        CellPosition pos = new CellPosition(row, col);
        E value = get(pos);
        items.add(new GridCell<E>(pos, value));

      }
    }
    return items.iterator();
  }

}
