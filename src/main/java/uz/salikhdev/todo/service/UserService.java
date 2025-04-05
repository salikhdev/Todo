package uz.salikhdev.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.salikhdev.todo.dto.LoginDto;
import uz.salikhdev.todo.dto.RegisterDto;
import uz.salikhdev.todo.dto.TokenDto;
import uz.salikhdev.todo.entitiy.User;
import uz.salikhdev.todo.exception.EntityAlreadyExists;
import uz.salikhdev.todo.exception.EntityNotFound;
import uz.salikhdev.todo.exception.UsernameOrPasswordNotMatch;
import uz.salikhdev.todo.mapper.UserMapper;
import uz.salikhdev.todo.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public void userRegister(RegisterDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new EntityAlreadyExists("User already exists email: %s".formatted(dto.email()));
        }

        User user = userMapper.toRegisterEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setTodoLimit(10L);
        userRepository.save(user);
    }

    public TokenDto login(LoginDto dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new EntityNotFound("User not found"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UsernameOrPasswordNotMatch("Password is incorrect");
        }

        String token = jwtService.generateToken(user);

        return TokenDto.builder()
                .token(token)
                .expirationAt(jwtService.getExpirationTime())
                .build();
    }

    public User findByToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
