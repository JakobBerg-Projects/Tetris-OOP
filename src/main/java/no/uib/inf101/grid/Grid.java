package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.IGrid;

public class Grid<E> implements IGrid<E>{

  private int rows;
  private int cols;
  private E[][] grid;

  public Grid(int rows, int cols, E initialValue){
    this.rows = rows;
    this.cols = cols;
    this.grid = (E[][]) new Object[rows][cols];
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col<cols; col++){
            grid[row][col] = initialValue;
      }}
      
    }
    public Grid(int rows, int cols) {
      this(rows, cols, null); 
  }
  
  @Override
  public E get(CellPosition pos){
    int col = pos.col();
    int row = pos.row();
    return (E) grid[row][col];


  }
  @Override
  public void set(CellPosition pos, E value){
    int row = pos.row();
    int col = pos.col();
    grid[row][col] = value;
  }
  @Override
  public boolean positionIsOnGrid(CellPosition pos){
    int row = pos.row();
    int col = pos.col();
    return row >=0 && row < rows && col >= 0 && col < cols;
  }
  @Override
  public int rows(){
    return rows;
  }
  @Override
  public int cols(){
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


