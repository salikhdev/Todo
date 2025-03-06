package uz.salikhdev.todo.dto;

import lombok.Builder;

@Builder
public record TodoStatusDto(
        boolean status
) {
}
