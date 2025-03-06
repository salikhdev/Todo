package uz.salikhdev.todo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.todo.dto.CreateTodoDto;
import uz.salikhdev.todo.dto.MessageDto;
import uz.salikhdev.todo.dto.TodoStatusDto;
import uz.salikhdev.todo.service.TodoService;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
@Tag(name = "Todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTodo() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTodo(@RequestBody CreateTodoDto dto) {
        todoService.createTodo(dto);
        return new ResponseEntity<>(MessageDto.success("Success created !", HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") Long id, @RequestBody TodoStatusDto statusDto) {
        todoService.updateTodoStatus(id, statusDto);
        return ResponseEntity.ok(MessageDto.success("Success updated !", HttpStatus.OK.value()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") Long id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.ok(MessageDto.success("Success deleted !", HttpStatus.NO_CONTENT.value()));
    }

}
