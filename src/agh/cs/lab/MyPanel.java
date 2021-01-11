package agh.cs.lab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class MyPanel extends JPanel {
    int startX = 80;
    int startY = 110;
    int width = 4;
    int height = 4;
    int squareWidth = 80;
    int squareHeight = 80;
    int xGap = 35;
    int yGap = 40;
    Board board = new Board(this.width, this.height);


    JLabel score;
    JLabel resetInfo;


    public MyPanel() {
        setPreferredSize(new Dimension(400, 400));
        this.score = new JLabel("<html>Score:" + String.valueOf(this.board.getScore()) + "<br>Press SPACE to Restart</html>");
        this.resetInfo = new JLabel("<html>Press SPACE to Restart</html>");

        this.resetInfo.setLocation(0,0);

        this.score.setSize(new Dimension(100,100));
        this.resetInfo.setSize(new Dimension(200,100));



        this.add(this.score);
        //this.add(this.resetInfo);

        this.resetInfo.setLocation(100,200);


    }



    private void drawBoard(Graphics2D g2d)
    {
        for (int i=0; i<this.height; i++)
        {
            for (int j=0; j<this.width; j++)
            {
                this.drawSquare(g2d, i,j);
            }
        }
    }

    private void drawSquare(Graphics2D g2d, int i, int j)
    {
        int x = this.startX + this.squareWidth*j;
        int y = this.startY + this.squareHeight*i;
        g2d.drawRect(x,y,this.squareWidth, this.squareHeight);

        int points = this.board.getPos(i,j);

        if (points != 0)
        {
            g2d.setColor(this.board.getColor(points));
            g2d.fillRect(x,y,this.squareWidth,this.squareHeight);
            g2d.setColor(Color.BLACK);

            String str = String.valueOf(points);
            Font font = new Font("Serif", Font.PLAIN, 20);
            g2d.setFont(font);
            g2d.drawString(str, (float) (x + this.xGap - 4*Math.log10(points)),y+this.yGap);
        }
    }

    public void makeMove(Direction direction)
    {
        this.board.move(direction);
        Result result = this.board.getResult();

        if (result == Result.WIN)
        {
            this.score.setText("<html>Score:" + String.valueOf(this.board.getScore()) + "<br>Press SPACE to Restart <br> You won!</html>");
        }
        else if (result == Result.LOSS)
        {
            this.score.setText("<html>Score:" + String.valueOf(this.board.getScore()) + "<br>Press SPACE to Restart <br> You lost!</html>");
        }
        else
        {
            this.score.setText("<html>Score:" + String.valueOf(this.board.getScore()) + "<br>Press SPACE to Restart</html>");

        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.drawBoard(g2d);
    }

    public void reset()
    {
        this.board = new Board(this.width, this.height);
        this.repaint();
    }


}