package tetris;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class Tetristest {
	private Tetris tetris;
	private static final int[][] tetrisBlockI = {{1}, {1}, {1}, {1}};
    @Before
    public void setUp() {
        tetris = new Tetris();
    }
    @Test
    public void shouldHaveCorrectWidth() {
        assertEquals(Block.BLOCK_LENGTH * Board.boardWidth + 140, tetris.getWidth());
    }
    @Test
    public void shouldHaveCorrectHeight() {
        assertEquals(Board.boardHeight*Block.BLOCK_LENGTH + 28, tetris.getHeight());
    }
    
    @Test
    public void shouldcalculatePoints() {
    	tetris.calculatePoints(0);
    	int expectedvalue = 0;
    	assertEquals(expectedvalue, tetris.totalPoints);
    	tetris.calculatePoints(1);
    	assertEquals(expectedvalue + tetris.ONE_ROW_CLEARED_POINT, tetris.totalPoints);
    	tetris.calculatePoints(2);
    	assertEquals(expectedvalue + tetris.ONE_ROW_CLEARED_POINT + tetris.TWO_ROW_CLEARED_POINT, tetris.totalPoints);
    	tetris.calculatePoints(3);
    	assertEquals(expectedvalue + tetris.ONE_ROW_CLEARED_POINT + tetris.TWO_ROW_CLEARED_POINT+
    			     tetris.THREE_ROW_CLEARED_POINT, tetris.totalPoints);
    	tetris.calculatePoints(4);
    	assertEquals(expectedvalue + tetris.ONE_ROW_CLEARED_POINT + tetris.TWO_ROW_CLEARED_POINT+
			     tetris.THREE_ROW_CLEARED_POINT+ tetris.FOUR_ROW_CLEARED_POINT, tetris.totalPoints);
    }
    
    @Test
    public void shouldKeyPressed() {
    	tetris.restart();
    	KeyEvent e = new KeyEvent(tetris, 0, 0, 0, KeyEvent.VK_DOWN);
		tetris.keyPressed(e);
		assertEquals(1, tetris.totalPoints);
		e = new KeyEvent(tetris, 0, 0, 0, KeyEvent.VK_UP);
		tetris.keyPressed(e);
		assertEquals("[[3, 0], [3, 0], [3, 3]]", Arrays.deepToString(tetris.currentpieces.getCurrentState()));
		e = new KeyEvent(tetris, 0, 0, 0, KeyEvent.VK_RIGHT);
		tetris.keyPressed(e);
		assertEquals(8, tetris.currentpieces.getColNum());
		e = new KeyEvent(tetris, 0, 0, 0, KeyEvent.VK_LEFT);
		tetris.keyPressed(e);
		assertEquals(7, tetris.currentpieces.getColNum());
		e = new KeyEvent(tetris, 0, 0, 0, KeyEvent.VK_SPACE);
		tetris.keyPressed(e);
		assertEquals(41, tetris.totalPoints);
		e = new KeyEvent(tetris, 0, 0, 0, KeyEvent.VK_R);
		tetris.keyPressed(e);
		assertEquals('L', tetris.rotateDirection);
		tetris.keyPressed(e);
		assertEquals('R', tetris.rotateDirection);
		e = new KeyEvent(tetris, 0, 0, 0, KeyEvent.VK_P);
		tetris.keyPressed(e);
		assertTrue(tetris.paused);
		e = new KeyEvent(tetris, 0, 0, 0, KeyEvent.VK_X);
		tetris.keyPressed(e);
		assertEquals(0, tetris.totalPoints);
    }
}
