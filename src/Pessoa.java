import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Pessoa {
    private int id;
    private String nome;
    private LocalDate dataNasc;
    private String cpf;
    private List<Endereco> enderecos;

    public Pessoa(String nome, LocalDate dataNasc, String cpf, List<Endereco> enderecos) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.enderecos = enderecos;
    }

    public int calcularIdade(){
        LocalDate dataHoje = LocalDate.now();
        return Period.between(this.getDataNasc(), dataHoje).getYears();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
