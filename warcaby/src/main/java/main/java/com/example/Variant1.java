package main.java.com.example;

public class Variant1 implements Variant {
    //angielskie checkers:
    //bicia tylko do przodu, bicia obowiazkowe
    //damka: porusza sie o jedno pole do przodu lub do tylu, moze bic do przodu i do tylu

    //0 - puste pole
    //1 - bialy pionek
    //2 - czarny pionek
    //3 - biala krolowa
    //4 - czarna krolowa
    
    int startRow, startCol, endRow, endCol;

    @Override
    public boolean isValidMove() { //dodac wg zasad
        //jesli jest mozliwe bicie, musi zostac wykonane
        //jesli zostal zbity jeden pionek i jest mozliwe zbicie kolejnego, to gracz musi zrobic jeszcze jeden ruch
        //bicie tylko do przodu
        //jesli pionek jest dama, to porusza sie o jedno pole do przodu, do tylu, moze bic do przodu i do tylu
        if (gameBoard.board[startRow][startCol] != 1 && gameBoard.board[startRow][startCol] != 2) { //jesli na startowej pozycji nie ma pionka
            //System.out.println("1");
            return false;
        }
        if (gameBoard.isWhiteTurn && gameBoard.board[startRow][startCol] != 1) { //jesli jest kolej bialych, a na polu nie ma 1
            //System.out.println("2");
            return false;
        }
        if (!gameBoard.isWhiteTurn && gameBoard.board[startRow][startCol] != 2) { //jesli jest kolej czarnych, a na polu nie ma 2
            //System.out.println("3");
            return false;
        }
        if (endRow < 0 || endRow >= gameBoard.size || endCol < 0 || endCol >= gameBoard.size) { //jesli koncowe pole jest poza zakresem
            //System.out.println("4");
            return false;
        }
        if (gameBoard.board[endRow][endCol] != 0) { //jesli na koncowym polu jest pionek
            //System.out.println("5");
            return false;
        }
        if (Math.abs(startRow - endRow) != Math.abs(startCol - endCol)) { //sprawdz czy ruch jest po przekatnej
            //System.out.println("6");
            return false;
        }
        if (gameBoard.isWhiteTurn) { //sprawdz czy ruch jest do przodu czy do tylu, dla pionkow musza byc do przodu
            if (startRow <= endRow && gameBoard.board[startRow][startCol] == 1) {
                //System.out.println(startRow + " " + endRow);
                //System.out.println("7"); //TO
                return false;
            }
        } else {
            if (startRow >= endRow && gameBoard.board[startRow][startCol] == 2) {
                //System.out.println("8");
                return false;
            }
        }

        if (possibleChecks() && Math.abs(endRow - startRow) == 1) {
            return false;
        }
        return true;
    }

    //dopisac tutaj to, ze jak zrobil jedno bicie i ma kolejne to musi je zrobic tym samym pionkiem w tej samej turze i ze jesli zle wpisal ruch
    //to ma wpisac jeszcze raz
    //zrobic testy do tych funkcji 
    //jakis while od onepiececheck?


    //stawanie sie dama - jest

    //metody do bicia itd
    //jakie sa mozliwe bicia, jesli sa mozliwe, a gracz chce zrobic inny, to ruch nie jest valid
    //dla pionkow sprawdzac tylko o 1 do przodu, dla damek o 1 do przodu i do tylu
    //damki poruszaja sie o jedno pole w te i wewte
    //jesli gracz wykonal jedno bicie i jest mozliwe nastepne TYM SAMYM PIONKIEM, to musi je wykonac - jest?

    //bicie jest mozliwe, jesli przed pionkiem stoi przeciwny pionek, a za nim jest puste pole,
    //wiec dla kazdego pionka trzeba przechowywac info o dwoch polach na przod

    //ruch nie jest valid, jesli sa mozliwe bicia, a ruch nie jest biciem

    public boolean possibleChecks() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (onePieceCheck(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onePieceCheck(int row, int col) { //sprawdza czy jeden pionek moze bic. dla dam tez o jeden, ale i do przodu, i do tylu
        if (gameBoard.isWhiteTurn) { //sprawdz jedno pole na lewo na ukos i na prawo na ukos do przodu i sprawdzaj czy valid
            if (gameBoard.board[row][col] == 3) {
                if ((gameBoard.board[row+1][col-1] == 2 || gameBoard.board[row+1][col-1] == 4) && gameBoard.board[row+2][col-2] == 0) { //w lewo
                    return true;
                }
                if ((gameBoard.board[row+1][col+1] == 2 || gameBoard.board[row+1][col+1] == 4) && gameBoard.board[row+2][col+2] == 0) { //w prawo
                    return true;
                }
            }
            if ((gameBoard.board[row-1][col-1] == 2 || gameBoard.board[row-1][col-1] == 4) && gameBoard.board[row-2][col-2] == 0) { //w lewo
                return true;
            }
            if ((gameBoard.board[row-1][col+1] == 2 || gameBoard.board[row-1][col+1] == 4) && gameBoard.board[row-2][col+2] == 0) { //w prawo
                return true;
            }
            return false;
        } else { //do dolu dla czarnych
            if (gameBoard.board[row][col] == 4) {
                if ((gameBoard.board[row-1][col-1] == 1 || gameBoard.board[row-1][col-1] == 3) && gameBoard.board[row-2][col-2] == 0) { //w lewo
                    return true;
                }
                if ((gameBoard.board[row-1][col+1] == 1 || gameBoard.board[row-1][col+1] == 3) && gameBoard.board[row-2][col+2] == 0) { //w prawo
                    return true;
                }
            }
            if ((gameBoard.board[row+1][col-1] == 1 || gameBoard.board[row+1][col-1] == 3) && gameBoard.board[row+2][col-2] == 0) { //w lewo
                return true;
            }
            if ((gameBoard.board[row+1][col+1] == 1 || gameBoard.board[row+1][col+1] == 3) && gameBoard.board[row+2][col+2] == 0) { //w prawo
                return true;
            }
            return false;
        }
    }

    //jakas metoda robiaca ten check i zerujaca zbity pionek


    //jak niepoprawny ruch, to powtarzaj zapytanie, a nie przelaczaj kolejke
    @Override
    public void makeMove() { //zrob ruch
        //bicie: jesli ruch jest valid i pomiedzy koncowym a startowym jest 1 pole przerwy, to zbij.


        if (isValidMove()) {
            
            gameBoard.board[endRow][endCol] = gameBoard.board[startRow][startCol];
            gameBoard.board[startRow][startCol] = 0;

            if (onePieceCheck(endRow, endCol)) { //ale jesli to bylo bicie! przeniesc to do innej metody
                //kaz mu zrobic znowu ruch, tym pionkiem
            }

            //jesli pionek dotarl na koniec planszy, jest dama
            if (gameBoard.isWhiteTurn && endRow == 0) {
                gameBoard.board[endRow][endCol] = 3;
            }

            if (!gameBoard.isWhiteTurn && endRow == 7) {
                gameBoard.board[endRow][endCol] = 4;
            }

            if (Math.abs(endRow - startRow) == 2) { //to do calkowitej zmiany, bo damy
                int row, col;
                col = startCol + ((endCol - startCol)/2);
                if (gameBoard.isWhiteTurn) {
                    row = endRow + 1;
                } else {
                    row = endRow - 1;
                }
                gameBoard.board[row][col] = 0;
                //System.out.println(row + " " + col);
            }
        } else { 
            System.out.println("Niepoprawny ruch.");
            //i kaz mu zrobic kolejny, poprawny
        }
    }

    @Override
    public boolean isGameOver() {
        int whiteCount = 0;
        int blackCount = 0;

        for (int i = 0; i < gameBoard.size; i++) {
            for (int j = 0; j < gameBoard.size; j++) {
                if (gameBoard.board[i][j] == 1) {
                    whiteCount++;
                }
                if (gameBoard.board[i][j] == 2) {
                    blackCount++;
                }
            }
        }
        if (whiteCount > 0 && blackCount > 0) {
            return false;
        } else {
            return true;
        }
    }
    
}
