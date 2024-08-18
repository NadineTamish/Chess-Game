
package ChessCore;

public enum BoardFile {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private final int value;
    private static final BoardFile[] values = values();

    private BoardFile(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public boolean hasNext() {
        return this.value != H.getValue();
    }

    public BoardFile getNext() {
        if (this.value == H.getValue()) {
            throw new IndexOutOfBoundsException("There is no next.");
        } else {
            return values[this.ordinal() + 1];
        }
    }

    public boolean hasPrevious() {
        return this.value != A.getValue();
    }

    public BoardFile getPrevious() {
        if (this.value == A.getValue()) {
            throw new IndexOutOfBoundsException("There is no previous.");
        } else {
            return values[this.ordinal() - 1];
        }
    }
}
