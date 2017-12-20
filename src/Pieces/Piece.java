package Pieces;

import java.math.*;
import ai.chess.*;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
//chess pieces model

abstract public class Piece {

    public int yPos; //row
    public int xPos; //column
    public PieceColor color;
    public int priority;
    public Image blackImage;
    public Image whiteImage;
    public String Name;
    public int value;
            
    
    //piece motion
    //change xpos w ypos if possible
    abstract public boolean move(int xdespos, int ydespos, ChessBoard board) throws Exception;

    public String toString() {
        return "Xpos " + this.xPos + "\nYpos " + this.yPos;
    }

    abstract public void CalculateAllPossibleMoves(ChessBoard board, ArrayList<Points> AvailableDes);

    public boolean halelmalekfe5atar(ChessBoard board, ArrayList<Points> AvailableDes) {

        int kingXpos = -1, kingYpos = -1;
        Points p = new Points(-1, -1);
        for (int i = 0; i < board.pieces.size(); i++) {
            if (board.pieces.get(i).priority == 10 && board.pieces.get(i).color == this.color) {
                kingXpos = board.pieces.get(i).xPos;
                kingYpos = board.pieces.get(i).yPos;
            }
        }

        for (int i = 0; i < board.pieces.size(); i++) {
            if (board.pieces.get(i).color != this.color) {
                board.pieces.get(i).CalculateAllPossibleMoves(board, AvailableDes);
                for (int j = 0; j < AvailableDes.size(); j++) {
                    if (AvailableDes.get(j).xPos == kingXpos && AvailableDes.get(j).yPos == kingYpos) {
                        p.yPos = board.pieces.get(i).yPos;
                        p.xPos = board.pieces.get(i).xPos;
                        return true;
                    }
                }
            }
            AvailableDes.clear();
        }
        return false;

    }

    public boolean overallCheck(ChessBoard board, int ydespos, int xdespos, ArrayList<Points> AvailableDes) throws IOException {
        ChessBoard tmpBoard = board.copyBoard();
        tmpBoard.Squares[this.yPos][this.xPos].ContainPiece = false;

        if (tmpBoard.Squares[ydespos][xdespos].ContainPiece) {
            tmpBoard.pieces.remove(tmpBoard.getPiece(ydespos, xdespos));
        }

        tmpBoard.Squares[ydespos][xdespos].ContainPiece = true;
        Piece tmpPiece = tmpBoard.getPiece(this.yPos, this.xPos);
        tmpPiece.yPos = ydespos;
        tmpPiece.xPos = xdespos;
        this.CalculateAllPossibleMoves(tmpBoard, AvailableDes);
        boolean fe5atar = tmpPiece.halelmalekfe5atar(tmpBoard,AvailableDes);
        if (fe5atar) {
            //   System.out.println("Overall returned true");
            return true;
        }
        //  System.out.println("Overall returned false");
        return false;
    }
    
    public Points getCurrentPoint(){
        return new Points(yPos, xPos);
    }

    
            
}
