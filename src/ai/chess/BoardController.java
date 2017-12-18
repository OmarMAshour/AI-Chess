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

        //Get the difference points of pieces
        for (Piece piece : board.pieces) {
            if (AIPieceColor == piece.color) {
                value += piece.value;
            } else if (AIPieceColor != piece.color) {
                value -= piece.value;
            }

            //Add half the points of the piecses that any AI piece can kill
            // and minus half the points of the piecses that any player piece can kill
            for (Piece tempPiece : board.pieces) {

                for (Points p : piece.availableDes) {

                    if (p.xPos == tempPiece.xPos && p.yPos == tempPiece.yPos && tempPiece.color != piece.color) {
                        if (piece.color == AIPieceColor) {
                            value += tempPiece.value / 2;

                        } else if (piece.color != AIPieceColor) {
                            value -= tempPiece.value / 2;
                        }
                    }
                }
            }

            //In case the king is checked will return suitable value based upon its AI or Player 
            if (piece.halelmalekfe5atar(board) && piece.color == AIPieceColor && !PlayerKingFirstCheck) {
                value += 200;
                PlayerKingFirstCheck = true;
            } else if (piece.halelmalekfe5atar(board) && piece.color != AIPieceColor && !AIKingFirstCheck) {
                value -= 200;
                AIKingFirstCheck = true;
            }

        }
        return value;
    }
    public int Algorithm(ChessBoard board, boolean turn, int depth) throws Exception {
        if (depth == 1) {
            return this.getBoardValue(board);
        }
        int Alpha = Integer.MIN_VALUE;
        int Beta = Integer.MAX_VALUE;
        int Value = Integer.MIN_VALUE;
        //calculate all possible moves
        for (int i=0;i<board.pieces.size();i++){
            board.pieces.get(i).CalculateAllPossibleMoves(board);
        }
        for (int i = 0; i < board.pieces.size(); i++) {
            for (int j = 0; j < board.pieces.get(i).availableDes.size(); j++) {
                ChessBoard TmpBoard = board.copyBoard();
                if (turn && board.pieces.get(i).color == AIPieceColor) {
                    if (TmpBoard.pieces.get(i).move(board.pieces.get(i).availableDes.get(j).xPos, board.pieces.get(i).availableDes.get(j).yPos, TmpBoard)) {
                        Value = this.Algorithm(TmpBoard, !turn, depth + 1);
                        if (depth == 0) {
                            this.BoardArrayList.add(new BoardAndValueCollector(TmpBoard, Value));
                        }
                       
                            if (Value > Beta) {
                                return Value;
                            } else {
                                Alpha = Value;
                            }
                        } 

                    }
                else if (!turn && board.pieces.get(i).color == PlayerPieceColor){
                     if (TmpBoard.pieces.get(i).move(board.pieces.get(i).availableDes.get(j).xPos, board.pieces.get(i).availableDes.get(j).yPos, TmpBoard)) {
                        Value = this.Algorithm(TmpBoard, turn, depth + 1);
                        if (depth == 0) {
                            this.BoardArrayList.add(new BoardAndValueCollector(TmpBoard, Value));
                        }
                       
                            if (Value < Alpha) {
                                return Value;
                            } else {
                                Beta = Value;
                            }
                        }

                    }
                }
            }
        return Value;
    }
    public ChessBoard BoardToDraw (ChessBoard board) throws Exception{
        int value = Algorithm (board,true,0);
        for (int i=0;i<this.BoardArrayList.size();i++){
            if(value == BoardArrayList.get(i).Value){
                System.out.println(value);
                ChessBoard tobedrawn = BoardArrayList.get(i).board;
                BoardArrayList.clear();
                return tobedrawn;
            }
        }
        return null;
    }

}
