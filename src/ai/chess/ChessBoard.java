/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.chess;

import Pieces.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
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
        //initialize pieces
        // initializing black pieces
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
        pieces.add(k);
        pieces.add(bi);
        pieces.add(Q);
        pieces.add(K);
        pieces.add(bi1);
        pieces.add(k1);
        pieces.add(r1);

        //initializing black soldiers
        for (int i = 0; i < 8; i++) {
            Pawn p = new Pawn(1, i, PieceColor.Black, 1);
            pieces.add(p);
            this.Squares[1][i].ContainPiece = true;
        }

        //initializing white soldiers
        for (int i = 0; i < 8; i++) {
//            if(i==7){
//                break;
//            }
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
        pieces.add(k);
        pieces.add(bi);
        pieces.add(Q);
        pieces.add(K);
        pieces.add(bi1);
        pieces.add(k1);
        pieces.add(r1);

    }

//    public ChessBoard(BoardSquare[][] squares, ArrayList<Piece> pieces) {
//        this.Squares = squares;
//        this.pieces = pieces;
//    }

    public Piece getPiece(int ydespos, int xdespos) {
        for (int i = 0; i < this.pieces.size(); i++) {
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
            for (int i = 0; i < this.pieces.size(); i++) {
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
                    desBoard.pieces.add(new Pawn(this.pieces.get(i).yPos, this.pieces.get(i).xPos, this.pieces.get(i).color, 1));
                }

            }
            return desBoard;
        } catch (Exception e) {

        }
        return null;
    }

    public Piece cpyPiece(Piece p) throws IOException {
        if (p.priority == 10) {
            try {
                return new King(p.yPos, p.xPos, p.color, 10);
            } catch (IOException ex) {
                Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        else if (p.priority == 9) {
            return new Queen(p.yPos, p.xPos, p.color, 9);
        } else if (p.priority == 3 && p.Name.equalsIgnoreCase("knight")) {
            return new Knight(p.yPos, p.xPos, p.color, 3);
        } else if (p.priority == 3 && p.Name.equalsIgnoreCase("Bishop")) {
            return new Bishop(p.yPos, p.xPos, p.color, 3);
        } else if (p.priority == 5) {
            return new Rook(p.yPos, p.xPos, p.color, 5);
        } else if (p.priority == 1) {
            return new Pawn(p.yPos, p.xPos, p.color, 1);
        }
        return null;
    }

    
    /*  public boolean checkMate(boolean isPlayerWhite) {
        //white move
        int counter = 0;
        King tmpKing = null;
        boolean checked = false;
        PieceColor req;

        req = (isPlayerWhite) ? PieceColor.White : PieceColor.Black;

        //get king
        for (int i = 0; i < this.pieces.size(); i++) {
            if (this.pieces.get(i).priority == 10 && this.pieces.get(i).color == req) {
                tmpKing = (King) this.pieces.get(i);
                break;
            }
        }
        //cal all pss moves for all black
        for (int i = 0; i < this.pieces.size(); i++) {
            if (tmpKing.color != this.pieces.get(i).color) {
                this.pieces.get(i).CalculateAllPossibleMoves(this);
            }
        }
        tmpKing.CalculateAllPossibleMoves(this);
        counter = tmpKing.availableDes.size();
        // compare all moves
        for (int i = 0; i < this.pieces.size(); i++) {
            checked = false;
            if (this.pieces.get(i).color == req) {
                continue;
            } else {
                for (int j = 0; j < this.pieces.get(i).availableDes.size(); j++) {
                    if (checked) {
                        break;
                    }
                    for (int k = 0; k < tmpKing.availableDes.size(); k++) {
                        if (this.pieces.get(i).availableDes.get(j).yPos == tmpKing.availableDes.get(k).yPos
                                && this.pieces.get(i).availableDes.get(j).xPos == tmpKing.availableDes.get(k).xPos) {
                            counter--;
                            checked = true;
                            break;
                        }
                    }
                }
            }

        }
        if (counter == 0) {
            return true;
        }
        return false;
    }*/
}
