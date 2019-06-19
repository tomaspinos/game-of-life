package org.jaweze.gol;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Value
public class Grid {

    Map<Coordinates, Cell> livingCells;

    public Grid(List<Cell> livingCells) {
        this.livingCells = livingCells.stream().collect(Collectors.toMap(Cell::getCoordinates, Function.identity()));
    }

    public int countLivingCells(List<Coordinates> coordinates) {
        return (int) coordinates.stream().map(livingCells::get).filter(Objects::nonNull).count();
    }

    public List<Cell> getLivingCells() {
        return new ArrayList<>(livingCells.values());
    }
}
