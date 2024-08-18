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

public final class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    public boolean isValidMove(Move move, ChessGame game) {
        int deltaX = move.getAbsDeltaX();
        int deltaY = move.getAbsDeltaY();
        return deltaX == 1 && deltaY == 2 || deltaX == 2 && deltaY == 1;
    }

    public boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board) {
        Move move = new Move(pieceSquare, squareUnderAttack);
        int deltaX = move.getAbsDeltaX();
        int deltaY = move.getAbsDeltaY();
        return deltaX == 1 && deltaY == 2 || deltaX == 2 && deltaY == 1;
    }
}
