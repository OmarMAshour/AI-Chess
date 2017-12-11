package ai.chess;

import java.util.*;
import ai.chess.*;
import java.io.IOException;

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
    public ChessBoard() throws IOException {
        boolean white = false;
        for (int j = 0; j < 8; j++) {
            if (j % 2 == 0) {
                white = false;
            } else {
                white = true;
            }
            for (int i = 0; i < 8; i++) {
                if (!white) {
                    if ((j * 8 + i) % 2 == 0) {
                        this.Squares[j][i] = new boardSquare(BoardSquareColor.Black, false);
                    } else {
                        this.Squares[j][i] = new boardSquare(BoardSquareColor.White, false);
                    }
                } else {
                    if ((j * 8 + i) % 2 == 0) {
                        this.Squares[j][i] = new boardSquare(BoardSquareColor.White, false);
                    } else {
                        this.Squares[j][i] = new boardSquare(BoardSquareColor.Black, false);
                    }

                }
            }
        }
        //initialize pieces
        //initializing black soldiers
        for (int i = 0; i < 8; i++) {
            Pawn p = new Pawn(1,i , PieceColor.Black, 1);
            pieces.add(p);
            this.Squares[1][i].ContainPiece=true;
        }
        // initializing all other black pieces 
        Rook r = new Rook(0, 0, PieceColor.Black, 5);
        this.Squares[0][0].ContainPiece=true;
        Rook r1 = new Rook(7, 0, PieceColor.Black, 5);
        this.Squares[7][0].ContainPiece=true;
//        Knight k = new Knight(1, 0, PieceColor.Black, 3);
//        Knight k1 = new Knight(6, 0, PieceColor.Black, 3);
//        Bishop bi = new Bishop(2, 0, PieceColor.Black, 3);
//        Bishop bi1 = new Bishop(5, 0, PieceColor.Black, 3);
//        Queen Q = new Queen(3, 0, PieceColor.Black, 9);
//        King K = new King(4, 0, PieceColor.Black, 10);
        pieces.add(r);
        pieces.add(r1);
//        pieces.add(k);
//        pieces.add(k1);
//        pieces.add(bi);
//        pieces.add(bi1);
//        pieces.add(Q);
//        pieces.add(K);
        //initializing white soldiers
        for (int i = 0; i < 8; i++) {
            Pawn p = new Pawn(6, i, PieceColor.White, 1);
            pieces.add(p);
            this.Squares[6][i].ContainPiece=true;
        }
        // initializing all other white pieces 
        r = new Rook(0, 7, PieceColor.White, 5);
        this.Squares[0][7].ContainPiece=true;
        r1 = new Rook(7, 7, PieceColor.White, 5);
        this.Squares[7][7].ContainPiece=true;
//        k = new Knight(1, 7, PieceColor.White, 3);
//        k1 = new Knight(6, 7, PieceColor.White, 3);
//        bi = new Bishop(2, 7, PieceColor.White, 3);
//        bi1 = new Bishop(5, 7, PieceColor.White, 3);
//        Q = new Queen(3, 7, PieceColor.White, 9);
//        K = new King(4, 7, PieceColor.White, 10);
        pieces.add(r);
        pieces.add(r1);
//        pieces.add(k);
//        pieces.add(k1);
//        pieces.add(bi);
//        pieces.add(bi1);
//        pieces.add(Q);
//        pieces.add(K);

    }

    //view Board Status
    public void viewBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println(this.Squares[i][j] + " x pos= " + i + " y pos= " + j + " Contain 7aga = " + this.Squares[i][j].ContainPiece );
            }
        }
    }
    //board cpy

    public Piece getPiece(int ydespos, int xdespos) {
        for (int i = 0; i < this.pieces.size(); i++) {
            if(this.pieces.get(i).yPos == ydespos && this.pieces.get(i).xPos == xdespos){
                return this.pieces.get(i);
            }
        }
        return null;
    }
    
    
}

public class BoardController {

}
