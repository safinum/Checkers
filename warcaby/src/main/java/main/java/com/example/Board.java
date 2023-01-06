package main.java.com.example;

public class Board {
    int size = 8;
    public int[][] board = new int[size][size];
    public Player whitePlayer = new Player();
    public Player blackPlayer = new Player();
    public boolean isWhiteTurn = true;

    public boolean isPlayerMove (Player player) {
        if (isWhiteTurn == true && player == whitePlayer) {
            return true;
        } 
        if (isWhiteTurn == false && player == blackPlayer) {
            return true;
        }
        return false;
    }

    public void makeBoard() { 
        //0 - puste pole
        //1 - bialy pionek
        //2 - czarny pionek
        //3 - biala krolowa
        //4 - czarna krolowa

        for (int i = 0; i < 3; i++) { //ustaw czarne pionki
            for (int j = 0; j < size; j++) {
                if ((i + j) % 2 != 0) {
                    board[i][j] = 1;
                }
            }
        }
        for (int i = 5; i < size; i++) { //ustaw biale pionki
            for (int j = 0; j < size; j++) {
                if ((i + j) % 2 != 0) {
                    board[i][j] = 2;
                }
            }
        }
    }
}