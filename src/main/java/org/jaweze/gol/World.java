package org.jaweze.gol;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class World {

    Grid grid;

    public World(List<Cell> livingCells) {
        grid = new Grid(livingCells);
    }

    public World nextGeneration() {
        List<Cell> nextLivingCells = new ArrayList<>();

        for (Coordinates coordinates : grid.getLivingCellAndNeighboursCoordinates()) {
            if (determineNextCellState(grid.getCellState(coordinates), grid.getLivingNeighboursCount(coordinates)) == CellState.ALIVE) {
                nextLivingCells.add(new Cell(coordinates, CellState.ALIVE));
            }
        }

        return new World(nextLivingCells);
    }

    private CellState determineNextCellState(CellState currentState, int livingNeighboursCount) {
        // TODO
        return CellState.ALIVE;
    }
}
