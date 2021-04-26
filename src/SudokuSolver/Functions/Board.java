package SudokuSolver.Functions;

import SudokuSolver.GUI.Timer;

public class Board implements Runnable {
    public static int[][][] board = new int[9][9][2];

    public void run(){
        solve(10);
    }

    public static boolean valid(int y, int x, int num){
        //Check row
        for(int i = 0; i < board[y].length; i++){
            if(i != x){
                if(board[y][i][0] == num){
                    return false;
                }
            }
        }
        //Check column
        for(int j = 0; j < board.length; j++){
            if(j != y){
                if(board[j][x][0] == num){
                    return false;
                }
            }
        }
        //Check box
        int boxY = y / 3;
        int boxX = x / 3;
        for(int Y = boxY * 3; Y < boxY * 3 + 3; Y++){
            for(int X = boxX * 3; X < boxX * 3 + 3; X++){
                if(board[Y][X][0] == num){
                    return false;
                }
            }
        }
        return true;
    }
    public static void lock(){
        for(int y = 0; y < board.length; y++){
            for(int x = 0; x < board[y].length; x++){
                if(board[y][x][1] != 4){ // Right answer
                    board[y][x][1] = 0;
                }
            }
        }
    }

    public static int[] getEmpty(){
        int[] pos = new int[2];
        find:
        for(int y = 0; y < board.length; y++){
            for(int x = 0; x < board[y].length; x++){
                if(board[y][x][0] == 0){
                    pos[0] = y;
                    pos[1] = x;
                    break find;
                } else {
                    pos[0] = 10;
                }
            }
        }
        return pos;
    }

    public static boolean solve(int sleep) {
        int[] pos = getEmpty();
        if (pos[0] == 10) {
            if(Timer.getStartTime() > 0){
                Timer.end();
            }
            return true;
        } else {
            for (int solution = 1; solution < 10; solution++) {
                board[pos[0]][pos[1]][0] = solution;
                board[pos[0]][pos[1]][1] = 1;
                try {
                    Thread.sleep(sleep);
                    board[pos[0]][pos[1]][0] = 0;
                } catch(InterruptedException e) {
                }
                if (valid(pos[0], pos[1], solution)) {
                    board[pos[0]][pos[1]][0] = solution;
                    board[pos[0]][pos[1]][1] = 2;
                    if (solve(sleep)) {
                        try {
                            board[pos[0]][pos[1]][1] = 3;
                            if(sleep > 0){
                                Thread.sleep(50);
                            }
                            board[pos[0]][pos[1]][1] = 4;
                        } catch(InterruptedException e) { }
                        return true;
                    } else {
                        board[pos[0]][pos[1]][0] = 0;
                    }
                }
            }
            return false;
        }
    }
    public static void reset(){
        for(int y = 0; y < board.length; y ++){
            for(int x = 0; x < board[y].length; x++){
                if(board[y][x][1] != 0){
                    board[y][x][0] = 0;
                    board[y][x][1] = 5;
                }
            }
        }
    }

    public static boolean isEmpty(){
        for (int y = 0; y < Board.board.length; y++) {
            for (int x = 0; x < Board.board[y].length; x++) {
                if (Board.board[y][x][0] != 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
