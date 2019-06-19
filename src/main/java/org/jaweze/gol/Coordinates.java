package org.jaweze.gol;

import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value(staticConstructor = "of")
public class Coordinates {

    private static final List<Coordinates> NEIGHBOURS_RELATIVE_COORDINATES = Arrays.asList(
            of(-1, 1), of(0, 1), of(1, 1),
            of(-1, 0), of(1, 0),
            of(-1, -1), of(0, -1), of(1, -1)
    );

    int x;
    int y;


    public List<Coordinates> getMeAndNeighbours() {
        List<Coordinates> coordinates = new ArrayList<>();
        coordinates.add(this);
        coordinates.addAll(getNeighbours());
        return coordinates;
    }

    public List<Coordinates> getNeighbours() {
        return NEIGHBOURS_RELATIVE_COORDINATES.stream().map(this::plus).collect(Collectors.toList());
    }

    private Coordinates plus(Coordinates other) {
        return of(x + other.x, y + other.y);
    }
}
