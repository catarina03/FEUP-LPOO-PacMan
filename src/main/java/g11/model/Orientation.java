package g11.model;

public enum Orientation {
    UP, DOWN, LEFT, RIGHT;

    public Orientation getOpposite(){
        if (this == Orientation.UP)
            return Orientation.DOWN;
        if (this == Orientation.DOWN)
            return Orientation.UP;
        if (this == Orientation.LEFT)
            return Orientation.RIGHT;
        if (this == Orientation.RIGHT)
            return Orientation.LEFT;
        return null;
    }
}

