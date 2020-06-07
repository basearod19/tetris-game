package tetris;

public class TetrisBlockT implements Shape{

	@Override
	public int[][] makeBlock() {
		// TODO Auto-generated method stub
		final int[][] tetrisBlock = {{0, 6, 0}, {6, 6, 6}};
		return tetrisBlock;
	}

}
