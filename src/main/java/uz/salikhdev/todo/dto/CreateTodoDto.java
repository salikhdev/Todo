package uz.salikhdev.todo.dto;

import lombok.Builder;

@Builder
public record CreateTodoDto(
        String title,
        String description
) {
}
