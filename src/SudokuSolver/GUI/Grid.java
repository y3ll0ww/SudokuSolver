package SudokuSolver.GUI;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    static SudokuSolver.GUI.Box[] grid = new SudokuSolver.GUI.Box[9];

    public Grid(){
        setLayout(new GridLayout(3, 3));

        int n = 0;
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                grid[n] = new SudokuSolver.GUI.Box(x, y);
                add(grid[n]);
                n++;
            }
        }
    }
}
