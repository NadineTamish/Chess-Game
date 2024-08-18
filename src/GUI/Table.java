package GUI;

import ChessCore.BoardFile;
import ChessCore.BoardRank;
import ChessCore.ChessBoard;
import ChessCore.ChessGame;
import ChessCore.GameObserverd;
import ChessCore.Move;
import ChessCore.PawnPromotion;
import ChessCore.Pieces.Bishop;
import ChessCore.Pieces.King;
import ChessCore.Pieces.Knight;
import ChessCore.Pieces.Pawn;
import ChessCore.Pieces.Piece;
import ChessCore.Pieces.PieceFactory;
import ChessCore.Pieces.Queen;
import ChessCore.Pieces.Rook;
import ChessCore.Player;
import ChessCore.PlayerObserved;
import ChessCore.Square;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Table {

    private JFrame table;
    private final BP bp;
    private final List<Spot> spots; // Define spots list in Table class
    private boolean click = true;
    private final ChessGame game;
    private final ChessBoard board;
    boolean moveSuccess;
    private Square selectedSquare;
    private List<Square> possibleMoves = new ArrayList<>();
    private List<Spot> highlightedSpots = new ArrayList<>();  // List to store highlighted spots 
    private static Dimension d = new Dimension(600, 600);
    private static Dimension dim = new Dimension(350, 350);
    private static Dimension spotDim = new Dimension(15, 15);

    private final Color whiteColor = Color.decode("#FFFFFF");
    private final Color blackColor = Color.decode("#000000");

    //   private final PlayerObserved playerObserved;
    //  private Player currentPlayer;
    public Table(ChessGame game) {
        this.table = new JFrame("Chess Game");
        this.table.setLayout(new BorderLayout());
        this.table.setSize(d);
        this.table.setVisible(true);
        this.game = game;
        this.board = game.getBoard();
        this.spots = new ArrayList<>(); // Initialize spots list

        this.bp = new BP(game);
        this.table.add(this.bp, BorderLayout.CENTER);

        game.addObserver(new GameObserverd());

//        playerObserved = new PlayerObserved();
//        currentPlayer = game.getWhoseTurn();
//        game.addPlayerObserver(playerObserved);
    }

//   
    private class BP extends JPanel {
        // List <Spot> spots ; 

        private final ChessGame game;

        BP(ChessGame game) {
            super(new GridLayout(8, 8));
            this.game = game;
            //this.spots = new ArrayList<>();
            for (int i = 0; i < 64; i++) {
                final Spot s = new Spot(this, i);
                spots.add(s);
                add(s);

            }
            setPreferredSize(dim);
            validate();
        }

    }

    private class Spot extends JPanel {

        private final int sp;
        private final ChessGame game;
        private boolean isBoardFlipped = false;

        Spot(final BP bp, final int sp) {
            super(new GridBagLayout());
            this.sp = sp;
            this.game = bp.game;

            setPreferredSize(spotDim);
            assignTileColor();
            assignPieces(board);
            validate();
//            initializeUndoButton();
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Piece clickedPiece = getPieceOnSpot(sp);
                    int clickedSpot = sp;
                    int fileIndex = clickedSpot % 8;
                    int rankIndex = 7 - (clickedSpot / 8); 
                    

                    BoardFile file = BoardFile.values()[fileIndex];
                    BoardRank rank = BoardRank.values()[rankIndex];
                    Square clickedSquare = new Square(file, rank);
                    System.out.println(click + "+");

                    if ((clickedPiece != null && clickedPiece.getOwner() == game.getWhoseTurn())) {
                        System.out.println("Hi!!!");
                        possibleMoves = game.getAllValidMovesFromSquare(clickedSquare);  // Pass the Square for the clicked piece
                        if (click) {

                            click = false;
                            System.out.println(click);

                            System.out.println("First click");
                            selectedSquare = clickedSquare;
                            if (!possibleMoves.isEmpty()) {
                                highlightPossibleMoves(possibleMoves);
                                //click = false;

                                System.out.println("Exit click");

                            }

                        }

                    } else if (selectedSquare.getFile() == clickedSquare.getFile() && selectedSquare.getRank() == clickedSquare.getRank()) {

                        clearHighlightedSpots();
                        click = true;
                        // clearHighlightedSpots(); 
                        System.out.println("hiiiiiii");
                    } else if (!click) {

                        for (int i = 0; i < possibleMoves.size(); i++) {
                            if (possibleMoves.get(i).getFile() == clickedSquare.getFile() && possibleMoves.get(i).getRank() == clickedSquare.getRank()) {
                                System.out.println(click);
                                System.out.println("made seocond click");
                                Move move = new Move(selectedSquare, clickedSquare);
                                moveSuccess = game.makeMove(move); 
                                if (moveSuccess) { 
                                    
                                    game.saveState(); 
                                      initializeUndoButton(clickedSquare, selectedSquare);
//                                    currentPlayer = playerObserved.updateSate(currentPlayer);
                                    //   currentPlayer = game.getWhoseTurn();
                                    System.out.println("made move");
                                    assignPieces(board);
                                    clearHighlightedSpots();
                                    checkForPawnPromotion(clickedSquare);
                                    flipBoard();
                                    revalidate();
                                    repaint();

                                }

                            }
                            click = true;

                        }
                    }
                }

            });
        }

        public int getSp() {
            return sp;
        }

        private void initializeUndoButton(Square from , Square to) {
            System.out.println(board);
            JButton undoButton = new JButton("Undo");
            undoButton.addActionListener(e -> {
                //game.saveState();
                ChessBoard br = game.undoMove(from,to);
                System.out.println(br);
                assignPieces(br);
                revalidate();
                repaint(); 
                  flipBoard();
                System.out.println("Salouma");
                //   redrawBoard();
            });

            table.add(undoButton, BorderLayout.SOUTH);
        }

//        private void redrawBoard() {
// 
//            assignPieces(board); 
//            revalidate();
//            repaint(); 
//            System.out.println("Salouma");
//        } 
//
//        private void showTable() {
//            table.setVisible(true);
//
//        }
        private Piece createPiece(String type, Player owner) {
            return PieceFactory.createPiece(type, owner);
        }

        private Piece getPieceOnSpot(int sp) {
            int squareIndex = sp;
            int fileIndex = squareIndex % 8;
            int rankIndex = 7 - (squareIndex / 8);

            BoardFile file = BoardFile.values()[fileIndex];
            BoardRank rank = BoardRank.values()[rankIndex];
            Square square = new Square(file, rank);

            return board.getPieceAtSquare(square);
        }

        private void clearHighlightedSpots() {
            for (Spot spot : highlightedSpots) {
                spot.setBorder(null); // Remove border to clear highlighting
            }
            highlightedSpots.clear(); // Clear the list of highlighted spots
        }

        private void highlightPossibleMoves(List<Square> possibleMoves) {
            // clearHighlightedSpots(); 

            Square kingSquare = getKingSquare(game.getWhoseTurn());
            for (Square move : possibleMoves) {
                int fileIndex = move.getFile().ordinal();
                int rankIndex = 7 - move.getRank().ordinal(); // Invert due to Swing coordinates  
                Spot spotToHighlight = spots.get(rankIndex * 8 + fileIndex);
                for (int i = 0; i < possibleMoves.size(); i++) {
                    if (possibleMoves.get(i).getFile() == kingSquare.getFile() && possibleMoves.get(i).getRank() == kingSquare.getRank()) {
                        Border border = BorderFactory.createLineBorder(Color.RED, 8);
                        spotToHighlight.setBorder(border); // Change to your desired highlighting color 
                        highlightedSpots.add(spotToHighlight);

                    }
                }

                if (move.getFile() == kingSquare.getFile() && move.getRank() == kingSquare.getRank()) {
                    Border border = BorderFactory.createLineBorder(Color.RED, 8); // Change to your desired highlighting color and thickness
                    spotToHighlight.setBorder(border); // Change to your desired highlighting color 
                    highlightedSpots.add(spotToHighlight);
                } else {
                    Border border = BorderFactory.createLineBorder(Color.YELLOW, 8);
                    spotToHighlight.setBorder(border);
                    highlightedSpots.add(spotToHighlight);

                }
            }
        }

        private Square getKingSquare(Player player) {
            for (BoardFile file : BoardFile.values()) {
                for (BoardRank rank : BoardRank.values()) {
                    Square square = new Square(file, rank);
                    Piece piece = game.getPieceAtSquare(square);
                    if (piece instanceof King && piece.getOwner() == player) {
                        return square;
                    }
                }
            }
            return null;
        }

        private void assignTileColor() {
            int row = this.sp / 8;

            if (BoardRank.values()[row] == BoardRank.FIRST || BoardRank.values()[row] == BoardRank.THIRD
                    || BoardRank.values()[row] == BoardRank.FIFTH || BoardRank.values()[row] == BoardRank.SEVENTH) {
                if (this.sp % 2 == 0) {
                    setBackground(whiteColor);
                } else {
                    setBackground(blackColor);
                }
            } else if (BoardRank.values()[row] == BoardRank.SECOND || BoardRank.values()[row] == BoardRank.FORTH
                    || BoardRank.values()[row] == BoardRank.SIXTH || BoardRank.values()[row] == BoardRank.EIGHTH) {
                if (this.sp % 2 != 0) {
                    setBackground(whiteColor);
                } else {
                    setBackground(blackColor);
                }
            }
        }

//        
        private void assignPieces(ChessBoard board) {
            this.removeAll();

            String basePath = "C:/Users/NADINE/Downloads/ChessJavaLib/ChessJavaLib/Icons/";
            int squareIndex = this.getSp();
            int fileIndex = squareIndex % 8;
            int rankIndex = 7 - (squareIndex / 8);

            BoardFile file = BoardFile.values()[fileIndex];
            BoardRank rank = BoardRank.values()[rankIndex];
            Square square = new Square(file, rank);

            Piece currentPiece = board.getPieceAtSquare(square);

            if (currentPiece != null) {
                try {

                    String pieceIconPath = basePath + getIconForPiece(currentPiece);
                    final BufferedImage image = ImageIO.read(new File(pieceIconPath));
                    Image scaledImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    JLabel label = new JLabel(new ImageIcon(scaledImage));
                    this.add(label);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }

            this.revalidate();
            this.repaint();

            Move lastMove = game.getLastMove();
            if (lastMove != null) {
                Square previousSquare = lastMove.getFromSquare();
                Spot previousSpot = spots.get((7 - previousSquare.getRank().ordinal()) * 8 + previousSquare.getFile().ordinal());
                previousSpot.removeAll();
                previousSpot.revalidate();
                previousSpot.repaint();
            }
        }

        private String getIconForPiece(Piece piece) {
            String playerIdentifier = (piece.getOwner() == Player.WHITE) ? "W_" : "B_";

            String pieceType = piece.getClass().getSimpleName().toLowerCase();
            return playerIdentifier + pieceType + "_icon.png";
        }

        private void flipBoard() {
            isBoardFlipped = !isBoardFlipped;

            Component[] components = bp.getComponents(); // Get all components from the board panel

            bp.removeAll();

            if (isBoardFlipped) {
                // Add components in reverse order for flipped board
                for (int i = components.length - 1; i >= 0; i--) {
                    bp.add(components[i]);
                }
            } else {
                // Add components in normal order
                for (Component component : components) {
                    bp.add(component);
                }
            }

            bp.revalidate();
            bp.repaint();
        }

        private void checkForPawnPromotion(Square square) {
            Piece currentPiece = game.getPieceAtSquare(square);
            if (currentPiece instanceof Pawn) {
                Player currentPlayer = currentPiece.getOwner();
                boolean promotionRankReached = (currentPlayer == Player.WHITE && square.getRank() == BoardRank.EIGHTH)
                        || (currentPlayer == Player.BLACK && square.getRank() == BoardRank.FIRST);

                if (promotionRankReached) {

                    String[] promotionOptions = {"Queen", "Rook", "Knight", "Bishop"};
                    String selectedPiece = (String) JOptionPane.showInputDialog(
                            null,
                            "Select piece for promotion:",
                            "Pawn Promotion",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            promotionOptions,
                            promotionOptions[0]
                    );

                    if (selectedPiece != null) {
                        // Convert selected piece name to PawnPromotion enum
                        PawnPromotion promotion = PawnPromotion.valueOf(selectedPiece.toUpperCase());

                        // Perform pawn promotion
                        Move move = new Move(square, square, promotion);
                        boolean promotionSuccess = game.makeMove(move);
                        if (promotionSuccess) {
                            assignPieces(board);
                            clearHighlightedSpots();
                            revalidate();
                            repaint();
                        }
                    }
                }
            }
        }

    }

}
