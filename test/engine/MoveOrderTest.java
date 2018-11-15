package engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    @Test
    void getMovesInTheRightOrder() {
        MoveOrder moveOrder = MoveOrder.Create("ULRD");
        List<MoveDirection> possibleDirections = new LinkedList<>();
        possibleDirections.add(LEFT);
        possibleDirections.add(UP);
        possibleDirections.add(RIGHT);

        possibleDirections.sort(new MoveDirectionComparator(moveOrder));

        assertEquals(new LinkedList<>(Arrays.asList(UP, LEFT, RIGHT)), possibleDirections);
    }
}