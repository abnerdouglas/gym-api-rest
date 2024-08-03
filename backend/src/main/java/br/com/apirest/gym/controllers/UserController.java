package br.com.apirest.gym.controllers;

import br.com.apirest.gym.dto.CreateUserDto;
import br.com.apirest.gym.dto.LoginUserDto;
import br.com.apirest.gym.dto.RecoveryJwtTokenDto;
import br.com.apirest.gym.models.User;
import br.com.apirest.gym.exceptions.ApiResponse;
import br.com.apirest.gym.security.authentication.JwtTokenService;
import br.com.apirest.gym.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody @Valid LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        ApiResponse apiResponse = new ApiResponse("Usuário criado com sucesso");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<String> homePage(){
        return new ResponseEntity<String>("Api Rest Spring Boot is running :)",HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestHeader("Authorization") String token) {

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        jwtTokenService.getSubjectFromToken(token);

        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}