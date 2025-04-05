package uz.salikhdev.todo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.salikhdev.todo.dto.LoginDto;
import uz.salikhdev.todo.dto.RegisterDto;
import uz.salikhdev.todo.dto.TokenDto;
import uz.salikhdev.todo.entitiy.User;
import uz.salikhdev.todo.exception.EntityAlreadyExists;
import uz.salikhdev.todo.exception.EntityNotFound;
import uz.salikhdev.todo.exception.UsernameOrPasswordNotMatch;
import uz.salikhdev.todo.mapper.UserMapperImpl;
import uz.salikhdev.todo.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Spy
    private UserMapperImpl userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void test_userRegisterError() {

        // 1
        when(userRepository.existsByEmail("salikhdev@gmail.com")).thenReturn(true);
        RegisterDto dto = new RegisterDto("salikhdev@gmail.com", "12345678", "Salikh");

        // 2
        Exception exception = assertThrows(
                EntityAlreadyExists.class,
                () -> userService.userRegister(dto)
        );

        // 3
        assertEquals("User already exists email: salikhdev@gmail.com", exception.getMessage());
    }

    @Test
    void test_userRegisterSuccess() {

        when(userRepository.existsByEmail("salikhdev@gmail.com")).thenReturn(false);
        when(passwordEncoder.encode("12345678")).thenReturn("12345678");
        RegisterDto dto = new RegisterDto("salikhdev@gmail.com", "12345678", "Salikh");

        userService.userRegister(dto);

        verify(userRepository, times(1)).save(any());


    }

    @Test
    void test_loginUserNotFound() {

        when(userRepository.findByEmail("salikhdev@gmail.com")).thenReturn(Optional.empty());

        LoginDto dto = new LoginDto("salikhdev@gmail.com", "123");

        Exception exception = assertThrows(
                EntityNotFound.class,
                () -> userService.login(dto)
        );

        assertEquals("User not found", exception.getMessage());

    }

    @Test
    void test_loginPasswordIncorrect() {

        User user = User.builder()
                .id(1L)
                .email("salikhdev@gmail.com")
                .password("123")
                .build();

        LoginDto dto = new LoginDto("salikhdev@gmail.com", "1234");

        when(userRepository.findByEmail("salikhdev@gmail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(dto.password(), user.getPassword())).thenReturn(false);

        Exception exception = assertThrows(
                UsernameOrPasswordNotMatch.class,
                () -> userService.login(dto)
        );

        assertEquals("Password is incorrect", exception.getMessage());
    }

    @Test
    void test_loginSuccess() {
        User user = User.builder()
                .id(1L)
                .email("salikhdev@gmail.com")
                .password("123")
                .build();

        LoginDto dto = new LoginDto("salikhdev@gmail.com", "123");

        when(userRepository.findByEmail("salikhdev@gmail.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(dto.password(), user.getPassword())).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn("my_token");
        when(jwtService.getExpirationTime()).thenReturn(123L);

        TokenDto data = userService.login(dto);


        verify(jwtService ,times(1)).generateToken(user);
        assertEquals("my_token", data.token());
        assertEquals(123L, data.expirationAt());
    }

}
