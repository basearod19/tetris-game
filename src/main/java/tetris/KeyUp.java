package tetris;

public class KeyUp extends KeyLeftRightUpDownEvent{

	@Override
	void function(Block currentpieces, Block nextpieces, DrawingBoard canvas, Board b1, long totalpoint) {
		if (execPressKey) {
	        if (rotateDirection == 'R') {
	            currentpieces.rotateRight(true);        
	        } else {
	            currentpieces.rotateLeft(true);
	        }
		}
		
	}
}
