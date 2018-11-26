package engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {

    @Test
    public void testPosition() {
        State state = new State((byte) 4, (byte) 4, new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0}, null, null, 0);

        //1
        assertEquals(state.getPuzzleX((byte) 1), 0);
        assertEquals(state.getPuzzleY((byte) 1), 0);

        //2
        assertEquals(state.getPuzzleX((byte) 2), 1);
        assertEquals(state.getPuzzleY((byte) 2), 0);

        //3
        assertEquals(state.getPuzzleX((byte) 3), 2);
        assertEquals(state.getPuzzleY((byte) 3), 0);

        //4
        assertEquals(state.getPuzzleX((byte) 4), 3);
        assertEquals(state.getPuzzleY((byte) 4), 0);

        //5
        assertEquals(state.getPuzzleX((byte) 5), 0);
        assertEquals(state.getPuzzleY((byte) 5), 1);

        //6
        assertEquals(state.getPuzzleX((byte) 6), 1);
        assertEquals(state.getPuzzleY((byte) 6), 1);

        //7
        assertEquals(state.getPuzzleX((byte) 7), 2);
        assertEquals(state.getPuzzleY((byte) 7), 1);

        //8
        assertEquals(state.getPuzzleX((byte) 8), 3);
        assertEquals(state.getPuzzleY((byte) 8), 1);

        //9
        assertEquals(state.getPuzzleX((byte) 9), 0);
        assertEquals(state.getPuzzleY((byte) 9), 2);

        //10
        assertEquals(state.getPuzzleX((byte) 10), 1);
        assertEquals(state.getPuzzleY((byte) 10), 2);

        //11
        assertEquals(state.getPuzzleX((byte) 11), 2);
        assertEquals(state.getPuzzleY((byte) 11), 2);

        //12
        assertEquals(state.getPuzzleX((byte) 12), 3);
        assertEquals(state.getPuzzleY((byte) 12), 2);

        //13
        assertEquals(state.getPuzzleX((byte) 13), 0);
        assertEquals(state.getPuzzleY((byte) 13), 3);

        //14
        assertEquals(state.getPuzzleX((byte) 14), 1);
        assertEquals(state.getPuzzleY((byte) 14), 3);

        //15
        assertEquals(state.getPuzzleX((byte) 15), 2);
        assertEquals(state.getPuzzleY((byte) 15), 3);

        //0
        assertEquals(state.getPuzzleX((byte) 0), 3);
        assertEquals(state.getPuzzleY((byte) 0), 3);
    }

    @Test
    public void testEquals() {
        State state1 = new State((byte) 4, (byte) 4, new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0}, null, null, 1);
        State state2 = new State((byte) 4, (byte) 4, new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0}, null, null, 2);
        State state3 = new State((byte) 4, (byte) 4, new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 15}, null, null, 3);

        assertTrue(state1.equals(state2));
        assertTrue(state2.equals(state1));
        assertFalse(state1.equals(state3));
        assertFalse(state3.equals(state1));
    }
}
