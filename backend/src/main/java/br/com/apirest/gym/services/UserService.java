package br.com.apirest.gym.services;

import br.com.apirest.gym.dto.CreateUserDto;
import br.com.apirest.gym.dto.LoginUserDto;
import br.com.apirest.gym.dto.RecoveryJwtTokenDto;
import br.com.apirest.gym.dto.UpdateUserDTO;
import br.com.apirest.gym.models.Role;
import br.com.apirest.gym.models.User;
import br.com.apirest.gym.exceptions.users.AuthErrorException;
import br.com.apirest.gym.exceptions.users.CpfAlreadyCreatedException;
import br.com.apirest.gym.exceptions.users.EmailAlreadyCreatedException;
import br.com.apirest.gym.repositories.UserRepository;
import br.com.apirest.gym.security.authentication.JwtTokenService;
import br.com.apirest.gym.security.userDetails.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidParameterException;
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

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

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

    public List<User> getAllUsers(String token) {
        // Verificar se o token é nulo ou vazio
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Token inválido ou ausente");
        }

        // Remover o prefixo "Bearer "
        token = token.substring(7);

        // Obter o assunto do token para qualquer lógica adicional (opcional)
        String subject = jwtTokenService.getSubjectFromToken(token);

        // Retornar a lista de usuários
        return userRepository.findAll();
    }

    public void removeUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new InvalidParameterException("Usuário com id " + id + " não encontrado.");
        }
        userRepository.deleteById(id);
    }

    public void updateUser(UpdateUserDTO userDTO){
        User existingUser = userRepository.findById(userDTO.id())
                .orElseThrow(() -> new InvalidParameterException("Usuário com id " + userDTO.id() + " não encontrado."));

        // Atualiza os campos do usuário existente
        existingUser.setName(userDTO.name());
        existingUser.setEmail(userDTO.email());
        existingUser.setDateOfBirth(userDTO.dateOfBirth());
        existingUser.setCpf(userDTO.cpf());
        existingUser.setPassword(passwordEncoder.encode(userDTO.password()));

        // Salva o usuário atualizado
        userRepository.save(existingUser);
    }
}
