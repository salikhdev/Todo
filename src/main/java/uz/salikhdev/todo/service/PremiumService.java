package uz.salikhdev.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.todo.dto.PremiumBuyDto;
import uz.salikhdev.todo.entitiy.Premium;
import uz.salikhdev.todo.entitiy.PremiumDay;
import uz.salikhdev.todo.exception.AlreadyHaveException;
import uz.salikhdev.todo.repository.PremiumRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PremiumService {

    private PremiumRepository premiumRepository;
    private UserService userService;

    public void buyPremium(PremiumBuyDto premiumBuyDto) {
        var user = userService.findByToken();

        if (user.getPremium() != null) {
            throw new AlreadyHaveException("You already have premium");
        }
        PremiumDay period = PremiumDay.fromDays(premiumBuyDto.day());

        if (period.getPrice() == premiumBuyDto.amount()) {
            Premium premium = Premium.builder()
                    .user(user)
                    .startAt(LocalDateTime.now())
                    .endAt(LocalDateTime.now().plusDays(period.getDay()))
                    .build();
            premiumRepository.save(premium);
        }
    }

}
