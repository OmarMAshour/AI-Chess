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

public class Knight extends Piece {

    public Knight(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/knightBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/knightWhite.png"));
        this.availableDes = new ArrayList<>();
        this.Name = "Knight";
    }

    public String getName() {
        return this.Name;
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) throws IOException {
        if ((Math.abs(this.yPos - ydespos) == 2
                && Math.abs(this.xPos - xdespos) == 1)
                || (Math.abs(this.yPos - ydespos) == 1
                && Math.abs(this.xPos - xdespos) == 2)) {
            if (!board.Squares[ydespos][xdespos].ContainPiece) {
//                if (!this.overallCheck(board, ydespos, xdespos)) {
//                    return false;
//                }

                board.Squares[this.yPos][this.xPos].ContainPiece = false;
                board.Squares[ydespos][xdespos].ContainPiece = true;
                this.yPos = ydespos;
                this.xPos = xdespos;
                return true;
            } //kill
            else {
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
    /*
    public void CalculateAllPossibleMoves(ChessBoard board) {
        //2 up 1 right
        if (!board.Squares[this.yPos + 2][this.xPos + 1].ContainPiece) {
            this.availableDes.add(new Points(yPos + 2, xPos + 1));
        }
        //2 up 1 left
        if (!board.Squares[this.yPos + 2][this.xPos - 1].ContainPiece) {
            this.availableDes.add(new Points(yPos + 2, xPos - 1));
        }
        //2 down 1 right
        if (!board.Squares[this.yPos - 2][this.xPos + 1].ContainPiece) {
            this.availableDes.add(new Points(yPos - 2, xPos + 1));
        }
        //2 down 1 left
        if (!board.Squares[this.yPos - 2][this.xPos - 1].ContainPiece) {
            this.availableDes.add(new Points(yPos - 2, xPos - 1));
        }
        //1 up 2 right
        if (!board.Squares[this.yPos + 1][this.xPos + 2].ContainPiece) {
            this.availableDes.add(new Points(yPos + 1, xPos + 2));
        }
        //1 up 2 left
        if (!board.Squares[this.yPos + 1][this.xPos - 2].ContainPiece) {
            this.availableDes.add(new Points(yPos + 1, xPos - 2));
        }
        //1 down 2 right
        if (!board.Squares[this.yPos - 1][this.xPos + 2].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos + 2));
        }
        //1 down 2 left
        if (!board.Squares[this.yPos - 1][this.xPos - 2].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos - 2));
        }
    }
     */
}

