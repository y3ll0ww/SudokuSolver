package SudokuSolver.GUI;

import javax.swing.*;
import java.awt.*;

public class BoardContainer extends JPanel {
    static int empties = 81;

    public BoardContainer(){
       // setSize(new Dimension(500, 500));
        setLayout(new GridLayout(1, 1));
        add(new SudokuSolver.GUI.Grid());
    }

    public static void setEmpties(int e){
        empties = e;
    }
    public static int getEmpties(){
        return empties;
    }

}
