public class QueueUnderflowException extends Exception {
    public QueueUnderflowException(String message) {
        super(message);
    }
    public QueueUnderflowException(){
        super("Queue is empty.");
    }
}