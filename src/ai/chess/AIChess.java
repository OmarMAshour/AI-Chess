package ai.chess;

import java.io.IOException;

public class AIChess {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ChessBoard chessBoard = new ChessBoard();
        GameBoard gb = new GameBoard(chessBoard);
        gb.setVisible(true);
    }
    
}
