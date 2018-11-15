package engine.solver;

import engine.MoveDirection;
import engine.MoveOrder;
import engine.State;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import result.ExtraInformation;
import result.SolutionInformation;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DepthFirstSearchSolverTest {

    @ParameterizedTest
    @MethodSource("generateDataForSolve")
    void solve(State initialState, MoveOrder moveOrder) {
        DepthFirstSearchSolver depthFirstSearchSolver = new DepthFirstSearchSolver(initialState, moveOrder);
        depthFirstSearchSolver.solve();
        assertNotNull(depthFirstSearchSolver);
    }
    static Stream<Arguments> generateDataForSolve() {
        byte[] initialValues = { 1, 2, 3, 4, 5, 6, 7, 8, 10, 13, 11, 12, 9, 14, 0, 15};
        State initialState = new State(Byte.parseByte("4"), Byte.parseByte("4"), initialValues, null, MoveDirection.NO_MOVE, 0);
        return Stream.of(
                Arguments.of(initialState, MoveOrder.RIGHT_DOWN_UP_LEFT),
                Arguments.of(initialState, MoveOrder.RIGHT_DOWN_LEFT_UP),
                Arguments.of(initialState, MoveOrder.DOWN_RIGHT_UP_LEFT),
                Arguments.of(initialState, MoveOrder.DOWN_RIGHT_LEFT_UP),
                Arguments.of(initialState, MoveOrder.LEFT_UP_DOWN_RIGHT),
                Arguments.of(initialState, MoveOrder.LEFT_UP_RIGHT_DOWN),
                Arguments.of(initialState, MoveOrder.UP_LEFT_DOWN_RIGHT),
                Arguments.of(initialState, MoveOrder.UP_LEFT_RIGHT_DOWN)
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataForExtraInformation")
    void getExtraInformation(State initialState, MoveOrder moveOrder) {
        DepthFirstSearchSolver depthFirstSearchSolver = new DepthFirstSearchSolver(initialState, moveOrder);
        ExtraInformation extraInformation = depthFirstSearchSolver.getExtraInformation();
        assertNotNull(extraInformation);
    }

    static Stream<Arguments> generateDataForExtraInformation() {
        byte[] initialValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        State initialState = new State(Byte.parseByte("4"), Byte.parseByte("4"), initialValues, null, MoveDirection.NO_MOVE, 0);
        return Stream.of(
                Arguments.of(initialState, MoveOrder.RIGHT_DOWN_UP_LEFT)
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataForSolutionInformation")
    void getSolutionInformation(State initialState, MoveOrder moveOrder) {
        DepthFirstSearchSolver depthFirstSearchSolver = new DepthFirstSearchSolver(initialState, moveOrder);
        SolutionInformation solutionInformation = depthFirstSearchSolver.getSolutionInformation();
        assertNotNull(solutionInformation);
    }

    static Stream<Arguments> generateDataForSolutionInformation() {
        byte[] initialValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        State initialState = new State(Byte.parseByte("4"), Byte.parseByte("4"), initialValues, null, MoveDirection.NO_MOVE, 0);
        return Stream.of(
                Arguments.of(initialState, MoveOrder.RIGHT_DOWN_UP_LEFT)
        );
    }
}