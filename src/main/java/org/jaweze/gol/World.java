package org.jaweze.gol;

import lombok.Value;

import java.util.List;

@Value
public class World {

    Grid grid;

    public World(List<Cell> livingCells) {
        grid = new Grid(livingCells);
    }

    public World nextGeneration() {
        grid.getLivingCells()
    }
}
