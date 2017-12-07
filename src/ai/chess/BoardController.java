package ai.chess;

import java.util.*;
import ai.chess.*;

enum BoardSquareColor {
    Black, White
};

class boardSquare {

    public BoardSquareColor color;
    public boolean ContainPiece;
    
    public boardSquare(BoardSquareColor color, boolean Contain) {
        this.color = color;
        this.ContainPiece = Contain;
    }

    public String toString() {
        return "color=" + this.color + ", Contain=" + this.ContainPiece + '}';
    }

}

class ChessBoard {
    //attributes
    public boardSquare[][] Squares = new boardSquare[8][8];
    public ArrayList<Piece> pieces = new ArrayList<Piece>(32);
    //constructor
    public ChessBoard() {
        boolean white = false;
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                white = false;
            } else {
                white = true;
            }
            for (int j = 0; j < 8; j++) {
                if (!white) {
                    if ((i * 8 + j) % 2 == 0) {
                        this.Squares[i][j] = new boardSquare(BoardSquareColor.Black, false);
                    } else {
                        this.Squares[i][j] = new boardSquare(BoardSquareColor.White, false);
                    }
                } else {
                    if ((i * 8 + j) % 2 == 0) {
                        this.Squares[i][j] = new boardSquare(BoardSquareColor.White, false);
                    } else {
                        this.Squares[i][j] = new boardSquare(BoardSquareColor.Black, false);
                    }

                }
            }
        }
        //initialize pieces
    }
    //view Board Status
    public void viewBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println(this.Squares[i][j] + " x pos= " + i + " y pos= " + j);
            }
        }
    }
    //board cpy
    
}

public class BoardController {

}
