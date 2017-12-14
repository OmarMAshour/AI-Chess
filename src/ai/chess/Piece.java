package ai.chess;

import java.math.*;
import ai.chess.*;
import java.awt.Image;
import java.awt.Point;
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
    ArrayList<Points> availableDes;

    //piece motion
    //change xpos w ypos if possible
    abstract public boolean move(int xdespos, int ydespos, ChessBoard board) throws Exception;

    public String toString() {
        return "Xpos " + this.xPos + "\nYpos " + this.yPos;
    }

/*    abstract public void CalculateAllPossibleMoves(ChessBoard board);

    public boolean halelmalekfe5atar(ChessBoard board) {

        int kingXpos = -1, kingYpos = -1;

        for (int i = 0; i < board.pieces.size(); i++) {
            if (board.pieces.get(i).priority == 10 && board.pieces.get(i).color == this.color) {
                kingXpos = board.pieces.get(i).xPos;
                kingYpos = board.pieces.get(i).yPos;
            }
        }

        for (int i = 0; i < board.pieces.size(); i++) {
            if (board.pieces.get(i).color != this.color) {
                board.pieces.get(i).CalculateAllPossibleMoves(board);
            }
        }

        Point tmpKingPos = new Point(kingYpos, kingXpos);
        for (int i = 0; i < board.pieces.size(); i++) {
            for (int j = 0; j < board.pieces.get(i).availableDes.size(); j++) {
                if (board.pieces.get(i).availableDes.get(j).xPos == kingXpos && board.pieces.get(i).availableDes.get(j).yPos == kingYpos) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean overallCheck(ChessBoard board, int ydespos, int xdespos) throws IOException {
        ChessBoard tmpBoard = new ChessBoard();
        board.copyBoard(tmpBoard);
        tmpBoard.Squares[this.yPos][this.xPos].ContainPiece = false;
        tmpBoard.Squares[ydespos][xdespos].ContainPiece = true;
        Piece tmpPiece = tmpBoard.getPiece(this.xPos, this.yPos);
        tmpPiece.yPos = ydespos;
        tmpPiece.xPos = xdespos;
        this.CalculateAllPossibleMoves(tmpBoard);
        boolean fe5atar = tmpPiece.halelmalekfe5atar(tmpBoard);
        if (fe5atar) {
            return true;
        }
        return false;
    }
*/
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
        this.availableDes = new ArrayList<>();
    }

/*    public void CalculateAllPossibleMoves(ChessBoard board) {
        //black move
        if (this.color == PieceColor.Black) {
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
        this.availableDes = new ArrayList<>();
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

class Bishop extends Piece {

    public Bishop(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.yPos = ypos;
        this.xPos = xpos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/bishopBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/bishopWhite.png"));
        this.availableDes = new ArrayList<>();
    }

    public boolean move(int xdespos, int ydespos, ChessBoard board) throws IOException, IOException, IOException {
        if (!(Math.abs(ydespos - this.yPos) == Math.abs(xdespos - this.xPos))) {
            return false;
        }

//        boolean fe5atar = this.halelmalekfe5atar(board);
//        if (fe5atar) {
//            board.clearAllAvailableMoves();
//            return false;
//        }
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

/*    public void CalculateAllPossibleMoves(ChessBoard board) {
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
}

class Queen extends Piece {

    public Queen(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/queenBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/queenWhite.png"));
        this.availableDes = new ArrayList<>();
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

class Knight extends Piece {

    public Knight(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/knightBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/knightWhite.png"));
        this.availableDes = new ArrayList<>();
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

class King extends Piece {

    public King(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/kingBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/kingWhite.png"));
        this.availableDes = new ArrayList<>();
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
