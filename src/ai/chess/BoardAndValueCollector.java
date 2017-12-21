package ai.chess;


public class BoardAndValueCollector {
    public ChessBoard board;
    public int Value;

    public BoardAndValueCollector(ChessBoard board) {
        this.board = board.copyBoard();
        this.Value = BoardController.getBoardValue(board);
    }
    
    public BoardAndValueCollector(ChessBoard board, int value) {
        this.board = board.copyBoard();
        this.Value = value;
    }

    
    
}
