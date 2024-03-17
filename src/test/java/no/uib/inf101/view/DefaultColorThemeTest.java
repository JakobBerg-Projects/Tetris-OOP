package no.uib.inf101.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import no.uib.inf101.tetris.view.ColorTheme;
import no.uib.inf101.tetris.view.DefaultColorTheme;

public class DefaultColorThemeTest {
  @Test
  public void sanityDefaultColorThemeTest() {
    ColorTheme colors = new DefaultColorTheme();
    assertEquals(Color.BLACK, colors.getBackgroundColor());
    assertEquals(Color.WHITE, colors.getFrameColor());
    assertEquals(Color.DARK_GRAY, colors.getCellColor('-'));
    assertEquals(Color.RED, colors.getCellColor('S'));
    assertEquals(Color.DARK_GRAY, colors.getCellColor('\n'));

  }

}
