package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;


public class CellPositionToPixelConverter {
  Rectangle2D box;
  GridDimension gd;
  double margin;
  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }
  public Rectangle2D getBoundsForCell(CellPosition cp){

    double cellWidth = (box.getWidth() - (gd.cols() + 1) * margin)/gd.cols();
    double cellHeight = (box.getHeight() - (gd.rows() + 1) * margin)/gd.rows();

    double cellX = box.getX() + margin + cp.col() * (cellWidth + margin);
    double cellY = box.getY() + margin + cp.row() * (cellHeight + margin);
    return new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight);
      
    
      
  }
}