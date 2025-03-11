package uz.salikhdev.todo.entitiy;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PremiumDay {
    ONE_MONTH(30, 30_000),
    THREE_MONTH(90, 90_000),
    ONE_YEAR(365, 300_000);

    private final int day;
    private final double price;


    public static PremiumDay fromDays(int days) {
        return Arrays.stream(PremiumDay.values())
                .filter(period -> period.getDay() == days)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Premium day not found"));
    }

}
