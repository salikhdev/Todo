package uz.salikhdev.todo.exception;


public class AlreadyHaveException extends RuntimeException {
    public AlreadyHaveException(String message) {
        super(message);
    }
}
