package tetris;

public interface Rotate {
	abstract void rotateStart(int col, int row, int[][] rotateArray, int[][] currBlockState);
}
