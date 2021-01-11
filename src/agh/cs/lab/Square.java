package agh.cs.lab;

public class Square
{
    private int x;
    private int y;

    public Square(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public static Square getRandomSquare(int widthRange, int heightRange)
    {
        int x = (int) (Math.random() * widthRange);
        int y = (int) (Math.random() * heightRange);
        return new Square(x, y);
    }

    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
