

package ChessCore;

public final class Move {
    private final Square fromSquare;
    private final Square toSquare;
    private final PawnPromotion pawnPromotion;

    public Move(Square fromSquare, Square toSquare) {
        this(fromSquare, toSquare, PawnPromotion.None);
    }

    public Move(Square fromSquare, Square toSquare, PawnPromotion pawnPromotion) {
        this.fromSquare = fromSquare;
        this.toSquare = toSquare;
        this.pawnPromotion = pawnPromotion;
    }

    public Square getFromSquare() {
        return this.fromSquare;
    }

    public Square getToSquare() {
        return this.toSquare;
    }

    public PawnPromotion getPawnPromotion() {
        return this.pawnPromotion;
    }

    public boolean isHorizontalMove() {
        return this.getDeltaX() != 0 && this.getDeltaY() == 0;
    }

    public boolean isVerticalMove() {
        return this.getDeltaX() == 0 && this.getDeltaY() != 0;
    }

    public boolean isDiagonalMove() {
        int deltaX = this.getAbsDeltaX();
        int deltaY = this.getAbsDeltaY();
        return deltaX == deltaY && deltaX != 0;
    }

    public int getDeltaX() {
        return this.getToSquare().getFile().getValue() - this.getFromSquare().getFile().getValue();
    }

    public int getDeltaY() {
        return this.getToSquare().getRank().getValue() - this.getFromSquare().getRank().getValue();
    }

    public int getAbsDeltaX() {
        return Math.abs(this.getDeltaX());
    }

    public int getAbsDeltaY() {
        return Math.abs(this.getDeltaY());
    }
}
