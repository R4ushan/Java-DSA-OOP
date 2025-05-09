public class QueueOverflowException extends Exception {
    public QueueOverflowException(String message) {
        super(message);
    }
    public QueueOverflowException(){
        super("Queue is full.");
    }
}
