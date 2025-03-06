package uz.salikhdev.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.salikhdev.todo.dto.MessageDto;
import uz.salikhdev.todo.exception.EntityAlreadyExists;
import uz.salikhdev.todo.exception.EntityNotFound;
import uz.salikhdev.todo.exception.LimitCountIsNotEnough;
import uz.salikhdev.todo.exception.UsernameOrPasswordNotMatch;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({EntityAlreadyExists.class, LimitCountIsNotEnough.class})
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(new MessageDto(e.getMessage(), HttpStatus.CONTINUE.value(), false), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<?> handleException2(Exception e) {
        return new ResponseEntity<>(new MessageDto(e.getMessage(), HttpStatus.NOT_FOUND.value(), false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameOrPasswordNotMatch.class)
    public ResponseEntity<?> handleException3(Exception e) {
        return new ResponseEntity<>(new MessageDto(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false), HttpStatus.BAD_REQUEST);
    }

}
