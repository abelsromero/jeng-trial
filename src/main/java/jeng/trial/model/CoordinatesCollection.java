package jeng.trial.model;

import java.util.List;

/**
 * Encapsutales a list of coordinates to ease the REST inteface
 *
 * Created by abelsr on 11/06/2016.
 */
public class CoordinatesCollection {

    private List<Coordinate> coordinates;

    List<Coordinate> getCoordinates() {
        return coordinates;
    }

    void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
