package SudokuSolver;

import javax.swing.*;
import java.awt.*;

public class SudokuSolver {
    public static JFrame frame = new JFrame("Sudoku Solver");
    static JPanel menu = new Menu();
    static JPanel game = new Game();
    static JPanel create = new Create();

    public static void main(String[] args) {
        initialize();
        frame.add(menu);
    }

    public static void initialize(){
        frame.setIconImage(new ImageIcon("./res/icon.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(1, 1));
        frame.setVisible(true);
    }

    public static void setScene(JPanel oldscene, JPanel newscene){
        frame.remove(oldscene);
        frame.add(newscene);
        frame.revalidate();
        frame.repaint();
    }
    public static JPanel getGame(){
        return game;
    }
    public static JPanel getMenu(){
        return menu;
    }
    public static JPanel getCreate(){
        return create;
    }
}
