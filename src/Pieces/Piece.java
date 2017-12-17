package Pieces;

import ai.chess.ChessBoard;
import ai.chess.Points;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.*;
//chess pieces model

abstract public class Piece {

    public int yPos; //row
    public int xPos; //column
    public PieceColor color;
    public int priority;
    public Image blackImage;
    public Image whiteImage;
    public ArrayList<Points> availableDes;
    protected String Name;

    //piece motion
    //change xpos w ypos if possible
    abstract public boolean move(int xdespos, int ydespos, ChessBoard board) throws Exception;

    public String toString() {
        return "Xpos " + this.xPos + "\nYpos " + this.yPos;
    }


        abstract public void CalculateAllPossibleMoves(ChessBoard board);

    public boolean halelmalekfe5atar(ChessBoard board) {

        int kingXpos = -1, kingYpos = -1;

        for (int i = 0; i < board.pieces.size(); i++) {
            if (board.pieces.get(i).priority == 10 && board.pieces.get(i).color == this.color) {
                kingXpos = board.pieces.get(i).xPos;
                kingYpos = board.pieces.get(i).yPos;
            }
        }

        for (int i = 0; i < board.pieces.size(); i++) {
            if (board.pieces.get(i).color != this.color) {
                board.pieces.get(i).CalculateAllPossibleMoves(board);
            }
        }

        Point tmpKingPos = new Point(kingYpos, kingXpos);
        for (int i = 0; i < board.pieces.size(); i++) {
            for (int j = 0; j < board.pieces.get(i).availableDes.size(); j++) {
                if (board.pieces.get(i).availableDes.get(j).xPos == kingXpos && board.pieces.get(i).availableDes.get(j).yPos == kingYpos) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean overallCheck(ChessBoard board, int ydespos, int xdespos) throws IOException {
        ChessBoard tmpBoard = new ChessBoard();
        board.copyBoard(tmpBoard);
        tmpBoard.Squares[this.yPos][this.xPos].ContainPiece = false;
        tmpBoard.Squares[ydespos][xdespos].ContainPiece = true;
        Piece tmpPiece = tmpBoard.getPiece(this.xPos, this.yPos);
        tmpPiece.yPos = ydespos;
        tmpPiece.xPos = xdespos;
        this.CalculateAllPossibleMoves(tmpBoard);
        boolean fe5atar = tmpPiece.halelmalekfe5atar(tmpBoard);
        if (fe5atar) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return Name;
    }
}
