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
    public ArrayList<Points> availableDes;
    public String Name;

    //piece motion
    //change xpos w ypos if possible
    abstract public boolean move(int xdespos, int ydespos, ChessBoard board) throws Exception;

    public String toString() {
        return "Xpos " + this.xPos + "\nYpos " + this.yPos;
    }

    abstract public void CalculateAllPossibleMoves(ChessBoard board);

    public boolean halelmalekfe5atar(ChessBoard board) {

        int kingXpos = -1, kingYpos = -1;
        Points p = new Points(-1, -1);
        boolean found = false;
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
            if (found) {
                break;
            }
            for (int j = 0; j < board.pieces.get(i).availableDes.size(); j++) {
                if (board.pieces.get(i).availableDes.get(j).xPos == kingXpos && board.pieces.get(i).availableDes.get(j).yPos == kingYpos) {
                    p.yPos = board.pieces.get(i).yPos;
                    p.xPos = board.pieces.get(i).xPos;
                    found = true;
                    return true;
                 
                }
            }
        }
/*      if (p.xPos == -1 && p.yPos == -1) {
            return false;
        }
        //calcuate kol ele m3 el malek possible moves
        for (int i = 0; i < board.pieces.size(); i++) {
            if (board.pieces.get(i).color == this.color) {
                board.pieces.get(i).CalculateAllPossibleMoves(board);
            }
        }

        for (int i = 0; i < board.pieces.size(); i++) {
            for (int j = 0; j < board.pieces.get(i).availableDes.size(); j++) {
                if (board.pieces.get(i).availableDes.get(j).xPos == p.xPos
                        && board.pieces.get(i).availableDes.get(j).yPos == p.yPos) {
                        //indicate b sora ma eno momkn da bs ele yet7arak
                }
            }
        }
*/
        return false;

    }

    public boolean overallCheck(ChessBoard board, int ydespos, int xdespos) throws IOException {
        ChessBoard tmpBoard = new ChessBoard();
        board.copyBoard(tmpBoard);
        tmpBoard.Squares[this.yPos][this.xPos].ContainPiece = false;
        
        if(tmpBoard.Squares[ydespos][xdespos].ContainPiece){
            tmpBoard.pieces.remove(tmpBoard.getPiece(ydespos, xdespos));
        }
        
        tmpBoard.Squares[ydespos][xdespos].ContainPiece = true;
        Piece tmpPiece = tmpBoard.getPiece(this.yPos, this.xPos);
        tmpPiece.yPos = ydespos;
        tmpPiece.xPos = xdespos;
        this.CalculateAllPossibleMoves(tmpBoard);
        boolean fe5atar = tmpPiece.halelmalekfe5atar(tmpBoard);
        if (fe5atar) {
            System.out.println("Overall returned true");
            return true;
        }
        System.out.println("Overall returned false");
        return false;
    }
}
