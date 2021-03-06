package tetris;

import java.awt.Color;

class Block {

    static final int BLOCK_LENGTH = 16;
    
    static BlockFactory blockFactory = new BlockFactory();
    static Shape tetrisBlock1 = blockFactory.getShape("TetrisBlockI");
    static Shape tetrisBlock2 = blockFactory.getShape("TetrisBlockJ");
    static Shape tetrisBlock3 = blockFactory.getShape("TetrisBlockL");
    static Shape tetrisBlock4 = blockFactory.getShape("TetrisBlockO");
    static Shape tetrisBlock5 = blockFactory.getShape("TetrisBlockS");
    static Shape tetrisBlock6 = blockFactory.getShape("TetrisBlockT");
    static Shape tetrisBlock7 = blockFactory.getShape("TetrisBlockZ");
    
    
    private static final int[][] tetrisBlockI = tetrisBlock1.makeBlock();
    private static final int[][] tetrisBlockJ = tetrisBlock2.makeBlock();
    private static final int[][] tetrisBlockL = tetrisBlock3.makeBlock();
    private static final int[][] tetrisBlockO = tetrisBlock4.makeBlock();
    private static final int[][] tetrisBlockS = tetrisBlock5.makeBlock();
    private static final int[][] tetrisBlockT = tetrisBlock6.makeBlock();
    private static final int[][] tetrisBlockZ = tetrisBlock7.makeBlock();

    private int[][] currentBlockState;

    //starting piece positions
    private int blockColLocation = 7;
    private int blockRowLocation = 0;

    Block() {
    }

    Block(int[][] currentBlock) {
        currentBlockState = currentBlock;
    }

    //getter
    static Color getColour(int color) {
        switch (color) {
            case 1:
                return Color.DARK_GRAY;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.RED;
            case 4:
                return Color.GREEN;
            case 5:
                return Color.BLACK;
            case 6:
                return Color.MAGENTA;
            case 7:
                return Color.GRAY;
        }
        return null;
    }

    int[][] getRandom() {
        switch ((int) (0 + Math.random() * 7)) {
            case 0:
                currentBlockState = tetrisBlockI;
                return tetrisBlockI;
            case 1:
                currentBlockState = tetrisBlockJ;
                return tetrisBlockJ;
            case 2:
                currentBlockState = tetrisBlockL;
                return tetrisBlockL;
            case 3:
                currentBlockState = tetrisBlockO;
                return tetrisBlockO;
            case 4:
                currentBlockState = tetrisBlockS;
                return tetrisBlockS;
            case 5:
                currentBlockState = tetrisBlockT;
                return tetrisBlockT;
            case 6:
                currentBlockState = tetrisBlockZ;
                return tetrisBlockZ;
            default:
                return null;
        }
    }

    int[][] getCurrentState() {
        return currentBlockState;
    }

    int getColNum() {
        return blockColLocation;
    }

    int getRowNum() {
        return blockRowLocation;
    }

    //rotator
    int[][] rotateRight(boolean shouldRotate) {
        int newColLength = currentBlockState.length;
        int newRowLength = currentBlockState[0].length;

        int[][] rotate90Array = new int[newRowLength][newColLength];
        for (int row = 0; row < newRowLength; row++) {
            for (int col = 0; col < newColLength; col++) {
                rotate90Array[row][col] = currentBlockState[newColLength - col - 1][row];
            }
        }

        // when the peice went overboard
        checkOverboard(newColLength);

        shouldRotate(shouldRotate, rotate90Array);

        return rotate90Array;
    }

	private void shouldRotate(boolean shouldRotate, int[][] rotate90Array) {
		if (shouldRotate) {
            currentBlockState = rotate90Array;
        }
	}

	private void checkOverboard(int newColLength) {
		if (blockColLocation + newColLength > Board.boardWidth) {
            blockColLocation = Board.boardWidth - newColLength;
        }
	}

    int[][] rotateLeft(boolean shouldRotate) {
        int newColLength = currentBlockState.length;
        int newRowLength = currentBlockState[0].length;

        int[][] rotate270Array = new int[newRowLength][newColLength];
        for (int row = 0; row < newRowLength; row++) {
            for (int col = 0; col < newColLength; col++) {
                rotate270Array[row][col] = currentBlockState[col][newRowLength - row - 1];
            }
        }

        checkOverboard(newColLength);
        shouldRotate(shouldRotate, rotate270Array);

        return rotate270Array;
    }

    //mover
    void moveLeft() {
        if (blockColLocation > 0) {
            blockColLocation--;
        }
    }

    void moveRight() {
    	boolean checkRightLimit = blockColLocation + currentBlockState[0].length < Board.boardWidth;
        if (checkRightLimit) {
            blockColLocation++;
        }
    }

    boolean moveDown() {
      	boolean checkDownLimit = currentBlockState.length + blockRowLocation < Board.boardHeight;
        if (checkDownLimit) {
            blockRowLocation++;
            return false;
        }
        return true;
    }
}
