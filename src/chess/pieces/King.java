package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
    
    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p!= null && p instanceof Rook && p.getColor()==this.getColor() && p.getMoveCount()==0;
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

        // testando castling
        if(getMoveCount()==0 && !chessMatch.getCheck()){
            // roque pequeno
            Position posR1 = new Position(position.getRow(), position.getColumn() + 3);
            if(testRookCastling(posR1)){
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                if(getBoard().piece(p1)==null && getBoard().piece(p2)==null){
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }

            // roque grande
            Position posR2 = new Position(position.getRow(), position.getColumn() - 4);
            if(testRookCastling(posR2)){
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                if(getBoard().piece(p1)==null && getBoard().piece(p2)==null && getBoard().piece(p3)==null){
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }

        return mat;
    }

    public String toString(){
        return "K";
    }

}
