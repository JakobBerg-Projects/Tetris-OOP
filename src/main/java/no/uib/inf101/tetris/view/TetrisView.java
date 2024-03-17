package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.GameState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * The view component of the Tetris game, responsible for rendering the game state.
 */
public class TetrisView extends JPanel {
  private ViewableTetrisModel viewableTetrisModel;
  private ColorTheme colorTheme;

  private static final Dimension PREFERRED_SIZE = new Dimension(600, 700);
  private static final double MARGIN = 3;
  private static final int SCORE_AREA_WIDTH = 100;
  private static final int FONT_SIZE_LARGE = 110;
  private static final int FONT_SIZE_MEDIUM = 40;
  private static final int FONT_SIZE_SMALL = 25;
  private static final int TRANSPARENCY_OVERLAY = 128;

  /**
   * Constructs a TetrisView with a given model.
   * 
   * @param viewableTetrisMode The model to be visualized by this view.
   */
  public TetrisView(ViewableTetrisModel viewableTetrisMode) {
    this.setFocusable(true);
    this.setPreferredSize(PREFERRED_SIZE);
    this.viewableTetrisModel = viewableTetrisMode;
    this.colorTheme = new DefaultColorTheme();
    this.setBackground(getBackground());
  }

  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGame(g2);
    drawScore(g2);
    if (viewableTetrisModel.getGameState() == GameState.GAME_OVER) {
      drawGameOver(g2);
    }
    if (viewableTetrisModel.getGameState() == GameState.MAIN_MENU) {
      drawMainMenu(g2);
    }
  }
  
  private void drawGame(Graphics2D g2) {
    double x = MARGIN;
    double y = MARGIN;
    double width = getWidth() - 2 * MARGIN - SCORE_AREA_WIDTH;
    double height = getHeight() - 2 * MARGIN;

    Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
    ColorTheme colorTheme = new DefaultColorTheme();
    g2.setColor(colorTheme.getFrameColor());

    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(rect, viewableTetrisModel.getDimension(), MARGIN);

    Iterable<GridCell<Character>> cellsGrid = viewableTetrisModel.getTilesOnBoard();

    drawCells(g2, cellsGrid, converter, colorTheme);

    // Creates an Iterable-object used for calling on the drawcells method for the
    // Tetromino
    Iterable<GridCell<Character>> cellsTetromino = viewableTetrisModel.getTilesOfFallingTetromino();
    drawCells(g2, cellsTetromino, converter, colorTheme);

  }

  private void drawGameOver(Graphics2D g2) {

    Shape overlay = new Rectangle2D.Double(0, 0, getWidth(), getHeight());

    g2.setColor(new Color(0, 0, 0, TRANSPARENCY_OVERLAY));
    g2.fill(overlay);

    g2.setColor(colorTheme.getLetterColor('0'));
    g2.setFont(new Font("DIALOG", Font.BOLD, FONT_SIZE_MEDIUM));
    String gameOverText = "GAME OVER";
    Rectangle2D textBounds = g2.getFontMetrics().getStringBounds(gameOverText, g2);

    
    double x = (this.getWidth() - textBounds.getWidth()) / 2;
    double y = (this.getHeight() + textBounds.getHeight()) / 2;
    g2.drawString(gameOverText, (float) x, (float) y);

    String scoreText = "score:" + viewableTetrisModel.getScore();
    g2.drawString(scoreText, (float) x + 50, (float) y + 50);

    
    String playAgain = "Press ENTER to restart";
    g2.drawString(playAgain, (float) x - 100, (float) y + 100);

    String backToMainMenu = "Press ESC to main menu";
    g2.setFont(new Font("DIALOG", Font.BOLD, FONT_SIZE_SMALL));
    g2.drawString(backToMainMenu, (float)x-30, (float) y + 150);

  }

  
  private void drawScore(Graphics2D g2) {
    int scoreAreaHeight = this.getHeight(); // Full height of the panel
    int scoreAreaX = this.getWidth() - SCORE_AREA_WIDTH; // Positioned on the right side
    int scoreAreaY = 0; // Start from the top

    Shape scoreArea = new Rectangle2D.Double(scoreAreaX, scoreAreaY, SCORE_AREA_WIDTH, getHeight());
    g2.setColor(colorTheme.getFrameColor());
    g2.fill(scoreArea);

    int score = viewableTetrisModel.getScore();
    String scoreText = "Score:";
    g2.setColor(colorTheme.getLetterColor('R'));
    g2.setFont(new Font("DIALOG", Font.BOLD, FONT_SIZE_SMALL));
    FontMetrics metrics = g2.getFontMetrics();
    int textWidth = metrics.stringWidth(scoreText);
    double xText = scoreAreaX + (SCORE_AREA_WIDTH - textWidth) / 2;
    double yText = (scoreAreaHeight / 2) - 250;

    g2.drawString(scoreText, (float) xText, (float) yText);

    String scoreString = "" + score;
    g2.drawString(scoreString, (float) xText + 30, (float) yText + 40);

    int spacing = 60;
    int startY = (int) yText + 175;
    g2.setFont(new Font("DIALOG", Font.BOLD, FONT_SIZE_LARGE-30));
    char[] letters = { 'T', 'e', 't', 'r', 'i', 's' };

    for (int i = 0; i < letters.length; i++) {
      char letter = letters[i];
      g2.setColor(colorTheme.getLetterColor(letter));

      String letterStr = String.valueOf(letter);
      int letterWidth = g2.getFontMetrics().stringWidth(letterStr);
      float letterX = (float) (scoreAreaX + (SCORE_AREA_WIDTH - letterWidth) / 2);
      g2.drawString(letterStr, (float) letterX, startY + (i * spacing));
    }

  }

  public static void drawCells(Graphics2D graphics2d, Iterable<GridCell<Character>> cells,
      CellPositionToPixelConverter converter, ColorTheme colorTheme) {
    // Goes through each cell in collection
    for (GridCell<Character> cell : cells) {
      CellPosition cp = cell.pos();
      Rectangle2D cellBounds = converter.getBoundsForCell(cp);
      Color cellColor = colorTheme.getCellColor(cell.value());
      graphics2d.setColor(cellColor);
      graphics2d.fill(cellBounds);
    }
  }

  private void drawMainMenu(Graphics2D g2) {
    BufferedImage image = Inf101Graphics.loadImageFromResources("/image.png");
    int menuAreaWidth = this.getWidth(); 
    int menuAreaHeight = this.getHeight(); 

    double scaleWidth = (double) menuAreaWidth / image.getWidth();
    double scaleHeight = (double) menuAreaHeight / image.getHeight();
    double scale = Math.min(scaleWidth, scaleHeight);

    int scaledWidth = (int) (image.getWidth() * scale);
    int scaledHeight = (int) (image.getHeight() * scale);
    int menuAreaX = (menuAreaWidth - scaledWidth) / 2;
    int menuAreaY = (menuAreaHeight - scaledHeight);

    Shape shape = new Rectangle2D.Double(0, 0, menuAreaWidth, menuAreaHeight);
    g2.setColor(colorTheme.getBackgroundColor());
    g2.fill(shape);

    Inf101Graphics.drawImage(g2, image, menuAreaX, menuAreaY, scale);

    g2.setColor(colorTheme.getLetterColor('o'));
    Font pressToPlayFont = new Font("DIALOG", Font.BOLD, FONT_SIZE_MEDIUM);
    g2.setFont(pressToPlayFont);
    String pressToPlay = "PRESS SPACE TO PLAY";
    FontMetrics fm = g2.getFontMetrics(pressToPlayFont);
    int stringWidth = fm.stringWidth(pressToPlay);

    double x = ((menuAreaWidth)-stringWidth) / 2;
    double y = ((menuAreaHeight) / 2+fm.getAscent())-100;

    g2.drawString(pressToPlay, (float) x, (float) y);

    double textAreaMiddle = this.getWidth() / 2;
    double textAreaY = y - 150;
    g2.setFont(new Font("DIALOG", Font.BOLD, FONT_SIZE_LARGE));

    g2.setColor(colorTheme.getLetterColor('T'));
    g2.drawString("T", (float) textAreaMiddle - 250, (float) textAreaY);

    g2.setColor(colorTheme.getLetterColor('e'));
    g2.drawString("E", (float) textAreaMiddle - 150, (float) textAreaY);

    g2.setColor(colorTheme.getLetterColor('t'));
    g2.drawString("T", (float) textAreaMiddle - 50, (float) textAreaY);

    g2.setColor(colorTheme.getLetterColor('r'));
    g2.drawString("R", (float) textAreaMiddle + 50, (float) textAreaY);

    g2.setColor(colorTheme.getLetterColor('i'));
    g2.drawString("I", (float) textAreaMiddle + 150, (float) textAreaY);

    g2.setColor(colorTheme.getLetterColor('s'));
    g2.drawString("S", (float) textAreaMiddle + 200, (float) textAreaY);

  }


}
