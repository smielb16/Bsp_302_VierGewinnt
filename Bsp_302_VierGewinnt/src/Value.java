
public enum Value {
    X(1), O(-1), EMPTY(0);

    private final int num;

    Value(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
