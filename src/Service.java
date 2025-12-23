import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public Pessoa criarUsuario(String nome, LocalDate dataNasc, String cpf, Endereco endereco){

        validarNome(nome);

        validarCpf(cpf, -1);

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

    public Pessoa buscarPessoaPorId(int pessoaId){
        return repository.buscarPorId(pessoaId);
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

    public void atualizarPessoa(int pessoaId, String nome, LocalDate dataNasc, String cpf) {
        Pessoa p = repository.buscarPorId(pessoaId);
        if (p == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }

        if (nome == null || nome.isBlank()){
            throw new RuntimeException("Campo nome é obrigatório");
        }

        validarCpf(cpf, pessoaId);

        p.setNome(nome);
        p.setDataNasc(dataNasc);
        p.setCpf(cpf);
    }

    public void validarCpf(String cpf, int pessoaId){
        if (cpf == null || cpf.isBlank()){
            throw new RuntimeException("Campo CPF é obrigatório");
        }

        for (Pessoa p : repository.getUsuarios()){
            if (p.getCpf().equals(cpf) && p.getId() != pessoaId){
                throw new RuntimeException("CPF já cadastrado");
            }
        }

        if (cpf.length() != 11){
            throw new RuntimeException("CPF deve conter 11 dígitos");
        }
    }

    public void validarNome(String nome){
        if (nome == null || nome.isBlank()){
            throw new RuntimeException("Campo nome é obrigatório");
        }
    }

    public void removerPessoa(int pessoaId) {
        Pessoa p = repository.buscarPorId(pessoaId);
        if (p == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        repository.remover(p);
    }

    public int calcularIdade(int pessoaId){
        var p = repository.buscarPorId(pessoaId);
        if (p == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        return p.calcularIdade();
    }
}