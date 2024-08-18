

package ChessCore;

import ChessCore.Pieces.Piece;

public final class ChessBoard {
    private final Piece[][] board;

    public ChessBoard(Piece[][] board) {
        this.board = board;
    }

    public ChessBoard(ChessBoard board) {
        this.board = new Piece[8][8];

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                this.board[i][j] = board.board[i][j];
            }
        }

    }

    public Piece getPieceAtSquare(Square square) {
        return this.board[square.getRank().ordinal()][square.getFile().ordinal()];
    }

    public void setPieceAtSquare(Square square, Piece piece) {
        this.board[square.getRank().ordinal()][square.getFile().ordinal()] = piece;
    }

    public boolean isTherePieceInBetween(Move move) {
        Square from = move.getFromSquare();
        Square to = move.getToSquare();
        boolean moveForward;
        if (move.isVerticalMove()) {
            BoardFile file = from.getFile();
            moveForward = move.getDeltaY() > 0;
            BoardRank currentRank = from.getRank();

            while(true) {
                currentRank = moveForward ? currentRank.getNext() : currentRank.getPrevious();
                if (currentRank == to.getRank()) {
                    break;
                }

                if (this.getPieceAtSquare(new Square(file, currentRank)) != null) {
                    return true;
                }
            }
        } else {
            BoardRank currentRank;
            if (move.isHorizontalMove()) {
                currentRank = from.getRank();
                moveForward = move.getDeltaX() > 0;
                BoardFile currentFile = from.getFile();

                while(true) {
                    currentFile = moveForward ? currentFile.getNext() : currentFile.getPrevious();
                    if (currentFile == to.getFile()) {
                        break;
                    }

                    if (this.getPieceAtSquare(new Square(currentFile, currentRank)) != null) {
                        return true;
                    }
                }
            } else if (move.isDiagonalMove()) {
                currentRank = from.getRank();
                BoardFile currentFile = from.getFile();
                boolean moveForwardX = move.getDeltaX() > 0;
                boolean moveForwardY = move.getDeltaY() > 0;

                while(true) {
                    currentRank = moveForwardY ? currentRank.getNext() : currentRank.getPrevious();
                    currentFile = moveForwardX ? currentFile.getNext() : currentFile.getPrevious();
                    if (currentRank == to.getRank() && currentFile == to.getFile()) {
                        break;
                    }

                    if (this.getPieceAtSquare(new Square(currentFile, currentRank)) != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
