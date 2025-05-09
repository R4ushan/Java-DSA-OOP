public class StackOverflowException extends Exception {
    public StackOverflowException(String message) {
        super(message);
    }
    public StackOverflowException(){
        super("Stack is full.");
    }
}