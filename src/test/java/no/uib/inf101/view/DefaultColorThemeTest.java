package no.uib.inf101.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tetris.view.ColorTheme;
import no.uib.inf101.tetris.view.DefaultColorTheme;

public class DefaultColorThemeTest {
    @Test
public void sanityDefaultColorThemeTest() {
  ColorTheme colors = new DefaultColorTheme();
  assertEquals(Color.BLACK, colors.getBackgroundColor());
  assertEquals(Color.GRAY, colors.getFrameColor());
  assertEquals(Color.BLACK, colors.getCellColor('-'));
  assertEquals(Color.RED, colors.getCellColor('r'));
  assertEquals(Color.BLACK, colors.getCellColor('\n'));
}

}
