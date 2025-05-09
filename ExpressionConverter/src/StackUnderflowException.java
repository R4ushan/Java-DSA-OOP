public class StackUnderflowException extends Exception {
    public StackUnderflowException(String message) {
        super(message);
    }
    public StackUnderflowException(){
        super("Stack is empty.");
    }
}