import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    private static void printBoard(char board[][]){
        for(int i = 0; i <= 3; i++){
            if(i == 0) System.out.print("* ");
            else System.out.print(i-1+" ");
        }
        System.out.println();
        for(int i = 0; i < 3; i++){
            System.out.print(i+ " ");
            for(int j = 0; j < 3; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // create a board of size 3*3 and fill the board with -
        char board[][] = new char[3][3];
        for(int i = 0; i < 3; i++){
            board[i][0] = '-';
            board[i][1] = '-';
            board[i][2] = '-';
        }
        // take players input
        System.out.println("Welcome to Arena! Proceed with your names: ");
        System.out.print("Player One's name: "); String player1 = sc.next();
        System.out.print("Player Two's name: "); String player2 = sc.next();
        printInstructions();
        System.out.print("Proceed in the game? Type 'Yes' or 'No': "); String flag = sc.next();
        if(flag.equals("No") || flag.equals("no")) {
            System.out.println("Hope to see you soon! :(");
            return;
        }
        System.out.println("Here are your symbols:");
        System.out.println(player1+"'s symbol: o");
        System.out.println(player2+"'s symbol: x");
        System.out.println("This is how your initial board looks like. Go ahead with your moves. Best of luck!");
        printBoard(board);
        boolean player1Turn = true;

        while(true){
            if(player1Turn){
                System.out.println(player1+" Please enter row and column: ");
                System.out.print("Row: "); int row = sc.nextInt();
                System.out.print("Col: "); int col = sc.nextInt();
                boolean vacant = checkValid(row, col, board);
                if(!vacant){
                    System.out.println("Choose a valid space!");
                    continue;
                }
                board[row][col] = 'o';
                player1Turn = false;
                boolean result = checkResult(board, row, col, 'o');
                if(result){
                    System.out.println("====== "+player1+ " has won. Here is the final board ======");
                    printBoard(board);
                    break;
                }
                printBoard(board);
            } else {
                System.out.println(player2+" Please enter row and column: ");
                System.out.print("Row: "); int row = sc.nextInt();
                System.out.print("Col: "); int col = sc.nextInt();
                boolean vacant = checkValid(row, col, board);
                if(!vacant){
                    System.out.println("Choose a valid space!");
                    continue;
                }
                board[row][col] = 'x';
                player1Turn = true;
                boolean result = checkResult(board, row, col, 'x');
                if(result){
                    System.out.println("====== "+player2+ " has won. Here is the final board ======");
                    printBoard(board);
                    break;
                }
                printBoard(board);
            }
            boolean notDraw = checkDraw(board);
            if(!notDraw){
                System.out.println("The match has been draw! Here is the final board: ======");
                printBoard(board);
                break;
            }
        }
        System.out.println("====== Happy gaming :) ======");
    }

    private static boolean checkDraw(char[][] board) {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == '-') return true;
            }
        }
        return false;
    }

    private static boolean checkResult(char[][] board, int row, int col, char ch) {
        boolean res = true;
        boolean checkLeftDiag = leftDiag(board, row, col, ch);
        if(checkLeftDiag){
            res = true;
        } else {
            res = false;
        }
        boolean checkRightDiag = rightDiag(board, row, col, ch);
        if(checkRightDiag){
            res = true;
        } else {
            res = false;
        }
        boolean checkVertical = vertical(board, row, col, ch);
        if(checkVertical){
            res = true;
        } else {
            res = false;
        }
        boolean checkHorizontal = horizontal(board, row, col, ch);
        if(checkHorizontal){
            res = true;
        } else {
            res = false;
        }

        return res;
    }

    private static boolean horizontal(char[][] board, int row, int col, char ch) {
        int i = row, j = col;
        while(j >= 0){
            if(board[i][j] != ch){
                return false;
            }
            j--;
        }
        i = row; j = col;
        while(j < 3){
            if(board[i][j] != ch){
                return false;
            }
            j++;
        }
        return true;
    }

    private static boolean vertical(char[][] board, int row, int col, char ch) {
        int i = row, j = col;
        while(i >= 0){
            if(board[i][j] != ch){
                return false;
            }
            i--;
        }
        i = row; j = col;
        while(i < 3){
            if(board[i][j] != ch){
                return false;
            }
            i++;
        }
        return true;
    }

    private static boolean rightDiag(char[][] board, int row, int col, char ch) {
        int i = row, j = col;
        while(i >= 0 && j >= 0){
            if(board[i][j] != ch){
                return false;
            }
            i--; j--;
        }
        i = row; j = col;
        while(i < 3 && j < 3){
            if(board[i][j] != ch){
                return false;
            }
            i++; j++;
        }
        return true;
    }

    private static boolean leftDiag(char[][] board, int row, int col, char ch) {
        int i = row, j = col;
        while(i >= 0 && j < 3){
            if(board[i][j] != ch){
                return false;
            }
            i--; j++;
        }
        i = row; j = col;
        while(i < 3 && j >= 0){
            if(board[i][j] != ch){
                return false;
            }
            i++; j--;
        }
        return true;
    }

    private static boolean checkValid(int row, int col, char[][] board) {
        if(row < 0 || col < 0 || row >= 3 || col >= 3) return false;
        if(board[row][col] == '-'){
            return true;
        }
        return false;
    }

    private static void printInstructions() {
        System.out.println("Before you go head to head, here are some instructions: ");
        System.out.println("Rule 1: You need to choose the corresponding row and column number of cell you want to fill.");
        System.out.println("Rule 2: That's it! We hope you know rest of the rules of playing TicTacToe.");
        System.out.println("===========================================================================");
    }
}