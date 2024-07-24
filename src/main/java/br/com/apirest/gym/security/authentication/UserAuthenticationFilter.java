package br.com.apirest.gym.security.authentication;

import br.com.apirest.gym.entities.User;
import br.com.apirest.gym.repositories.UserRepository;
import br.com.apirest.gym.security.config.SecurityConfiguration;
import br.com.apirest.gym.security.userDetails.UserDetailsImplementation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);
            if (token != null) {
                try {
                    String subject = jwtTokenService.getSubjectFromToken(token);
                    User user = userRepository.findByEmail(subject).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
                    UserDetailsImplementation userDetails = new UserDetailsImplementation(user);

                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception e) {
                    System.err.println("Erro ao processar o token: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.err.println("O token está ausente.");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }

}
