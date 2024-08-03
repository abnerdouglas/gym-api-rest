package br.com.apirest.gym.exceptions.users;

public class CpfAlreadyCreatedException extends RuntimeException {

    public CpfAlreadyCreatedException(){
        super("CPF já está vinculado a outro usuário cadastrado.");
    }
}
