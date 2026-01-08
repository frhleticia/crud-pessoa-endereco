package repository;

import entity.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Pessoa> usuarios = new ArrayList<>();

    public void salvar(Pessoa pessoa){
        usuarios.add(pessoa);
    }

    public void remover(Pessoa pessoa){
        usuarios.remove(pessoa);
    }

    public List<Pessoa> getUsuarios() {
        return usuarios;
    }
}