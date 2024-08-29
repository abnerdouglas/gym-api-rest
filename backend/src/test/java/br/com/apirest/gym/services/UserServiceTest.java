package br.com.apirest.gym.services;

import br.com.apirest.gym.dto.CreateUserDto;
import br.com.apirest.gym.dto.LoginUserDto;
import br.com.apirest.gym.dto.RecoveryJwtTokenDto;
import br.com.apirest.gym.exceptions.users.CpfAlreadyCreatedException;
import br.com.apirest.gym.exceptions.users.EmailAlreadyCreatedException;
import br.com.apirest.gym.models.Role;
import br.com.apirest.gym.models.Roles.RoleName;
import br.com.apirest.gym.models.User;
import br.com.apirest.gym.repositories.UserRepository;
import br.com.apirest.gym.security.authentication.JwtTokenService;
import br.com.apirest.gym.security.userDetails.UserDetailsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenService jwtTokenService;
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Deve autenticar o usuário com email, senha e token jwt")
    public void test01() {
        //ARRANGE
       String email = "email@gmail.com";
       String password = "password";
       String expectedToken = "tokenMockJwt";

       LoginUserDto loginUserDto = new LoginUserDto(email, password);

       UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

       Authentication authentication = mock(Authentication.class);
       UserDetailsImpl userDetails = mock(UserDetailsImpl.class);

       when(authenticationManager.authenticate(authToken)).thenReturn(authentication);
       when(authentication.getPrincipal()).thenReturn(userDetails);
       when(jwtTokenService.generateToken(userDetails)).thenReturn(expectedToken);

        //ACT
        RecoveryJwtTokenDto result = userService.authenticateUser(loginUserDto);

        //ASSERT
        assertNotNull(result);
        assertEquals(expectedToken, result.token());
        verify(authenticationManager).authenticate(authToken);
        verify(jwtTokenService).generateToken(userDetails);
    }

    @Test
    @DisplayName("Deve criar um usuário de acordo com os parametros")
    public void test02() {
        //ARRANGE
        String name = "name";
        String cpf = "12345";
        String email = "email@gmail.com";
        String dateOfBirth = "09/02/1994";
        String password = "password";
        String encodedPassword = "encodedPassword";
        RoleName role = RoleName.valueOf("ROLE_USER");

        CreateUserDto createUserDto = new CreateUserDto(name, email, dateOfBirth, cpf, password, role);

        when(userRepository.findByCpf(cpf)).thenReturn(Optional.empty());
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        User expectedUser = User.builder()
                .name(name)
                .email(email)
                .dateOfBirth(dateOfBirth)
                .cpf(cpf)
                .password(passwordEncoder.encode(password))
                .roles(List.of(Role.builder().name(role).build()))
                .build();

        //ACT
        userService.createUser(createUserDto);

        //ASSERT
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertEquals(name, savedUser.getName());
        assertEquals(email, savedUser.getEmail());
        assertEquals(dateOfBirth, savedUser.getDateOfBirth());
        assertEquals(cpf, savedUser.getCpf());
        assertEquals(encodedPassword, savedUser.getPassword());
        assertEquals(List.of(Role.builder().name(role).build()), savedUser.getRoles());
    }

    @Test
    @DisplayName("Deve lançar uma exceção caso o cpf já estiver cadastrado")
    public void test03(){
        //ARRANGE
        String cpf = "12345";
        CreateUserDto createUserDto = new CreateUserDto("name", "email@gmail.com", "09/02/1994",cpf, "password", RoleName.valueOf("ROLE_USER"));

        when(userRepository.findByCpf(cpf)).thenReturn(Optional.of(new User()));

        //ACT AND ASSERT
        assertThrows(CpfAlreadyCreatedException.class, () -> userService.createUser(createUserDto));
    }

    @Test
    @DisplayName("Deve lançar uma exceção caso o email já estiver cadastrado")
    public void test04(){
        //ARRANGE
        String email = "email@gmail.com";
        CreateUserDto createUserDto = new CreateUserDto("name", email, "09/02/1994","12345", "password", RoleName.valueOf("ROLE_USER"));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        //ACT AND ASSERT
        assertThrows(EmailAlreadyCreatedException.class, () -> userService.createUser(createUserDto));
    }
}
