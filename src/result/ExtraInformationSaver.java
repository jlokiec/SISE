package result;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ExtraInformationSaver implements InformationSaver {
    private String fileName;
    private ExtraInformation extraInformation;

    public ExtraInformationSaver(String fileName, ExtraInformation extraInformation) {
        this.fileName = fileName;
        this.extraInformation = extraInformation;
    }

    @Override
    public void save() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            printWriter.println(extraInformation.getSolutionLength());
            printWriter.println(extraInformation.getVisitedStates());
            printWriter.println(extraInformation.getProcessedStates());
            printWriter.println(extraInformation.getMaxRecursionDepth());
            printWriter.println(extraInformation.getComputationTime());
        } catch (IOException e) {
            System.out.println("Unable to write extra information to file");
        }
    }
}
