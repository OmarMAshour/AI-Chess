package Pieces;

import Pieces.Piece;
import Pieces.PieceColor;
import ai.chess.BoardController;
import ai.chess.ChessBoard;
import ai.chess.Points;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Bishop extends Piece implements Serializable {

    public Bishop(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.yPos = ypos;
        this.xPos = xpos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/bishopBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/bishopWhite.png"));
        this.availableDes = new ArrayList<>();
        this.Name = "Bishop";
        this.value = 6;
    }

    public void setImages() throws IOException {
        this.blackImage = ImageIO.read(new File("src/Images/bishopBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/bishopWhite.png"));
    }

    public void clearImages() {
        this.blackImage = null;
        this.whiteImage = null;
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) throws IOException, IOException, IOException {
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
                    if (this.overallCheck(board, ydespos, xdespos)) {
                        return false;
                    }

                    board.pieces.remove(board.getPiece(ydespos, xdespos));
                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                } else if (!board.Squares[counter][i].ContainPiece && i == xdespos) {
                    if (this.overallCheck(board, ydespos, xdespos)) {
                        return false;
                    }

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
                    if (this.overallCheck(board, ydespos, xdespos)) {
                        return false;
                    }

                    board.pieces.remove(board.getPiece(ydespos, xdespos));
                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                } else if (!board.Squares[counter][i].ContainPiece && i == xdespos) {
                    if (this.overallCheck(board, ydespos, xdespos)) {
                        return false;
                    }

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
                    if (this.overallCheck(board, ydespos, xdespos)) {
                        return false;
                    }

                    board.pieces.remove(board.getPiece(ydespos, xdespos));
                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                } else if (!board.Squares[counter][i].ContainPiece && i == xdespos) {
                    if (this.overallCheck(board, ydespos, xdespos)) {
                        return false;
                    }

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
                    if (this.overallCheck(board, ydespos, xdespos)) {
                        return false;
                    }

                    board.pieces.remove(board.getPiece(ydespos, xdespos));
                    board.Squares[this.yPos][this.xPos].ContainPiece = false;
                    this.yPos = ydespos;
                    this.xPos = xdespos;
                    return true;
                } else if (!board.Squares[counter][i].ContainPiece && i == xdespos) {
                    if (this.overallCheck(board, ydespos, xdespos)) {
                        return false;
                    }

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

    public void CalculateAllPossibleMoves(ChessBoard board) {
        //up right
        if(!this.availableDes.isEmpty()){
        this.availableDes.clear();
        }
        int counter = this.yPos - 1;

        for (int i = this.xPos + 1; i < 8; i++) {
            if (i==3 && counter==6){
                System.out.println(board.pieces.get(i).color);
                System.out.println(board.Squares[counter][i].ContainPiece);
            }
              if( counter >=0 && board.Squares[counter][i].ContainPiece){
                  if( board.getPiece(counter, i).color==this.color)
                break;
            }
            //   System.out.println("Counter : "+counter+" "+"i : "+i+" " );
            if (counter >= 0 && !board.Squares[counter][i].ContainPiece) {
                this.availableDes.add(new Points(counter, i));
                counter--;
            } else if (counter >= 0 && board.Squares[counter][i].ContainPiece) {
                //    System.out.println(board.Squares[counter][i].ContainPiece);
                if (board.getPiece(counter, i).color != this.color) {
                    this.availableDes.add(new Points(counter, i));
                    break;
                }
            } else {
                break;
            }
        }
        //up left 
        int counter1 = this.yPos - 1;
        for (int i = this.xPos - 1; i >= 0; i--) {
              
             if( counter1 >=0 && board.Squares[counter1][i].ContainPiece ){
                 if( board.getPiece(counter1, i).color==this.color)
                break;
            }
            if (counter1 >= 0 && !board.Squares[counter1][i].ContainPiece) {
                this.availableDes.add(new Points(counter1, i));
                counter1--;
            } else if (counter1 >= 0 && board.Squares[counter1][i].ContainPiece && board.getPiece(counter1, i).color != this.color) {
                this.availableDes.add(new Points(counter1, i));
                break;
            } else {
                break;
            }
        }
        //down right 
        int counter2 = this.yPos + 1;
        for (int i = this.xPos + 1; i < 8; i++) {
              if (i==3 && counter2==6){
                System.out.println("x");
            }
             if( counter2<8 && board.Squares[counter2][i].ContainPiece ){
                 if( board.getPiece(counter2, i).color==this.color)
                break;
            }
            if (counter2 < 8 && !board.Squares[counter2][i].ContainPiece) {
                this.availableDes.add(new Points(counter2, i));
                counter2++;
            } else if (counter2 < 8 && board.Squares[counter2][i].ContainPiece && board.getPiece(counter2, i).color != this.color) {
                this.availableDes.add(new Points(counter2, i));
                break;
            } else {
                break;
            }
        }
        //down left
        int counter3 = this.yPos + 1;
        for (int i = this.xPos - 1; i >= 0; i--) {
             
            if( counter3 <8 && board.Squares[counter3][i].ContainPiece){
                if( board.getPiece(counter3, i).color==this.color)
                break;
            }
            if (counter3 < 8 && !board.Squares[counter3][i].ContainPiece) {
                this.availableDes.add(new Points(counter3, i));
                counter3++;
            } else if (counter3 < 8 && board.Squares[counter3][i].ContainPiece && board.getPiece(counter3, i).color != this.color) {
                this.availableDes.add(new Points(counter3, i));
                break;
            } else {
                break;
            }
        }

    }
}
