import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final Service service;
    Scanner scanner = new Scanner(System.in);

    public Controller(Service service) {
        this.service = service;
    }

    public void criarUsuario(){
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Data de nascimento (aaaa-mm-dd): ");
        LocalDate dataNasc = LocalDate.parse(scanner.nextLine());
        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("Rua: ");
        String rua = scanner.nextLine();
        System.out.println("Número: ");
        Long numero = Long.valueOf(scanner.nextLine());
        System.out.println("Bairro: ");
        String bairro = scanner.nextLine();
        System.out.println("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.println("Estado: ");
        String estado = scanner.nextLine();
        System.out.println("CEP: ");
        String cep = scanner.nextLine();

        Endereco e = service.criarEndereco(rua, numero, bairro, cidade, estado, cep);
        service.criarUsuario(nome, dataNasc, cpf, e);
    }

    public Endereco criarEndereco(){
        System.out.println("Rua: ");
        String rua = scanner.nextLine();
        System.out.println("Número: ");
        Long numero = Long.valueOf(scanner.nextLine());
        System.out.println("Bairro: ");
        String bairro = scanner.nextLine();
        System.out.println("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.println("Estado: ");
        String estado = scanner.nextLine();
        System.out.println("CEP: ");
        String cep = scanner.nextLine();

        return service.criarEndereco(rua, numero, bairro, cidade, estado, cep);
    }

    public void addNovoEnderecoAUmUsuario(int pessoaId){
        Pessoa p = service.buscarPessoaPorId(pessoaId);
        Endereco e = criarEndereco();
        List<Endereco> enderecos = p.getEnderecos();
        enderecos.add(e);
        p.setEnderecos(enderecos);
    }

    public int mostrarIdade(int pessoaId){
        return service.calcularIdade(pessoaId);
    }

    public String mostrarTodosUsuarios(){
        return service.listarTodosUsuarios();
    }

    public String mostrarEnderecosPorId(int pessoaId){
        return service.listarEnderecosPorId(pessoaId);
    }

    public void atualizarDadosPorId(int pessoaId, String nome, LocalDate dataNasc, String cpf){
        service.atualizarPessoa(pessoaId, nome, dataNasc, cpf);
    }


    public void removerUsuario(int pessoaId){
        service.removerPessoa(pessoaId);
    }
}