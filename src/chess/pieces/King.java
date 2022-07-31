package chess.pieces;

import boardgame.Board;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    
    private ChessMatch chessMatch;

    public King(Board board, Color color) {
        super(board, color);
    }

    public ChessMatch getChessMatch() {
        return chessMatch;
    }

    public void setChessMatch(ChessMatch chessMatch) {
        this.chessMatch = chessMatch;
    }

    public String toString(){
        return "K";
    }

}
