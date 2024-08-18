//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ChessCore.Pieces;

import ChessCore.Player;

public class PieceFactory {
    public PieceFactory() {
    }

    public static Piece createPiece(String pieceType, Player owner) {
        switch (pieceType.toLowerCase()) {
            case "pawn":
                return new Pawn(owner);
            case "rook":
                return new Rook(owner);
            case "queen":
                return new Queen(owner);
            case "king":
                return new King(owner);
            case "knight":
                return new Knight(owner);
            case "bishop":
                return new Bishop(owner);
            default:
                throw new IllegalArgumentException("Invalid piece type provided");
        }
    }
}
