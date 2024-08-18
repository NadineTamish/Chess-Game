

package ChessCore;

import ChessCore.Pieces.King;
import ChessCore.Pieces.Piece;
import ChessCore.Pieces.PieceFactory;
import static java.nio.file.Files.move;

public final class Utilities {
    private Utilities() {
    }

    public static Player revertPlayer(Player player) {
        return player == Player.WHITE ? Player.BLACK : Player.WHITE;
    }

    public static boolean willOwnKingBeAttacked(Player whoseTurn, Move move, ChessBoard board) {
        ChessBoard clone = new ChessBoard(board);
        Piece pieceFrom = clone.getPieceAtSquare(move.getFromSquare());
        Square intermediateCastleSquare = tryGetIntermediateCastleSquare(pieceFrom, move);
        clone.setPieceAtSquare(move.getFromSquare(), (Piece)null);
        clone.setPieceAtSquare(move.getToSquare(), pieceFrom);
        Square kingSquare = getKingSquare(whoseTurn, clone);
        BoardFile[] files = BoardFile.values();
        BoardRank[] ranks = BoardRank.values();
        BoardFile[] var9 = files;
        int var10 = files.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            BoardFile file = var9[var11];
            BoardRank[] var13 = ranks;
            int var14 = ranks.length;

            for(int var15 = 0; var15 < var14; ++var15) {
                BoardRank rank = var13[var15];
                Square sq = new Square(file, rank);
                Piece p = clone.getPieceAtSquare(sq);
                if (p != null && p.getOwner() != whoseTurn && (p.isAttackingSquare(sq, kingSquare, clone) || intermediateCastleSquare != null && p.isAttackingSquare(sq, intermediateCastleSquare, clone))) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isInCheck(Player player, ChessBoard board) {
        ChessBoard clone = new ChessBoard(board);
        Square kingSquare = getKingSquare(player, clone);
        BoardFile[] files = BoardFile.values();
        BoardRank[] ranks = BoardRank.values();
        BoardFile[] var6 = files;
        int var7 = files.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            BoardFile file = var6[var8];
            BoardRank[] var10 = ranks;
            int var11 = ranks.length;

            for(int var12 = 0; var12 < var11; ++var12) {
                BoardRank rank = var10[var12];
                Square sq = new Square(file, rank);
                Piece p = clone.getPieceAtSquare(sq);
                if (p != null && p.getOwner() != player && p.isAttackingSquare(sq, kingSquare, clone)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Square tryGetIntermediateCastleSquare(Piece pieceFrom, Move move) {
        if (pieceFrom instanceof King && move.getAbsDeltaX() == 2 && move.getDeltaY() == 0) {
            BoardRank rank = move.getFromSquare().getRank();
            BoardFile file;
            if (move.getDeltaX() == 2) {
                file = BoardFile.F;
            } else {
                file = BoardFile.D;
            }

            return new Square(file, rank);
        } else {
            return null;
        }
    }

    private static Square getKingSquare(Player whoseTurn, ChessBoard board) {
        BoardFile[] files = BoardFile.values();
        BoardRank[] ranks = BoardRank.values();
        BoardFile[] var4 = files;
        int var5 = files.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            BoardFile file = var4[var6];
            BoardRank[] var8 = ranks;
            int var9 = ranks.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                BoardRank rank = var8[var10];
                Square sq = new Square(file, rank);
                Piece p = board.getPieceAtSquare(sq);
                // Piece pieceAtFrom = PieceFactory.createPiece(p);
                if (p instanceof King && p.getOwner() == whoseTurn) {
                    return sq;
                }
            }
        }

        throw new RuntimeException("There is no king in the board! Something went very wrong.");
    }
}
