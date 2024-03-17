package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

public interface ViewableTetrisModel {
  /**
  * Gets the dimension of the tetrisboard
  *
  * @return the board as a GridDimension object
  */
  GridDimension getDimension();
  
  /**
  * Goes over all the tiles on board
  *
  * @return Iterable object of all the tiles on the board
  */
  Iterable<GridCell<Character>> getTilesOnBoard();
  
  /**
  * Gets the tiles of the falling tetromino
  *
  * @return An iterable object of the tiles of the falling object
  */
  Iterable<GridCell<Character>> getTilesOfFallingTetromino();
  
  /**
  * Sets the gamestate default value as main menu
  * If the gamestate changes the return value gets changed
  * @return Gamestate object that decides what shall be shown
  */
  GameState getGameState();
  
  /**
  * Constantly running to update the variable score, depending 
  * on if rows have been removed
  * @return Current score
  */
  int getScore();
  
  
}
