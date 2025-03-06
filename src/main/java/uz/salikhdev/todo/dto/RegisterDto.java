package uz.salikhdev.todo.dto;

import lombok.Builder;

@Builder
public record RegisterDto(
        String email,
        String password,
        String fullName
) {
}
