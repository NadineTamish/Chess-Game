//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ChessCore.Pieces;

import ChessCore.BoardFile;
import ChessCore.BoardRank;
import ChessCore.ChessBoard;
import ChessCore.ChessGame;
import ChessCore.Move;
import ChessCore.Player;
import ChessCore.Square;

public final class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    public boolean isValidMove(Move move, ChessGame game) {
        int absDeltaX = move.getAbsDeltaX();
        int absDeltaY = move.getAbsDeltaY();
        if (absDeltaX <= 1 && absDeltaY <= 1) {
            return true;
        } else {
            if (absDeltaY == 0) {
                if (move.getDeltaX() == 2) {
                    if (game.isTherePieceInBetween(move)) {
                        return false;
                    }

                    if (this.getOwner() == Player.WHITE && game.isCanWhiteCastleKingSide()) {
                        if (move.getFromSquare().getRank() == BoardRank.FIRST && move.getFromSquare().getFile() == BoardFile.E) {
                            return true;
                        }

                        throw new RuntimeException("Castle can't be valid from square different than E1");
                    }

                    if (this.getOwner() == Player.BLACK && game.isCanBlackCastleKingSide()) {
                        if (move.getFromSquare().getRank() == BoardRank.EIGHTH && move.getFromSquare().getFile() == BoardFile.E) {
                            return true;
                        }

                        throw new RuntimeException("Castle can't be valid from square different than E8");
                    }
                } else if (move.getDeltaX() == -2) {
                    if (game.isTherePieceInBetween(move)) {
                        return false;
                    }

                    if (this.getOwner() == Player.WHITE && game.isCanWhiteCastleQueenSide()) {
                        if (move.getFromSquare().getRank() == BoardRank.FIRST && move.getFromSquare().getFile() == BoardFile.E) {
                            return true;
                        }

                        throw new RuntimeException("Castle can't be valid from square different than E1");
                    }

                    if (this.getOwner() == Player.BLACK && game.isCanBlackCastleQueenSide()) {
                        if (move.getFromSquare().getRank() == BoardRank.EIGHTH && move.getFromSquare().getFile() == BoardFile.E) {
                            return true;
                        }

                        throw new RuntimeException("Castle can't be valid from square different than E8");
                    }
                }
            }

            return false;
        }
    }

    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        Move move = new Move(pieceSquare, squareUnderAttack);
        int deltaX = move.getAbsDeltaX();
        int deltaY = move.getAbsDeltaY();
        return deltaX <= 1 && deltaY <= 1;
    }
}
