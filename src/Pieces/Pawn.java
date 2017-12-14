/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import ai.chess.ChessBoard;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author root
 */

public class Pawn extends Piece {

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
    }

    public String getName() {
        return this.Name;
    }


    /*    public void CalculateAllPossibleMoves(ChessBoard board) {
        //black move
        if (this.color == Piece.Black) {
            //first move (2 moves)
            if (this.canMoveTwice && !board.Squares[this.yPos + 1][this.xPos].ContainPiece && !board.Squares[this.yPos + 2][this.xPos].ContainPiece) {
                this.availableDes.add(new Points(yPos + 2, xPos));
                this.availableDes.add(new Points(yPos + 1, xPos));
            } //normal move (1 move)
            else if (!board.Squares[this.yPos + 1][this.xPos].ContainPiece) {
                this.availableDes.add(new Points(yPos + 1, xPos));
            } //KILL!!!!!!!
            else if (board.Squares[this.yPos + 1][this.xPos + 1].ContainPiece && board.getPiece(this.yPos + 1, this.xPos + 1).color != this.color) {
                this.availableDes.add(new Points(yPos + 1, xPos + 1));
            } else if (board.Squares[this.yPos + 1][this.xPos - 1].ContainPiece && board.getPiece(this.yPos + 1, this.xPos - 1).color != this.color) {
                this.availableDes.add(new Points(yPos + 1, xPos - 1));
            }
        } //white move
        else {
            if (this.canMoveTwice && !board.Squares[this.yPos - 1][this.xPos].ContainPiece && !board.Squares[this.yPos - 2][this.xPos].ContainPiece) {
                this.availableDes.add(new Points(yPos - 2, xPos));
                this.availableDes.add(new Points(yPos - 1, xPos));
            } //normal move (1 move)
            else if (!board.Squares[this.yPos - 1][this.xPos].ContainPiece) {
                this.availableDes.add(new Points(yPos - 1, xPos));
            } //KILL!!!!!!!
            else if (board.Squares[this.yPos - 1][this.xPos + 1].ContainPiece && board.getPiece(this.yPos - 1, this.xPos + 1).color != this.color) {
                this.availableDes.add(new Points(yPos - 1, xPos + 1));
            } else if (board.Squares[this.yPos - 1][this.xPos - 1].ContainPiece && board.getPiece(this.yPos - 1, this.xPos - 1).color != this.color) {
                this.availableDes.add(new Points(yPos - 1, xPos - 1));
            }
        }
    }
     */
    public boolean move(int xdespos, int ydespos, ChessBoard board) throws Exception {
        //black move
        if (this.color == PieceColor.Black) {
            //first move (2 moves)
            if (Math.abs(ydespos - this.yPos) == 2
                    && ydespos > this.yPos
                    && this.xPos == xdespos
                    && this.canMoveTwice
                    && !board.Squares[this.yPos + 1][this.xPos].ContainPiece
                    && !board.Squares[this.yPos + 2][this.xPos].ContainPiece) {
//                if (!this.overallCheck(board, ydespos, xdespos)) {
//                    return false;
//                }
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
//                if (!this.overallCheck(board, ydespos, xdespos)) {
//                    return false;
//                }
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
//                if (!this.overallCheck(board, ydespos, xdespos)) {
//                    return false;
//                }

                this.canMoveTwice = false;
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
            this.canMoveTwice = false;
            board.pieces.remove(board.getPiece(ydespos, xdespos));
            board.Squares[this.yPos][this.xPos].ContainPiece = false;
            this.yPos = ydespos;
            this.xPos = xdespos;
            return true;
        }
        return false;

    }
}
