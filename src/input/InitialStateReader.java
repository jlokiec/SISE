package input;

import engine.State;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InitialStateReader implements StateReader {
    private static final String SEPARATOR = " ";

    private String fileName;
    private State initialState;

    public InitialStateReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void read() {
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(fileName))) {
            String[] firstLine = bufferedReader.readLine().split(SEPARATOR);
            byte sizeY = Byte.parseByte(firstLine[0]);
            byte sizeX = Byte.parseByte(firstLine[1]);

            byte[] initialValues = new byte[sizeX * sizeY];

            for (byte row = 0; row < sizeY; row++) {
                String[] line = bufferedReader.readLine().split(SEPARATOR);

                for (byte column = 0; column < line.length; column++) {
                    byte value = Byte.parseByte(line[column]);

                    initialValues[row * sizeX + column] = value;
                }
            }

            initialState = new State();
            initialState.setSizeX(sizeX);
            initialState.setSizeY(sizeY);
            initialState.setStateArray(initialValues);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read initial state, file not found");
        } catch (IOException e) {
            System.out.println("Unable to read initial state");
        }
    }

    public State getInitialState() {
        return initialState;
    }
}
