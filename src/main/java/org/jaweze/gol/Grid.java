package org.jaweze.gol;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ToString
public class Grid {

    @Getter
    Set<Coordinates> livingCells;
    @Getter
    Set<Coordinates> livingCellsAndNeighbours;
    Map<Coordinates, Integer> livingNeighbourCount;

    public Grid(Set<Coordinates> livingCells) {
        this.livingCells = livingCells;

        this.livingCellsAndNeighbours = livingCells.stream()
                .flatMap(coordinates -> coordinates.getMeAndNeighbours().stream())
                .collect(Collectors.toSet());

        this.livingNeighbourCount = new HashMap<>();
        for (Coordinates coordinates : livingCellsAndNeighbours) {
            livingNeighbourCount.put(coordinates, countLivingCells(coordinates.getNeighbours()));
        }
    }

    public CellState getCellState(Coordinates coordinates) {
        return livingCells.contains(coordinates) ? CellState.alive : CellState.dead;
    }

    public int countLivingCells(List<Coordinates> coordinates) {
        return (int) coordinates.stream().map(livingCells::contains).filter(flag -> flag).count();
    }

    public int getLivingNeighboursCount(Coordinates coordinates) {
        return livingNeighbourCount.getOrDefault(coordinates, 0);
    }
}
