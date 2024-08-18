package ChessCore;

import ChessCore.Pieces.*;

public final class ClassicBoardInitializer implements BoardInitializer {
    private static final BoardInitializer instance = new ClassicBoardInitializer();

    public ClassicBoardInitializer() {
    }

    public static BoardInitializer getInstance() {
        return instance;
    }

    @Override
    public Piece[][] initialize() {
        Piece[][] initialState = {
            {PieceFactory.createPiece("rook", Player.WHITE), PieceFactory.createPiece("knight", Player.WHITE), PieceFactory.createPiece("bishop", Player.WHITE), PieceFactory.createPiece("queen", Player.WHITE), PieceFactory.createPiece("king", Player.WHITE), PieceFactory.createPiece("bishop", Player.WHITE), PieceFactory.createPiece("knight", Player.WHITE), PieceFactory.createPiece("rook", Player.WHITE)},
            {PieceFactory.createPiece("pawn", Player.WHITE), PieceFactory.createPiece("pawn", Player.WHITE), PieceFactory.createPiece("pawn", Player.WHITE), PieceFactory.createPiece("pawn", Player.WHITE), PieceFactory.createPiece("pawn", Player.WHITE), PieceFactory.createPiece("pawn", Player.WHITE), PieceFactory.createPiece("pawn", Player.WHITE), PieceFactory.createPiece("pawn", Player.WHITE)},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {PieceFactory.createPiece("pawn", Player.BLACK), PieceFactory.createPiece("pawn", Player.BLACK), PieceFactory.createPiece("pawn", Player.BLACK), PieceFactory.createPiece("pawn", Player.BLACK), PieceFactory.createPiece("pawn", Player.BLACK), PieceFactory.createPiece("pawn", Player.BLACK), PieceFactory.createPiece("pawn", Player.BLACK), PieceFactory.createPiece("pawn", Player.BLACK)},
            {PieceFactory.createPiece("rook", Player.BLACK), PieceFactory.createPiece("knight", Player.BLACK), PieceFactory.createPiece("bishop", Player.BLACK), PieceFactory.createPiece("queen", Player.BLACK), PieceFactory.createPiece("king", Player.BLACK), PieceFactory.createPiece("bishop", Player.BLACK), PieceFactory.createPiece("knight", Player.BLACK), PieceFactory.createPiece("rook", Player.BLACK)}
        };
        return initialState;
    }
}
