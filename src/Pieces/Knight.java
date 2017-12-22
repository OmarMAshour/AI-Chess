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

public class Knight extends Piece implements Serializable {

    public Knight(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/knightBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/knightWhite.png"));
        this.availableDes = new ArrayList<>();
        this.Name = "Knight";
        this.value = 6;
    }

    public void setImages() throws IOException {
        this.blackImage = ImageIO.read(new File("src/Images/knightBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/knightWhite.png"));
    }

    public void clearImages() {
        this.blackImage = null;
        this.whiteImage = null;
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) throws IOException {
        boolean b1 = (xdespos < 8 || xdespos >= 0);
        boolean b2 = (ydespos < 8 || ydespos >= 0);
        if (!(b1 && b2)) {
            return false;
        }
        if ((Math.abs(this.yPos - ydespos) == 2
                && Math.abs(this.xPos - xdespos) == 1)
                || (Math.abs(this.yPos - ydespos) == 1
                && Math.abs(this.xPos - xdespos) == 2)) {
            if (!board.Squares[ydespos][xdespos].ContainPiece) {
                if (this.overallCheck(board, ydespos, xdespos)) {
                    return false;
                }
                board.Squares[this.yPos][this.xPos].ContainPiece = false;
                board.Squares[ydespos][xdespos].ContainPiece = true;
                this.yPos = ydespos;
                this.xPos = xdespos;
                return true;
            } //kill
            else {
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
        //2 up 1 right
        if (this.xPos + 1 < 8 && this.yPos + 2 < 8 && !board.Squares[this.yPos + 2][this.xPos + 1].ContainPiece) {
            this.availableDes.add(new Points(yPos + 2, xPos + 1));
        } else if (this.xPos + 1 < 8 && this.yPos + 2 < 8) {
            if (board.Squares[this.yPos + 2][this.xPos + 1].ContainPiece && board.getPiece(this.yPos + 2, this.xPos + 1).color != this.color) {
                this.availableDes.add(new Points(this.yPos + 2, this.xPos + 1));
            }
        }
        //2 up 1 left
        if (this.xPos - 1 >= 0 && this.yPos + 2 < 8 && !board.Squares[this.yPos + 2][this.xPos - 1].ContainPiece) {
            this.availableDes.add(new Points(yPos + 2, xPos - 1));
        } else if (this.xPos - 1 >= 0 && this.yPos + 2 < 8) {
            if (board.Squares[this.yPos + 2][this.xPos - 1].ContainPiece && board.getPiece(this.yPos + 2, this.xPos - 1).color != this.color) {
                this.availableDes.add(new Points(this.yPos + 2, this.xPos - 1));
            }
        }
        //2 down 1 right
        if (this.xPos + 1 < 8 && this.yPos - 2 >= 0 && !board.Squares[this.yPos - 2][this.xPos + 1].ContainPiece) {
            this.availableDes.add(new Points(yPos - 2, xPos + 1));
        } else if (this.xPos + 1 < 8 && this.yPos - 2 >= 0) {
            if (board.Squares[this.yPos - 2][this.xPos + 1].ContainPiece && board.getPiece(this.yPos - 2, this.xPos + 1).color != this.color) {
                this.availableDes.add(new Points(this.yPos - 2, this.xPos + 1));
            }
        }
        //2 down 1 left
        if (this.xPos - 1 >= 0 && this.yPos - 2 >= 0 && !board.Squares[this.yPos - 2][this.xPos - 1].ContainPiece) {
            this.availableDes.add(new Points(yPos - 2, xPos - 1));
        } else if (this.xPos - 1 >= 0 && this.yPos - 2 >= 0) {
            if (board.Squares[this.yPos - 2][this.xPos - 1].ContainPiece && board.getPiece(this.yPos - 2, this.xPos - 1).color != this.color) {
                this.availableDes.add(new Points(this.yPos - 2, this.xPos - 1));
            }
        }
        //1 up 2 right
        if (this.xPos + 2 < 8 && this.yPos + 1 < 8 && !board.Squares[this.yPos + 1][this.xPos + 2].ContainPiece) {
            this.availableDes.add(new Points(yPos + 1, xPos + 2));
        } else if (this.xPos + 2 < 8 && this.yPos + 1 < 8) {
            if (board.Squares[this.yPos + 1][this.xPos + 2].ContainPiece && board.getPiece(this.yPos + 1, this.xPos + 2).color != this.color) {
                this.availableDes.add(new Points(this.yPos + 1, this.xPos + 2));
            }
        }

        //1 up 2 left
        if (this.xPos - 2 >= 0 && this.yPos + 1 < 8 && !board.Squares[this.yPos + 1][this.xPos - 2].ContainPiece) {
            this.availableDes.add(new Points(yPos + 1, xPos - 2));
        } else if (this.xPos - 2 >= 0 && this.yPos + 1 < 8) {
            if (board.Squares[this.yPos + 1][this.xPos - 2].ContainPiece && board.getPiece(this.yPos + 1, this.xPos - 2).color != this.color) {
                this.availableDes.add(new Points(this.yPos + 2, this.xPos - 1));
            }
        }

        //1 down 2 right
        if (this.xPos + 2 < 8 && this.yPos - 1 >= 0 && !board.Squares[this.yPos - 1][this.xPos + 2].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos + 2));
        } else if (this.xPos + 2 < 8 && this.yPos - 1 >= 0) {
            if (board.Squares[this.yPos - 1][this.xPos + 2].ContainPiece && board.getPiece(this.yPos - 1, this.xPos + 2).color != this.color) {
                this.availableDes.add(new Points(this.yPos - 1, this.xPos + 2));
            }
        }
        //1 down 2 left
        if (this.xPos - 2 >= 0 && this.yPos - 1 >= 0 && !board.Squares[this.yPos - 1][this.xPos - 2].ContainPiece) {
            this.availableDes.add(new Points(yPos - 1, xPos - 2));
        } else if (this.xPos - 2 >= 0 && this.yPos - 1 >= 0) {
            if (board.Squares[this.yPos - 1][this.xPos - 2].ContainPiece) {
                if (board.getPiece(this.yPos - 1, this.xPos - 2).color != this.color) {
                    this.availableDes.add(new Points(this.yPos - 2, this.xPos - 1));
                }
            }
        }
    }

}
