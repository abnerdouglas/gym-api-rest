package br.com.apirest.gym.exceptions;

public class EmailAlreadyCreatedException extends RuntimeException{

    public EmailAlreadyCreatedException() {
        super("Email já está vinculado a outro usuário cadastrado.");
    }
}
