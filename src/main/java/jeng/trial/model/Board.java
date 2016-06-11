package jeng.trial.model;

import java.util.List;

/**
 * Created by abelsr on 11/06/2016.
 */
public class Board {

    final Integer height;
    final Integer width;
    private String id;

    // List of positions with alive cells
    List<Coordinate> cells;

    public Board(Integer height, Integer width) {
        this.height = height;
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public List<Coordinate> getCells() {
        return cells;
    }

    public void setCells(List<Coordinate> cells) {
        if (cells != null) {
            for (Coordinate c : cells) {
                if (!validPosition(c.getX(), c.getY())) {
                    throw new InvalidCellException("Position [" + c.getX() + "," + c.getY() + "] " +
                            "is not valid for board of size [" + width + "," + height + "]");
                }
            }
        }
        this.cells = cells;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Tells wether a positions is valid inside the board or not
     */
    private boolean validPosition(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }
}
