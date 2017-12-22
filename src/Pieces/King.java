package Pieces;

import Pieces.Piece;
import Pieces.PieceColor;
import ai.chess.ChessBoard;
import ai.chess.Points;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class King extends Piece implements Serializable {

    public King(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/kingBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/kingWhite.png"));
        this.availableDes = new ArrayList<>();
        this.Name = "King";
        this.value = 200;
    }

    @Override
    public void setImages() throws IOException {
        this.blackImage = ImageIO.read(new File("src/Images/kingBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/kingWhite.png"));
    }

    public void clearImages() {
        this.blackImage = null;
        this.whiteImage = null;
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) throws IOException {
        //n4oof 7war el checkmate
        boolean b1 = (xdespos < 8 || xdespos >= 0);
        boolean b2 = (ydespos < 8 || ydespos >= 0);
        if (!(b1 && b2)) {
            return false;
        }

        if ((Math.abs(this.yPos - ydespos) == 1
                && Math.abs(xdespos - this.xPos) == 0)
                || (Math.abs(this.yPos - ydespos) == 1
                && Math.abs(xdespos - this.xPos) == 1)
                || (Math.abs(this.yPos - ydespos) == 0
                && Math.abs(xdespos - this.xPos) == 1)) {
            if (!board.Squares[ydespos][xdespos].ContainPiece) {
                if (this.overallCheck(board, ydespos, xdespos)) {
                    return false;
                }
                board.Squares[this.yPos][this.xPos].ContainPiece = false;
                board.Squares[ydespos][xdespos].ContainPiece = true;
                this.xPos = xdespos;
                this.yPos = ydespos;
                return true;
            } else if (board.Squares[ydespos][xdespos].ContainPiece && board.getPiece(ydespos, xdespos).color != this.color) {
                if (this.overallCheck(board, ydespos, xdespos)) {
                    return false;
                }
                board.pieces.remove(board.getPiece(ydespos, xdespos));
                board.Squares[this.yPos][this.xPos].ContainPiece = false;
                this.yPos = ydespos;
                this.xPos = xdespos;
                return true;
            }
        }

        return false;
    }

    public void CalculateAllPossibleMoves(ChessBoard board) {
        //down.
        if(!this.availableDes.isEmpty()){
        this.availableDes.clear();
        }
        if (this.yPos + 1 < 8 && !board.Squares[this.yPos + 1][this.xPos].ContainPiece) {
            this.availableDes.add(new Points(yPos + 1, xPos));
        }
        //up
        if (this.yPos - 1 >= 0 && !board.Squares[this.yPos - 1][this.xPos].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos));
        }
        //left
        if (this.xPos - 1 >= 0 && !board.Squares[this.yPos][this.xPos - 1].ContainPiece) {
            this.availableDes.add(new Points(yPos, xPos - 1));
        }
        //right
        if (this.xPos + 1 < 8 && !board.Squares[this.yPos][this.xPos + 1].ContainPiece) {
            this.availableDes.add(new Points(yPos, xPos + 1));
        }
        //up right
        if (this.yPos - 1 >= 0 && this.xPos + 1 < 8 && !board.Squares[this.yPos - 1][this.xPos + 1].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos + 1));
        }
        //up left
        if (this.yPos - 1 >= 0 && this.xPos - 1 >= 0 && !board.Squares[this.yPos - 1][this.xPos - 1].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos - 1));
        }
        //down right
        if (this.yPos + 1 < 8 && this.xPos + 1 < 8 && !board.Squares[this.yPos + 1][this.xPos + 1].ContainPiece) {
            this.availableDes.add(new Points(yPos + 1, xPos + 1));
        }
        //down left
        if (this.yPos + 1 < 8 && this.xPos - 1 >= 0 && !board.Squares[this.yPos + 1][this.xPos - 1].ContainPiece) {
            this.availableDes.add(new Points(yPos + 1, xPos - 1));
        }
    }
}
