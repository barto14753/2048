package agh.cs.lab;


public enum Direction
{
    UP, RIGHT, DOWN, LEFT;

    Square toSquare(Direction direction)
    {
        switch (direction)
        {
            case UP -> {return new Square(0,-1);}
            case DOWN -> {return new Square(0,1);}
            case LEFT -> {return new Square(-1,0);}
            case RIGHT -> {return new Square(1,0);}
        }
        return new Square(0,0);
    }

    static Direction toDirection(String string)
    {
        switch(string)
        {
            case "w":
            {
                return Direction.UP;
            }
            case "s":
            {
                return Direction.DOWN;
            }
            case "a":
            {
                return Direction.LEFT;
            }
            case "d":
            {
                return Direction.RIGHT;
            }
        }
        return Direction.UP;
    }
}
