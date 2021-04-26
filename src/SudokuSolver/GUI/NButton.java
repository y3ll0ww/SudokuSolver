package SudokuSolver.GUI;

import SudokuSolver.Functions.Board;
import SudokuSolver.Functions.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NButton extends JButton {
    int posX;
    int posY;
    boolean selected = false;

    public NButton(String text){
        super(text);
        super.addActionListener(a_listener);
        super.addKeyListener(k_listener);

    }
    public boolean isSelected(){
        return selected;
    }

    public void setPosX(int x){
        posX = x;
    }
    public void setPosY(int y){
        posY = y;
    }

    ActionListener a_listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(Board.board[posY][posX][1] > 4){ // Input: Clickable
                selected = true;
                if(Board.board[posY][posX][1] < 6){
                    if(SudokuSolver.GUI.Timer.getStartTime() == 0){
                        SudokuSolver.GUI.Timer.start();
                    } else if(SudokuSolver.GUI.Timer.isPaused()) {
                        SudokuSolver.GUI.Timer.go();
                    }
                }
            }
        }
    };

    final KeyListener k_listener = new KeyListener() {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            //SudokuSolver.Create: validate input
            if (Board.board[posY][posX][1] > 5) {
                if (!Board.valid(posY, posX, key - 48)) {
                    Board.board[posY][posX][1] = 7;
                } else {
                    Board.board[posY][posX][1] = 6;
                }
            }

            //Adding desired number
            if (selected) {
                if (key >= 48 && key < 58) { // Adding 1 to 9
                    Board.board[posY][posX][0] = key - 48;
                } else if (key == 8 || key == 127) { // Delete & backspace
                    Board.board[posY][posX][0] = 0;
                }
            }

            //SudokuSolver.Create: Set total empties
            if(Board.board[posY][posX][1] > 5) {
                int empties = 0;
                for (int y = 0; y < Board.board.length; y++) {
                    for (int x = 0; x < Board.board[y].length; x++) {
                        if (Board.board[y][x][0] == 0) {
                            empties++;
                        }
                    }
                }
                BoardContainer.setEmpties(empties);
            }

            //SudokuSolver.Game: Completing the board
            if (Board.board[posY][posX][1] < 5) {
                if (Board.getEmpty()[0] == 10) {
                    SudokuSolver.GUI.Timer.end();
                    Validator.validate();
                    String message = "You had " + Validator.getRight() + " right answers and " + Validator.getWrong() + " wrong answers.";
                    if (Validator.getUnfilled() > 0) {
                        message += "\nHowever, you had to fill " + Validator.getUnfilled() + " more spaces";
                    }
                    Alert.message("Result", message);
                }
            }
        }

        public void keyReleased(KeyEvent event) {
        }

        public void keyTyped(KeyEvent event) {
        }
    };

    // Style/Class (board[][][x]):
    // x = 0) Default: No functionality; Foreground: BLACK; Background: WHITE;
    // x = 1) Solving 1/2: No functionality; Foreground: RED; Background: LIGHT GRAY;
    // x = 2) Solving 2/2: No functionality; Foreground: RED; Background: WHITE;
    // x = 3) Solved 1/2: No functionality; Foreground: GREEN; Background: YELLOW;
    // x = 4) Solved 2/2: No functionality; Foreground: GREEN; Background: WHITE;
    // x = 5) Input 1/2 (SudokuSolver.Game): Clickable, KeyListener; Foreground: GRAY; Background: WHITE;
    // x = 6) Input 2/2 (SudokuSolver.Create): Clickable, KeyListener; Foreground: GRAY; Background: LIGHT GRAY;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
        revalidate();

        int attribute = Board.board[posY][posX][1];
        if (Board.board[posY][posX][0] == 0) {
            setText(" ");
            setBackground(Color.WHITE);
        } else {
            if (attribute == 1) {
                setForeground(Color.RED);
                setBackground(Color.LIGHT_GRAY);
            } else if (attribute == 2) {
                setBackground(Color.WHITE);
            } else if (attribute == 3) {
                setForeground(new Color(0, 216, 3));
                setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                setBackground(new Color(229, 242, 208));
            } else if (attribute == 4) {
                setForeground(new Color(0, 216, 3));
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
                setBackground(Color.WHITE);
            } else if (attribute == 5) {
                setForeground(Color.GRAY);
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
                setBackground(Color.WHITE);
            } else if (attribute == 6) {
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
            } else if (attribute == 7) {
                setForeground(Color.RED);
            } else {
                setForeground(Color.BLACK);
                setBackground(Color.WHITE);
            }
            setText(Integer.toString(Board.board[posY][posX][0]));
        }
    }
}
