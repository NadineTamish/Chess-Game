

package ChessCore;

public class ChessMemento {
    private final ChessBoard board;
    private final GameStatus gameStatus; 
    private final Move move; 

    public ChessMemento(ChessBoard board, GameStatus gameStatus, Move move) {
        this.board = board;
        this.gameStatus = gameStatus; 
        this.move = move ;
    } 

    public Move getMove() {
        return move;
    }
    

    public ChessBoard getBoard() {
        return this.board;
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }
}
 