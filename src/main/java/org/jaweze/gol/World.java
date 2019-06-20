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
            if (nextCellState == CellState.alive) {
                nextLivingCells.add(coordinates);
            }
        }

        return new World(nextLivingCells);
    }

    private CellState determineNextCellState(CellState currentState, int livingNeighboursCount) {
        if (currentState == CellState.alive) {
            if (livingNeighboursCount < 2) {
                return CellState.dead;
            } else if (livingNeighboursCount == 2 || livingNeighboursCount == 3) {
                return CellState.alive;
            } else {
                return CellState.dead;
            }
        } else if (currentState == CellState.dead) {
            if (livingNeighboursCount == 3) {
                return CellState.alive;
            } else {
                return CellState.dead;
            }
        } else {
            throw new IllegalStateException("Unknown cell state: " + currentState);
        }
    }
}
