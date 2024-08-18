package chessgamefinal;

import ChessCore.ChessGame;
import ChessCore.ClassicBoardInitializer;
import ChessCore.Move;
import GUI.Table;
import javax.swing.SwingUtilities;


public class ChessGameFinal {

   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClassicBoardInitializer boardInitializer = new ClassicBoardInitializer();
            ChessGame game = new ChessGame(boardInitializer) {
                protected boolean isValidMoveCore(Move move) {
                    return true;
                }
            };
            new Table(game);
        });
    }
    
}
