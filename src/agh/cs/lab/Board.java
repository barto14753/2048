package agh.cs.lab;

import java.awt.*;
import java.util.Arrays;

public class Board
{
    private int[][] board;
    private final int width;
    private final int height;
    private int score = 0;
    private Direction lastMove = null;
    private Result result = Result.INPROGRESS;

    public Board(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.board = new int[this.width][this.height];

        this.fillRandomSquare();
        this.fillRandomSquare();

    }




    private boolean canMoveAgainVertical()
    {
        for (int j=0; j<this.width; j++)
        {
            int last=-1;
            for (int i=0; i<this.height; i++)
            {
                if (this.board[i][j] == last)
                {
                    return true;
                }
                else if (this.board[i][j] != 0)
                {
                    last = this.board[i][j];
                }
            }
        }
        return false;
    }

    private boolean canMoveAgainHorizontal()
    {
        for (int i=0; i<this.height; i++)
        {
            int last=-1;
            for (int j=0; j<this.width; j++)
            {
                if (this.board[i][j] == last)
                {
                    return true;
                }
                else if (this.board[i][j] != 0)
                {
                    last = this.board[i][j];
                }
            }
        }
        return false;
    }

    private Square getFreeSquare()
    {
        if (this.isFull())
        {
            return null;
        }
        else
        {
            Square square = Square.getRandomSquare(this.width, this.height);

            while (this.board[square.getX()][square.getY()] != 0)
            {

                square = Square.getRandomSquare(this.width, this.height);
            }

            return square;
        }

    }

    public void move(Direction direction)
    {
        if (this.result != Result.INPROGRESS)
        {
            return;
        }

        else if (direction == this.lastMove)
        {
            if (direction == Direction.UP)
            {
                if (!this.canMoveAgainVertical() && !this.canMoveUp()) return;
            }
            else if (direction == Direction.DOWN)
            {
                if (!this.canMoveAgainVertical() && !this.canMoveDown()) return;
            }
            else if (direction == Direction.LEFT)
            {
                if (!this.canMoveAgainHorizontal() && !this.canMoveLeft()) return;
            }
            else if (direction == Direction.RIGHT)
            {
                if (!this.canMoveAgainHorizontal() && !this.canMoveRight()) return;
            }
        }

        switch (direction)
        {
            case UP:
            {
                this.moveUp();
                break;
            }
            case DOWN:
            {
                this.moveDown();
                break;
            }
            case LEFT:
            {
                this.moveLeft();
                break;
            }
            case RIGHT:
            {
                this.moveRight();
                break;
            }
        }
        this.lastMove = direction;
        if (this.isWin())
        {
            this.result = Result.WIN;
        }
        else if (this.isLoss())
        {
            this.result = Result.LOSS;
        }

        this.fillRandomSquare();
    }

    private void moveUp()
    {
        for (int i=0; i<this.width; i++)
        {
            int[] col = new int[this.height];
            int k=0;

            for (int j=0; j<this.height; j++)
            {
                if (this.board[j][i] != 0)
                {
                    col[k] = this.board[j][i];
                    k++;
                }
            }

            col = this.computeArray(col);

            for (int j=0; j<this.height; j++)
            {
                this.board[j][i] = col[j];
            }



        }
    }

    private void moveDown()
    {
        for (int i=0; i<this.width; i++)
        {
            int[] col = new int[this.height];
            int k=0;

            for (int j=this.height-1; j>=0; j--)
            {
                if (this.board[j][i] != 0)
                {
                    col[k] = this.board[j][i];
                    k++;
                }
            }

            col = this.computeArray(col);

            for (int j=0; j<this.height; j++)
            {
                this.board[this.height-j-1][i] = col[j];
            }



        }
    }

    private void moveLeft()
    {
        for (int j=0; j<this.height; j++)
        {
            int[] row = new int[this.width];
            int k=0;

            for (int i=0; i<this.width; i++)
            {
                if (this.board[j][i] != 0)
                {
                    row[k] = this.board[j][i];
                    k++;
                }
            }

            row = this.computeArray(row);

            for (int i=0; i<this.width; i++)
            {
                this.board[j][i] = row[i];
            }

        }
    }

    private void moveRight()
    {
        for (int j=0; j<this.height; j++)
        {
            int[] row = new int[this.width];
            int k=0;

            for (int i=this.width-1; i>=0; i--)
            {
                if (this.board[j][i] != 0)
                {
                    row[k] = this.board[j][i];
                    k++;
                }
            }

            row = this.computeArray(row);

            for (int i=0; i<this.width; i++)
            {
                this.board[j][this.width-i-1] = row[i];
            }

        }
    }

    private int[] computeArray(int[] array)
    {
        int[] newArr = new int[array.length];
        int k = 0;
        for (int i=0; i<array.length-1; i++)
        {
            if (array[i] != 0)
            {
                if (array[i] == array[i+1])
                {
                    newArr[k] = 2* array[i];
                    array[i+1] = 0;
                    this.score += newArr[k];

                }
                else
                {
                    newArr[k] = array[i];
                    //array[i] = 0;
                }
                k++;
            }

        }
        newArr[array.length-1] = array[array.length-1];
        return newArr;
    }

    private boolean canMoveLeft()
    {
        for (int i=0; i<this.height; i++)
        {
            for (int j=1; j<this.width; j++)
            {
                if (this.board[i][j] != 0 && this.board[i][j-1] == 0)
                    return true;
            }
        }
        return false;
    }

    private boolean canMoveRight()
    {
        for (int i=0; i<this.height; i++)
        {
            for (int j=0; j<this.width-1; j++)
            {
                if (this.board[i][j] != 0 && this.board[i][j+1] == 0)
                    return true;
            }
        }
        return false;
    }

    private boolean canMoveUp()
    {
        for (int i=1; i<this.height; i++)
        {
            for (int j=0; j<this.width; j++)
            {
                if (this.board[i][j] != 0 && this.board[i-1][j] == 0)
                    return true;
            }
        }
        return false;
    }

    private boolean canMoveDown()
    {
        for (int i=0; i<this.height-1; i++)
        {
            for (int j=0; j<this.width; j++)
            {
                if (this.board[i][j] != 0 && this.board[i+1][j] == 0)
                    return true;
            }
        }
        return false;
    }

    private void fillRandomSquare()
    {
        Square square = this.getFreeSquare();
        if (square != null)
        {
            this.board[square.getX()][square.getY()] = 2;
        }
    }

    private boolean isWin()
    {
        for (int i=0; i<this.width; i++)
        {
            for (int j=0; j<this.height; j++)
            {
                if (this.board[i][j] >= 2048)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLoss()
    {
        if (this.isFull())
        {
            for (int i=1; i<this.height-1; i++)
            {
                for (int j=1; j<this.width-1; j++)
                {
                    if (this.squareHasSameNeighbour(i,j)) return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean squareHasSameNeighbour(int i, int j)
    {
        int num = this.board[i][j];

        if (this.board[i-1][j] == num) return true;
        else if (this.board[i+1][j] == num) return true;
        else if (this.board[i][j-1] == num) return true;
        else if (this.board[i][j+1] == num) return true;
        else return false;
    }

    private boolean isFull()
    {
        for (int i=0; i<this.height; i++)
        {
            for (int j=0; j<this.width; j++)
            {
                if (this.board[i][j] == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }



    @Override
    public String toString() {
        String result = "Score: " + this.score + "\n";

        for (int i=0; i<this.height; i++)
        {
            result += "|";
            for (int j=0; j<this.width; j++)
            {
                result += "  " + this.board[i][j] + "  |";
            }
            result += "\n";
        }
        return result;
    }

    public int getPos(int i, int j)
    {
        return this.board[i][j];
    }

    public void setPos(int i, int j, int val)
    {
        this.board[i][j] = val;
    }

    public Color getColor(int num)
    {
        if (num<4)
        {
            return new Color(246, 233, 195);
        }
        else if (num<8)
        {
            return new Color(248, 207, 88);
        }
         else if (num<16)
        {
            return new Color(252, 183, 0);
        }
          else if (num<32)
        {
            return new Color(248, 124, 0);
        }
           else if (num<64)
        {
            return new Color(255, 103, 0);
        }
            else if (num<128)
        {
            return new Color(255, 90, 0);
        }
             else if (num<256)
        {
            return new Color(253, 116, 76);
        }
             else if (num<512)
        {
            return new Color(161, 0, 0);
        }
             else if (num<1024)
        {
            return new Color(108, 0, 0);
        }
        else if (num<2048)
        {
            return new Color(8, 255, 0);
        }
        else
        {
            return new Color(68, 165, 0);
        }


    }

    public int getScore()
    {
        return this.score;
    }

    public Result getResult()
    {
        return this.result;
    }
}
