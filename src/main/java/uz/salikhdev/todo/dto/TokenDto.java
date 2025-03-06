package uz.salikhdev.todo.dto;

import lombok.Builder;

@Builder
public record TokenDto(
        String token,
        long expirationAt
) {
}
