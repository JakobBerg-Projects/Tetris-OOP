package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(char ch) {
        switch (ch){
            case 'g':
                return Color.GREEN;
            case 'y':
                return Color.YELLOW;
            case 'r':
                return Color.RED;
            case 'b':
                return Color.BLUE;
            default:
                return Color.BLACK;
        }
    }

    @Override
    public Color getFrameColor() {
        return Color.GRAY;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }
    
}
