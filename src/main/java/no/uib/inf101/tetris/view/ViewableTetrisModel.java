package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public interface ViewableTetrisModel {
    // @return gridDimension
    GridDimension getDimension();


    // @returns object that iterates over all tiles
    Iterable<GridCell<Character>> getTilesOnBoard();

    
}
