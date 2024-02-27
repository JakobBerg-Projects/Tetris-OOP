package no.uib.inf101.tetris.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel{
    TetrisBoard tetrisBoard;
    TetrominoFactory tetrominoFactory;
    Tetromino tetromino;

    public TetrisModel(TetrisBoard tetrisBoard, TetrominoFactory tetrominoFactory){
        this.tetrisBoard = tetrisBoard;
        this.tetrominoFactory = tetrominoFactory;
        GridDimension gridDimension = tetrisBoard;
        this.tetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(gridDimension);
    }

    @Override
    public GridDimension getDimension() {
        int rows = tetrisBoard.rows();
        int cols = tetrisBoard.cols();
        return new Grid<>(rows, cols);
        
    }
    

    
    @Override
    public Iterable<GridCell<Character>> getTilesOfFallingTetromino() {
        List<GridCell<Character>> gridCellList = new ArrayList<>();
        for (GridCell<Character> cell : tetromino) {
            gridCellList.add(cell);
        }
        return gridCellList;

    
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return new Iterable<GridCell<Character>>() {
            @Override
            public Iterator<GridCell<Character>> iterator() {
                return (Iterator<GridCell<Character>>) tetrisBoard.iterator();
            }
        };
    }
}


    

            
            
    
    

    