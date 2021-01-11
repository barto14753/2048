package agh.cs.lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener {

    MyPanel panel;

    public MyFrame() {
        super("2048");
        this.panel = new MyPanel();



        this.add(panel);
        this.addKeyListener(this);
        this.setVisible(true);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(new Dimension(500,500));
        this.setResizable(false);
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            this.panel.makeMove(Direction.LEFT);
        }
        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            this.panel.makeMove(Direction.RIGHT);
        }
        else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            this.panel.makeMove(Direction.UP);
        }
        else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            this.panel.makeMove(Direction.DOWN);
        }
        else if (key == KeyEvent.VK_SPACE) {
            this.panel.reset();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}