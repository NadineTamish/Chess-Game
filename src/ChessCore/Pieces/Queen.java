//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ChessCore.Pieces;

import ChessCore.ChessBoard;
import ChessCore.ChessGame;
import ChessCore.Move;
import ChessCore.Player;
import ChessCore.Square;

public final class Queen extends Piece {
    public Queen(Player owner) {
        super(owner);
    }

    public boolean isValidMove(Move move, ChessGame game) {
        return (move.isDiagonalMove() || move.isHorizontalMove() || move.isVerticalMove()) && !game.isTherePieceInBetween(move);
    }

    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        Move move = new Move(pieceSquare, squareUnderAttack);
        return (move.isDiagonalMove() || move.isHorizontalMove() || move.isVerticalMove()) && !board.isTherePieceInBetween(move);
    }
}
