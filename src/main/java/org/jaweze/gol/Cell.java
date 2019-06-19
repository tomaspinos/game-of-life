package org.jaweze.gol;

import lombok.Value;

import java.util.List;

@Value
public class Cell {

    Coordinates coordinates;
    CellState state;

    List<Coordinates> getMyAndNeighboursCoordinates() {
        return coordinates.getMeAndNeighbours();
    }

    List<Coordinates> getNeighboursCoordinates() {
        return coordinates.getNeighbours();
    }
}
