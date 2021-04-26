package SudokuSolver.GUI;

import SudokuSolver.Functions.Board;

import javax.swing.*;
import java.awt.*;

public class Box extends JPanel {
    static SudokuSolver.GUI.NButton nums[] = new SudokuSolver.GUI.NButton[9];
    Font given = new Font("SansSerif", Font.BOLD, 30);

    int x;
    int y;

    Box(int x, int y){
        this.x = x;
        this.y = y;
        setLayout(new GridLayout(3, 3));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        int ix = 0;
        for(int v = (y * 3); v < (y * 3) + 3; v++) {
            for (int h = (x * 3); h < (x * 3) + 3; h++) {
                nums[ix] = new NButton(Integer.toString(Board.board[v][h][0]));
                nums[ix].setPosX(h);
                nums[ix].setPosY(v);
                if(Board.board[v][h][0] == 0){
                    nums[ix].setText(" ");
                }
                nums[ix].setOpaque(true);
                nums[ix].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                nums[ix].setHorizontalAlignment(SwingConstants.CENTER);
                nums[ix].setFont(given);
                add(nums[ix]);
                ix++;
            }
        }
    }
}
