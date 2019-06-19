package org.jaweze.gol;

import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Value
public class Grid {

    Map<Coordinates, Cell> livingCells;
    @Getter
    List<Coordinates> livingCellAndNeighboursCoordinates;
    Map<Coordinates, Integer> livingNeighbourCount;

    public Grid(List<Cell> livingCells) {
        this.livingCells = livingCells.stream().collect(Collectors.toMap(Cell::getCoordinates, Function.identity()));

        this.livingCellAndNeighboursCoordinates = livingCells.stream()
                .flatMap(cell -> cell.getMyAndNeighboursCoordinates().stream())
                .collect(Collectors.toList());

        this.livingNeighbourCount = new HashMap<>();
        for (Coordinates coordinates : livingCellAndNeighboursCoordinates) {
            livingNeighbourCount.put(coordinates, countLivingCells(coordinates.getNeighbours()));
        }
    }

    public CellState getCellState(Coordinates coordinates) {
        return livingCells.containsKey(coordinates) ? CellState.ALIVE : CellState.DEAD;
    }

    public int countLivingCells(List<Coordinates> coordinates) {
        return (int) coordinates.stream().map(livingCells::get).filter(Objects::nonNull).count();
    }

    public List<Cell> getLivingCells() {
        return new ArrayList<>(livingCells.values());
    }

    public int getLivingNeighboursCount(Coordinates coordinates) {
        return livingNeighbourCount.getOrDefault(coordinates, 0);
    }
}
