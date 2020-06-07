package tetris;

public class KeyRight extends KeyLeftRightUpDownEvent {

	@Override
	void function(Block currentpieces, Block nextpieces, DrawingBoard canvas, Board b1, long totalpoint) {
		// TODO Auto-generated method stub
		if(execPressKey) {
			currentpieces.moveRight();

	        canvas.setNextPiece(nextpieces.getCurrentState());  // Sets the next piece
	        canvas.setArrayBoard(b1.getBoardArray());   // Sets the board
	        canvas.setPaintLocation(currentpieces.getColNum(), currentpieces.getRowNum());   //Sets the location to draw the current piece
	        canvas.setArrayPiece(currentpieces.getCurrentState()); // Sets the current piece
	        canvas.repaint(); // PAINT GAME CANVAS AGAIN
		}
	}

}
