package ChessCore;

/**
 *
 * @author NADINE
 */
public class GameObserverd implements GameObserver {

    @Override
    public void updateSate(GameStatus status) {
        switch (status) {
            case IN_PROGRESS:
                System.out.println("Game is in progress");
                break;
            case WHITE_WON:
                System.out.println("White player has won!");
                break;
            case BLACK_WON:
                System.out.println("Black player has won!");
                break;
            case STALEMATE:
                System.out.println("The game ended in a stalemate.");
                break;
            case WHITE_UNDER_CHECK:
                System.out.println("White player is under check.");
                break;
            case BLACK_UNDER_CHECK:
                System.out.println("Black player is under check.");
                break;
            case INSUFFICIENT_MATERIAL:
                System.out.println("Insufficient material for checkmate.");
                break;
            default:
                System.out.println("Unknown game status.");
                break;
        }

    }

}
