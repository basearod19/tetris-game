package tetris;

public class KeyLeft extends KeyLeftRightUpDownREvent{

	@Override
	char pressKey(Block currentpieces, Block nextpieces, DrawingBoard canvas, Board b1, long totalpoint, char rotateDirection) {
		// TODO Auto-generated method stub
		if(execPressKey) {
			currentpieces.moveLeft();

	        canvas.setNextPiece(nextpieces.getCurrentState());  // Sets the next piece
	        canvas.setArrayBoard(b1.getBoardArray());   // Sets the board
	        canvas.setPaintLocation(currentpieces.getColNum(), currentpieces.getRowNum());   //Sets the location to draw the current piece
	        canvas.setArrayPiece(currentpieces.getCurrentState()); // Sets the current piece
	        canvas.repaint(); // PAINT GAME CANVAS AGAIN
		}
		return rotateDirection;
	}

}
