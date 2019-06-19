package org.jaweze.gol;

import lombok.Value;

import java.util.LinkedHashSet;
import java.util.Set;

@Value
public class World {

    Grid grid;

    public World(Set<Coordinates> livingCells) {
        grid = new Grid(livingCells);
    }

    public World nextGeneration() {
        Set<Coordinates> nextLivingCells = new LinkedHashSet<>();

        for (Coordinates coordinates : grid.getLivingCellsAndNeighbours()) {
            if (determineNextCellState(grid.getCellState(coordinates), grid.getLivingNeighboursCount(coordinates)) == CellState.ALIVE) {
                nextLivingCells.add(coordinates);
            }
        }

        return new World(nextLivingCells);
    }

    private CellState determineNextCellState(CellState currentState, int livingNeighboursCount) {
        // TODO
        return CellState.ALIVE;
    }
}
