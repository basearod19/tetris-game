package tetris;

import static tetris.PlayMusic.playMusic;

class Board {

    final static int boardHeight = 25;
    final static int boardWidth = 15;

    private int[][] boardArray = new int[boardHeight][boardWidth];

    Board() {
    }

    //0 means we can place
    //1 means we can't place
    int placePiece(int[][] Piece, int row, int col, boolean shouldPlace) {
        int column = col;
        for (int[] aPiece : Piece) {
            for (int c = 0; c < Piece[0].length; c++) {
                try {
                	boolean canPlace= (boardArray[row][col] == 0 || aPiece[c] == 0);
                    if (!canPlace) {
                        return 1;
                    } else {
                        if (shouldPlace) {
                            boardArray[row][col] |= aPiece[c];
                        }
                        col++;
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return 1;
                }

            }
            row++;
            col = column;
        }
        return 0;
    }
    boolean isGameOver() {
        for (int i = 0; i < boardArray[0].length; i++) {
            if (boardArray[0][i] != 0) {
                return true;
            }
        }
        return false;
    }

    int calculateRowsCleared() {
        int clearedRows = 0;
        int completedLength = 0;

        for (int row = 0; row < boardArray.length; row++) {
            for (int col = 0; col < boardArray[0].length; col++) {
                if (boardArray[row][col] != 0) {
                    completedLength++;
                }
            }

            if (completedLength >= boardArray[0].length) {
                removeLine(row);
                clearedRows++;
            }

            completedLength = 0;
        }

        return clearedRows;
    }

    void removeLine(int y) {
        if (y < 0 || y >= boardHeight) {
            return;
        }

        while (y > 0) {
            // Copy row above to below for all rows
            System.arraycopy(boardArray[y - 1], 0, boardArray[y], 0, boardWidth);
            y--;
        }

        Runnable task = () -> {
            playMusic("wav/blockClear.wav", false);
        };
        new Thread(task).start();
    }

    int[][] getBoardArray() {
        return boardArray;
    }
}
