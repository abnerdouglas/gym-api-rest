package br.com.apirest.gym.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class InvalidTokenException extends JWTVerificationException{

    public InvalidTokenException(){
        super("Token Inválido ou expirado");
    }
}
