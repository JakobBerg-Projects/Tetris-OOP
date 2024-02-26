package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class TetrisBoard extends Grid<Character>{
    int rows;
    int cols;

    public TetrisBoard(int rows, int cols){
        super(rows, cols, '-');
        this.rows = rows;
        this.cols = cols;

        
    }
    public String prettyString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                stringBuilder.append(get(new CellPosition(row, col)));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString().trim();
    
    
}
}