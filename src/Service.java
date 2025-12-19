import java.util.List;

public class Service {
    private Repository repository;

    public void criarPessoa(Pessoa p){
        repository.salvar(p);
    }

    public List<Pessoa> listarTodosUsuarios(){
        return repository.mostrarTodosUsuarios();
    }

    public List<Endereco> listarEnderecosPorId(int pessoaId){
        var p = repository.buscarPorId(pessoaId);

        if (p == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }

        return p.getEnderecos();
    }

    public void atualizarPessoa(int pessoaId, Pessoa dadosNovos) {
        var p = repository.buscarPorId(pessoaId);
        if (p == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        p.setNome(dadosNovos.getNome());
        p.setDataNasc(dadosNovos.getDataNasc());
        p.setCpf(dadosNovos.getCpf());
        p.setEnderecos(dadosNovos.getEnderecos());
    }

    public void removerPessoa(int pessoaId){
        repository.remover(pessoaId);
    }

    public int calcularIdade(int pessoaId){
        var p = repository.buscarPorId(pessoaId);
        if (p == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        return p.calcularIdade();
    }

//    public void validarCpf(Pessoa cpf){
//        if (cpf != null){
//
//        }
//    }
}