package no.uib.inf101.tetris.view;
import javax.swing.JPanel;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Iterator;


public class TetrisView extends JPanel{
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
     // Note: NEVER call paintComponent directly yourself
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);

    }
    private void drawGame(Graphics2D g2){
        double margin = 2;
        double x = margin;
        double y = margin;
        double width = this.getWidth() - 2 * margin;
        double height = this.getHeight() - 2 * margin;

        ColorTheme colorTheme = new DefaultColorTheme();
        g2.setColor(colorTheme.getFrameColor());
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        ((Graphics2D) g2).fill(rect);

        CellPositionToPixelConverter cellPositionToPixelConverter = new CellPositionToPixelConverter(rect, viewableTetrisModel.getDimension(), margin);
        Iterable<GridCell<Character>> cells = viewableTetrisModel.getTilesOnBoard();
        drawCells(g2, cells, cellPositionToPixelConverter, colorTheme);

    }
    public static void drawCells(Graphics2D graphics2d, Iterable<GridCell<Character>> iterable, CellPositionToPixelConverter cellPositionToPixelConverter, ColorTheme colorTheme){
        for (GridCell<Character> cell : iterable) {
            CellPosition cp = cell.pos();
            Rectangle2D cellBounds = cellPositionToPixelConverter.getBoundsForCell(cp);
            Color cellColor = colorTheme.getCellColor(cell.value());
            graphics2d.setColor(cellColor);
            graphics2d.fill(cellBounds);
    }
    }}

    
    

