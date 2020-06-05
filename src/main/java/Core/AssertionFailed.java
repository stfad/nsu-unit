package Core;

public class AssertionFailed extends RuntimeException {
    AssertionFailed(String message) {
        super("ASSERTION FAILED: " + message);
    }
}
