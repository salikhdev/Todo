package uz.salikhdev.todo.mapper;

import org.mapstruct.Mapper;
import uz.salikhdev.todo.dto.CreateTodoDto;
import uz.salikhdev.todo.dto.TodoDto;
import uz.salikhdev.todo.entitiy.Todo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    Todo toCreateEntity(CreateTodoDto dto);

    Todo toEntity(TodoDto dto);

    TodoDto toDto(Todo entity);

   // List<TodoDto> toListDto(List<Todo> entities);

}
