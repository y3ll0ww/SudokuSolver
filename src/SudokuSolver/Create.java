package SudokuSolver;

import SudokuSolver.Functions.Board;
import SudokuSolver.Functions.Generator;
import SudokuSolver.GUI.Alert;
import SudokuSolver.GUI.BoardContainer;
import SudokuSolver.GUI.GButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Create extends JPanel {
    static GridBagConstraints c = new GridBagConstraints();
    static int[][][] bo = new int[9][9][2];

    GButton quit = new GButton("Back to menu", Color.ORANGE, Color.ORANGE);
    GButton play = new GButton("Play", Color.WHITE, Color.GREEN);
    GButton clear = new GButton("Clear", Color.WHITE, new Color(208, 210, 242));
    static JLabel difficulty = new JLabel("Empty");

    public Create(){
        setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        //Introduce buttons
        quitBtn(quit);
        playBtn(play);
        clearBtn(clear);

        //First row
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 10;
        add(difficulty, c);
        c.gridx = 10;
        add(quit, c);
        difficulty.setHorizontalAlignment(JLabel.CENTER);
        setDifficultyLabel(9 * 9);

        //Sudoku board
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 20;
        add(new BoardContainer(), c);

        //Last row
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 10;
        add(clear, c);
        c.gridx = 10;
        add(play, c);
    }

    public static void makeEditable(){
        for(int y = 0; y < Board.board.length; y++){
            for(int x = 0; x < Board.board[y].length; x++){
                if(Board.board[y][x][1] != 7){
                    Board.board[y][x][1] = 6;
                }
            }
        }
    }

    static boolean validBoard(){
        for(int y = 0; y < Board.board.length; y++){
            for(int x = 0; x < Board.board[y].length; x++){
                if(Board.board[y][x][1] == 7){
                    return false;
                }
            }
        }
        return true;
    }

    static void saveBoard() {
        if(Board.isEmpty()){
            for(int y = 0; y < Board.board.length; y++) {
                for (int x = 0; x < Board.board[y].length; x++) {
                    bo[y][x][0] = Board.board[y][x][0];
                    bo[y][x][1] = Board.board[y][x][1];
                }
            }
        } else {
            bo = new int[9][9][2];
        }
    }
    public static void loadBoard(){
        for(int y = 0; y < Board.board.length; y++) {
            for (int x = 0; x < Board.board[y].length; x++) {
                Board.board[y][x][0] = bo[y][x][0];
                Board.board[y][x][1] = bo[y][x][1];
            }
        }
    }

    static void convertBoard(){
        for(int y = 0; y < Board.board.length; y++) {
            for (int x = 0; x < Board.board[y].length; x++) {
                if(Board.board[y][x][0] != 0){
                    Board.board[y][x][1] = 0;
                } else {
                    Board.board[y][x][1] = 5;
                }
            }
        }
    }

    public static void setDifficultyLabel(int empties){
        String dif = "<html><font color='BLACK'>Difficulty:  </font>";
        if(empties == 81){
            difficulty.setText(dif + "Empty</html>");
            difficulty.setForeground(Color.GRAY);
        } else if(empties < 31) {
            difficulty.setText(dif + "Easy</html>");
            difficulty.setForeground(Color.GREEN);
        } else if(empties < 41) {
            difficulty.setText(dif + "Medium</html>");
            difficulty.setForeground(Color.ORANGE);
        } else if(empties < 61) {
            difficulty.setText(dif + "Hard</html>");
            difficulty.setForeground(new Color(242, 92, 5));
        } else {
            difficulty.setText(dif + "Expert</html>");
            difficulty.setForeground(Color.RED);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
        revalidate();

        setDifficultyLabel(BoardContainer.getEmpties());
    }

    //Buttons
    public void quitBtn(JButton s){
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveBoard();
                SudokuSolver.setScene(SudokuSolver.getCreate(), SudokuSolver.getMenu());
            }
        });
    }
    public void playBtn(JButton s){
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(validBoard()){
                    saveBoard();
                    convertBoard();
                    SudokuSolver.setScene(SudokuSolver.getCreate(), SudokuSolver.getGame());
                } else {
                    Alert.message("Invalid board", "This is not a valid board, correct errors before clicking play.");
                }
            }
        });
    }
    public void clearBtn(JButton s){
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Alert.confirm("Are you sure want to clear the board?")){
                    Generator.generateEmpty();
                    BoardContainer.setEmpties(81);
                    makeEditable();
                }
            }
        });
    }
}
