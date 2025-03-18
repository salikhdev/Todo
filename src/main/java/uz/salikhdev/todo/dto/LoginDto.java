package uz.salikhdev.todo.dto;

import lombok.Builder;

@Builder
public record LoginDto(
        String email,
        String password
) {
}
