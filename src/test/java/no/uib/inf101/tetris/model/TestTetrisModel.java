package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TestTetrisModel {
  @Test
  public void initialPositionOfO() {
    TetrisBoard board = new TetrisBoard(20, 10);
    TetrominoFactory factory = new PatternedTetrominoFactory("O");
    ViewableTetrisModel model = new TetrisModel(board, factory);

    List<GridCell<Character>> tetroCells = new ArrayList<>();
    for (GridCell<Character> gc : model.getTilesOfFallingTetromino()) {
      tetroCells.add(gc);
    }

    assertEquals(4, tetroCells.size());
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
  }

  @Test
  public void initialPositionOfI() {
    TetrisBoard board = new TetrisBoard(20, 10);
    TetrominoFactory factory = new PatternedTetrominoFactory("I");
    ViewableTetrisModel model = new TetrisModel(board, factory);

    List<GridCell<Character>> tetroCells = new ArrayList<>();
    for (GridCell<Character> gc : model.getTilesOfFallingTetromino()) {
      tetroCells.add(gc);
    }

    assertEquals(4, tetroCells.size());
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
    assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
  }

  @Test
  public void movingTetrominoReturnsCorrectBoolean() {

    // Creates a new board
    TetrisBoard board = new TetrisBoard(20, 10);
    TetrominoFactory factory = new PatternedTetrominoFactory("I");
    ControllableTetrisModel model = new TetrisModel(board, factory);

    // Attempt to move up by 1
    boolean moveResultUpByOne = model.moveTetromino(-1, 0);

    // Attempt to move down by 1
    boolean moveResultDownByOne = model.moveTetromino(1, 0);

    // Assert the outcome should not be able to move up from start pos, but down is
    // allowed
    assertFalse(moveResultUpByOne, "Expected the Tetromino to not be able to go out of the board");
    assertTrue(moveResultDownByOne, "Expected the Tetromino to move down successfully.");

  }

  @Test
  public void testDroppingOfTetromino() {

    // Creates a new board
    TetrisBoard board = new TetrisBoard(20, 10);
    TetrominoFactory factory = new PatternedTetrominoFactory("I");
    ControllableTetrisModel model = new TetrisModel(board, factory);

    // Drops the tetromino to the last row
    model.dropTetromino();

    // The row should be the last row, while the col should stay the same
    assertEquals(board.get(new CellPosition(19, 3)), 'I');
    assertEquals(board.get(new CellPosition(19, 4)), 'I');
    assertEquals(board.get(new CellPosition(19, 5)), 'I');
    assertEquals(board.get(new CellPosition(19, 6)), 'I');
  }

  @Test
  public void rotatedPositionFourTimes() {

    // Creates a board for a rotated tetromino
    TetrisBoard boardRotated = new TetrisBoard(20, 10);
    TetrominoFactory factoryRotated = new PatternedTetrominoFactory("I");
    ControllableTetrisModel modelRotated = new TetrisModel(boardRotated, factoryRotated);

    // Creates a board for a nonrotated tetromino
    TetrisBoard boardNotRotated = new TetrisBoard(20, 10);
    TetrominoFactory factoryNotRotated = new PatternedTetrominoFactory("I");
    ControllableTetrisModel modelNotRotated = new TetrisModel(boardNotRotated, factoryNotRotated);

    // Moves both tetrominos down by one, so they´re allowed to rotate
    modelNotRotated.moveTetromino(1, 0);
    modelRotated.moveTetromino(1, 0);

    // Rotates one of them 4 times
    for (int i = 0; i < 4; i++) {
      modelRotated.rotatedTetrominoCopy();
    }

    // Retrieve the positions of the Tetromino tiles from both models
    List<GridCell<Character>> rotatedPositions = new ArrayList<>();
    modelRotated.getTilesOfFallingTetromino().forEach(rotatedPositions::add);

    List<GridCell<Character>> notRotatedPositions = new ArrayList<>();
    modelNotRotated.getTilesOfFallingTetromino().forEach(notRotatedPositions::add);

    // Compare the two lists of GridCells for equality
    assertEquals(notRotatedPositions, rotatedPositions,
        "Tetromino should have the same position after four rotations.");

  }

  @Test
  public void oTetrominoDoesntRotate() {

    // Creates a new board for a rotated 'O' tetromino
    TetrisBoard boardRotated = new TetrisBoard(20, 10);
    TetrominoFactory factoryRotated = new PatternedTetrominoFactory("O");
    ControllableTetrisModel modelRotated = new TetrisModel(boardRotated, factoryRotated);

    // Creates a new board for nonrotated 'O' tetromino
    TetrisBoard boardNotRotated = new TetrisBoard(20, 10);
    TetrominoFactory factoryNotRotated = new PatternedTetrominoFactory("O");
    ControllableTetrisModel modelNotRotated = new TetrisModel(boardNotRotated, factoryNotRotated);

    // Moves both tetrominos down by one so they´re allowed to rotate
    modelNotRotated.moveTetromino(1, 0);
    modelRotated.moveTetromino(1, 0);

    // Rotates one of the tetrominos 6 times
    for (int i = 0; i < 6; i++) {
      modelRotated.rotatedTetrominoCopy();
    }

    // Retrieve the positions of the Tetromino tiles from both models
    List<GridCell<Character>> rotatedPositions = new ArrayList<>();
    modelRotated.getTilesOfFallingTetromino().forEach(rotatedPositions::add);

    List<GridCell<Character>> notRotatedPositions = new ArrayList<>();
    modelNotRotated.getTilesOfFallingTetromino().forEach(notRotatedPositions::add);

    // Compare the two lists of GridCells for equality
    assertEquals(notRotatedPositions, rotatedPositions,
        "Tetromino should have the same position after four rotations.");

  }

  @Test
  public void cantRotateTetrominoOutOfBoard() {

    // Creates a new board
    TetrisBoard boardRotated = new TetrisBoard(20, 10);
    TetrominoFactory factoryRotated = new PatternedTetrominoFactory("I");
    ControllableTetrisModel modelRotated = new TetrisModel(boardRotated, factoryRotated);

    // Shouldn´t be allowed to rotate the 'I' tetromino as the top would go out of
    // bounds

    assertFalse(modelRotated.rotatedTetrominoCopy());
  }
}
