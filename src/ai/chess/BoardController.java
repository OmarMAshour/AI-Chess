package ai.chess;

import Pieces.*;
import java.util.*;
import ai.chess.*;
import java.io.IOException;
import static ai.chess.AIChess.*;
import Pieces.*;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import static java.lang.Integer.max;
import static java.lang.Integer.min;

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

    public static int getBoardValue(ChessBoard board) {
        PieceColor AIPieceColor = PieceColor.Black;
        PieceColor PlayerPieceColor = PieceColor.White;
        if (!isPlayerWhite) {
            AIPieceColor = PieceColor.White;
            PlayerPieceColor = PieceColor.Black;
        }
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
            return getBoardValue(board);
        }
        int Alpha = Integer.MIN_VALUE;
        int Beta = Integer.MAX_VALUE;
        int Value = Integer.MIN_VALUE;
        //calculate all possible moves
        for (int i = 0; i < board.pieces.size(); i++) {
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

                } else if (!turn && board.pieces.get(i).color == PlayerPieceColor) {
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

    public ChessBoard BoardToDraw(ChessBoard board) throws Exception {
        int value = Algorithm(board, true, 0);
        for (int i = 0; i < this.BoardArrayList.size(); i++) {
            if (value == BoardArrayList.get(i).Value) {
                System.out.println(value);
                ChessBoard tobedrawn = BoardArrayList.get(i).board;
                BoardArrayList.clear();
                return tobedrawn;
            }
        }
        return null;
    }

    public ArrayList<BoardAndValueCollector> boardsCollected = new ArrayList<>();

    final int MAX = 10000;
    final int MIN = -10000;

// Returns optimal value for current player (Initially called
// for root and maximizer)
    int minimax(int depth, boolean maximizingPlayer, ChessBoard board, int alpha, int beta) throws Exception {
        // Terminating condition. i.e leaf node is reached
        if (depth == 2) {
            BoardAndValueCollector b = new BoardAndValueCollector(board);
            boardsCollected.add(b);
            return b.Value;
        }

        if (maximizingPlayer) {
            int best = MIN;

            // Recur for left and right children
            for (int i = 0; i < board.pieces.size(); i++) {
                if (board.pieces.get(i).color != AIPieceColor) {
                    continue;
                }
                board.pieces.get(i).CalculateAllPossibleMoves(board);
                for (Points point : board.pieces.get(i).availableDes) {
                    ChessBoard newBoard = board.copyBoard();

                    System.out.println("The index is  =  " + i);
                    newBoard.pieces.get(i).move(point.xPos, point.yPos, newBoard);
                    int val = minimax(depth + 1, false, newBoard, alpha, beta);
                    best = max(best, val);
                    alpha = max(alpha, best);

                    // Alpha Beta Pruning
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return best;
        } else {
            int best = MAX;

            // Recur for left and right children
            for (int i = 0; i < board.pieces.size(); i++) {
                if (board.pieces.get(i).color == AIPieceColor) {
                    continue;
                }
                board.pieces.get(i).CalculateAllPossibleMoves(board);
                for (Points point : board.pieces.get(i).availableDes) {
                    ChessBoard newBoard = board.copyBoard();
                    newBoard.pieces.get(i).move(point.xPos, point.yPos, newBoard);
                    int val = minimax(depth + 1, true, newBoard, alpha, beta);
                    best = min(best, val);
                    beta = min(beta, best);

                    // Alpha Beta Pruning
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return best;

        }
    }

    public void nextStepBoard(ChessBoard board) throws Exception {
        int valueReturned = minimax(0, true, board, MIN, MAX);
        for (BoardAndValueCollector bc : boardsCollected) {
            if (bc.Value == valueReturned) {
//                singleBoardPanel.getChessBoard().pieces.clear();
//                for(int i=0;i< bc.board.pieces.size();i++){
////                singleBoardPanel.getChessBoard().pieces.clear();
//                    singleBoardPanel.getChessBoard().pieces.add(bc.board.pieces.get(i));
//                }
                singleBoardPanel.setChessBoard(bc.board);
            }
        }

    }
}
