
package ChessCore;

public enum BoardRank {
    FIRST(0),
    SECOND(1),
    THIRD(2),
    FORTH(3),
    FIFTH(4),
    SIXTH(5),
    SEVENTH(6),
    EIGHTH(7);

    private final int value;
    private static final BoardRank[] values = values();

    private BoardRank(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public boolean hasNext() {
        return this.value != EIGHTH.getValue();
    }

    public BoardRank getNext() {
        if (this.value == EIGHTH.getValue()) {
            throw new IndexOutOfBoundsException("There is no next.");
        } else {
            return values[this.ordinal() + 1];
        }
    }

    public boolean hasPrevious() {
        return this.value != FIRST.getValue();
    }

    public BoardRank getPrevious() {
        if (this.value == FIRST.getValue()) {
            throw new IndexOutOfBoundsException("There is no previous.");
        } else {
            return values[this.ordinal() - 1];
        }
    }
}
