package SudokuSolver.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class GButton extends JButton {
    Color c1;
    Color c2;

    public GButton(String text, Color c1, Color c2){
        super(text);
        this.c1 = c1;
        this.c2 = c2;
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        GradientPaint p;
        p = new GradientPaint(0, 0, c1, 0, getHeight(), c2);

        Paint oldPaint = g2.getPaint();
        g2.setPaint(p);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(oldPaint);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        super.paintComponent(g);

    }

    public void mousePressed(MouseEvent e) {
        GradientPaint p;
        p = new GradientPaint(0, 0, c2, 0, getHeight(), c1);
        Graphics g = getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        Paint oldPaint = g2.getPaint();
        g2.setPaint(p);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(oldPaint);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        super.repaint();
    }
}
