package uz.salikhdev.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.todo.dto.MessageDto;
import uz.salikhdev.todo.dto.RegisterDto;
import uz.salikhdev.todo.dto.TokenDto;
import uz.salikhdev.todo.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth qilish uchun apilar")
public class AuthController {

    private final UserService userService;

    @Operation(summary = "Register qilish")
    @ApiResponse(responseCode = "201", description = "Register is success")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        userService.userRegister(dto);
        return ResponseEntity.ok(new MessageDto("Register is success", 201, true));
    }

    @Operation(summary = "Login qilish")
    @ApiResponse(responseCode = "200", description = "Mahsulot topildi")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterDto dto) {
        TokenDto token = userService.login(dto);
        return ResponseEntity.ok(token);
    }


}
