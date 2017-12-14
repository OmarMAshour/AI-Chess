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


public class Queen extends Piece {

    public Queen(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/queenBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/queenWhite.png"));
        this.availableDes = new ArrayList<>();
        this.Name = "Queen";
    }

    public String getName() {
        return this.Name;
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
        //up right

        int counter = this.yPos - 1;
        for (int i = this.xPos + 1; i < 8; i++) {
            if (!board.Squares[counter][i].ContainPiece) {
                this.availableDes.add(new Points(counter, i));
                counter--;
            } else {
                break;
            }
        }
        //up left 
        int counter1 = this.yPos - 1;
        for (int i = this.xPos - 1; i >= 0; i--) {
            if (!board.Squares[counter1][i].ContainPiece) {
                this.availableDes.add(new Points(counter1, i));
                counter1--;
            } else {
                break;
            }
        }
        //down right 
        int counter2 = this.yPos + 1;
        for (int i = this.xPos + 1; i < 8; i++) {
            if (!board.Squares[counter2][i].ContainPiece) {
                this.availableDes.add(new Points(counter2, i));
                counter2++;
            } else {
                break;
            }
        }
        //down left
        int counter3 = this.yPos + 1;
        for (int i = this.xPos - 1; i >= 0; i--) {
            if (!board.Squares[counter3][i].ContainPiece) {
                this.availableDes.add(new Points(counter3, i));
                counter3++;
            } else {
                break;
            }
        }
    }*/
    public boolean move(int xdespos, int ydespos, ChessBoard board) throws IOException {

        //x dir 
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
        if (!(Math.abs(ydespos - this.yPos) == Math.abs(xdespos - this.xPos))) {
            return false;
        }
        //up right
        if (ydespos < this.yPos && xdespos > this.xPos) {
            int counter = this.yPos - 1;
            for (int i = this.xPos + 1; i <= xdespos; i++) {
                if (board.Squares[counter][i].ContainPiece && i != xdespos) {
                    return false;
                } //KILL!!
                else if (board.Squares[counter][i].ContainPiece && i == xdespos && board.getPiece(ydespos, xdespos).color != this.color) {
//                    if (!this.overallCheck(board, ydespos, xdespos)) {
//                        return false;
//                    }

                    board.pieces.remove(board.getPiece(ydespos, xdespos));
                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                } else if (!board.Squares[counter][i].ContainPiece && i == xdespos) {
//                    if (!this.overallCheck(board, ydespos, xdespos)) {
//                        return false;
//                    }

                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    board.Squares[ydespos][xdespos].ContainPiece = true;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                }
                counter--;
            }
        } //up left 
        else if (ydespos < this.yPos && xdespos < this.xPos) {

            int counter = this.yPos - 1;
            for (int i = this.xPos - 1; i >= xdespos; i--) {
                if (board.Squares[counter][i].ContainPiece && i != xdespos) {
                    return false;
                } //KILL!!
                else if (board.Squares[counter][i].ContainPiece && i == xdespos && board.getPiece(ydespos, xdespos).color != this.color) {
//                    if (!this.overallCheck(board, ydespos, xdespos)) {
//                        return false;
//                    }

                    board.pieces.remove(board.getPiece(ydespos, xdespos));
                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                } else if (!board.Squares[counter][i].ContainPiece && i == xdespos) {

//                    if (!this.overallCheck(board, ydespos, xdespos)) {
//                        return false;
//                    }
                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    board.Squares[ydespos][xdespos].ContainPiece = true;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                }
                counter--;
            }
        } //down right 
        else if (ydespos > this.yPos && xdespos > this.xPos) {

            int counter = this.yPos + 1;
            for (int i = this.xPos + 1; i <= xdespos; i++) {
                if (board.Squares[counter][i].ContainPiece && i != xdespos) {
                    return false;
                } //KILL!!
                else if (board.Squares[counter][i].ContainPiece && i == xdespos && board.getPiece(ydespos, xdespos).color != this.color) {
//                    if (!this.overallCheck(board, ydespos, xdespos)) {
//                        return false;
//                    }

                    board.pieces.remove(board.getPiece(ydespos, xdespos));
                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                } else if (!board.Squares[counter][i].ContainPiece && i == xdespos) {
//                    if (!this.overallCheck(board, ydespos, xdespos)) {
//                        return false;
//                    }

                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    board.Squares[ydespos][xdespos].ContainPiece = true;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                }
                counter++;
            }
        } //down left
        else if (ydespos > this.yPos && xdespos < this.xPos) {

            int counter = this.yPos + 1;
            for (int i = this.xPos - 1; i >= xdespos; i--) {
                if (board.Squares[counter][i].ContainPiece && i != xdespos) {
                    return false;
                } //KILL!!
                else if (board.Squares[counter][i].ContainPiece && i == xdespos && board.getPiece(ydespos, xdespos).color != this.color) {
//                    if (!this.overallCheck(board, ydespos, xdespos)) {
//                        return false;
//                    }

                    board.pieces.remove(board.getPiece(ydespos, xdespos));
                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                } else if (!board.Squares[counter][i].ContainPiece && i == xdespos) {
//                    if (!this.overallCheck(board, ydespos, xdespos)) {
//                        return false;
//                    }

                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    board.Squares[ydespos][xdespos].ContainPiece = true;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                }
                counter++;
            }
        }
        return false;
    }
}
