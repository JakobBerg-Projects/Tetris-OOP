package no.uib.inf101.tetris.model.tetromino;

public class PatternedTetrominoFactory implements TetrominoFactory {
  private String pattern;
  private int currentIndex;

  public PatternedTetrominoFactory(String pattern) {
    this.pattern = pattern;
    this.currentIndex = 0;

  }

  public Tetromino getNext() {
    char nextSymbol = pattern.charAt(currentIndex);
    Tetromino tetromino = Tetromino.newTetromino(nextSymbol);

    // Move to the next symbol in the pattern
    currentIndex = (currentIndex + 1) % pattern.length();

    return tetromino;

  }
}
