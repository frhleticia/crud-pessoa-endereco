package service;

import repository.Repository;
import entity.Endereco;
import entity.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private final Repository repository;
    private int proximoPessoaId = 1;
    private int proximoEnderecoId = 1;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public void criarUsuario(String nome, LocalDate dataNasc, String cpf, Endereco endereco){
        validarNome(nome);
        validarCpf(cpf, -1);

        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco);

        Pessoa p = new Pessoa(nome, dataNasc, cpf, enderecos);
        p.setId(proximoPessoaId++);
        repository.salvar(p);
    }

    public void atribuirEnderecoAUmUsuario(int pessoaId, Endereco dadosDoEndereco){
        Pessoa p = validarPessoa(pessoaId);
        Endereco e = criarEndereco(dadosDoEndereco.getRua(), dadosDoEndereco.getNumero(), dadosDoEndereco.getBairro(), dadosDoEndereco.getCidade(), dadosDoEndereco.getEstado(), dadosDoEndereco.getCep());
        List<Endereco> enderecos = p.getEnderecos();
        enderecos.add(e);
    }

    public void setIdDoEndereco(Endereco endereco){
        endereco.setId(proximoEnderecoId++);
    }

    public Endereco criarEndereco(String rua, Long numero, String bairro, String cidade, String estado, String cep){
        if (cep.length() != 8){
            throw new RuntimeException("CEP inválido");
        }
        Endereco e = new Endereco(rua, numero, bairro, cidade, estado, cep);
        setIdDoEndereco(e);
        return e;
    }

    public String listarTodosUsuarios(){
        return repository.getUsuarios().toString();
    }

    public String listarEnderecosPorId(int pessoaId){
        var p = validarPessoa(pessoaId);

        return p.getEnderecos().toString();
    }

    public void atualizarPessoa(int pessoaId, String nome, LocalDate dataNasc, String cpf) {
        validarNome(nome);
        validarCpf(cpf, pessoaId);

        Pessoa p = validarPessoa(pessoaId);

        p.setNome(nome);
        p.setDataNasc(dataNasc);
        p.setCpf(cpf);
    }

    public void validarCpf(String cpf, int pessoaId){
        if (cpf == null || cpf.isBlank()){
            throw new RuntimeException("Campo CPF é obrigatório");
        }

        if (cpf.length() != 11){
            throw new RuntimeException("CPF deve conter 11 dígitos");
        }

        for (Pessoa p : repository.getUsuarios()){
            if (p.getCpf().equals(cpf) && p.getId() != pessoaId){
                throw new RuntimeException("CPF já cadastrado");
            }
        }
    }

    public void validarNome(String nome){
        if (nome == null || nome.isBlank()){
            throw new RuntimeException("Campo nome é obrigatório");
        }
    }

    public Pessoa validarPessoa(int pessoaId){
        for (Pessoa pessoa : repository.getUsuarios()){
            if (pessoa.getId() == pessoaId){
                return pessoa;
            }
        }
        throw new RuntimeException("Pessoa não encontrada");
    }

    public void removerPessoa(int pessoaId) {
        if (repository.getUsuarios().isEmpty()){
            throw new RuntimeException("Não há usuários para remover");
        }
        Pessoa p = validarPessoa(pessoaId);
        repository.remover(p);
    }

    public int calcularIdade(int pessoaId){
        Pessoa p = validarPessoa(pessoaId);
        return p.calcularIdade();
    }
}