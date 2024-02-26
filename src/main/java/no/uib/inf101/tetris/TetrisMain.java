package no.uib.inf101.tetris;

import javax.swing.JFrame;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.view.TetrisView;


public class TetrisMain {
  public static final String WINDOW_TITLE = "INF101 Tetris";
  
  public static void main(String[] args) {
    
    TetrisBoard tetrisBoard = new TetrisBoard(20, 10);
    tetrisBoard.set(new CellPosition(0, 0), 'q');
    tetrisBoard.set(new CellPosition(0, tetrisBoard.cols()-1), 'y');
    tetrisBoard.set(new CellPosition(tetrisBoard.rows()-1, 0), 'r');
    tetrisBoard.set(new CellPosition(tetrisBoard.rows()-1, tetrisBoard.cols()-1), 'b');
    TetrisModel tetrisModel = new TetrisModel(tetrisBoard);
    TetrisView view = new TetrisView(tetrisModel);
    

    // The JFrame is the "root" application window.
    // We here set som properties of the main window, 
    // and tell it to display our tetrisView
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Here we set which component to view in our window
    frame.setContentPane(view);
    
    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
  }
  
}
