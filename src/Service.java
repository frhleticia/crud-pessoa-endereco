import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private Repository repository;

    public Pessoa procurarPessoaPorId(int pessoaId){
        for (Pessoa pessoa : repository.getUsuarios()){
            if (!(pessoa.getId() == pessoaId)){
                throw new RuntimeException("Pessoa não encontrada");
            }
            return pessoa;
        }
        return null;
    }

    public Pessoa criarUsuario(String nome, LocalDate dataNasc, String cpf, Endereco endereco){

        if (nome.isBlank()){
            throw new RuntimeException("Campo nome é obrigatório");
        }

        if (cpf.isBlank()){
            throw new RuntimeException("Campo CPF é obrigatório");
        }

        for (Pessoa p : repository.getUsuarios()){
            if (cpf.equals(p.getCpf())){
                throw new RuntimeException("CPF já cadastrado");
            }
        }

        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco);

        Pessoa p = new Pessoa(nome, dataNasc, cpf, enderecos);
        repository.salvar(p);
        return p;
    }

    public Endereco criarEndereco(String rua, Long numero, String bairro, String cidade, String estado, String cep){

        if (cep.length() != 8){
            throw new RuntimeException("CEP inválido");
        }

        return new Endereco(rua, numero, bairro, cidade, estado, cep);
    }

    public List<Pessoa> listarTodosUsuarios(){
        return repository.mostrarTodosUsuarios();
    }

    public String listarEnderecosPorId(int pessoaId){
        var p = repository.buscarPorId(pessoaId);

        if (p == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }

        return p.getEnderecos().toString();
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
        for (Pessoa pessoa : repository.getUsuarios()){
            if (!(pessoa.getId() == pessoaId)){
                throw new RuntimeException("Pessoa não encontrada");
            }
        }
        repository.remover(pessoaId);
    }

    public int calcularIdade(int pessoaId){
        var p = repository.buscarPorId(pessoaId);
        if (p == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        return p.calcularIdade();
    }
}