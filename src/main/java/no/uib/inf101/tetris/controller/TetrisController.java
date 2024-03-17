package no.uib.inf101.tetris.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

import no.uib.inf101.tetris.view.TetrisView;
import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.model.TetrisModel;

public class TetrisController implements java.awt.event.KeyListener {
  private ControllableTetrisModel controllableTetrisModel;
  private TetrisView tetrisView;
  private Timer timer;
  private TetrisSong tetrisSong;

  /**
 * Constructs a new TetrisController.
 * Initializes the game model, view, timer for game logic updates, and starts playing the Tetris theme song.
 * The controller also registers itself as a key listener to the view to handle user input.
 *
 * @param controllableTetrisModel The game model that this controller will manage.
 * @param tetrisView The game view that this controller will update based on game state changes.
 */
  public TetrisController(ControllableTetrisModel controllableTetrisModel, TetrisView tetrisView) {
    this.controllableTetrisModel = controllableTetrisModel;
    this.tetrisView = tetrisView;
    this.timer = new Timer(controllableTetrisModel.getDelay(), this::clockTick);
    this.tetrisSong = new TetrisSong();
    tetrisSong.run();
    tetrisView.addKeyListener(this);
    tetrisView.setFocusable(true);
    timer.start();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      controllableTetrisModel.moveTetromino(0, -1);
      // Left arrow was pressed

    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      controllableTetrisModel.moveTetromino(0, 1);
      // Right arrow was pressed

    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      controllableTetrisModel.moveTetromino(1, 0);
      timer.restart();
      // Down arrow was pressed

    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
      controllableTetrisModel.rotatedTetrominoCopy();
      // Up arrow was pressed

    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      if (controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
        controllableTetrisModel.dropTetromino();
        if (true)
          timer.restart();
      }
      if (controllableTetrisModel.getGameState() == GameState.MAIN_MENU) {
        ((TetrisModel) controllableTetrisModel).startGame();
      }
      // Spacebar was pressed

    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      if (controllableTetrisModel.getGameState() == GameState.GAME_OVER) {
        ((TetrisModel) controllableTetrisModel).mainMenu();
      }
      // Escape was pressed

    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (controllableTetrisModel.getGameState() == GameState.GAME_OVER) {
        ((TetrisModel) controllableTetrisModel).startGame();
      }
      // Enter was pressed
    }
    tetrisView.repaint();

  }

  @Override
  public void keyReleased(KeyEvent e) {
    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
  }

  
  //Executes actions in a regular interval defined by the game's timer.
  private void clockTick(ActionEvent event) {
    if (controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME) {
      controllableTetrisModel.clockTick();
      tetrisView.repaint();
    }
  }

   /**
   * Updates the game's timer delay based on the current speed level.
   * This could be used to increase the game's difficulty over time.
   */
  public void getDelay() {
    int delay = controllableTetrisModel.getDelay();
    timer.setDelay(delay);
    timer.setInitialDelay(delay);
  }

}
