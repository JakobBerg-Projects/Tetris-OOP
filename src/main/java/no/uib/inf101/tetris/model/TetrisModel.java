package no.uib.inf101.tetris.model;

import java.util.Iterator;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;


public class TetrisModel implements ControllableTetrisModel{
  private TetrisBoard tetrisBoard;
  private TetrominoFactory tetrominoFactory;
  private Tetromino currentTetromino;
  private GameState gameState;
  private int score = 0;
  private TetrisView view;
  
  /**
 * Constructs a new TetrisModel with a specified Tetris board and Tetromino factory.
 * Initializes the game state to MAIN_MENU, fetches the next Tetromino from the factory,
 * and positions it at the top center of the board.
 *
 * @param tetrisBoard The Tetris board to be used by this model. 
 * @param tetrominoFactory The factory for creating Tetromino pieces. 
 */
  public TetrisModel(TetrisBoard tetrisBoard, TetrominoFactory tetrominoFactory){
    this.tetrisBoard = tetrisBoard;
    this.tetrominoFactory = tetrominoFactory;
    this.currentTetromino = this.tetrominoFactory.getNext().shiftedToTopCenterOf(tetrisBoard);
    this.gameState = GameState.MAIN_MENU;
    
  }
  @Override
  public GridDimension getDimension() {
    int rows = tetrisBoard.rows();
    int cols = tetrisBoard.cols();
    return new Grid<>(rows, cols);
    
  }
  /** 
  * Sets this model to be current view
  * @param view the view that is shown
  */
  public void setView(TetrisView view) {
    this.view = view;
  }
  
  
  
  @Override
  public Iterable<GridCell<Character>> getTilesOfFallingTetromino() {
    // Tetromino implementerer Iterable, så vi kan returnere den direkte
    return currentTetromino;

    
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
  
  // Checks if the move is legal (within bounds and not occupied)
  private boolean isMoveTetrominoLegal(Tetromino newTetromino) {
    for (GridCell<Character> cell : newTetromino) {
      CellPosition pos = cell.pos();
      if (!tetrisBoard.positionIsOnGrid(pos) || !tetrisBoard.get(pos).equals('-')) {
        return false;
      }
    }
    return true;
  }
  
  
  
  @Override
  public boolean moveTetromino(int deltaRow, int deltaCol) {
    Tetromino newTetromino = this.currentTetromino.shiftedBy(deltaRow, deltaCol);
    if (!isMoveTetrominoLegal(newTetromino)){
      return false;
    }
    this.currentTetromino = newTetromino;
    return true;
  }
  
  
  @Override
  public boolean rotatedTetrominoCopy() {
    Tetromino newTetromino = this.currentTetromino.rotatedTetrominoCopy();
    if (!isMoveTetrominoLegal(newTetromino)){
      return false;
    }
    this.currentTetromino = newTetromino;
    return true;
  }
  
  
  
  @Override
  public void dropTetromino() {
    while (isMoveTetrominoLegal(currentTetromino.shiftedBy(1, 0))) {
      currentTetromino = currentTetromino.shiftedBy(1, 0);
    }
    setTetrominoOnBoard();
    
    getNewTetromino();
  }
  
  // Retrieves a new tetromino and places it at the top center of the board.
  private void getNewTetromino(){
    Tetromino newTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(getDimension());
    this.currentTetromino = newTetromino;
    
    
    if(!isMoveTetrominoLegal(newTetromino)){
      gameState = GameState.GAME_OVER;
      
    } else {
      this.currentTetromino = newTetromino;
    }
  }
  
  // Places the current tetromino on the board and updates the score based on any full rows cleared.
  private void setTetrominoOnBoard(){
    for(GridCell<Character> cell : currentTetromino){
      tetrisBoard.set(cell.pos(), cell.value());
    }
    int rowsRemoved = tetrisBoard.removeFullRows();
    updateScore(rowsRemoved);
  }
  
  @Override
  public GameState getGameState() {
    if(gameState == GameState.GAME_OVER){
      return GameState.GAME_OVER;
    }
    if(gameState == GameState.ACTIVE_GAME){
      return GameState.ACTIVE_GAME;
    }
    return GameState.MAIN_MENU;
  }
  
  private void updateScore(int rowsRemoved){
    if(rowsRemoved == 1){
      score += 100;
    }
    if(rowsRemoved == 2){
      score += 300;
    }
    if(rowsRemoved == 3){
      score += 500;
    }
    if(rowsRemoved == 4){
      score += 800;
    }
    if(view!=null){
      view.repaint();
    }
    
  }
  
  @Override
  public void clockTick() {
    Tetromino movedTetromino = currentTetromino.shiftedBy(1, 0);
    if(isMoveTetrominoLegal(movedTetromino)){
      currentTetromino = movedTetromino;
    }else{
      setTetrominoOnBoard();
      getNewTetromino();
    }
  }
  
  @Override
  public int getDelay() {
    return 1000;
  }
  @Override
  public int getScore(){
    return score;
  }
  
  /** Sets the board as empty, and starts a new game */
  public void startGame() {
    score = 0;
    currentTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(getDimension());
    gameState = GameState.ACTIVE_GAME;
    for (int i = 0; i < tetrisBoard.rows(); i++) {
      tetrisBoard.setRowToValue(i, '-');
    }}

  /** Sets GameState as MainMenu */
  public void mainMenu(){
    gameState = GameState.MAIN_MENU;
    }
    
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  