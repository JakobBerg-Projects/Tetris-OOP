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
    public Tetromino shiftedBy(int deltaRow, int deltaCol){
        CellPosition newCellPosition = new CellPosition(cellPosition.row()+deltaRow, cellPosition.col()+deltaCol);
        return new Tetromino(ch, shape, newCellPosition);
    }
    public Tetromino shiftedToTopCenterOf(GridDimension gridDimension){
        int deltaRow = -1;
        int deltaCol = (gridDimension.cols() - shape[0].length) / 2;
        CellPosition newCellPosition = new CellPosition(deltaRow, deltaCol);
        return new Tetromino(ch, shape, newCellPosition);
    }

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
}
