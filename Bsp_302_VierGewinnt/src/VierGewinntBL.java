//die Erkennung des Gewinners ist unverlässlich, die
//Implementation der Gewinnermittlung in der Diagonale
//ist funktionsunfähig

import java.util.HashMap;

public class VierGewinntBL {

    private Value[][] field = new Value[7][7];
    private Value currentPlayer;
    private HashMap<Integer, Integer> mappedRows = new HashMap();

    public VierGewinntBL() {
        reset();
    }

    public void reset() {
        for (int col = 0; col < field.length; col++) {
            for (int row = 1; row < field.length; row++) {
                field[row][col] = Value.EMPTY;
            }
        }
        mappedRows.clear();
        currentPlayer = Value.X;
    }

    public Value makeMove(int col) throws Exception {
        if (mappedRows.containsKey(col)) {
            if (field[mappedRows.get(col)][col] == Value.EMPTY) {
                field[mappedRows.get(col)][col] = currentPlayer;
            }
        } else {
            mappedRows.put(col, field.length - 1);
            field[mappedRows.get(col)][col] = currentPlayer;
        }
        currentPlayer = (currentPlayer == Value.X) ? Value.O : Value.X;
        return determineWin();
    }

    private Value determineWin() {
        //vier in einer Zeile
        for (int i = field.length - 1; i > 0; i--) {
            int rowsum = 0;
            for (int j = field[i].length - 1; j >= 0; j--) {
                rowsum += field[i][j].getNum();
            }
            if (rowsum == 4) {
                return Value.X;
            }
            if (rowsum == -4) {
                return Value.O;
            }
        }

        //vier in einer Spalte
        for (int j = field.length - 1; j >= 0; j--) {
            int colsum = 0;
            for (int i = field[j].length - 1; i > 0; i--) {
                colsum += field[i][j].getNum();
            }
            if (colsum == 4) {
                return Value.X;
            }
            if (colsum == -4) {
                return Value.O;
            }
        }

        //vier in einer Diagonale
        /*int diagsum = 0;
        int diagsum2 = 0;
        for (int i = 1; i < field.length; i++) {
            diagsum += field[i][i].getNum();
            diagsum2 += field[3 - i][i].getNum();
        }
        if (diagsum == 4 || diagsum2 == 4) {
            return Value.X;
        }
        if (diagsum == -4 || diagsum2 == -4) {
            return Value.O;
        }*/

        return Value.EMPTY;
    }

    public Value getValueAt(int col) {
        return field[mappedRows.get(col)][col];
    }

    public int getRow(int col) {
        int row = mappedRows.get(col);
        mappedRows.put(col, mappedRows.get(col) - 1);
        return row;
    }

}
