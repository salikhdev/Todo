package uz.salikhdev.todo.dto;

import lombok.Builder;

@Builder
public record PremiumBuyDto(
        int day,
        double amount
) {
}
