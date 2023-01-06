package main.java.com.example;

public interface Variant {
    Board gameBoard = new Board();
    public boolean isValidMove();
    public void makeMove();
    public boolean isGameOver();
}
