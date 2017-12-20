package ai.chess;

import Pieces.*;
import java.util.*;
import ai.chess.*;
import java.io.IOException;
import static ai.chess.AIChess.isPlayerWhite;
import Pieces.*;

public class BoardController {

    public ArrayList<BoardAndValueCollector> BoardArrayList;
    PieceColor AIPieceColor = PieceColor.Black;
    PieceColor PlayerPieceColor = PieceColor.White;
    ArrayList<Points> pieceAvailableDes = new ArrayList();

    public BoardController() {
        BoardArrayList = new ArrayList();
        if (!isPlayerWhite) {
            AIPieceColor = PieceColor.White;
            PlayerPieceColor = PieceColor.Black;
        }
    }

    public int getBoardValue(ChessBoard board) {

        int value = 0;

        boolean AIKingFirstCheck = false;
        boolean PlayerKingFirstCheck = false;

        for (Piece piece : board.pieces) {

            //Get the difference points of pieces
            if (AIPieceColor == piece.color) {
                value += piece.value;
            } else if (AIPieceColor != piece.color) {
                value -= piece.value;
            }
            //Add half the points of the piecses that any AI piece can kill
            // and minus half the points of the piecses that any player piece can kill
            for (Piece tempPiece : board.pieces) {

                piece.CalculateAllPossibleMoves(board, pieceAvailableDes);

                if (pieceAvailableDes.contains(tempPiece.getCurrentPoint())) {
                    if (piece.color == AIPieceColor && piece.color != tempPiece.color) {
                        value += tempPiece.value / 2;
                    } else if (piece.color != AIPieceColor && piece.color != tempPiece.color) {
                        value -= tempPiece.value / 2;
                    }
                }

//                for (Points p : pieceAvailableDes) {
//                    if (p.xPos == tempPiece.xPos && p.yPos == tempPiece.yPos && tempPiece.color != piece.color) {
//                        if (piece.color == AIPieceColor) {
//                            value += tempPiece.value / 2;
//
//                        } else if (piece.color != AIPieceColor) {
//                            value -= tempPiece.value / 2;
//                        }
//                    }
//                }
            }
            pieceAvailableDes.clear();

            //In case the king is checked will return suitable value based upon its AI or Player 
            if (piece.halelmalekfe5atar(board, pieceAvailableDes) && piece.color == AIPieceColor && !PlayerKingFirstCheck) {
                value += 200;
                PlayerKingFirstCheck = true;
            } else if (piece.halelmalekfe5atar(board, pieceAvailableDes) && piece.color != AIPieceColor && !AIKingFirstCheck) {
                value -= 200;
                AIKingFirstCheck = true;
            }

        }

        return value;
    }

    public void reviseBoardSquares(ChessBoard board) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.Squares[i][j].ContainPiece = false;
            }
        }

        for (Piece piece : board.pieces) {
            board.Squares[piece.yPos][piece.xPos].ContainPiece = true;

        }

    }

    public int Algorithm(ChessBoard board, boolean turn, int depth) throws Exception {

        //reviseBoardSquares(board);
        if (depth == 2) {
            return this.getBoardValue(board);
        }
        int Alpha = Integer.MIN_VALUE;
        int Beta = Integer.MAX_VALUE;
        int Value = Integer.MIN_VALUE;

        for (int i = 0; i < board.pieces.size(); i++) {
            ArrayList<Points> tmpAvailableDes = new ArrayList();
            Stack<Piece> PlayedPieces = new Stack();
//            if (depth == 1 && i == 27) {
//                System.out.println("x");
//            }
            //calculate all possible moves for the current piece
            board.pieces.get(i).CalculateAllPossibleMoves(board, tmpAvailableDes);
            if (!tmpAvailableDes.isEmpty()) {//if there's available destinations
                Piece p = board.pieces.get(i);
                Piece p2 = board.cpyPiece(p);
                PlayedPieces.add(p2);
            }
            for (int j = 0; j < tmpAvailableDes.size(); j++) {

                Stack<Piece> DeadPieces = new Stack();

                if (turn && board.pieces.get(i).color == AIPieceColor) {
                    //ChessBoard TmpBoard = board.copyBoard();
                    if (board.Squares[tmpAvailableDes.get(j).yPos][tmpAvailableDes.get(j).xPos].ContainPiece
                            && board.getPiece(tmpAvailableDes.get(j).yPos, tmpAvailableDes.get(j).xPos).color != board.pieces.get(i).color) {

                        for (int k = 0; k < board.pieces.size(); k++) {
                            if (tmpAvailableDes.get(j).yPos == board.pieces.get(k).yPos
                                    && tmpAvailableDes.get(j).xPos == board.pieces.get(k).xPos) {
                                DeadPieces.push(board.getPiece(tmpAvailableDes.get(j).yPos, tmpAvailableDes.get(j).xPos));
                                break;
                            }
                        }

                    }
                    if (board.pieces.get(i).move(tmpAvailableDes.get(j).xPos, tmpAvailableDes.get(j).yPos, board)) {

                        Value = this.Algorithm(board, false, depth + 1);
                        if (!DeadPieces.isEmpty()) {
                            Piece p = DeadPieces.pop();
                            board.pieces.add(p);
                            board.pieces.get(i).yPos = PlayedPieces.peek().yPos;
                            board.pieces.get(i).xPos = PlayedPieces.peek().xPos;
                            board.Squares[PlayedPieces.peek().yPos][PlayedPieces.peek().xPos].ContainPiece = true;
                            continue;
                        }
                        if (depth != 0) {
                            board.Squares[PlayedPieces.peek().yPos][PlayedPieces.peek().xPos].ContainPiece = true;
                            board.Squares[tmpAvailableDes.get(j).yPos][tmpAvailableDes.get(j).xPos].ContainPiece = false;
                            board.pieces.get(i).yPos = PlayedPieces.peek().yPos;
                            board.pieces.get(i).xPos = PlayedPieces.peek().xPos;
                        }
                        if (depth == 0) {
                            this.BoardArrayList.add(new BoardAndValueCollector(board, Value));
                            board.Squares[PlayedPieces.peek().yPos][PlayedPieces.peek().xPos].ContainPiece = true;
                            board.Squares[tmpAvailableDes.get(j).yPos][tmpAvailableDes.get(j).xPos].ContainPiece = false;
                            board.pieces.get(i).yPos = PlayedPieces.peek().yPos;
                            board.pieces.get(i).xPos = PlayedPieces.peek().xPos;
                        }

                        if (Value > Beta) {
                            return Value;
                        } else {
                            Alpha = Value;
                        }
                    }

                } //karim turn
                else if (!turn && board.pieces.get(i).color == PlayerPieceColor) {
                    if (board.Squares[tmpAvailableDes.get(j).yPos][tmpAvailableDes.get(j).xPos].ContainPiece
                            && board.getPiece(tmpAvailableDes.get(j).yPos, tmpAvailableDes.get(j).xPos).color != board.pieces.get(i).color) {
                        for (int k = 0; k < board.pieces.size(); k++) {
                            if (tmpAvailableDes.get(j).yPos == board.pieces.get(k).yPos
                                    && tmpAvailableDes.get(j).xPos == board.pieces.get(k).xPos) {
                                DeadPieces.push(board.pieces.get(k));
                                break;
                            }
                        }
                    }

                    if (board.pieces.get(i).move(tmpAvailableDes.get(j).xPos, tmpAvailableDes.get(j).yPos, board)) {

                        Value = this.Algorithm(board, true, depth + 1);
                        if (!DeadPieces.isEmpty()) {
                            Piece p = DeadPieces.pop();
                            board.pieces.add(p);
                            Piece tmpP = PlayedPieces.peek();
                            board.pieces.get(i).yPos = tmpP.yPos;
                            board.pieces.get(i).xPos = tmpP.xPos;
                            board.Squares[tmpP.yPos][tmpP.xPos].ContainPiece = true;
                            continue;

                        }
                        if (depth != 0) {
                            board.Squares[PlayedPieces.peek().yPos][PlayedPieces.peek().xPos].ContainPiece = true;
                            board.Squares[tmpAvailableDes.get(j).yPos][tmpAvailableDes.get(j).xPos].ContainPiece = false;
                            board.pieces.get(i).yPos = PlayedPieces.peek().yPos;
                            board.pieces.get(i).xPos = PlayedPieces.peek().xPos;

                        }
                        if (depth == 0) {
                            this.BoardArrayList.add(new BoardAndValueCollector(board, Value));
                            board.Squares[PlayedPieces.peek().yPos][PlayedPieces.peek().xPos].ContainPiece = true;
                            board.Squares[tmpAvailableDes.get(j).yPos][tmpAvailableDes.get(j).xPos].ContainPiece = false;
                            board.pieces.get(i).yPos = PlayedPieces.peek().yPos;
                            board.pieces.get(i).xPos = PlayedPieces.peek().xPos;
                        }

                        if (Value < Alpha) {
                            return Value;
                        } else {
                            Beta = Value;
                        }
                    }
                }
            }

            if (!PlayedPieces.isEmpty()) {
                PlayedPieces.pop();
            }

        }

        return Value;
    }

    public ChessBoard BoardToDraw(ChessBoard board) throws Exception {
        int value = 0;
        System.out.println("Algorithm Started");
        value = Algorithm(board, true, 0);
        System.out.println("Algorithm Finished");
        for (int i = 0; i < this.BoardArrayList.size(); i++) {
            if (value == BoardArrayList.get(i).Value) {
                System.out.println(value);
                ChessBoard tobedrawn = BoardArrayList.get(i).board;
                BoardArrayList.clear();
                return tobedrawn;
            }
        }
        BoardArrayList.clear();
        return null;
    }

}
