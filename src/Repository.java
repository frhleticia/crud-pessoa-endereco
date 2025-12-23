import java.util.List;

public class Repository {
    private List<Pessoa> usuarios;
    private static int proximoId = 1;

    public void salvar(Pessoa pessoa){
        pessoa.setId(proximoId++);
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

    public void remover(int id){
        usuarios.remove(id);
    }

    public List<Pessoa> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Pessoa> usuarios) {
        this.usuarios = usuarios;
    }

    public static int getProximoId() {
        return proximoId;
    }

    public static void setProximoId(int proximoId) {
        Repository.proximoId = proximoId;
    }
}