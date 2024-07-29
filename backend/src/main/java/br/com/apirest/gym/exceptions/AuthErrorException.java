package br.com.apirest.gym.exceptions;

public class AuthErrorException extends RuntimeException {
    public AuthErrorException(Exception e){
        super("Falha na autenticação do token jwt: " + e);
    }

}
