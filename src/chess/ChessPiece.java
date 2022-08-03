package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{

    private Color color;
    private Integer moveCount;

    public ChessPiece(Board board, Color color){
        super(board);
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public int getMoveCount(){
        return this.moveCount;
    }

    public void increaseMoveCount(){
        moveCount++;
    }

    public void decreaseMoveCount(){
        moveCount--;
    }

    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOponnentPiece(Position position){
        return (this.getBoard().thereIsAPiece(position) && ((ChessPiece)(this.getBoard().piece(position))).getColor()!=this.getColor());
    }
    
}
