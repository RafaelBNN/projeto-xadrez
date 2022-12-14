import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    protected static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc){
        try{
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }
        catch(RuntimeException e){
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8");
        }
    }
    
    public static void printBoard(ChessPiece[][] pieces){

        for(int i=0;i<pieces.length;i++){
            System.out.print((pieces.length-i) + " ");
            for(int j=0;j<pieces.length;j++){
                System.out.print(ANSI_BLACK_BACKGROUND);
                printPiece(pieces[i][j], false);
            }
            System.out.println(ANSI_RESET);
        }
        System.out.println("  a b c d e f g h");
        
    }
    
    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedPieces){
        
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(capturedPieces);
        System.out.println();
        String toMove = (chessMatch.getCurrentPlayer() == Color.BLACK) ? ANSI_PURPLE : ANSI_CYAN;
        System.out.print("Turn: " + chessMatch.getTurn() + " | ");
        if(!chessMatch.getCheckmate()){
            System.out.print("To move: " + toMove + chessMatch.getCurrentPlayer() + ANSI_RESET);
            if(chessMatch.getCheck()) System.out.println(" | " + ANSI_RED_BACKGROUND + ANSI_WHITE + "CHECK!" + ANSI_RESET);
        }
        else{
            System.out.println("CHECKMATE!");
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
        }
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves){

        for(int i=0;i<pieces.length;i++){
            System.out.print((pieces.length-i) + " ");
            for(int j=0;j<pieces.length;j++){
                System.out.print(ANSI_BLACK_BACKGROUND);
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println(ANSI_RESET);
        }
        System.out.println("  a b c d e f g h");
        
    }

    private static void printPiece(ChessPiece piece, boolean background){
        if(background){
            System.out.print(ANSI_GREEN_BACKGROUND);
        }
        if(piece==null){
            System.out.print("-");
        }
        else{
            if(piece.getColor()==Color.WHITE){
                System.out.print(ANSI_CYAN + piece);
            }
            else{
                System.out.print(ANSI_PURPLE + piece);
            }
        }
        System.out.print(" " + ANSI_RESET);
    }

    private static void printCapturedPieces(List<ChessPiece> captured){

        List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());

        System.out.println("\nCaptured pieces:");
        System.out.print("White: ");
        System.out.print(ANSI_PURPLE);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Black: ");
        System.out.print(ANSI_CYAN);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);

    }

}
