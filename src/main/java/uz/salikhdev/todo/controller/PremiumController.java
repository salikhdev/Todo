package uz.salikhdev.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.todo.dto.PremiumBuyDto;
import uz.salikhdev.todo.service.PremiumService;

@RestController
@RequestMapping("/premium")
@RequiredArgsConstructor
public class PremiumController {

    private PremiumService premiumService;


    @PostMapping("/buy")
    public ResponseEntity<?> buyPremium(@RequestBody PremiumBuyDto dto) {
        premiumService.buyPremium(dto);
        return ResponseEntity.ok().build();
    }

}
