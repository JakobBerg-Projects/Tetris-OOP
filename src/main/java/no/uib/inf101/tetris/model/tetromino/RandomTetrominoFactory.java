package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

public class RandomTetrominoFactory implements TetrominoFactory {
  
  @Override
  public Tetromino getNext() {
    String s = "LJSZTIO";
    Random random = new Random();
    int index = random.nextInt(s.length());
    char randomS = s.charAt(index);
    Tetromino randomTetromino = Tetromino.newTetromino(randomS);
    return randomTetromino;
  }
  
  
}
