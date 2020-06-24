package tetris;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BlockTest {
	static BlockFactory blockFactory = new BlockFactory();
    private static final int[][] tetrisBlockI = {{1}, {1}, {1}, {1}};
    private static final int[][] tetrisBlockJ = {{2, 0, 0}, {2, 2, 2}};
    private static final int[][] tetrisBlockL = {{0, 0, 3}, {3, 3, 3}};
    private static final int[][] tetrisBlockO = {{4, 4}, {4, 4}};
    private static final int[][] tetrisBlockS = {{0, 5, 5}, {5, 5, 0}};
    private static final int[][] tetrisBlockT = {{0, 6, 0}, {6, 6, 6}};
    private static final int[][] tetrisBlockZ = {{7, 7, 0}, {0, 7, 7}};
    static Shape tetrisBlocki = blockFactory.getShape("TetrisBlockI");
    static Shape tetrisBlockj = blockFactory.getShape("TetrisBlockJ");
    static Shape tetrisBlockl = blockFactory.getShape("TetrisBlockL");
    static Shape tetrisBlocko = blockFactory.getShape("TetrisBlockO");
    static Shape tetrisBlocks = blockFactory.getShape("TetrisBlockS");
    static Shape tetrisBlockt = blockFactory.getShape("TetrisBlockT");
    static Shape tetrisBlockz = blockFactory.getShape("TetrisBlockZ");
    private static final int[][] tetrisBlockI1 = tetrisBlocki.makeBlock();
    private static final int[][] tetrisBlockI2 = tetrisBlockj.makeBlock();
    private static final int[][] tetrisBlockI3 = tetrisBlockl.makeBlock();
    private static final int[][] tetrisBlockI4 = tetrisBlocko.makeBlock();
    private static final int[][] tetrisBlockI5 = tetrisBlocks.makeBlock();
    private static final int[][] tetrisBlockI6 = tetrisBlockt.makeBlock();
    private static final int[][] tetrisBlockI7 = tetrisBlockz.makeBlock();
    
    
    private static final List<String> blocks = Lists.newArrayList(
        Arrays.deepToString(tetrisBlockI),
        Arrays.deepToString(tetrisBlockJ),
        Arrays.deepToString(tetrisBlockL),
        Arrays.deepToString(tetrisBlockO),
        Arrays.deepToString(tetrisBlockS),
        Arrays.deepToString(tetrisBlockT),
        Arrays.deepToString(tetrisBlockZ)
    );

    private Block fixture;

    @Before
    public void setUp() {
        fixture = new Block(tetrisBlockI);
    }
    
    @Test
    public void shouldGetSameBlock() {
        assertEquals(Arrays.deepToString(tetrisBlockI), Arrays.deepToString(tetrisBlockI1));
        assertEquals(Arrays.deepToString(tetrisBlockJ), Arrays.deepToString(tetrisBlockI2));
        assertEquals(Arrays.deepToString(tetrisBlockL), Arrays.deepToString(tetrisBlockI3));
        assertEquals(Arrays.deepToString(tetrisBlockO), Arrays.deepToString(tetrisBlockI4));
        assertEquals(Arrays.deepToString(tetrisBlockS), Arrays.deepToString(tetrisBlockI5));
        assertEquals(Arrays.deepToString(tetrisBlockT), Arrays.deepToString(tetrisBlockI6));
        assertEquals(Arrays.deepToString(tetrisBlockZ), Arrays.deepToString(tetrisBlockI7));
    }
    
    @Test
    public void shouldGetColour() {
        assertEquals(Color.DARK_GRAY, Block.getColour(1));
        assertEquals(Color.BLUE, Block.getColour(2));
        assertEquals(Color.RED, Block.getColour(3));
        assertEquals(Color.GREEN, Block.getColour(4));
        assertEquals(Color.BLACK, Block.getColour(5));
        assertEquals(Color.MAGENTA, Block.getColour(6));
        assertEquals(Color.GRAY, Block.getColour(7));
    }

    @Test
    public void shouldGetRandom() {
        assertTrue(blocks.contains(Arrays.deepToString(fixture.getRandom())));
    }

    @Test
    public void shouldGetCurrentState() {
        assertTrue(blocks.contains(Arrays.deepToString(fixture.getCurrentState())));
    }

    @Test
    public void shouldGetStartingColumn() {
        assertEquals(7, fixture.getColNum());
    }

    @Test
    public void shouldGetStartingRow() {
        assertEquals(0, fixture.getRowNum());
    }

    @Test
    public void shouldRotateRight() {
        assertEquals(Arrays.deepToString(tetrisBlockI), Arrays.deepToString(fixture.getCurrentState()));
        int[][] actual = fixture.rotateRight(true);
        String expectedArrayString = "[[1, 1, 1, 1]]";
        assertEquals(expectedArrayString, Arrays.deepToString(actual));
        assertEquals(expectedArrayString, Arrays.deepToString(fixture.getCurrentState()));
    }

    @Test
    public void shouldRotateLeft() {
        fixture = new Block(tetrisBlockL);
        assertEquals(Arrays.deepToString(tetrisBlockL), Arrays.deepToString(fixture.getCurrentState()));
        int[][] actual = fixture.rotateLeft(true);
        String expectedArrayString = "[[3, 3], [0, 3], [0, 3]]";
        assertEquals(expectedArrayString, Arrays.deepToString(actual));
        assertEquals(expectedArrayString, Arrays.deepToString(fixture.getCurrentState()));
    }

    @Test
    public void shouldMoveLeft() {
        fixture.moveLeft();
        assertEquals(6, fixture.getColNum());
    }

    @Test
    public void shouldMoveRight() {
        fixture.moveRight();
        assertEquals(8, fixture.getColNum());
    }

}