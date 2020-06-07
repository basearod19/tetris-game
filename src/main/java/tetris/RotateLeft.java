package tetris;

public class RotateLeft implements Rotate{

	@Override
	public void rotateStart(int newRowLength, int newColLength, int[][] rotateArray, int[][] currentBlockState) {
		for (int row = 0; row < newRowLength; row++) {
            for (int col = 0; col < newColLength; col++) {
                rotateArray[row][col] = currentBlockState[col][newRowLength - row - 1];
            }
        }
	}
}
