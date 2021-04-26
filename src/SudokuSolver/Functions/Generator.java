package SudokuSolver.Functions;

import java.util.Random;

public class Generator {

    public static void generateEmpty(){
        int[][][] bo = new int[9][9][2];
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                bo[y][x][0] = 0;
                bo[y][x][1] = 0;
            }
        }
        Board.board = bo;
    }

    public static int[][][] generateRandom(int empties){
        int[][][] bo = new int[9][9][2];
        Random rd = new Random();
        int row = rd.nextInt(9);
        for(int i = 1; i < 10; i++){
            int ix = rd.nextInt(9);
            while(bo[row][ix][0] != 0){
                ix = rd.nextInt(9);
            }
            bo[0][rd.nextInt(9)][0] = i;
        }
        Board.board = bo;
        Board.solve(0);
        for(int j = 0; j < empties; j++){
            int y = rd.nextInt(9);
            int x = rd.nextInt(9);
            while(bo[y][x][0] == 0){
                y = rd.nextInt(9);
                x = rd.nextInt(9);
            }
            bo[y][x][0] = 0;
        }
        for(int y = 0; y < Board.board.length; y++){
            for(int x = 0; x < Board.board[y].length; x++){
                if(Board.board[y][x][0] == 0){
                    Board.board[y][x][1] = 5;
                } else {
                    Board.board[y][x][1] = 0;
                }
            }
        }
        return bo;
    }
}
