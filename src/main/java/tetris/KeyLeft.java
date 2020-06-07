package tetris;

public class KeyLeft extends KeyLeftRightUpDownEvent{

	@Override
	void function(Block currentpieces, Block nextpieces, DrawingBoard board, Board b1, long totalpoint) {
		// TODO Auto-generated method stub
		if(execPressKey) {
			currentpieces.moveLeft();
		}
	}

}
