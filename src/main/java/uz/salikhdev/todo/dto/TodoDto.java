package uz.salikhdev.todo.dto;

import lombok.Builder;

@Builder
public record TodoDto(
        Long id,
        String title,
        String description,
        boolean isCompleted
) {
}
