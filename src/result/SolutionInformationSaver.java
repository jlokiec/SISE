package result;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SolutionInformationSaver implements FileSaver {
    private String fileName;
    private SolutionInformation solutionInformation;

    public SolutionInformationSaver(String fileName, SolutionInformation solutionInformation) {
        this.fileName = fileName;
        this.solutionInformation = solutionInformation;
    }

    @Override
    public void save() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            printWriter.println(solutionInformation.getSolutionLength());

            if (solutionInformation.getSolutionLength() == -1) {
                return;
            }

            printWriter.println(solutionInformation.getSolutionMoves());
        } catch (IOException e) {
            System.out.println("Unable to write solution information to file");
        }
    }
}
