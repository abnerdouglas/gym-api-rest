package br.com.apirest.gym.exceptions;

public class CpfAlreadyCreatedException extends RuntimeException {

    public CpfAlreadyCreatedException(){
        super("CPF já está vinculado a outro usuário cadastrado.");
    }
}
