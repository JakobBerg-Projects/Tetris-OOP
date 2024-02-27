package no.uib.inf101.tetris.view;
import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.tetromino.Tetromino;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Dimension;



public class TetrisView extends JPanel{
    // Instance variables
    ViewableTetrisModel viewableTetrisModel;
    ColorTheme colorTheme;
    
      // Constructor
    public TetrisView(ViewableTetrisModel viewableTetrisMode) {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(300, 400));
        this.viewableTetrisModel = viewableTetrisMode;
        this.colorTheme = new DefaultColorTheme();
        this.setBackground(getBackground());
    }
  
    // The paintComponent method is called by the Java Swing framework every time
    // either the window opens or resizes, or we call .repaint() on this object. 
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);

    }
    
    private void drawGame(Graphics2D g2){
        // Sets the values of needed variables for rectangle
        double margin = 2;
        double x = margin;
        double y = margin;
        double width = this.getWidth() - 2 * margin;
        double height = this.getHeight() - 2 * margin;

        

        // Creates a rectangle with the created variables
        // Fills the rectangle with color that belongs to the frame 
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        ColorTheme colorTheme = new DefaultColorTheme();
        g2.setColor(colorTheme.getFrameColor());

        
        

        // Creates a CellPositionToPixelConverter Object based on the rectangle
        CellPositionToPixelConverter cellPositionToPixelConverter = new CellPositionToPixelConverter(rect, viewableTetrisModel.getDimension(), margin);

        Iterable<GridCell<Character>> cellsGrid = viewableTetrisModel.getTilesOnBoard();

        
        // Draws the cells of the grid
        drawCells(g2,cellsGrid,  cellPositionToPixelConverter, colorTheme);
        
        

        // Creates an Iterable-object used for calling on the drawcells method for the Tetromino
        Iterable<GridCell<Character>> cellsTetromino = viewableTetrisModel.getTilesOfFallingTetromino();
        drawCells(g2, cellsTetromino, cellPositionToPixelConverter, colorTheme);
        
        

        
        

    }
    public static void drawCells(Graphics2D graphics2d, Iterable<GridCell<Character>> iterable, CellPositionToPixelConverter cellPositionToPixelConverter, ColorTheme colorTheme){
        // Goes through each cell in collection
        for (GridCell<Character> cell : iterable) {
            CellPosition cp = cell.pos();
            Rectangle2D cellBounds = cellPositionToPixelConverter.getBoundsForCell(cp);
            Color cellColor = colorTheme.getCellColor(cell.value());
            graphics2d.setColor(cellColor);
            graphics2d.fill(cellBounds);
    }
    }}

    
    

