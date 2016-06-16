package jeng.trial.model;

import java.util.Comparator;

/**
 * Created by abelsr on 11/06/2016.
 */
public class Coordinate implements Comparable<Coordinate> {

    private final Integer x;
    private final Integer y;

    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }


    @Override
    public int compareTo(Coordinate other) {
        if (this == other)
            return 0;

        if (x < other.x)
            return -1;
        else if (x == other.x)
            return y.compareTo(other.y);
        else
            return 1;
    }

    @Override
    public boolean equals(Object o) {
        return (this == o ||
                (x == ((Coordinate) o).x && y == ((Coordinate) o).y));

    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

}
