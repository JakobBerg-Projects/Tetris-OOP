package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

    @Override
    // sets the color character of the cells to Green, Yellow, Red, Blue, or else default Value Black
    public Color getCellColor(char ch) {
        switch (ch){
            case 'L':
                return Color.GREEN;
            case 'J':
                return Color.YELLOW;
            case 'S':
                return Color.RED;
            case 'Z':
                return Color.BLUE;
            case 'T':
                return Color.PINK;
            case 'I':
                return Color.ORANGE;
            case 'O':
                return Color.CYAN;
            default:
                return Color.DARK_GRAY;
        }
    }
    // Sets the color of the frame to white
    @Override
    public Color getFrameColor() {
        return Color.WHITE;
    }
    // Sets the background color to Black
    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }
    
}
