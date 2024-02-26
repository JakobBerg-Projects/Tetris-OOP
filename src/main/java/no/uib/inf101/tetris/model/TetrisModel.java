package no.uib.inf101.tetris.model;

import java.util.Iterator;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel{
    TetrisBoard tetrisBoard;

    public TetrisModel(TetrisBoard tetrisBoard){
        this.tetrisBoard = tetrisBoard;
    }

    @Override
    public GridDimension getDimension() {
        int rows = tetrisBoard.rows();
        int cols = tetrisBoard.cols();
        return new Grid<>(rows, cols);
        
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