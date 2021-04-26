package SudokuSolver;

import SudokuSolver.Functions.Generator;
import SudokuSolver.Functions.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Menu extends JPanel {
    JLabel img = new JLabel(new ImageIcon("./res/icon.png"));
    JButton solver = new JButton("Sudoku Solver");
    JButton create = new JButton("Create Sudoku");
    JLabel Github = new JLabel("Github");
    JLabel LinkedIn = new JLabel("LinkedIn");
    JLabel credits = new JLabel("<html><center>If you want to get in touch or check out other projects<br>Click the links above</center></html>", SwingConstants.CENTER);
    Font font = new Font("MonoFont", Font.PLAIN, 10);

    boolean createprogress;
    boolean solverprogress;

    public Menu(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.WHITE);
        credits.setFont(font);

        add(new JLabel("<html><br><br><br><br></html>"));
        add(img);
        add(solver);
        add(create);
        add(new JLabel("<html><br><br><br><br></html>"));
        add(Github);
        add(LinkedIn);
        add(credits);

        System.out.println(getComponentCount());
        //Center all items
        for(int i = 0; i < this.getComponentCount(); i++){
            JComponent com = (JComponent)this.getComponent(i);
            com.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        //Add button functionality
        sudosolverBtn(solver);
        createsudoBtn(create);
        setHyperLinks();
    }

    void setHyperLinks(){
        Github.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Github.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/y3ll0ww"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                Github.setForeground(new Color(215, 58, 73));
            }
            public void mouseExited(MouseEvent e) {
                Github.setForeground(Color.BLACK);
            }
        });
        LinkedIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        LinkedIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/jelle-van-geel-437656120/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            public void mouseEntered(MouseEvent e) {
                LinkedIn.setForeground(new Color(14, 118, 168));
            }
            public void mouseExited(MouseEvent e) {
                LinkedIn.setForeground(Color.BLACK);
            }
        });
    }

    public void sudosolverBtn(JButton s){
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!solverprogress){
                    Generator.generateRandom(40);
                    Validator.setValidation();
                    solverprogress = true;
                } else {
                    Game.loadBoard();
                }
                SudokuSolver.setScene(SudokuSolver.getMenu(), SudokuSolver.getGame());
            }
        });
    }
    public void createsudoBtn(JButton s){
        s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!createprogress){
                    Generator.generateEmpty();
                    createprogress = true;
                } else {
                    Create.loadBoard();
                }
                Create.makeEditable();
                SudokuSolver.setScene(SudokuSolver.getMenu(), SudokuSolver.getCreate());
            }
        });
    }
}
