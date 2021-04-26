package SudokuSolver;

import SudokuSolver.Functions.Board;
import SudokuSolver.Functions.Generator;
import SudokuSolver.Functions.Validator;
import SudokuSolver.GUI.Alert;
import SudokuSolver.GUI.BoardContainer;
import SudokuSolver.GUI.GButton;
import SudokuSolver.GUI.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Game extends JPanel {
    static GridBagConstraints c = new GridBagConstraints();
    boolean solved = false;

    GButton easy = new GButton("Easy", Color.WHITE, Color.GREEN);
    GButton medium = new GButton("Medium", Color.WHITE, Color.ORANGE);
    GButton hard = new GButton("Hard", Color.WHITE, new Color(242, 92, 5));
    GButton expert = new GButton("Expert", Color.WHITE, Color.RED);
    GButton solve = new GButton("Instant", Color.WHITE, new Color(168, 192, 206));
    GButton step = new GButton("Solve", Color.WHITE, new Color(168, 192, 206));
    GButton quit = new GButton("x", Color.RED, Color.RED);
    GButton validate = new GButton("Validate", Color.WHITE, new Color(208, 210, 242));
    GButton clear = new GButton("Clear", Color.WHITE, new Color(208, 210, 242));

    DateFormat dateFormat = new SimpleDateFormat("mm:ss.SSS");
    JLabel time = new JLabel("0.00s");

    static int[][][] bo = new int[9][9][2];

    Board tbo = new Board();
    Thread t;

    public Game(){
        setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        // Introduce buttons
        instantBtn(solve);
        solveBtn(step);
        quitBtn(quit);
        clearBtn(clear);
        genBtn(easy, medium, hard, expert);
        validateBtn(validate);

        //Step-, solve-, timer and back button
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        add(step, c);
        c.gridx = 4;
        add(solve, c);
        c.gridx = 8;
        add(time, c);
        c.gridx = 12;
        add(validate, c);
        c.gridx = 16;
        add(quit, c);

        time.setHorizontalAlignment(JLabel.CENTER);

        // Sudoku board
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 20;
        add(new BoardContainer(), c);

        // Buttons for generating new board
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 4;
        add(easy, c);
        c.gridx = 4;
        add(medium, c);
        c.gridx = 8;
        add(hard, c);
        c.gridx = 12;
        add(expert, c);
        c.gridx = 16;
        add(clear, c);
    }

    static void saveBoard(){
        for(int y = 0; y < Board.board.length; y++) {
            for (int x = 0; x < Board.board[y].length; x++) {
                bo[y][x][0] = Board.board[y][x][0];
                bo[y][x][1] = Board.board[y][x][1];
            }
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

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
        revalidate();

        char t = 's';
        if(Timer.track() < 60000) {
            t = 's';
            dateFormat = new SimpleDateFormat("ss.SSS");
        } else if(Timer.track() < 600000){
            t = 'm';
            dateFormat = new SimpleDateFormat("m:ss.SSS");
        } else {
            dateFormat = new SimpleDateFormat( "mm:ss.SSS");
        }
        time.setText(dateFormat.format(Timer.track()) + t);
    }
    

    public void instantBtn(JButton s){
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!solved){
                    solved = true;
                    Validator.validate();
                    Board.lock();
                    Timer.reset();
                    Timer.start();
                    Board.solve(0);
                }
            }
        });
    }
    public void solveBtn(JButton s){
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!solved){
                    Validator.validate();
                    Board.lock();
                    Timer.start();
                    solved = true;
                    t = new Thread(tbo);
                    t.start();
                }
            }
        });
    }
    public void genBtn(JButton easy, JButton medium, JButton hard, JButton expert){
        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(t != null && t.currentThread().isAlive()){
                    t.stop();
                }
                Timer.reset();
                Board.board = Generator.generateRandom(30);
                solved = false;
                Validator.setValidation();
                System.out.println("easy");
            }
        });
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(t != null && t.currentThread().isAlive()){
                    t.stop();
                }
                Timer.reset();
                Board.board = Generator.generateRandom(40);
                solved = false;
                Validator.setValidation();
                System.out.println("medium");
            }
        });
        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(t != null && t.currentThread().isAlive()){
                    t.stop();
                }
                Timer.reset();
                Board.board = Generator.generateRandom(60);
                solved = false;
                Validator.setValidation();
                System.out.println("hard");
            }
        });
        expert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(t != null && t.currentThread().isAlive()){
                    t.stop();
                }
                Timer.reset();
                Board.board = Generator.generateRandom(75);
                solved = false;
                Validator.setValidation();
                System.out.println("expert");
            }
        });
    }
    public void quitBtn(JButton s){
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(t != null){
                    t.stop();
                    solved = false;
                }
                saveBoard();
                Timer.reset();
                Board.reset();
                SudokuSolver.setScene(SudokuSolver.getGame(), SudokuSolver.getMenu());
            }
        });
    }
    public void clearBtn(JButton s){
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(t != null){
                    t.stop();
                }
                solved = false;
                Timer.reset();
                Board.reset();
            }
        });
    }
    public void validateBtn(JButton s){
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if((t == null || !t.isAlive()) && Timer.getStartTime() != 0){
                    Timer.end();
                    Timer.pause();
                    Validator.validate();
                    String message = "You have " + Validator.getRight() + " right answers and " + Validator.getWrong() + " wrong answers.";
                    if(Validator.getUnfilled() > 0){
                        message += "\nHowever, you have to fill " + Validator.getUnfilled() + " more spaces";
                    }
                    Alert.message("Result", message);
                }
            }
        });
    }

}
