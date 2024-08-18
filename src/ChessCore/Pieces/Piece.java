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

public abstract class Piece {
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return this.owner;
    }

    public abstract boolean isValidMove(Move move, ChessGame game);

    public abstract boolean isAttackingSquare(Square pieceSquare, Square squareUnderAttack, ChessBoard board);
}
