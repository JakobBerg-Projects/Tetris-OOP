package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public interface ControllableTetrisModel extends ViewableTetrisModel{
  
  /**
  * Checks if the position of a Tetromino has changed.
  *
  * @param deltaRow The change in the rows
  * @param deltaCol The change in the cols
  * @return boolean that checks if the position has changed
  */
  public boolean moveTetromino(int deltaRow, int deltaCol);
  
  /**
  * Creates a rotatedCopy of the tetromino, and check if it´s a legal move.
  *
  * @return boolean that tells if the position has changed
  */
  public boolean rotatedTetrominoCopy();
  
  
  /** While the tetromino is allowed to move down to a given position, it does so until it´s no longer allowed. 
  * When its no longer allowed it sets the tetromino on the board and gets a new one. 
  */
  public void dropTetromino();
  
  /** Sets the GameState to MAIN_MENU at first. Then if the GameState is changed, it returns the new gameChange.
  * @return Returns the state of the game
  */
  public GameState getGameState();
  
  /** Gets the delay to of the current delay in the game
  * @return Returns an integer of the delay in milliseconds
  */
  public int getDelay();
  
  /** Sets the clocktick of the game to run and repaints after each tick
  */
  public void clockTick();
  
  
  
}
