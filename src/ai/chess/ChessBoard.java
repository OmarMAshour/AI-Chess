package ai.chess;

import Pieces.*;
import java.io.IOException;
import java.util.ArrayList;

public class ChessBoard {

    //attributes
    public BoardSquare[][] Squares = new BoardSquare[8][8];
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
                        this.Squares[j][i] = new BoardSquare(BoardSquareColor.Black, false);
                    } else {
                        this.Squares[j][i] = new BoardSquare(BoardSquareColor.White, false);
                    }
                } else if ((j * 8 + i) % 2 == 0) {
                    this.Squares[j][i] = new BoardSquare(BoardSquareColor.White, false);
                } else {
                    this.Squares[j][i] = new BoardSquare(BoardSquareColor.Black, false);
                }
            }
        }
        //initializing black soldiers
        for (int i = 0; i < 8; i++) {
            Pawn p = new Pawn(1, i, PieceColor.Black, 1);
            pieces.add(p);
            this.Squares[1][i].ContainPiece = true;
        }
        // initializing black pieces
        Rook r1 = new Rook(0, 0, PieceColor.Black, 5);
        this.Squares[0][0].ContainPiece = true;
        pieces.add(r1);
        Rook r2 = new Rook(0, 7, PieceColor.Black, 5);
        this.Squares[0][7].ContainPiece = true;
        pieces.add(r2);
        Knight k1 = new Knight(0, 1, PieceColor.Black, 3);
        this.Squares[0][1].ContainPiece = true;
        pieces.add(k1);
        Knight k2 = new Knight(0, 6, PieceColor.Black, 3);
        this.Squares[0][6].ContainPiece = true;
        pieces.add(k2);
        Bishop b1 = new Bishop(0, 2, PieceColor.Black, 3);
        this.Squares[0][2].ContainPiece = true;
        pieces.add(b1);
        Bishop b2 = new Bishop(0, 5, PieceColor.Black, 3);
        this.Squares[0][5].ContainPiece = true;
        pieces.add(b2);
        Queen Q = new Queen(0, 3, PieceColor.Black, 9);
        this.Squares[0][3].ContainPiece = true;
        pieces.add(Q);
        King K = new King(0, 4, PieceColor.Black, 10);
        this.Squares[0][4].ContainPiece = true;
        pieces.add(K);
        //initializing white soldiers
        for (int i = 0; i < 8; i++) {
            Pawn p = new Pawn(6, i, PieceColor.White, 1);
            pieces.add(p);
            this.Squares[6][i].ContainPiece = true;
        }
        // initializing all other white pieces 
        r1 = new Rook(7, 0, PieceColor.White, 5);
        this.Squares[7][0].ContainPiece = true;
        pieces.add(r1);
        r2 = new Rook(7, 7, PieceColor.White, 5);
        this.Squares[7][7].ContainPiece = true;
        pieces.add(r2);
        k1 = new Knight(7, 1, PieceColor.White, 3);
        this.Squares[7][1].ContainPiece = true;
        pieces.add(k1);
        k2 = new Knight(7, 6, PieceColor.White, 3);
        this.Squares[7][6].ContainPiece = true;
        pieces.add(k2);
        b1 = new Bishop(7, 2, PieceColor.White, 3);
        this.Squares[7][2].ContainPiece = true;
        pieces.add(b1);
        b2 = new Bishop(7, 5, PieceColor.White, 3);
        this.Squares[7][5].ContainPiece = true;
        pieces.add(b2);
        Q = new Queen(7, 3, PieceColor.White, 9);
        this.Squares[7][3].ContainPiece = true;
        pieces.add(Q);
        K = new King(7, 4, PieceColor.White, 10);
        this.Squares[7][4].ContainPiece = true;
        pieces.add(K);
    }

    public ChessBoard(BoardSquare[][] squares, ArrayList<Piece> pieces) {
        this.Squares = squares;
        this.pieces = pieces;
    }

    //get reference to the desired piece
    public Piece getPiece(int ydespos, int xdespos) {
        int sizei = this.pieces.size();
        for (int i = 0; i < sizei; i++) {
            if (this.pieces.get(i).yPos == ydespos && this.pieces.get(i).xPos == xdespos) {
                return this.pieces.get(i);
            }
        }
        return null;
    }

    //board cpy
    public ChessBoard copyBoard() {
        try {
            ChessBoard desBoard = new ChessBoard();
            //copy squares
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    desBoard.Squares[i][j].ContainPiece = this.Squares[i][j].ContainPiece;
                    desBoard.Squares[i][j].color = this.Squares[i][j].color;
                }
            }
            //Collections.copy(desBoard.pieces, this.pieces);
            desBoard.pieces.clear();
            int sizei = this.pieces.size();
            for (int i = 0; i < sizei; i++) {
                if (this.pieces.get(i).priority == 10) {
                    desBoard.pieces.add(new King(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 10));
                } else if (this.pieces.get(i).priority == 9) {
                    desBoard.pieces.add(new Queen(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 9));
                } else if (this.pieces.get(i).priority == 3 && this.pieces.get(i).Name.equalsIgnoreCase("knight")) {
                    desBoard.pieces.add(new Knight(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 3));
                } else if (this.pieces.get(i).priority == 3 && this.pieces.get(i).Name.equalsIgnoreCase("Bishop")) {
                    desBoard.pieces.add(new Bishop(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 3));
                } else if (this.pieces.get(i).priority == 5) {
                    desBoard.pieces.add(new Rook(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 5));
                } else if (this.pieces.get(i).priority == 1) {
                    Pawn tmp = (Pawn) this.getPiece(this.pieces.get(i).yPos, this.pieces.get(i).xPos);
                    Pawn p = new Pawn(tmp.yPos, tmp.xPos, tmp.color, 1);
                    p.canMoveTwice = tmp.canMoveTwice;
                    desBoard.pieces.add(p);
                }

            }
            return desBoard;
        } catch (Exception e) {

        }
        return null;
    }

    //copy board 2
    public void copyBoard2(ChessBoard desBoard) throws IOException {
        //copy squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                desBoard.Squares[i][j].ContainPiece = this.Squares[i][j].ContainPiece;
                desBoard.Squares[i][j].color = this.Squares[i][j].color;
            }
        }
        //Collections.copy(desBoard.pieces, this.pieces);
        desBoard.pieces.clear();
        int sizei = this.pieces.size();
        for (int i = 0; i < sizei; i++) {
            if (this.pieces.get(i).priority == 10) {
                desBoard.pieces.add(new King(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 10));
            } else if (this.pieces.get(i).priority == 9) {
                desBoard.pieces.add(new Queen(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 9));
            } else if (this.pieces.get(i).priority == 3 && this.pieces.get(i).Name.equalsIgnoreCase("knight")) {
                desBoard.pieces.add(new Knight(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 3));
            } else if (this.pieces.get(i).priority == 3 && this.pieces.get(i).Name.equalsIgnoreCase("Bishop")) {
                desBoard.pieces.add(new Bishop(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 3));
            } else if (this.pieces.get(i).priority == 5) {
                desBoard.pieces.add(new Rook(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 5));
            } else if (this.pieces.get(i).priority == 1) {
                Pawn tmp = (Pawn) this.getPiece(this.pieces.get(i).yPos, this.pieces.get(i).xPos);
                Pawn p = new Pawn(tmp.yPos, tmp.xPos, tmp.color, 1);
                p.canMoveTwice = tmp.canMoveTwice;
                desBoard.pieces.add(p);
            }
        }
    }

    //clear all possible moves
    public void clearAllAvailableMoves() {
        int sizei = this.pieces.size();
        for (int i = 0; i < sizei; i++) {
            this.pieces.get(i).availableDes.clear();
        }
    }

    public boolean checkMate(PieceColor pc) {
        //white move
        int counter = 0;
        King tmpKing = null;
        PieceColor req = pc;
        ArrayList <Points> CanReachKing = new ArrayList ();
        //get king
        int sizei = this.pieces.size();
        for (int i = 0; i < sizei; i++) {
            if (this.pieces.get(i).priority == 10 && this.pieces.get(i).color == req) {
                tmpKing = (King) this.pieces.get(i);
                break;
            }
        }
        //cal all possible moves for all opponents
        for (int i = 0; i < sizei; i++) {
                this.pieces.get(i).CalculateAllPossibleMoves(this);
        }
        tmpKing.CalculateAllPossibleMoves(this);
        counter = tmpKing.availableDes.size();
        // compare all moves
        for (int i = 0; i < sizei; i++) {
            if(counter==0){
                break;
            }
            if (this.pieces.get(i).color == req) {
                continue;
            } 
            else {
                int sizej = this.pieces.get(i).availableDes.size();
                for (int j = 0; j < sizej; j++) {
                    if(counter==0){
                        break;
                    }
                    int sizek = tmpKing.availableDes.size();
                    for (int k = 0; k < sizek; k++) {
                        if (this.pieces.get(i).availableDes.get(j).yPos == tmpKing.availableDes.get(k).yPos
                                && 
                                this.pieces.get(i).availableDes.get(j).xPos == tmpKing.availableDes.get(k).xPos) {
                            CanReachKing.add(new Points (this.pieces.get(i).yPos,this.pieces.get(i).xPos));
                            counter--;
                            if(counter==0){
                                break;
                            }
                        }
                    }
                }
            }

        }
        if(CanReachKing.size()==1){
        for (int i=0;i<CanReachKing.size();i++){
           for(int j=0;j<sizei;j++){
               if(tmpKing.color==this.pieces.get(j).color){
                   continue;
               }
               else{
                   int sizek= this.pieces.get(j).availableDes.size();
                   for(int k=0;i<sizek;k++){
                       if(CanReachKing.get(i).yPos==this.pieces.get(j).availableDes.get(k).yPos && CanReachKing.get(i).xPos ==this.pieces.get(j).availableDes.get(k).xPos ){
                           return true;
                       }
                   }
               }
           }
        }
        }
        if (counter == 0 && tmpKing.availableDes.size() != 0) {
            return true;
        }
        return false;
    }

    //view board in console
    public void viewBoard() {
        for (int i = 0; i < 8; i++) {
            System.out.println("row " + (i));
            for (int j = 0; j < 8; j++) {
                System.out.print("Element: (" + j + "," + i + ")");
                if (!this.Squares[i][j].ContainPiece) {
                    System.out.println(" Contain Piece:" + this.Squares[i][j].ContainPiece);
                }
                if (this.Squares[i][j].ContainPiece) {
                    System.out.print(" Contain Piece:" + this.Squares[i][j].ContainPiece);
                    System.out.println(" Piece: " + this.getPiece(i, j).color + " " + this.getPiece(i, j).Name);
                }
            }
            System.out.println("");
        }
    }

}
