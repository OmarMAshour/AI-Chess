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
                } else if ((j * 8 + i) % 2 == 0) {
                    this.Squares[j][i] = new boardSquare(BoardSquareColor.White, false);
                } else {
                    this.Squares[j][i] = new boardSquare(BoardSquareColor.Black, false);
                }
            }
        }
        //initialize pieces
        //initializing black soldiers
        for (int i = 0; i < 8; i++) {
            Pawn p = new Pawn(1, i, PieceColor.Black, 1);
            pieces.add(p);
            this.Squares[1][i].ContainPiece = true;
        }
        // initializing all other black pieces 
        Rook r = new Rook(0, 0, PieceColor.Black, 5);
        this.Squares[0][0].ContainPiece = true;
        Rook r1 = new Rook(0, 7, PieceColor.Black, 5);
        this.Squares[0][7].ContainPiece = true;
        Knight k = new Knight(0, 1, PieceColor.Black, 3);
        this.Squares[0][1].ContainPiece = true;
        Knight k1 = new Knight(0, 6, PieceColor.Black, 3);
        this.Squares[0][6].ContainPiece = true;
        Bishop bi = new Bishop(0, 2, PieceColor.Black, 3);
        this.Squares[0][2].ContainPiece = true;
        Bishop bi1 = new Bishop(0, 5, PieceColor.Black, 3);
        this.Squares[0][5].ContainPiece = true;
        Queen Q = new Queen(0, 3, PieceColor.Black, 9);
        this.Squares[0][3].ContainPiece = true;
        King K = new King(0, 4, PieceColor.Black, 10);
        this.Squares[0][4].ContainPiece = true;

        pieces.add(r);
        pieces.add(r1);
        pieces.add(k);
        pieces.add(k1);
        pieces.add(bi);
        pieces.add(bi1);
        pieces.add(Q);
        pieces.add(K);
        //initializing white soldiers
        for (int i = 0; i < 8; i++) {
            Pawn p = new Pawn(6, i, PieceColor.White, 1);
            pieces.add(p);
            this.Squares[6][i].ContainPiece = true;
        }
        // initializing all other white pieces 
        r = new Rook(7, 0, PieceColor.White, 5);
        this.Squares[7][0].ContainPiece = true;
        r1 = new Rook(7, 7, PieceColor.White, 5);
        this.Squares[7][7].ContainPiece = true;
        k = new Knight(7, 1, PieceColor.White, 3);
        this.Squares[7][1].ContainPiece = true;
        k1 = new Knight(7, 6, PieceColor.White, 3);
        this.Squares[7][6].ContainPiece = true;
        bi = new Bishop(7, 2, PieceColor.White, 3);
        this.Squares[7][2].ContainPiece = true;
        bi1 = new Bishop(7, 5, PieceColor.White, 3);
        this.Squares[7][5].ContainPiece = true;
        Q = new Queen(7, 3, PieceColor.White, 9);
        this.Squares[7][3].ContainPiece = true;
        K = new King(7, 4, PieceColor.White, 10);
        this.Squares[7][4].ContainPiece = true;
        pieces.add(r);
        pieces.add(r1);
        pieces.add(k);
        pieces.add(k1);
        pieces.add(bi);
        pieces.add(bi1);
        pieces.add(Q);

        pieces.add(K);

    }

    //view Board Status
    public void viewBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.Squares[i][j].ContainPiece) {
                    System.out.println("Piece Name: " + this.getPiece(i, j).Name + " " + this.Squares[i][j] + " y pos= " + i + " x pos= " + j + " Contain 7aga = " + this.Squares[i][j].ContainPiece);
                }
            }
        }
    }

    //board cpy
    public Piece getPiece(int ydespos, int xdespos) {
        for (int i = 0; i < this.pieces.size(); i++) {
            if (this.pieces.get(i).yPos == ydespos && this.pieces.get(i).xPos == xdespos) {
                return this.pieces.get(i);
            }
        }
        return null;
    }

    public void copyBoard(ChessBoard desBoard) {
        //copy squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                desBoard.Squares[i][j].ContainPiece = this.Squares[i][j].ContainPiece;
                desBoard.Squares[i][j].color = this.Squares[i][j].color;
            }
        }
        Collections.copy(desBoard.pieces, this.pieces);
    }

    public void clearAllAvailableMoves() {
        for (int i = 0; i < this.pieces.size(); i++) {
            this.pieces.get(i).availableDes.clear();
        }
    }

}

public class BoardController {

}
