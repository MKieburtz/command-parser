package cs350s21project.cli;

public class Verifier {

    private static void throwError(String datatype, String badValue) {
        throw new RuntimeException("Error! Invalid " + datatype + ": " + badValue);
    }
}
