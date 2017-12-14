/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import ai.chess.ChessBoard;
import ai.chess.Points;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author root
 */

public class King extends Piece {

    public King(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/kingBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/kingWhite.png"));
        this.availableDes = new ArrayList<>();
        this.Name = "King";
    }

    public String getName() {
        return this.Name;
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) throws IOException {
        if ((Math.abs(this.yPos - ydespos) == 1
                && Math.abs(xdespos - this.xPos) == 0)
                || (Math.abs(this.yPos - ydespos) == 1
                && Math.abs(xdespos - this.xPos) == 1)
                || (Math.abs(this.yPos - ydespos) == 0
                && Math.abs(xdespos - this.xPos) == 1)) {
            if (!board.Squares[ydespos][xdespos].ContainPiece) {
//                if (!this.overallCheck(board, ydespos, xdespos)) {
//                    return false;
//                }
                board.Squares[this.yPos][this.xPos].ContainPiece = false;
                board.Squares[ydespos][xdespos].ContainPiece = true;
                this.xPos = xdespos;
                this.yPos = ydespos;
                return true;
            } else if (board.Squares[ydespos][xdespos].ContainPiece && board.getPiece(ydespos, xdespos).color != this.color) {
//                if (!this.overallCheck(board, ydespos, xdespos)) {
//                    return false;
//                }
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
        //up
        if (!board.Squares[this.yPos + 1][this.xPos].ContainPiece) {
            this.availableDes.add(new Points(yPos + 1, xPos));
        }
        //down
        if (!board.Squares[this.yPos - 1][this.xPos].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos));
        }
        //left
        if (!board.Squares[this.yPos][this.xPos - 1].ContainPiece) {
            this.availableDes.add(new Points(yPos, xPos - 1));
        }
        //right
        if (!board.Squares[this.yPos][this.xPos + 1].ContainPiece) {
            this.availableDes.add(new Points(yPos, xPos + 1));
        }
        //up right
        if (!board.Squares[this.yPos - 1][this.xPos + 1].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos + 1));
        }
        //up left
        if (!board.Squares[this.yPos - 1][this.xPos - 1].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos - 1));
        }
        //down right
        if (!board.Squares[this.yPos + 1][this.xPos + 1].ContainPiece) {
            this.availableDes.add(new Points(yPos + 1, xPos + 1));
        }
        //down left
        if (!board.Squares[this.yPos + 1][this.xPos - 1].ContainPiece) {
            this.availableDes.add(new Points(yPos + 1, xPos - 1));
        }
    }
}
