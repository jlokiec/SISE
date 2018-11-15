package engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static engine.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.*;

class MoveOrderTest {

    @Test
    void createRDUL() {
        MoveOrder moveOrder = MoveOrder.Create("RDUL");

        assertEquals("RDUL", moveOrder.toString());
        assertEquals(MoveOrder.RIGHT_DOWN_UP_LEFT, moveOrder);
    }

    @Test
    void createRDLU() {
        MoveOrder moveOrder = MoveOrder.Create("RDLU");

        assertEquals("RDLU", moveOrder.toString());
        assertEquals(MoveOrder.RIGHT_DOWN_LEFT_UP, moveOrder);
    }

    @Test
    void createDRUL() {
        MoveOrder moveOrder = MoveOrder.Create("DRUL");

        assertEquals("DRUL", moveOrder.toString());
        assertEquals(MoveOrder.DOWN_RIGHT_UP_LEFT, moveOrder);
    }

    @Test
    void createDRLU() {
        MoveOrder moveOrder = MoveOrder.Create("DRLU");

        assertEquals("DRLU", moveOrder.toString());
        assertEquals(MoveOrder.DOWN_RIGHT_LEFT_UP, moveOrder);
    }

    @Test
    void createLUDR() {
        MoveOrder moveOrder = MoveOrder.Create("LUDR");

        assertEquals("LUDR", moveOrder.toString());
        assertEquals(MoveOrder.LEFT_UP_DOWN_RIGHT, moveOrder);
    }

    @Test
    void createLURD() {
        MoveOrder moveOrder = MoveOrder.Create("LURD");

        assertEquals("LURD", moveOrder.toString());
        assertEquals(MoveOrder.LEFT_UP_RIGHT_DOWN, moveOrder);
    }

    @Test
    void createULDR() {
        MoveOrder moveOrder = MoveOrder.Create("ULDR");

        assertEquals("ULDR", moveOrder.toString());
        assertEquals(MoveOrder.UP_LEFT_DOWN_RIGHT, moveOrder);
    }

    @Test
    void createULRD() {
        MoveOrder moveOrder = MoveOrder.Create("ULRD");
        assertEquals("ULRD", moveOrder.toString());
        assertEquals(MoveOrder.UP_LEFT_RIGHT_DOWN, moveOrder);
    }

    @Test
    void createThrowError() {
        Assertions.assertThrows(InvalidParameterException.class, () -> MoveOrder.Create("WRONG"));
    }

    @ParameterizedTest
    @MethodSource("generateDataForGetMovesInTheRightOrder")
    void getMovesInTheRightOrder(MoveOrder moveOrder, List<MoveDirection> expected, List<MoveDirection> possibleDirections) {
        possibleDirections.sort(new MoveDirectionComparator(moveOrder));

        assertEquals(expected, possibleDirections);
    }
    static Stream<Arguments> generateDataForGetMovesInTheRightOrder() {
        return Stream.of(
                Arguments.of(MoveOrder.RIGHT_DOWN_UP_LEFT, Arrays.asList(RIGHT, DOWN, UP, LEFT), Arrays.asList(LEFT, UP, DOWN, RIGHT)),
                Arguments.of(MoveOrder.RIGHT_DOWN_LEFT_UP, Arrays.asList(RIGHT, DOWN, LEFT, UP), Arrays.asList(UP, LEFT, DOWN, RIGHT)),
                Arguments.of(MoveOrder.DOWN_RIGHT_UP_LEFT, Arrays.asList(DOWN, RIGHT, UP, LEFT), Arrays.asList(UP, DOWN, LEFT, RIGHT)),
                Arguments.of(MoveOrder.DOWN_RIGHT_LEFT_UP, Arrays.asList(DOWN, RIGHT, LEFT, UP), Arrays.asList(UP, LEFT, DOWN, RIGHT)),
                Arguments.of(MoveOrder.LEFT_UP_DOWN_RIGHT, Arrays.asList(LEFT, UP, DOWN, RIGHT), Arrays.asList(RIGHT, DOWN, UP, LEFT)),
                Arguments.of(MoveOrder.LEFT_UP_RIGHT_DOWN, Arrays.asList(LEFT, UP, RIGHT, DOWN), Arrays.asList(RIGHT, UP, DOWN, LEFT)),
                Arguments.of(MoveOrder.UP_LEFT_DOWN_RIGHT, Arrays.asList(UP, LEFT, DOWN, RIGHT), Arrays.asList(DOWN, UP, RIGHT, LEFT)),
                Arguments.of(MoveOrder.UP_LEFT_RIGHT_DOWN, Arrays.asList(UP, LEFT, RIGHT, DOWN), Arrays.asList(RIGHT, DOWN, LEFT, UP)),

                Arguments.of(MoveOrder.RIGHT_DOWN_UP_LEFT, Arrays.asList(DOWN, UP, LEFT), Arrays.asList(LEFT, UP, DOWN)),
                Arguments.of(MoveOrder.RIGHT_DOWN_LEFT_UP, Arrays.asList(RIGHT, DOWN, UP), Arrays.asList(UP, DOWN, RIGHT)),
                Arguments.of(MoveOrder.DOWN_RIGHT_UP_LEFT, Arrays.asList(RIGHT, UP, LEFT), Arrays.asList(UP, LEFT, RIGHT)),
                Arguments.of(MoveOrder.DOWN_RIGHT_LEFT_UP, Arrays.asList(DOWN, RIGHT, LEFT), Arrays.asList(LEFT, DOWN, RIGHT)),
                Arguments.of(MoveOrder.LEFT_UP_DOWN_RIGHT, Arrays.asList(LEFT, UP, RIGHT), Arrays.asList(RIGHT, UP, LEFT)),
                Arguments.of(MoveOrder.LEFT_UP_RIGHT_DOWN, Arrays.asList(LEFT, UP, DOWN), Arrays.asList(UP, DOWN, LEFT)),
                Arguments.of(MoveOrder.UP_LEFT_DOWN_RIGHT, Arrays.asList(UP, DOWN, RIGHT), Arrays.asList(DOWN, UP, RIGHT)),
                Arguments.of(MoveOrder.UP_LEFT_RIGHT_DOWN, Arrays.asList(UP, LEFT, RIGHT), Arrays.asList(RIGHT, LEFT, UP))
        );
    }
}