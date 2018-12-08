
import java.util.HashMap;

public class VierGewinntBL {

    private Value[][] field = new Value[7][7];
    private Value currentPlayer;
    private final int BOTTOM_ROW = 1;
    private HashMap<Integer, Integer> mappedRows = new HashMap();

    private void reset() {
        for (int col = 0; col < field.length; col++) {
            for (int row = 0; row < field.length; row++) {
                field[row][col] = Value.EMPTY;
            }
        }
        currentPlayer = Value.X;
    }

    public Value makeMove(int col) throws Exception {

        for (int row = field.length - 1; row > 0; row--) {
            if (field[row][col] == Value.EMPTY) {
                throw new Exception("Invalid move!");
            }
        }
        field[mappedRows.get(col)][col] = currentPlayer;
        if (mappedRows.containsValue(col)) {
            mappedRows.put(col, mappedRows.get(col) + 1);
        } else {
            mappedRows.put(col, 1);
        }
        currentPlayer = (currentPlayer == Value.X) ? Value.O : Value.X;
        return determineWin();

    }

    private Value determineWin() {
        return Value.EMPTY;
    }

    public Value getValueAt(int col) {
        return field[mappedRows.get(col)][col];
    }
    
}
