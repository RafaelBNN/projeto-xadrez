package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    
    private Board board;
    private Integer turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkmate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch(){
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn(){
        return this.turn;
    }

    public Color getCurrentPlayer(){
        return this.currentPlayer;
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for(int i=0;i<board.getRows();i++){
            for(int j=0;j<board.getColumns();j++){
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);

        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source,target);
        this.nextTurn();
        return (ChessPiece)capturedPiece;
    }

    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);

        if(capturedPiece!=null){
            capturedPieces.add(capturedPiece);
            piecesOnTheBoard.remove(capturedPiece);
        }

        board.placePiece(p, target);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("Error in position: there is no piece in source position " + position);
        }
        if(((ChessPiece)board.piece(position)).getColor()!=this.getCurrentPlayer()){
            throw new ChessException("Error in movement: its the other player's turn");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("Error in movement: there are no possible moves for this piece");
        }
    }

    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMoves()[target.getRow()][target.getColumn()]){
            throw new ChessException("Error in movement: this move is not allowed");
        }
    }

    private void nextTurn(){
        currentPlayer = (this.getCurrentPlayer()==Color.WHITE) ? Color.BLACK : Color.WHITE;
        this.turn++;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup(){
        placeNewPiece('b', 6, new King(board, Color.WHITE));
        placeNewPiece('c', 3, new Rook(board, Color.BLACK));
        placeNewPiece('e', 4, new King(board, Color.BLACK));
    }


}
