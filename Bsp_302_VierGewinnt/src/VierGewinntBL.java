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

        //vier in Spalte
        for (int i = 1; i < field.length - 3; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == Value.X
                        && field[i + 1][j] == Value.X
                        && field[i + 2][j] == Value.X
                        && field[i + 3][j] == Value.X) {
                    return Value.X;
                } else if (field[i][j] == Value.O
                        && field[i + 1][j] == Value.O
                        && field[i + 2][j] == Value.O
                        && field[i + 3][j] == Value.O) {
                    return Value.O;
                }
            }
        }

        //vier in Reihe
        for (int j = 0; j < field.length - 3; j++) {
            for (int i = 1; i < field.length; i++) {
                if (field[i][j] == Value.O
                        && field[i][j + 1] == Value.O
                        && field[i][j + 2] == Value.O
                        && field[i][j + 3] == Value.O) {
                    return Value.O;
                } else if (field[i][j] == Value.X
                        && field[i][j + 1] == Value.X
                        && field[i][j + 2] == Value.X
                        && field[i][j + 3] == Value.X) {
                    return Value.X;
                }
            }
        }

        //aufsteigend diagonal
        for (int i = 4; i < field.length; i++) {
            for (int j = 0; j < field.length - 3; j++) {
                if (field[i][j] == Value.O
                        && field[i - 1][j + 1] == Value.O
                        && field[i - 2][j + 2] == Value.O
                        && field[i - 3][j + 3] == Value.O) {
                    return Value.O;
                } else if (field[i][j] == Value.X
                        && field[i - 1][j + 1] == Value.X
                        && field[i - 2][j + 2] == Value.X
                        && field[i - 3][j + 3] == Value.X) {
                    return Value.X;
                }
            }
        }

        //absteigend diagonal
        for (int i = 4; i < field.length; i++) {
            for (int j = 3; j < field.length; j++) {
                if (field[i][j] == Value.O
                        && field[i - 1][j - 1] == Value.O
                        && field[i - 2][j - 2] == Value.O
                        && field[i - 3][j - 3] == Value.O) {
                    return Value.O;
                } else if (field[i][j] == Value.X
                        && field[i - 1][j - 1] == Value.X
                        && field[i - 2][j - 2] == Value.X
                        && field[i - 3][j - 3] == Value.X) {
                    return Value.X;
                }
            }
        }

        return Value.EMPTY;
    }

    public Value getValueAt(int col) {
        return field[mappedRows.get(col)][col];
    }

    public int getRow(int col) throws Exception{
        int row = mappedRows.get(col);
        if (row > 0) {
            mappedRows.put(col, mappedRows.get(col) - 1);
        }
        else{
            throw new Exception("Row is full!");
        }
        return row;
    }

}
