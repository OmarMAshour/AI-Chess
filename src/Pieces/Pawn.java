package Pieces;

import ai.chess.BoardController;
import ai.chess.ChessBoard;
import ai.chess.Points;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Pawn extends Piece implements Serializable {

    public boolean canMoveTwice;

    public Pawn(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.yPos = ypos;
        this.xPos = xpos;
        this.color = color;
        this.priority = priority;
        this.canMoveTwice = true;
        this.blackImage = ImageIO.read(new File("src/Images/pawnBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/pawnWhite.png"));
        this.availableDes = new ArrayList<>();
        this.Name = "Pawn";
        this.value = 2;
    }

    public void setImages() throws IOException {
        this.blackImage = ImageIO.read(new File("src/Images/pawnBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/pawnWhite.png"));
    }

    public void clearImages() {
        this.blackImage = null;
        this.whiteImage = null;
    }

    @Override
    public void CalculateAllPossibleMoves(ChessBoard board) {
        //black move
        if(!this.availableDes.isEmpty()){
        this.availableDes.clear();
        }
        if (this.color == PieceColor.Black) {
            //first move (2 moves)
            if (this.yPos + 1 < 8 && this.yPos + 2 < 8 && this.canMoveTwice && !board.Squares[this.yPos + 1][this.xPos].ContainPiece && !board.Squares[this.yPos + 2][this.xPos].ContainPiece) {
                this.availableDes.add(new Points(yPos + 2, xPos));
                this.availableDes.add(new Points(yPos + 1, xPos));
            } //normal move (1 move)
            else if (this.yPos + 1 < 8 && !board.Squares[this.yPos + 1][this.xPos].ContainPiece) {
                this.availableDes.add(new Points(yPos + 1, xPos));
            } //KILL!!!!!!!
            else if (this.yPos + 1 < 8 && this.xPos + 1 < 8 && board.Squares[this.yPos + 1][this.xPos + 1].ContainPiece && board.getPiece(this.yPos + 1, this.xPos + 1).color != this.color) {
                this.availableDes.add(new Points(yPos + 1, xPos + 1));
            } else if (this.yPos + 1 < 8 && this.xPos - 1 >= 0 && board.Squares[this.yPos + 1][this.xPos - 1].ContainPiece && board.getPiece(this.yPos + 1, this.xPos - 1).color != this.color) {
                this.availableDes.add(new Points(yPos + 1, xPos - 1));
            }
        } //white move
        else if (this.yPos - 1 >= 0 && this.yPos - 2 >= 0 && this.canMoveTwice && !board.Squares[this.yPos - 1][this.xPos].ContainPiece && !board.Squares[this.yPos - 2][this.xPos].ContainPiece) {
            this.availableDes.add(new Points(yPos - 2, xPos));
            this.availableDes.add(new Points(yPos - 1, xPos));
        } //normal move (1 move)
        else if (this.yPos - 1 >= 0 && !board.Squares[this.yPos - 1][this.xPos].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos));
        } //KILL!!!!!!!
        else if (this.yPos - 1 >= 0 && this.xPos + 1 < 8 && board.Squares[this.yPos - 1][this.xPos + 1].ContainPiece && board.getPiece(this.yPos - 1, this.xPos + 1).color != this.color) {
            this.availableDes.add(new Points(yPos - 1, xPos + 1));
        } else if (this.yPos - 1 >= 0 && this.xPos - 1 >= 0 && board.Squares[this.yPos - 1][this.xPos - 1].ContainPiece && board.getPiece(this.yPos - 1, this.xPos - 1).color != this.color) {
            this.availableDes.add(new Points(yPos - 1, xPos - 1));
        }
    }

    @Override
    public boolean move(int xdespos, int ydespos, ChessBoard board) throws Exception {
        boolean b1 = (xdespos < 8 || xdespos >= 0);
        boolean b2 = (ydespos < 8 || ydespos >= 0);
        if (!(b1 && b2)) {
            return false;
        }
        //black move
        
        if (this.color == PieceColor.Black) {
            //first move (2 moves)
            if (Math.abs(ydespos - this.yPos) == 2
                    && ydespos > this.yPos
                    && this.xPos == xdespos
                    && this.canMoveTwice
                    && !board.Squares[this.yPos + 1][this.xPos].ContainPiece
                    && !board.Squares[this.yPos + 2][this.xPos].ContainPiece) {
                if (this.overallCheck(board, ydespos, xdespos)) {
                    return false;
                }
                this.canMoveTwice = false;
                board.Squares[this.yPos][this.xPos].ContainPiece = false;
                board.Squares[ydespos][xdespos].ContainPiece = true;
                this.yPos = ydespos;
                this.xPos = xdespos;
                return true;
            } //normal move (1 move)
            else if (Math.abs(ydespos - this.yPos) == 1
                    && ydespos > this.yPos
                    && this.xPos == xdespos
                    && !board.Squares[ydespos][xdespos].ContainPiece) {
                if (this.overallCheck(board, ydespos, xdespos)) {
                    return false;
                }
                this.canMoveTwice = false;
                board.Squares[this.yPos][this.xPos].ContainPiece = false;
                board.Squares[ydespos][xdespos].ContainPiece = true;
                this.yPos = ydespos;
                this.xPos = xdespos;
                return true;
            } //KILL!!!!!!!
            else if (Math.abs(ydespos - this.yPos) == 1
                    && ydespos > this.yPos
                    && Math.abs(xdespos - this.xPos) == 1
                    && board.Squares[ydespos][xdespos].ContainPiece
                    && board.getPiece(ydespos, xdespos).color != this.color) {
                if (this.overallCheck(board, ydespos, xdespos)) {
                    return false;
                }

                this.canMoveTwice = false;
                // System.out.println(board.getPiece(ydespos, xdespos));
                board.pieces.remove(board.getPiece(ydespos, xdespos));
                board.Squares[this.yPos][this.xPos].ContainPiece = false;
                this.yPos = ydespos;
                this.xPos = xdespos;
                return true;
            }

        } //white move
        else //first move (2 moves)
        if (Math.abs(ydespos - this.yPos) == 2
                && ydespos < this.yPos
                && this.xPos == xdespos
                && this.canMoveTwice
                && !board.Squares[this.yPos - 1][this.xPos].ContainPiece
                && !board.Squares[this.yPos - 2][this.xPos].ContainPiece) {
            if (this.overallCheck(board, ydespos, xdespos)) {
                return false;
            }
            this.canMoveTwice = false;
            board.Squares[this.yPos][this.xPos].ContainPiece = false;
            board.Squares[ydespos][xdespos].ContainPiece = true;
            this.yPos = ydespos;
            this.xPos = xdespos;
            return true;
        } //normal move (1 move)
        else if (Math.abs(ydespos - this.yPos) == 1
                && ydespos < this.yPos
                && this.xPos == xdespos
                && !board.Squares[ydespos][xdespos].ContainPiece) {
            if (this.overallCheck(board, ydespos, xdespos)) {
                return false;
            }
            this.canMoveTwice = false;
            board.Squares[this.yPos][this.xPos].ContainPiece = false;
            board.Squares[ydespos][xdespos].ContainPiece = true;
            this.yPos = ydespos;
            this.xPos = xdespos;
            return true;
        } //KILL!!!!!!!
        else if (Math.abs(ydespos - this.yPos) == 1
                && ydespos < this.yPos
                && Math.abs(xdespos - this.xPos) == 1
                && board.Squares[ydespos][xdespos].ContainPiece
                && board.getPiece(ydespos, xdespos).color != this.color) {
            if (this.overallCheck(board, ydespos, xdespos)) {
                return false;
            }
            this.canMoveTwice = false;
            Piece p = board.getPiece(ydespos, xdespos);
            board.pieces.remove(board.getPiece(ydespos, xdespos));
            // System.out.println("3dadhom  = "+board.pieces.size());
            board.Squares[this.yPos][this.xPos].ContainPiece = false;
            this.yPos = ydespos;
            this.xPos = xdespos;
            return true;
        }
        return false;
    }

   
}
