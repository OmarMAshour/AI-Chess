package Pieces;

import ai.chess.*;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import javax.swing.JOptionPane;
//chess pieces model

abstract public class Piece implements Serializable{

    public int yPos; //row
    public int xPos; //column
    public PieceColor color;
    public int priority;
    public Image blackImage;
    public Image whiteImage;
    public ArrayList<Points> availableDes;
    public String Name;
    public int value;

    //piece motion
    //change xpos w ypos if possible
    abstract public boolean move(int xdespos, int ydespos, ChessBoard board) throws Exception;

    public String toString() {
        return "Xpos " + this.xPos + "\nYpos " + this.yPos;
    }

    abstract public void CalculateAllPossibleMoves(ChessBoard board);
    abstract public void setImages () throws IOException;
    abstract public void clearImages();
    public boolean halelmalekfe5atar(ChessBoard board,ChessBoard prevBoard) {

        int kingXpos = -1, kingYpos = -1;
        Points p = new Points(-1, -1);
        boolean found = false;
        King tmpKing = null;
        for (int i = 0; i < board.pieces.size(); i++) {
            if (board.pieces.get(i).priority == 10 && board.pieces.get(i).color == this.color) {
                kingXpos = board.pieces.get(i).xPos;
                kingYpos = board.pieces.get(i).yPos;
                tmpKing = (King) board.getPiece(board.pieces.get(i).yPos, board.pieces.get(i).xPos);
            }
        }

        for (int i = 0; i < board.pieces.size(); i++) {
            if (board.pieces.get(i).color != this.color) {
                board.pieces.get(i).CalculateAllPossibleMoves(board);
            }
        }
        for (int i = 0; i < board.pieces.size(); i++) {
            if (found) {
                break;
            }
            for (int j = 0; j < board.pieces.get(i).availableDes.size(); j++) {
                if (board.pieces.get(i).availableDes.get(j).xPos == kingXpos && board.pieces.get(i).availableDes.get(j).yPos == kingYpos) {
                    p.yPos = board.pieces.get(i).yPos;
                    p.xPos = board.pieces.get(i).xPos;
                    found = true;
                     if (tmpKing.color == PieceColor.Black) {
                        if (prevBoard.checkMate(PieceColor.Black)) {
                            JOptionPane.showMessageDialog(null, PieceColor.White + " Wins");
                            System.exit(0);
                        }
                    } else if (tmpKing.color == PieceColor.White) {
                        if (prevBoard.checkMate(PieceColor.White)) {
                            JOptionPane.showMessageDialog(null, PieceColor.Black + " Wins");
                            System.exit(0);
                        }
                    }
                     return true;

                }

            }
            board.pieces.get(i).availableDes.clear();
        }
        return false;

    }

    public boolean overallCheck(ChessBoard board, int ydespos, int xdespos) throws IOException {
        ChessBoard tmpBoard = board.copyBoard();
        tmpBoard.Squares[this.yPos][this.xPos].ContainPiece = false;
        if (tmpBoard.Squares[ydespos][xdespos].ContainPiece) {
            tmpBoard.pieces.remove(tmpBoard.getPiece(ydespos, xdespos));
        }
        tmpBoard.Squares[ydespos][xdespos].ContainPiece = true;
        Piece tmpPiece = tmpBoard.getPiece(this.yPos, this.xPos);
        tmpPiece.yPos = ydespos;
        tmpPiece.xPos = xdespos;
        this.CalculateAllPossibleMoves(tmpBoard);
        boolean fe5atar = tmpPiece.halelmalekfe5atar(tmpBoard,board);
        if (fe5atar) {
            
            return true;
        }

        return false;
    }
}
