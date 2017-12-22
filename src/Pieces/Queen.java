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

public class Queen extends Piece implements Serializable {

    public Queen(int ypos, int xpos, PieceColor color, int priority) throws IOException {
        this.xPos = xpos;
        this.yPos = ypos;
        this.color = color;
        this.priority = priority;
        this.blackImage = ImageIO.read(new File("src/Images/queenBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/queenWhite.png"));
        this.availableDes = new ArrayList<>();
        this.Name = "Queen";
        this.value = 20;
    }

    public void setImages() throws IOException {
        this.blackImage = ImageIO.read(new File("src/Images/queenBlack.png"));
        this.whiteImage = ImageIO.read(new File("src/Images/queenWhite.png"));
    }

    public void clearImages() {
        this.blackImage = null;
        this.whiteImage = null;
    }

    public void CalculateAllPossibleMoves(ChessBoard board) {

        //right
        if(!this.availableDes.isEmpty()){
        this.availableDes.clear();
        }
        for (int i = this.xPos + 1; i < 8; i++) {
            if (!board.Squares[this.yPos][i].ContainPiece) {
                this.availableDes.add(new Points(this.yPos, i));
            } else if (board.Squares[this.yPos][i].ContainPiece && board.getPiece(this.yPos, i).color != this.color) {
                this.availableDes.add(new Points(this.yPos, i));
                break;
            } else {
                break;
            }
        }
        //left
        for (int i = this.xPos - 1; i >= 0; i--) {
            if (!board.Squares[this.yPos][i].ContainPiece) {
                this.availableDes.add(new Points(this.yPos, i));
            } else if (board.Squares[this.yPos][i].ContainPiece && board.getPiece(this.yPos, i).color != this.color) {
                this.availableDes.add(new Points(this.yPos, i));
                break;
            } else {
                break;
            }
        }
        //down
        for (int i = this.yPos + 1; i < 8; i++) {
            if (!board.Squares[i][this.xPos].ContainPiece) {
                this.availableDes.add(new Points(i, this.xPos));
            } else if (board.Squares[i][this.xPos].ContainPiece && board.getPiece(i, this.xPos).color != this.color) {
                this.availableDes.add(new Points(i, this.xPos));
                break;
            } else {
                break;
            }
        }
        for (int i = this.yPos - 1; i >= 0; i--) {
            if (!board.Squares[i][this.xPos].ContainPiece) {
                this.availableDes.add(new Points(i, this.xPos));
            } else if (board.Squares[i][this.xPos].ContainPiece && board.getPiece(i, this.xPos).color != this.color) {
                this.availableDes.add(new Points(i, this.xPos));
                break;
            } else {
                break;
            }
        }
        //up right

        int counter = this.yPos - 1;
        for (int i = this.xPos + 1; i < 8; i++) {
            if (counter >= 0 && !board.Squares[counter][i].ContainPiece) {
                this.availableDes.add(new Points(counter, i));
                counter--;
            } else if (counter >= 0 && board.Squares[counter][i].ContainPiece && board.getPiece(counter, i).color != this.color) {
                this.availableDes.add(new Points(counter, i));
                break;
            } else {
                break;
            }
        }
        //up left 
        int counter1 = this.yPos - 1;
        for (int i = this.xPos - 1; i >= 0; i--) {
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

    public boolean move(int xdespos, int ydespos, ChessBoard board) throws IOException {
        boolean b1 = (xdespos < 8 || xdespos >= 0);
        boolean b2 = (ydespos < 8 || ydespos >= 0);
        if (!(b1 && b2)) {
            return false;
        }
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
                        if (this.overallCheck(board, ydespos, xdespos)) {
                            return false;
                        }

                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    } //normal move
                    else if (!board.Squares[this.yPos][i].ContainPiece && i == xdespos) {
                        if (this.overallCheck(board, ydespos, xdespos)) {
                            return false;
                        }

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
                        if (this.overallCheck(board, ydespos, xdespos)) {
                            return false;
                        }

                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    }//normal move
                    else if (!board.Squares[this.yPos][i].ContainPiece && i == xdespos) {
                        if (this.overallCheck(board, ydespos, xdespos)) {
                            return false;
                        }

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
                        if (this.overallCheck(board, ydespos, xdespos)) {
                            return false;
                        }

                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    } //normal move
                    else if (!board.Squares[i][this.xPos].ContainPiece && i == ydespos) {
                        if (this.overallCheck(board, ydespos, xdespos)) {
                            return false;
                        }

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
                        if (this.overallCheck(board, ydespos, xdespos)) {
                            return false;
                        }

                        board.pieces.remove(board.getPiece(ydespos, xdespos));
                        board.Squares[this.yPos][this.xPos].ContainPiece = false;
                        this.yPos = ydespos;
                        this.xPos = xdespos;
                        return true;
                    } //normal move
                    else if (!board.Squares[i][this.xPos].ContainPiece && i == ydespos) {
                        if (this.overallCheck(board, ydespos, xdespos)) {
                            return false;
                        }

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
}
