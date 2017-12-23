package ai.chess;

import Pieces.*;
import java.util.*;
import ai.chess.*;
import static ai.chess.AIChess.isPlayerWhite;
import static ai.chess.AIChess.verboseStrings;
import java.io.Serializable;

public class BoardController implements Serializable {

    public ArrayList<BoardAndValueCollector> BoardArrayList;
    PieceColor AIPieceColor = PieceColor.Black;
    PieceColor PlayerPieceColor = PieceColor.White;
    private int CutOff=0;
    private int Nnodes=0;
    private int NPlusOneNodes=0;
    private int leafnode=0;
    public BoardController() {
        BoardArrayList = new ArrayList();
        if (!isPlayerWhite) {
            AIPieceColor = PieceColor.White;
            PlayerPieceColor = PieceColor.Black;
        }
    }
    public int GetNNodes(){
        return Nnodes;
    }
    public int GetNPluesOneNodes(){
        return NPlusOneNodes;
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
        if (depth == 2) {
            int val = this.getBoardValue(board);
            leafnode++;
            verboseStrings.add("Leaf Node Number : " + leafnode+", Utility Value : "+val);
            return val;
        }
        int Alpha = Integer.MIN_VALUE;
        int Beta = Integer.MAX_VALUE;
        int Value = Integer.MIN_VALUE;
        //calculate all possible moves
        for (int i = 0; i < board.pieces.size(); i++) {
            if (turn && board.pieces.get(i).color == AIPieceColor) {
                board.pieces.get(i).CalculateAllPossibleMoves(board);
            } else if (!turn && board.pieces.get(i).color == PlayerPieceColor) {
                board.pieces.get(i).CalculateAllPossibleMoves(board);
            }
        }
        int sizei = board.pieces.size();
        for (int i = 0; i < sizei; i++) {
            int sizej = board.pieces.get(i).availableDes.size();
            if(depth==0 ){
                Nnodes+=sizej;
            }
            else{
                NPlusOneNodes+=sizej;
            }
            for (int j = 0; j < sizej; j++) {
                //---------------------------------------------------------
                //AI turn
                if (turn && board.pieces.get(i).color == AIPieceColor) {
                    ChessBoard TmpBoard = board.copyBoard();
                    if (TmpBoard.pieces.get(i).move(board.pieces.get(i).availableDes.get(j).xPos, board.pieces.get(i).availableDes.get(j).yPos, TmpBoard)) {
                        Value = this.Algorithm(TmpBoard, !turn, depth + 1);
                        if (depth == 0) {
                            this.BoardArrayList.add(new BoardAndValueCollector(TmpBoard, Value));
                        }
                        if (Value > Beta) {
                            CutOff++;
                            verboseStrings.add("Cutoff Number : "+ CutOff);
                            return Value;
                        } else {
                            Alpha = Value;
                        }
                    }
                }
                //---------------------------------------------------------
                //human turn for AI 
                else if (!turn && board.pieces.get(i).color == PlayerPieceColor) {
                    ChessBoard TmpBoard = board.copyBoard();
                    if (TmpBoard.pieces.get(i).move(board.pieces.get(i).availableDes.get(j).xPos, board.pieces.get(i).availableDes.get(j).yPos, TmpBoard)) {
                        Value = this.Algorithm(TmpBoard, !turn, depth + 1);
                        if (depth == 0) {
                            this.BoardArrayList.add(new BoardAndValueCollector(TmpBoard, Value));
                        }
                        if (Value < Alpha) {
                            CutOff++;
                            verboseStrings.add("Cutoff Number : "+ CutOff);
                            return Value;
                        } else {
                            Beta = Value;
                        }
                    }
                }
                //---------------------------------------------------------
            }
        }
        return Value;
    }
    public ChessBoard BoardToDraw(ChessBoard board) throws Exception {
       leafnode=0;
        board.viewBoard();
        ChessBoard tobedrawn = null;
        ArrayList <BoardAndValueCollector> SameValues = new ArrayList ();
        verboseStrings.add("Algorithm Started");
        int value = Algorithm(board, true, 0);
        verboseStrings.add("Algorithm Ended");
        for (int i = 0; i < this.BoardArrayList.size(); i++) {
            if (value == BoardArrayList.get(i).Value) {
                SameValues.add(BoardArrayList.get(i));
            }
        }
        Random r = new Random();
        if(SameValues.size()-1>0){
       tobedrawn = SameValues.get(r.nextInt(SameValues.size()-1)).board;
        }
        else{
            tobedrawn = SameValues.get(0).board;
        }
       BoardArrayList.clear();
        return tobedrawn;
    }

}
