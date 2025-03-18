package uz.salikhdev.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.todo.dto.CreateTodoDto;
import uz.salikhdev.todo.dto.TodoDto;
import uz.salikhdev.todo.dto.TodoStatusDto;
import uz.salikhdev.todo.entitiy.Todo;
import uz.salikhdev.todo.entitiy.User;
import uz.salikhdev.todo.exception.EntityNotFound;
import uz.salikhdev.todo.exception.LimitCountIsNotEnough;
import uz.salikhdev.todo.mapper.TodoMapper;
import uz.salikhdev.todo.repository.TodoRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;
    private final TodoMapper todoMapper;

    private boolean canCreateTodo(User user) {
        List<Todo> all = todoRepository.findAllByUserId(user.getId());
        return all.size() < user.getTodoLimit();
    }

    private Todo findTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("Todo not found id: %s".formatted(id))
        );
    }

    public TodoDto getTodoById(Long id) {
        Todo todo = findTodoById(id);
        return todoMapper.toDto(todo);
    }

    public void createTodo(CreateTodoDto dto) {
        User user = userService.findByToken();

        if (!canCreateTodo(user)) {
            throw new LimitCountIsNotEnough("You can't create more than 10 todos");
        }

        Todo entity = todoMapper.toCreateEntity(dto);
        entity.setUser(user);

        todoRepository.save(entity);
    }

    public void updateTodoStatus(Long todoId, TodoStatusDto dto) {
        Todo todo = findTodoById(todoId);
        todo.setCompleted(dto.status());
        todoRepository.save(todo);
    }

    public void deleteTodoById(Long id) {
        Todo todo = findTodoById(id);
        todoRepository.delete(todo);
    }

    public List<TodoDto> getAllTodos() {
        User user = userService.findByToken();
        List<Todo> allTodos = todoRepository.findAllByUserId(user.getId());

        return allTodos.stream()
                .map(todoMapper::toDto)
                .toList();
    }

}
