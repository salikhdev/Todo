package uz.salikhdev.todo.exception;

public class LimitCountIsNotEnough extends RuntimeException {
    public LimitCountIsNotEnough(String message) {
        super(message);
    }
}
