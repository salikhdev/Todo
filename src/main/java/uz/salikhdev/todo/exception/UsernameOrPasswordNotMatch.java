package uz.salikhdev.todo.exception;

public class UsernameOrPasswordNotMatch extends RuntimeException {
    public UsernameOrPasswordNotMatch(String message) {
        super(message);
    }
}
