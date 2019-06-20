package org.jaweze.gol;

import lombok.Value;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class WorldTest {

    static TestCase[] testCases = {
            TestCase.of(
                    "block",
                    new String[][]{
                            {
                                    "0000",
                                    "0110",
                                    "0110",
                                    "0000"
                            },
                            {
                                    "0000",
                                    "0110",
                                    "0110",
                                    "0000"
                            }
                    }
            ),
            TestCase.of(
                    "beehive",
                    new String[][]{
                            {
                                    "000000",
                                    "001100",
                                    "010010",
                                    "001100",
                                    "000000"
                            },
                            {
                                    "000000",
                                    "001100",
                                    "010010",
                                    "001100",
                                    "000000"
                            }
                    }
            ),
            TestCase.of(
                    "blinker",
                    new String[][]{
                            {
                                    "00000",
                                    "00100",
                                    "00100",
                                    "00100",
                                    "00000"
                            },
                            {
                                    "00000",
                                    "00000",
                                    "01110",
                                    "00000",
                                    "00000"
                            }
                    }
            )
    };

    @Test
    public void shouldGenerateNextWorld() {
        for (TestCase testCase : testCases) {
            World world = new World(testCase.getInitialLivingCells());
            World nextWorld = world.nextGeneration();
            assertEquals("Test case: " + testCase.name, testCase.getExpectedLivingCells(), nextWorld.getLivingCells());
        }
    }

    @Value(staticConstructor = "of")
    static class TestCase {

        String name;
        String[][] grids;

        Set<Coordinates> arrayToGrid(String[] array) {
            Set<Coordinates> coordinates = new LinkedHashSet<>();

            for (int y = 0; y < array.length; y++) {
                String cells = array[y];
                for (int x = 0; x < cells.length(); x++) {
                    char cell = cells.charAt(x);
                    if (cell == '1') {
                        coordinates.add(Coordinates.of(x, y));
                    }
                }
            }

            return coordinates;
        }

        Set<Coordinates> getExpectedLivingCells() {
            return arrayToGrid(grids[1]);
        }

        Set<Coordinates> getInitialLivingCells() {
            return arrayToGrid(grids[0]);
        }
    }
}