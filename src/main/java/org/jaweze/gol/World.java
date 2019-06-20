package org.jaweze.gol;

import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@ToString
public class World {

    final Grid grid;

    public World(Set<Coordinates> livingCells) {
        grid = new Grid(livingCells);
    }

    public Set<Coordinates> getLivingCells() {
        return grid.getLivingCells();
    }

    public World nextGeneration() {
        Set<Coordinates> nextLivingCells = new LinkedHashSet<>();

        for (Coordinates coordinates : grid.getLivingCellsAndNeighbours()) {
            CellState nextCellState = determineNextCellState(grid.getCellState(coordinates), grid.getLivingNeighboursCount(coordinates));
            if (nextCellState == CellState.ALIVE) {
                nextLivingCells.add(coordinates);
            }
        }

        return new World(nextLivingCells);
    }

    private CellState determineNextCellState(CellState currentState, int livingNeighboursCount) {
        if (currentState == CellState.ALIVE) {
            if (livingNeighboursCount < 2) {
                return CellState.DEAD;
            } else if (livingNeighboursCount == 2 || livingNeighboursCount == 3) {
                return CellState.ALIVE;
            } else {
                return CellState.DEAD;
            }
        } else if (currentState == CellState.DEAD) {
            if (livingNeighboursCount == 3) {
                return CellState.ALIVE;
            } else {
                return CellState.DEAD;
            }
        } else {
            throw new IllegalStateException("Unknown cell state: " + currentState);
        }
    }
}
