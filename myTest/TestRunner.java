package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * This class is used to run the unit tests for the ListAdapter class.
 * It uses the JUnitCore class from the JUnit framework to run the tests and display the results.
 * The results include the total number of tests run, the number of failed tests, details of the failed tests, and the total time taken to run the tests.
 */
public class TestRunner {
    /**
     * The main method that runs the unit tests for the ListAdapter class.
     * It creates a Result object by running the tests in the ListAdapterTest class.
     * It then prints the total number of tests run, the number of failed tests, details of the failed tests, and the total time taken to run the tests.
     *
     * @param args The command line arguments. This is not used in this method.
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ListAdapterTest.class);
        System.out.println("Numero totale di test eseguiti: " + result.getRunCount());
        System.out.println("Numero di test falliti: " + result.getFailureCount());
        System.out.println("Test falliti:");
        for (int i = 0; i < result.getFailures().size(); i++) {
            System.out.println(result.getFailures().get(i).toString());
        }
        System.out.println();
        System.out.println("Tempo richiesto per l'esecuzione dei test: " + result.getRunTime() + "ms");
        System.out.println("Tutti i test sono passati: " + result.wasSuccessful());
    }
}
