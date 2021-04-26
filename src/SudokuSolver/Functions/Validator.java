package SudokuSolver.Functions;

public class Validator {
    static int[][] validation = new int[9][9];
    static int right = 0;
    static int wrong = 0;
    static int unfilled = 0;

    public static int getRight(){
        return right;
    }
    public static int getWrong(){
        return wrong;
    }
    public static int getUnfilled(){
        return unfilled;
    }

    public static void validate(){
        right = 0;
        wrong = 0;
        unfilled = 0;

        for(int y = 0; y < Board.board.length; y++){
            for(int x = 0; x < Board.board[y].length; x++){
                if(Board.board[y][x][1] > 0){
                    if(Board.board[y][x][0] == validation[y][x] && Board.board[y][x][0] != 0){
                        right++;
                        Board.board[y][x][1] = 4;
                    } else {
                        if(Board.board[y][x][0] != 0){
                            wrong++;
                        } else {
                            unfilled++;
                        }
                        Board.board[y][x][0] = 0;
                    }
                }
            }
        }
    }

    public static void validateSingle(int y, int x){
        if(Board.board[y][x][0] == validation[y][x]){
            Board.board[y][x][1] = 4;
        } else {
            Board.board[y][x][0] = 0;
            Board.board[y][x][1] = 0;
        }
    }

    public static void setValidation(){
        copyBoard();
        Board.solve(0);
        resetBoard();
        for(int y = 0; y < Board.board.length; y++){
            for(int x = 0; x < Board.board[y].length; x++){
                if(Board.board[y][x][0] == 0){
                    Board.board[y][x][1] = 5; // Make clickable
                }
            }
        }
    }

    public static void copyBoard(){
        for(int y = 0; y < validation.length; y++){
            for(int x = 0; x < validation[y].length; x++){
                validation[y][x] = Board.board[y][x][0];
            }
        }
    }

    public static void resetBoard(){
        int[][] temp = new int[9][9];
        for(int y = 0; y < temp.length; y++){
            for(int x = 0; x < temp[y].length; x++){
                temp[y][x] = validation[y][x];
            }
        }
        copyBoard();
        for(int y = 0; y < Board.board.length; y++){
            for(int x = 0; x < Board.board[y].length; x++){
                Board.board[y][x][0] = temp[y][x];
            }
        }
    }

}
