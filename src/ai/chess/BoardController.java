package ai.chess;

import Pieces.*;
import java.util.*;
import ai.chess.*;
import java.io.IOException;
import static ai.chess.AIChess.isPlayerWhite;
import Pieces.*;

public class BoardController {

    public BoardController() {
    }

    public int getBoardValue(ChessBoard board) {

        int value = 0;
        PieceColor AIPieceColor = PieceColor.Black;
        PieceColor PlayerPieceColor = PieceColor.White;
        
        boolean AIKingFirstCheck = false;
        boolean PlayerKingFirstCheck = false;
        
        if (!isPlayerWhite) {
            AIPieceColor = PieceColor.White;
            PlayerPieceColor = PieceColor.Black;
        }

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
            if(piece.halelmalekfe5atar(board) && piece.color==AIPieceColor && !PlayerKingFirstCheck){
                value+=200;
                PlayerKingFirstCheck=true;
            }else if(piece.halelmalekfe5atar(board) && piece.color!=AIPieceColor && !AIKingFirstCheck){
                value-=200;
                AIKingFirstCheck=true;
            }

        }
        return value;
    }
    
}
