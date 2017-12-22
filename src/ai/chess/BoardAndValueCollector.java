package ai.chess;

import java.io.Serializable;


public class BoardAndValueCollector implements Serializable{
    public ChessBoard board;
    public int Value;

    public BoardAndValueCollector(ChessBoard board, int Value) {
        this.board = board;
        this.Value = Value;
    }
    
}
