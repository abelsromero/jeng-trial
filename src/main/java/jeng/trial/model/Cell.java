package jeng.trial.model;

/**
 * Created by abelsr on 11/06/2016.
 */
public class Cell {

    private Integer x;
    private Integer y;
    private State state = State.DEAD;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


}
