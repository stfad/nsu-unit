import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Core.ClassToTestLoader;

import static java.lang.System.*;

public class MainClass {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar <junit-jar> N class-name [class-name]*");
            exit(1);
        }
        System.out.println("Hello from NSUUnit!");

        int nThreads = 0;
        try {
            nThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException err) {
            out.println("first argument should be positive integer");
            exit(1);
        }
        List<String> classesToTest = new ArrayList<>(Arrays.asList(args).subList(1, args.length));
        ClassToTestLoader.loadClassesToQueue(nThreads, classesToTest);
    }
}
