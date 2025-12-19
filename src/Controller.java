import java.time.LocalDate;
import java.util.Scanner;

public class Controller {
    private Service service;
    Scanner scanner = new Scanner(System.in);

    public void criarUsuario(){
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Data de nascimento: ");
        LocalDate dataNasc = LocalDate.parse(scanner.nextLine());
        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        return new Produto(nome, dataNasc, precoProduto, cpf);
    }

    public int mostrarIdade(int pessoaId){
        return service.calcularIdade(pessoaId);
    }

    public String mostrarTodosUsuarios(){
        return service.listarTodosUsuarios().toString();
    }

    public String mostrarEnderecosPorId(int pessoaId){
        return service.listarEnderecosPorId(pessoaId).toString();
    }

    public void atualizarDadosPorId(int pessoaId, Pessoa dadosNovos){
        service.atualizarPessoa(pessoaId, dadosNovos);
    }

    public void removerUsuario(int pessoaId){
        service.removerPessoa(pessoaId);
    }
}
