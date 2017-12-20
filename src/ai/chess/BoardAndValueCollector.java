package ai.chess;


public class BoardAndValueCollector {
    public ChessBoard board;
    public int Value;

    public BoardAndValueCollector(ChessBoard board, int Value) {
        this.board = board.copyBoard();
        this.Value = Value;
    }
    
}
