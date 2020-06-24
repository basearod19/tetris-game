package tetris;

public class BlockFactory {
	
	public static Shape getShape(String blockType) {
		if(blockType == null) {
			return null;
		}
		if(blockType.equalsIgnoreCase("TETRISBLOCKI")) {
			return new TetrisBlockI();
		} else if(blockType.equalsIgnoreCase("TETRISBLOCKJ")) {
			return new TetrisBlockJ();
		} else if(blockType.equalsIgnoreCase("TETRISBLOCKL")) {
			return new TetrisBlockL();
		} else if(blockType.equalsIgnoreCase("TETRISBLOCKO")) {
			return new TetrisBlockO();
		} else if(blockType.equalsIgnoreCase("TETRISBLOCKS")) {
			return new TetrisBlockS();
		} else if(blockType.equalsIgnoreCase("TETRISBLOCKT")) {
			return new TetrisBlockT();
		} else if(blockType.equalsIgnoreCase("TETRISBLOCKZ")) {
			return new TetrisBlockZ();
		}
		return null;
	}
}
