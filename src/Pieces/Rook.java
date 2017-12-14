/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import ai.chess.*;

/**
 *
 * @author root
 */
public class Rook extends Piece {

    public Rook(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.yPos = ypos;
        this.xPos = xpos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/rookBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/rookWhite.png"));
        this.availableDes = new ArrayList<>();
        this.Name = "Rook";
    }

    public String getName() {
        return this.Name;
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) throws IOException {
        if (ydespos == this.yPos) {
            //right
            if (xdespos > this.xPos) {
                for (int i = this.xPos + 1; i <= xdespos; i++) {
                    //lw fih 7aga fl seka
                    if (board.Squares[this.yPos][i].ContainPiece && i != xdespos) {
                        return false;
                    } //kill
                    else if (board.Squares[this.yPos][i].ContainPiece
                            && i == xdespos
                            && board.getPiece(this.yPos, i).color != this.color) {
//                        if (!this.overallCheck(board, ydespos, xdespos)) {
//                            return false;
//                        }
                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;

// 
                        return true;
                    } //normal move
                    else if (!board.Squares[this.yPos][i].ContainPiece && i == xdespos) {
//                        if (!this.overallCheck(board, ydespos, xdespos)) {
//                            return false;
//                        }
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        board.Squares[ydespos][xdespos].ContainPiece = true;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    }
                }
            } //left
            else {
                for (int i = this.xPos - 1; i >= xdespos; i--) {
                    //lw fih 7aga fl seka
                    if (board.Squares[this.yPos][i].ContainPiece && i != xdespos) {
                        return false;
                    } //kill
                    else if (board.Squares[this.yPos][i].ContainPiece
                            && i == xdespos
                            && board.getPiece(this.yPos, i).color != this.color) {
//                        if (!this.overallCheck(board, ydespos, xdespos)) {
//                            return false;
//                        }

                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    }//normal move
                    else if (!board.Squares[this.yPos][i].ContainPiece && i == xdespos) {
//                        if (!this.overallCheck(board, ydespos, xdespos)) {
//                            return false;
//                        }

                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        board.Squares[ydespos][xdespos].ContainPiece = true;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    }

                }
            }
        } //y dir
        else if (xdespos == this.xPos) {
            // down 
            if (ydespos > this.yPos) {
                for (int i = this.yPos + 1; i <= ydespos; i++) {
                    //lw fih 7aga fl seka
                    if (board.Squares[i][this.xPos].ContainPiece && i != ydespos) {
                        return false;
                    } //kill
                    else if (board.Squares[i][this.xPos].ContainPiece
                            && i == ydespos
                            && board.getPiece(i, this.xPos).color != this.color) {
//                        if (!this.overallCheck(board, ydespos, xdespos)) {
//                            return false;
//                        }

                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    } //normal move
                    else if (!board.Squares[i][this.xPos].ContainPiece && i == ydespos) {
//                        if (!this.overallCheck(board, ydespos, xdespos)) {
//                            return false;
//                        }

                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        board.Squares[ydespos][xdespos].ContainPiece = true;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    }
                }
            } //up
            else {
                for (int i = this.yPos - 1; i >= ydespos; i--) {
                    //lw fih 7aga fl seka
                    if (board.Squares[i][this.xPos].ContainPiece && i != ydespos) {
                        return false;
                    } //kill
                    else if (board.Squares[i][this.xPos].ContainPiece
                            && i == ydespos
                            && board.getPiece(i, this.xPos).color != this.color) {
//                        if (!this.overallCheck(board, ydespos, xdespos)) {
//                            return false;
//                        }
                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    } //normal move
                    else if (!board.Squares[i][this.xPos].ContainPiece && i == ydespos) {
//                        if (!this.overallCheck(board, ydespos, xdespos)) {
//                            return false;
//                        }

                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        board.Squares[ydespos][xdespos].ContainPiece = true;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    }
                }

            }
        }

        return false;
    }

    /*    public void CalculateAllPossibleMoves(ChessBoard board) {
        //right

        for (int i = this.xPos + 1; i < 8; i++) {
            if (!board.Squares[this.yPos][i].ContainPiece) {
                this.availableDes.add(new Points(this.yPos, i));
            } else {
                break;
            }
        }
        //left
        for (int i = this.xPos - 1; i >= 0; i--) {
            if (!board.Squares[this.yPos][i].ContainPiece) {
                this.availableDes.add(new Points(this.yPos, i));
            } else {
                break;
            }
        }
        //down
        for (int i = this.yPos + 1; i < 8; i++) {
            if (!board.Squares[i][this.xPos].ContainPiece) {
                this.availableDes.add(new Points(i, this.xPos));
            } else {
                break;
            }
        }
        for (int i = this.yPos - 1; i >= 0; i--) {
            if (!board.Squares[i][this.xPos].ContainPiece) {
                this.availableDes.add(new Points(i, this.xPos));
            } else {
                break;
            }
        }

    }*/
}