package myAdapter;
/**
 * Copied from java.lang.IllegalStateException, necessary since cldc 1.1 does not have this class.
 * <br>
 * Signals that a method has been invoked at an illegal or inappropriate time.
 * In other words, the Java environment or Java application is not in an
 * appropriate state for the requested operation.
 */
public class IllegalStateException extends RuntimeException{
    /**
     * Constructs an IllegalStateException with no detail message. A detail message is a String that describes this particular exception.
     */
    public IllegalStateException() {
        super();
    }

    /**
     * Constructs an IllegalStateException with the specified detail message. A detail message is a String that describes this particular exception.
     *
     * @param s the String that contains a detailed message
     */
    public IllegalStateException(String s) {
        super(s);
    }
}
