package org.jaweze.gol;

import lombok.Value;

@Value
public class Cell {

    Coordinates coordinates;
    CellState state;
}
