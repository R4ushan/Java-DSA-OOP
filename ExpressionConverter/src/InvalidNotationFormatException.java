public class InvalidNotationFormatException extends Exception {
    public InvalidNotationFormatException(String message){
        super(message);
    }
    public InvalidNotationFormatException(){
        super("message");

    }
}

