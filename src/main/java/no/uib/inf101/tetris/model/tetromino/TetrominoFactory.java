package no.uib.inf101.tetris.model.tetromino;

public interface TetrominoFactory {
  /**
  * Creates a new random tetromino
  *
  * @return a new {@link tetromino} object
  */
  public Tetromino getNext();
}
