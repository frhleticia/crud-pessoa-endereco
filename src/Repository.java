import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Pessoa> usuarios = new ArrayList<>();

    public void salvar(Pessoa pessoa){
        usuarios.add(pessoa);
    }

    public Pessoa buscarPorId(int id){
        for (Pessoa pessoa : usuarios){
            if (pessoa.getId() == id){
                return pessoa;
            }
        }
        return null;
    }

    public List<Pessoa> mostrarTodosUsuarios(){
        return usuarios;
    }

    public void remover(Pessoa pessoa){
        usuarios.remove(pessoa);
    }

    public List<Pessoa> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Pessoa> usuarios) {
        this.usuarios = usuarios;
    }
}