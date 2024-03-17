package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public final class Tetromino implements Iterable<GridCell<Character>> {
  private char ch;
  private boolean[][] shape;
  private CellPosition cellPosition;
  
  
  private Tetromino(char ch, boolean[][] shape, CellPosition cellPosition){
    this.cellPosition = cellPosition;
    this.shape = shape;
    this.ch = ch;
    
  }
  @Override
  public int hashCode(){
    return Objects.hash(ch, Arrays.deepHashCode(this.shape), cellPosition);
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    
    Tetromino tetromino = (Tetromino) obj;
    return ch == tetromino.ch &&
    Arrays.deepEquals(shape, tetromino.shape) &&
    Objects.equals(cellPosition, tetromino.cellPosition);
  }
  
  static Tetromino newTetromino(char ch){
    CellPosition cellPosition = new CellPosition(0, 0);
    boolean[][] shape;
    // sets the shape of tetromino
    switch(ch) {
      case 'L':
      shape = new boolean[][] {   
        { false, false, false },
        { true, true, true },
        { true, false, false }
      };
      break;
      
      case 'J':
      shape = new boolean[][] {   
        { false, false, false },
        { true, true, true },
        { false, false, true }
      };
      break;
      
      case 'S':
      shape = new boolean[][] {   
        { false, false, false },
        { false, true, true },
        { true, true, false }
      };
      break;
      
      case 'Z':
      shape = new boolean[][] {   
        { false, false, false },
        { true, true, false },
        { false, true, true }
      };
      break;
      
      case 'T':
      shape = new boolean[][] {   
        { false, false, false },
        { true, true, true },
        { false, true, false }
      };
      break;
      
      case 'I':
      shape = new boolean[][] {   
        { false, false, false, false },
        { true, true, true, true },
        { false, false, false, false },
        { false, false, false, false }
      };
      break;
      
      case 'O':
      shape = new boolean[][] {   
        { false, false, false, false },
        { false, true, true, false },
        { false, true, true, false },
        { false, false, false, false }
      };
      break;
      
      default:
      // Handle unknown Tetromino types or return null
      return null;
    }
    
    return new Tetromino(ch, shape, cellPosition);
  }

  /**
 * Returns a new {@link Tetromino} instance that is shifted by the specified delta row and column
 * values from this Tetromino's position. The new Tetromino retains the same shape and character.
 *
 * @param deltaRow the number of rows to shift the Tetromino
 * @param deltaCol the number of columns to shift the Tetromino
 * @return a new {@link Tetromino} instance shifted by the specified deltas
 */
  public Tetromino shiftedBy(int deltaRow, int deltaCol){
    CellPosition newCellPosition = new CellPosition(cellPosition.row()+deltaRow, cellPosition.col()+deltaCol);
    return new Tetromino(ch, shape, newCellPosition);
  }

  /**
 * Returns a new {@link Tetromino} instance that is positioned at the top center of the given
 * grid dimension. The position is adjusted such that the Tetromino is as centered as possible.
 *
 * @param gridDimension the dimension of the grid in which the Tetromino will be placed
 * @return a new {@link Tetromino} instance positioned at the top center of the specified grid dimension
 */
  public Tetromino shiftedToTopCenterOf(GridDimension gridDimension){
    int deltaRow = -1; //The top of the grid
    int deltaCol = (gridDimension.cols()- shape[0].length) / 2; //The center column
    
    CellPosition newCellPosition = new CellPosition(deltaRow, deltaCol);
    return new Tetromino(ch, shape, newCellPosition);
  }
  
  //Feilsøking vha chatgpt
  @Override
  public Iterator<GridCell<Character>> iterator() {
    List<GridCell<Character>> gridCellList = new ArrayList<>();
    for (int row = 0; row < shape.length; row++) {
      for (int col = 0; col < shape[0].length; col++) {
        if(shape[row][col]){
          CellPosition positionOnBoard = new CellPosition(cellPosition.row() + row, cellPosition.col() + col);
          GridCell<Character> gridCell = new GridCell<>(positionOnBoard, ch);
          gridCellList.add(gridCell);
          
        }
        
      }
      
    }
    return gridCellList.iterator();
  }

  /**
 * Creates a new {@link Tetromino} instance that represents this Tetromino rotated 90 degrees clockwise.
 * @return a new {@link Tetromino} instance that is a rotated copy of this Tetromino
 */
  public Tetromino rotatedTetrominoCopy(){
    boolean[][] originalShape = shape;
    int rows = originalShape.length;
    int cols = originalShape[0].length;
    
    boolean[][] rotatedShape = new boolean[cols][rows];
    
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        int rotatedRow = c;
        int rotatedCol = rows - 1 - r;
        rotatedShape[rotatedRow][rotatedCol] = originalShape[r][c];
      }
      
    }
    return new Tetromino(ch, rotatedShape, cellPosition);
  }
  
}

