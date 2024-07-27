package br.com.apirest.gym.services;

import br.com.apirest.gym.dto.CreateUserDto;
import br.com.apirest.gym.dto.LoginUserDto;
import br.com.apirest.gym.dto.RecoveryJwtTokenDto;
import br.com.apirest.gym.entities.Role;
import br.com.apirest.gym.entities.User;
import br.com.apirest.gym.exceptions.AuthErrorException;
import br.com.apirest.gym.exceptions.CpfAlreadyCreatedException;
import br.com.apirest.gym.exceptions.EmailAlreadyCreatedException;
import br.com.apirest.gym.repositories.UserRepository;
import br.com.apirest.gym.security.authentication.JwtTokenService;
import br.com.apirest.gym.security.userDetails.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();

            return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
        } catch (Exception e) {
            throw new AuthErrorException(e);
        }
    }

    public void createUser(CreateUserDto createUserDto) {
        if (userRepository.findByEmail(createUserDto.email()).isPresent()) {
            throw new EmailAlreadyCreatedException();
        }

        if (userRepository.findByCpf(createUserDto.cpf()).isPresent()){
            throw new CpfAlreadyCreatedException();
        }

        User newUser = User.builder()
                .name(createUserDto.name())
                .email(createUserDto.email())
                .dateOfBirth(createUserDto.dateOfBirth())
                .cpf(createUserDto.cpf())
                .password(passwordEncoder.encode(createUserDto.password()))
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();

        userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
