package tetris;

import static tetris.PlayMusic.playMusic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

class DrawingBoard extends Canvas {

    private static final int PIECE_WIDTH = 16;
    private static final int xOffset = 5;
    private static final int yOffset = 3;

    private static final int BLOCKDRAWSIZEPIECE = 14;
    private static final int FIRST_SCORE_BRACKET = 500;
    private static final int SECOND_SCORE_BRACKET = 1000;
    private static final int THIRD_SCORE_BRACKET = 3000;
    private static final int FOURTH_SCORE_BRACKET = 5000;
    private static final int FIFTH_SCORE_BRACKET = 10000;
    private static final int SIXTH_SCORE_BRACKET = 20000;
    private static final int SEVENTH_SCORE_BRACKET = 30000;
    private static final int GAMEAREAWIDTH = Board.boardWidth * PIECE_WIDTH + xOffset;
    private static final int GAMEAREAHEIGHT = Board.boardHeight * PIECE_WIDTH + yOffset;

    private static final int GAMEAREAXLOC = 1;
    private static final int GAMEAREAYLOC = 1;
    private static final int NPAREAXLOC = Board.boardWidth * PIECE_WIDTH + 10;
    private static final int NPAREAYLOC = 1;
    private static final int NPWIDTH = 128;
    private static final int NPHEIGHT = 100;
    private static final int GAMEINFOWIDTH = 128;
    private static final int GAMEINFOHEIGHT = 299;
    private static final int GAMEINFOXLOC = Board.boardWidth * PIECE_WIDTH + 10;
    private static final int GAMEINFOYLOC = 100 + yOffset;
    private static final int SCOREXLOC = Board.boardWidth * PIECE_WIDTH + 17;
    private static final int SCOREYLOC = 120 + yOffset;
    private static final int ROWSXLOC = Board.boardWidth * PIECE_WIDTH + 17;
    private static final int ROWSYLOC = 160 + yOffset;
    private static final int ROTATIONXLOC = Board.boardWidth * PIECE_WIDTH + 12;
    private static final int ROTATIONYLOC = 200 + yOffset;
    private static final int LEVELXLOC = Board.boardWidth * PIECE_WIDTH + 12;
    private static final int LEVELYLOC = 220 + yOffset;
    private static final int TIMELEFTXLOC = Board.boardWidth * PIECE_WIDTH + 12;
    private static final int TIMELEFTYLOC = 240 + yOffset;

    private int[][] boardArray;
    private int[][] piece, nextPiece;

    private int rowLocation = 0;
    private int columnLocation = 0;

    private boolean drawGameOver = false;
    private boolean drawPaused = false;
    private Map<Integer, Boolean> playMusicTracker;

    private int completedRows = 0;
    private long score = 0;
    private int level = 0;
    private int secondsTillNextLevel = 0;
    private String rotationDirection = "";

    private Image offscreen;

    DrawingBoard() {
        playMusicTracker = new HashMap<>();
        playMusicTracker.put(FIRST_SCORE_BRACKET, false);
        playMusicTracker.put(SECOND_SCORE_BRACKET, false);
        playMusicTracker.put(THIRD_SCORE_BRACKET, false);
        playMusicTracker.put(FOURTH_SCORE_BRACKET, false);
        playMusicTracker.put(FIFTH_SCORE_BRACKET, false);
        playMusicTracker.put(SIXTH_SCORE_BRACKET, false);
        playMusicTracker.put(SEVENTH_SCORE_BRACKET, false);
    }

    void setArrayPiece(int[][] array) {
        piece = array;
    }

    void setNextPiece(int[][] array) {
        nextPiece = array;
    }

    void setGameOver(boolean gameOver) {
        drawGameOver = gameOver;
    }

    void setArrayBoard(int[][] array) {
        boardArray = array;
    }

    void setPaintLocation(int columnLocation, int rowLocation) {
        this.columnLocation = columnLocation;
        this.rowLocation = rowLocation;
    }

    void setRowsComplete(int rows) {
        completedRows = rows;
    }

    void setScore(long score) {
        this.score = score;
    }

    void setPaused(boolean isPaused) {
        drawPaused = isPaused;
    }

    void setLevel(int level) {
        this.level = level;
    }

    void setSeconds(int seconds) {
        secondsTillNextLevel = seconds;
    }

    void setRotationDirection(String direction) {
        rotationDirection = direction;
    }

    public void update(Graphics graphic) {
        this.paint(graphic);
    }

    public void paint(Graphics graphic) {
        if (offscreen == null) {
            offscreen = createImage(getWidth(), getHeight());
        }

        // setup canvas
        Graphics graphics = offscreen.getGraphics();
        graphics.clearRect(0, 0, getWidth(), getHeight());

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(Color.BLACK);

        // where piece drops (game area)
        graphics.drawRect(GAMEAREAXLOC, GAMEAREAYLOC, GAMEAREAWIDTH, GAMEAREAHEIGHT);

        // next piece
        graphics.drawRect(NPAREAXLOC, NPAREAYLOC, NPWIDTH, NPHEIGHT);

        // game info area
        graphics.drawRect(GAMEINFOXLOC, GAMEINFOYLOC, GAMEINFOWIDTH, GAMEINFOHEIGHT);

        // current piece
        int yPositionBlock;
		int xPositionBlock;
		currentBlockPosition(graphics);

        // let's fill it
        minMaxXPositionandYPosition(graphics);
        graphics.setColor(Color.BLACK);

        //next piece setup
        nextPieceSetUpPosition(graphics);

        //info section
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("", Font.BOLD, 12));
        graphics.drawString("Level: " + level, LEVELXLOC, LEVELYLOC);
        graphics.drawString("Rotation: " + rotationDirection, ROTATIONXLOC, ROTATIONYLOC);
        graphics.drawString("Next level in: " + secondsTillNextLevel, TIMELEFTXLOC, TIMELEFTYLOC);
        graphics.drawString("Completed Rows: ", ROWSXLOC, ROWSYLOC);
        graphics.drawString(String.valueOf(completedRows), ROWSXLOC+20, ROWSYLOC+20);

        alertScore();
        scoreBoard(graphics);
        graphics.drawString(String.valueOf(score), SCOREXLOC+20, SCOREYLOC+20);
        gameOver(graphics);

        graphics.setFont(new Font("", Font.PLAIN, 12));
        graphics.setColor(Color.BLACK);
        graphics.drawString("By Calvin Lee", 5, 15);

        graphics.dispose();

        //draw!
        graphic.drawImage(offscreen, 0, 0, this);
    }

	private void minMaxXPositionandYPosition(Graphics graphics) {
		int yPositionBlock;
		int xPositionBlock;
		yPositionBlock = yOffset;
        xPositionBlock = xOffset;
        for (int[] aBoardArray : boardArray) {
            for (int index = 0; index < boardArray[0].length; index++) {
                if (aBoardArray[index] != 0) {
                    graphics.setColor(Block.getColour(aBoardArray[index]));
                    graphics.fillRect(xPositionBlock, yPositionBlock, BLOCKDRAWSIZEPIECE, BLOCKDRAWSIZEPIECE);
                }
                xPositionBlock += PIECE_WIDTH;
                
            }
            xPositionBlock = xOffset;
            yPositionBlock += PIECE_WIDTH;
            
        }
	}

	private void nextPieceSetUpPosition(Graphics graphics) {
		int midr = (NPHEIGHT - (nextPiece.length * PIECE_WIDTH)) / 2;
        int midc = (NPWIDTH - (nextPiece[0].length * PIECE_WIDTH)) / 2;
        int yPositionSetupBlock = 2 + midr, xPositionSetupBlock = Board.boardWidth * PIECE_WIDTH + xOffset + xOffset + midc;

        for (int[] aNextPiece : nextPiece) {
            for (int index = 0; index < nextPiece[0].length; index++) {
                if (aNextPiece[index] != 0) {
                    graphics.setColor(Block.getColour(aNextPiece[index]));
                    graphics.drawRect(xPositionSetupBlock, yPositionSetupBlock, BLOCKDRAWSIZEPIECE, BLOCKDRAWSIZEPIECE);
                }
                xPositionSetupBlock += PIECE_WIDTH;
            }
            xPositionSetupBlock = Board.boardWidth * PIECE_WIDTH + 10 + midc;
            yPositionSetupBlock += PIECE_WIDTH;
        }
	}

	private void currentBlockPosition(Graphics graphics) {
		int yPositionBlock = PIECE_WIDTH * rowLocation + yOffset;
        int xPositionBlock = PIECE_WIDTH * columnLocation + xOffset;
        
        for (int[] aPiece : piece) {
            int widthOfBlock = piece[0].length;
			for (int length = 0; length < widthOfBlock; length++) {
                if (aPiece[length] != 0) {
                    graphics.setColor(Block.getColour(aPiece[length]));
                    graphics.drawRect(xPositionBlock, yPositionBlock, BLOCKDRAWSIZEPIECE, BLOCKDRAWSIZEPIECE);
                }
                xPositionBlock += PIECE_WIDTH;
            }
            xPositionBlock = PIECE_WIDTH * columnLocation + xOffset;
            yPositionBlock += PIECE_WIDTH;     
        }
	}

	private void scoreBoard(Graphics graphics) {
		if (exceedSeventh_Score_Bracket()) {
            graphics.drawString("Bitchin!", SCOREXLOC, SCOREYLOC);
        } else if (exceedSixth_Score_Bracket()) {
            graphics.drawString("Holy Nightmare!", SCOREXLOC, SCOREYLOC);
        } else if (exceedFifth_Score_Bracket()) {
            graphics.drawString("Holy Mashed Potato!", SCOREXLOC, SCOREYLOC);
        } else if (exceedFourth_Score_Bracket()) {
            graphics.drawString("Holy Heart Failure!", SCOREXLOC, SCOREYLOC);
        } else if (exceedThird_Score_Bracket()) {
            graphics.drawString("Holy Fruit Salad!", SCOREXLOC, SCOREYLOC);
        } else if (exceedSecond_Score_Bracket()) {
            graphics.drawString("Holy Caffeine!", SCOREXLOC, SCOREYLOC);
        } else if (exceedFirst_Score_Bracket()) {
            graphics.drawString("Holy Alphabet!", SCOREXLOC, SCOREYLOC);
        } else {
            graphics.drawString("Score: ", SCOREXLOC, SCOREYLOC);
        }
	}

	private boolean exceedFirst_Score_Bracket() {
		return score >= FIRST_SCORE_BRACKET;
	}

	private boolean exceedSecond_Score_Bracket() {
		return score >= SECOND_SCORE_BRACKET;
	}

	private boolean exceedThird_Score_Bracket() {
		return score >= THIRD_SCORE_BRACKET;
	}

	private boolean exceedFourth_Score_Bracket() {
		return score >= FOURTH_SCORE_BRACKET;
	}

	private boolean exceedFifth_Score_Bracket() {
		return score >= FIFTH_SCORE_BRACKET;
	}

	private boolean exceedSixth_Score_Bracket() {
		return score >= SIXTH_SCORE_BRACKET;
	}

	private boolean exceedSeventh_Score_Bracket() {
		return score >= SEVENTH_SCORE_BRACKET;
	}

	private void gameOver(Graphics graphics) {
		if (drawPaused) {
            graphics.setFont(new Font("", Font.BOLD, 16));
            graphics.setColor(Color.BLUE);
            graphics.drawString("PAUSED", Board.boardWidth * PIECE_WIDTH + 23, 300);
            graphics.drawString("Press 'p' ", Board.boardWidth * PIECE_WIDTH + 23, 320);
            graphics.drawString("to continue", Board.boardWidth * PIECE_WIDTH + 23, 340);
            graphics.drawString("Press 'x' ", Board.boardWidth * PIECE_WIDTH + 23, 360);
            graphics.drawString("to restart", Board.boardWidth * PIECE_WIDTH + 23, 380);
        } else if (drawGameOver) {
            new Thread(() -> {
                playMusic("wav/game_over.wav", false);
            }).start();
            playMusicTracker.put(FIRST_SCORE_BRACKET, true);
            graphics.setFont(new Font("", Font.BOLD, 16));
            graphics.setColor(Color.BLUE);
            graphics.drawString("GAME OVER!", Board.boardWidth * PIECE_WIDTH + 23, 300);
            graphics.drawString("Press 'x' ", Board.boardWidth * PIECE_WIDTH + 23, 320);
            graphics.drawString("to restart", Board.boardWidth * PIECE_WIDTH + 23, 340);
        }
	}

	private void alertScore() {
		if (exceedFirst_Score_Bracket() && !playMusicTracker.get(FIRST_SCORE_BRACKET)) {
            new Thread(() -> {
                playMusic("wav/holy_alphabet.wav", false);
            }).start();
            playMusicTracker.put(FIRST_SCORE_BRACKET, true);
        } else if (exceedSecond_Score_Bracket() && !playMusicTracker.get(SECOND_SCORE_BRACKET)) {
            new Thread(() -> {
                playMusic("wav/holy_caffeine.wav", false);
            }).start();
            playMusicTracker.put(SECOND_SCORE_BRACKET, true);
        } else if (exceedThird_Score_Bracket() && !playMusicTracker.get(THIRD_SCORE_BRACKET)) {
            new Thread(() -> {
                playMusic("wav/holy_fruit_salad.wav", false);
            }).start();
            playMusicTracker.put(THIRD_SCORE_BRACKET, true);
        } else if (exceedFourth_Score_Bracket() && !playMusicTracker.get(FOURTH_SCORE_BRACKET)) {
            new Thread(() -> {
                playMusic("wav/holy_heart_failure.wav", false);
            }).start();
            playMusicTracker.put(FOURTH_SCORE_BRACKET, true);
        } else if (exceedFifth_Score_Bracket() && !playMusicTracker.get(FIFTH_SCORE_BRACKET)) {
            new Thread(() -> {
                playMusic("wav/holy_mashed_potatoes.wav", false);
            }).start();
            playMusicTracker.put(FIFTH_SCORE_BRACKET, true);
        } else if (exceedSixth_Score_Bracket() && !playMusicTracker.get(SIXTH_SCORE_BRACKET)) {
            new Thread(() -> {
                playMusic("wav/holy_nightmare.wav", false);
            }).start();
            playMusicTracker.put(SIXTH_SCORE_BRACKET, true);
        } else if (exceedSeventh_Score_Bracket() && !playMusicTracker.get(SEVENTH_SCORE_BRACKET)) {
            new Thread(() -> {
                playMusic("wav/bitchin.wav", false);
            }).start();
            playMusicTracker.put(SEVENTH_SCORE_BRACKET, true);
        }
	}

}