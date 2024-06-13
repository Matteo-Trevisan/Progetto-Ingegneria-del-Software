package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestRunner {
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
