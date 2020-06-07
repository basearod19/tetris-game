package tetris;

public class TetrisBlockZ implements Shape{

	@Override
	public int[][] makeBlock() {
		// TODO Auto-generated method stub
		final int[][] tetrisBlock = {{7, 7, 0}, {0, 7, 7}};
		return tetrisBlock;
	}

}
