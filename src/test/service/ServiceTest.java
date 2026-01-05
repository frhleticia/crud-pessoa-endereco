package service;

import entity.Endereco;
import entity.Pessoa;
import repository.Repository;

import java.time.LocalDate;

public class ServiceTest {

    static void deveLancarExcecaoQuandoNomeNulo() {
        //Arranjo...dado
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        //Ação...quando & Verificação...então
        try {
            service.criarUsuario(null, LocalDate.of(2007, 12, 31), "12345678901", endereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    static void deveLancarExcecaoQuandoNomeBlank() {
        //Arranjo...dado
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        //Ação...quando & Verificação...então
        try {
            service.criarUsuario("", LocalDate.of(2007, 12, 31), "12345678901", endereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    static void deveLancarExcecaoQuandoCpfNulo() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        try {
            service.criarUsuario("João", LocalDate.of(2007, 12, 31), null, endereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    static void deveLancarExcecaoQuandoCpfBlank() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        try {
            service.criarUsuario("João", LocalDate.of(2007, 12, 31), " ", endereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    static void deveLancarExcecaoQuandoCpfRepetido() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        Endereco outroEndereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        try {
            service.criarUsuario("João", LocalDate.of(2007, 12, 31), "11122233344", endereco);
            service.criarUsuario("Joana", LocalDate.of(2005, 1, 3), "11122233344", outroEndereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    static void deveLancarExcecaoQuandoCpfTamanhoInvalido() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        try {
            service.criarUsuario("João", LocalDate.of(2007, 12, 31), "123456789", endereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    static void deveSalvarUsuarioQuandoTodosDadosValidos() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        Pessoa p = service.buscarPessoaPorId(1);

        if (p == null) {
            System.out.println("Teste falhou: pessoa inexistente");
        } else {
            System.out.println("Teste passou: pessoa salva devidamente");
        }
    }

    static void deveLancarExcecaoQuandoCepTamanhoInvalido() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        try {
            service.criarEndereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345");
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    static void deveCriarEnderecoQuandoTodosDadosValidos() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco e = service.criarEndereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        if (e == null) {
            System.out.println("Teste falhou: endereço inexistente");
        } else {
            System.out.println("Teste passou: endereço salvo devidamente");
        }
    }

    static void deveRetornarNuloQuandoPessoaInexistente() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Pessoa p = service.buscarPessoaPorId(999);

        if (p == null) {
            System.out.println("Teste passou: pessoa inexistente retornou null");
        } else {
            System.out.println("Teste falhou: pessoa deveria ser null");
        }
    }

    static void deveRetornarPessoaQuandoExistente() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco(null, null, null, null, null, null);
        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        Pessoa p = service.buscarPessoaPorId(1);

        if (p == null) {
            System.out.println("Teste falhou: pessoa existente retornou null");
        } else {
            System.out.println("Teste passou: pessoa existente encontrada");
        }
    }

    static void deveRetornarNuloQuandoListarEnderecosDePessoaInexistente() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        try {
            Pessoa p = service.buscarPessoaPorId(999);
            if (p == null) {
                throw new RuntimeException("Teste passou: pessoa não encontrada");
            }
            throw new RuntimeException("Teste falhou: pessoa encontrada indevidamente");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    static void deveRetornarListaDeEnderecosQuandoDePessoaExistente() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco(null, null, null, null, null, null);
        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        try {
            service.criarEndereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
            Pessoa p = service.buscarPessoaPorId(1);
            if (p == null) {
                throw new RuntimeException("Teste passou: pessoa não encontrada");
            }
            throw new RuntimeException("Teste falhou: pessoa encontrada indevidamente");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    static void deveLancarExcecaoQuandoRemoverPessoaInexistente() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.criarUsuario("Joao", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        try {
            service.removerPessoa(999);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //Criar usuário
        deveLancarExcecaoQuandoNomeNulo();
        deveLancarExcecaoQuandoNomeBlank();
        deveLancarExcecaoQuandoCpfNulo();
        deveLancarExcecaoQuandoCpfBlank();
        deveLancarExcecaoQuandoCpfRepetido();
        deveLancarExcecaoQuandoCpfTamanhoInvalido();
        deveSalvarUsuarioQuandoTodosDadosValidos();
        //Criar endereço
        deveLancarExcecaoQuandoCepTamanhoInvalido();
        deveCriarEnderecoQuandoTodosDadosValidos();
        //Buscar pessoa por id
        deveRetornarNuloQuandoPessoaInexistente();
        deveRetornarPessoaQuandoExistente();
        deveRetornarNuloQuandoListarEnderecosDePessoaInexistente();
        //Remover pessoa
        deveLancarExcecaoQuandoRemoverPessoaInexistente();
    }
}

//Endereco endereco = new Endereco(null, null, null, null, null, null);
//        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "12345678901", endereco);