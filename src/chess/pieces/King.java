package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return (p==null || p.getColor()!=this.getColor());
    }

    @Override
    public boolean[][] possibleMoves(){
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        // testando movimento para cima
        p.setValues(position.getRow()-1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)) mat[p.getRow()][p.getColumn()] = true;

        // testando movimento para baixo
        p.setValues(position.getRow()+1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)) mat[p.getRow()][p.getColumn()] = true;

        // testando movimento para a direita
        p.setValues(position.getRow(), position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)) mat[p.getRow()][p.getColumn()] = true;

        // testando movimento para a esquerda
        p.setValues(position.getRow(), position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)) mat[p.getRow()][p.getColumn()] = true;

        // testando movimento para a diagonal cima-esquera
        p.setValues(position.getRow()-1, position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)) mat[p.getRow()][p.getColumn()] = true;

        // testando movimento para a diagonal cima-direita
        p.setValues(position.getRow()-1, position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)) mat[p.getRow()][p.getColumn()] = true;

        // testando movimento para a diagonal baixo-esquerda
        p.setValues(position.getRow()+1, position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)) mat[p.getRow()][p.getColumn()] = true;

        // testando movimento para a diagonal baixo-direita
        p.setValues(position.getRow()+1, position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)) mat[p.getRow()][p.getColumn()] = true;

        return mat;
    }

    public String toString(){
        return "K";
    }

}
