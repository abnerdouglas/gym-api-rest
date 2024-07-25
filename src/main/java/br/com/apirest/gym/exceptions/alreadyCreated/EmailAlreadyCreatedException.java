package br.com.apirest.gym.exceptions.alreadyCreated;

public class EmailAlreadyCreatedException extends RuntimeException{

    public EmailAlreadyCreatedException() {
        super("Email já está vinculado a outro usuário cadastrado.");
    }
}
