package SudokuSolver.GUI;

import javax.swing.*;

public class Alert {
    static JOptionPane optionPane = new JOptionPane();

    public static void message(String title, String message) {
        optionPane.showMessageDialog(SudokuSolver.SudokuSolver.frame, message, title, optionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirm(String message) {
        int result = optionPane.showConfirmDialog(SudokuSolver.SudokuSolver.frame, message, "Confirmation required", optionPane.YES_NO_OPTION);
        if(result == optionPane.YES_OPTION){
            return true;
        } else {
            return false;
        }
    }
}
