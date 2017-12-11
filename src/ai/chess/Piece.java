package ai.chess;

import java.math.*;
import ai.chess.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
//chess pieces model

enum PieceColor {
    Black, White
};

abstract public class Piece {

    public int yPos; //row
    public int xPos; //column
    public PieceColor color;
    public int priority;
    public Image blackImage;
    public Image whiteImage;
    //piece motion
    //change xpos w ypos if possible
    abstract public boolean move(int xdespos, int ydespos, ChessBoard board) throws Exception;
    public String toString() {
        return "Xpos " + this.xPos + "\nYpos " + this.yPos;
    }

}

class Pawn extends Piece {
    public boolean canMoveTwice;
    public Pawn(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.yPos = ypos;
        this.xPos = xpos;
        this.color = color;
        this.priority = priority;
        this.canMoveTwice = true;
        this.blackImage = ImageIO.read(new File("src/Images/pawnBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/pawnWhite.png"));
    }
    public boolean move(int xdespos, int ydespos, ChessBoard board) {
        //black move
        if (this.color == PieceColor.Black) {
            //first move (2 moves)
            if (Math.abs(ydespos - this.yPos) == 2
                    && ydespos > this.yPos
                    && this.xPos == xdespos
                    && this.canMoveTwice
                    && !board.Squares[this.yPos + 1][this.xPos].ContainPiece
                    && !board.Squares[this.yPos + 2][this.xPos].ContainPiece) {
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
                this.canMoveTwice = false;
                board.pieces.remove(board.getPiece(ydespos, xdespos));
                board.Squares[this.yPos][this.xPos].ContainPiece = false;
                this.yPos = ydespos;
                this.xPos = xdespos;
                return true;
            }

        } //white move
        else {
            //first move (2 moves)
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
        }
        return false;
    }
}
class Rook extends Piece {
    public Rook(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.yPos = ypos;
        this.xPos = xpos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/rookBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/rookWhite.png"));
    }
    public boolean move(int xdespos, int ydespos, ChessBoard board) {
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
                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    } //normal move
                    else if (!board.Squares[this.yPos][i].ContainPiece && i == xdespos) {
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        board.Squares[ydespos][xdespos].ContainPiece = true;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    }
                }
            } //left
            else {
                for (int i = this.xPos-1; i >= xdespos; i--) {
                    //lw fih 7aga fl seka
                    if (board.Squares[this.yPos][i].ContainPiece && i != xdespos) {
                        return false;
                    } //kill
                    else if (board.Squares[this.yPos][i].ContainPiece
                            && i == xdespos
                            && board.getPiece(this.yPos, i).color != this.color) {
                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    }//normal move
                    else if (!board.Squares[this.yPos][i].ContainPiece && i == xdespos) {
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
                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    } //normal move
                    else if (!board.Squares[i][this.xPos].ContainPiece && i == ydespos) {
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        board.Squares[ydespos][xdespos].ContainPiece = true;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    }
                }
            } //up
            else {
                for (int i = this.yPos- 1; i >= ydespos; i--) {
                    //lw fih 7aga fl seka
                    if (board.Squares[i][this.xPos].ContainPiece && i != ydespos) {
                        return false;
                    } //kill
                    else if (board.Squares[i][this.xPos].ContainPiece
                            && i == ydespos
                            && board.getPiece(i, this.xPos).color != this.color) {
                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    } //normal move
                    else if (!board.Squares[i][this.xPos].ContainPiece && i == ydespos) {
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
}

class Bishop extends Piece {

    public Bishop(int xpos, int ypos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/bishopBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/bishopWhite.png"));
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) {
        if (Math.abs(this.xPos - xdespos) / Math.abs(this.yPos - ydespos) == 1) {
            if (ydespos > this.yPos && xdespos > this.xPos) {
                int start = this.yPos;
                int end = ydespos;
                for (int counter = start; counter <= end; counter++) {
                    if (board.Squares[counter][counter].ContainPiece) {
                        return false;
                    }
                }
            } //up left
            else if (ydespos > this.yPos && xdespos < this.xPos) {
                int start = this.yPos;
                int end = ydespos;
                for (int i = start, negCounter = start; i <= end; i++, negCounter--) {
                    if (board.Squares[negCounter][i].ContainPiece) {
                        return false;
                    }
                }
            } //down right
            else if (ydespos < this.yPos && xdespos > this.xPos) {
                int start = this.xPos;
                int end = xdespos;
                for (int i = start, negCounter = start; i <= end; i++, negCounter--) {
                    if (board.Squares[i][negCounter].ContainPiece) {
                        return false;
                    }
                }

            } //down left
            else if (ydespos < this.yPos && xdespos < this.xPos) {
                int start = ydespos;
                int end = this.yPos;
                for (int counter = start; counter <= end; counter--) {
                    if (board.Squares[counter][counter].ContainPiece) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
}

class King extends Piece {

    public King(int xpos, int ypos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/kingBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/kingWhite.png"));
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) {
        if (Math.abs(ydespos - this.yPos) == 1 || Math.abs(xdespos - this.xPos) == 1 || (Math.abs(xdespos - this.xPos) == 1 && Math.abs(ydespos - this.yPos) == 1)) {
            if (!board.Squares[ydespos][xdespos].ContainPiece) {
                board.Squares[this.yPos][this.xPos].ContainPiece = false;
                this.yPos = ydespos;
                this.xPos = xdespos;
                board.Squares[ydespos][xdespos].ContainPiece = true;
                return true;
            }
        }
        return false;
    }

}

class Queen extends Piece {

    public Queen(int xpos, int ypos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/queenBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/queenWhite.png"));
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) {
        if (Math.abs(this.xPos - xdespos) / Math.abs(this.yPos - ydespos) == 1) {
            if (ydespos > this.yPos && xdespos > this.xPos) {
                int start = this.yPos;
                int end = ydespos;
                for (int counter = start; counter <= end; counter++) {
                    if (board.Squares[counter][counter].ContainPiece) {
                        return false;
                    }
                }
            } //up left
            else if (ydespos > this.yPos && xdespos < this.xPos) {
                int start = this.yPos;
                int end = ydespos;
                for (int i = start, negCounter = start; i <= end; i++, negCounter--) {
                    if (board.Squares[negCounter][i].ContainPiece) {
                        return false;
                    }
                }
            } //down right
            else if (ydespos < this.yPos && xdespos > this.xPos) {
                int start = this.xPos;
                int end = xdespos;
                for (int i = start, negCounter = start; i <= end; i++, negCounter--) {
                    if (board.Squares[i][negCounter].ContainPiece) {
                        return false;
                    }
                }

            } //down left
            else if (ydespos < this.yPos && xdespos < this.xPos) {
                int start = ydespos;
                int end = this.yPos;
                for (int counter = start; counter <= end; counter--) {
                    if (board.Squares[counter][counter].ContainPiece) {
                        return false;
                    }
                }
            }
        } else if (this.xPos - xdespos == 0) {
            if (ydespos > this.yPos) {
                for (int i = this.yPos; i <= ydespos; i++) {
                    if (board.Squares[this.xPos][i].ContainPiece) {
                        return false;
                    }
                }
            }
            if (ydespos < this.yPos) {
                for (int i = this.yPos; i >= ydespos; i--) {
                    if (board.Squares[this.xPos][i].ContainPiece) {
                        return false;
                    }
                }
            }
        } else if (this.yPos - ydespos == 0) {
            if (xdespos > this.xPos) {
                for (int i = this.xPos; i <= xdespos; i++) {
                    if (board.Squares[i][this.yPos].ContainPiece) {
                        return false;
                    }
                }
            }
            if (xdespos < this.xPos) {
                for (int i = this.xPos; i >= xdespos; i--) {
                    if (board.Squares[i][this.yPos].ContainPiece) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

}

class Knight extends Piece {

    public Knight(int xpos, int ypos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/knightBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/knightWhite.png"));
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) {
        if (Math.abs(this.yPos - ydespos) == 2 && Math.abs(this.xPos - xdespos) == 1) {
            if (this.yPos > ydespos) {
                if (board.Squares[this.yPos - 1][this.xPos].ContainPiece || board.Squares[this.yPos - 2][this.xPos].ContainPiece || board.Squares[ydespos][xdespos].ContainPiece) {
                    return false;
                }
            } else if (this.yPos < ydespos) {
                if (board.Squares[this.yPos + 1][this.xPos].ContainPiece || board.Squares[this.yPos + 2][this.xPos].ContainPiece || board.Squares[ydespos][xdespos].ContainPiece) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

}
