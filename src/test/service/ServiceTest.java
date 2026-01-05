package service;

import entity.Endereco;
import entity.Pessoa;
import repository.Repository;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    //Criar usuário
    @Test
    void deveLancarExcecaoQuandoNomeNulo() {
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

    @Test
    void deveLancarExcecaoQuandoNomeBlank() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        try {
            service.criarUsuario("", LocalDate.of(2007, 12, 31), "12345678901", endereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    @Test
    void deveLancarExcecaoQuandoCpfNulo() {
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

    @Test
    void deveLancarExcecaoQuandoCpfBlank() {
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

    @Test
    void deveLancarExcecaoQuandoCpfRepetido() {
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

    @Test
    void deveLancarExcecaoQuandoCpfTamanhoInvalido() {
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

    @Test
    void deveSalvarUsuarioQuandoTodosDadosValidos() {
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

    //Criar endereço
    @Test
    void deveLancarExcecaoQuandoCepTamanhoInvalido() {
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

    @Test
    void deveCriarEnderecoQuandoTodosDadosValidos() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco e = service.criarEndereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        if (e == null) {
            System.out.println("Teste falhou: endereço inexistente");
        } else {
            System.out.println("Teste passou: endereço salvo devidamente");
        }
    }

    @Test
    void deveAtribuirNovoEnderecoAoUsuarioQuandoTodosDadosValidos() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco(null, null, null, null, null, null);
        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "12345678901", endereco);
        Endereco novoEndereco = service.criarEndereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.atribuirEnderecoAUmUsuario(1, novoEndereco);
        Pessoa p = service.buscarPessoaPorId(1);

        if (p.getEnderecos().size() > 1) {
            System.out.println("Teste passou: endereço novo salvo devidamente");
        } else {
            System.out.println("Teste falhou: endereço novo não foi salvo");
        }
    }

    //Buscar pessoa por id
    @Test
    void deveRetornarNuloQuandoPessoaInexistente() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        try {
            Pessoa p = service.buscarPessoaPorId(999);
            if (p == null) {
                throw new RuntimeException("Teste passou: pessoa inexistente retornou null");
            }
        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    @Test
    void deveRetornarPessoaQuandoExistente() {
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

    @Test
    void deveRetornarNuloQuandoListarEnderecosDePessoaInexistente() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        try {
            Pessoa p = service.buscarPessoaPorId(999);
            if (p == null) {
                throw new RuntimeException("Teste passou: pessoa não encontrada");
            }
            throw new RuntimeException("Teste falhou: pessoa encontrada indevidamente");
        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    @Test
    void deveRetornarListaDeEnderecosQuandoPessoaExistente() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        String listaDeEnderecos = service.listarEnderecosPorId(1);

        assertTrue(listaDeEnderecos.contains("Rua Gomes"));
    }

    //Remover pessoa
    @Test
    void deveLancarExcecaoQuandoRemoverPessoaInexistente() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        try {
            service.removerPessoa(999);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    @Test
    void deveRemoverPessoaQuandoPessoaExistenteForRemovida() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.criarUsuario("Joao", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        try {
            service.removerPessoa(1);
            service.buscarPessoaPorId(1);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
            System.out.println("Teste passou: " + e.getMessage());
        }
    }

    //Calcular idade
    @Test
    void deveLancarExcecaoQuandoCalcularIdadeDePessoaInexistente() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        assertThrows(RuntimeException.class, () -> service.calcularIdade(999));
    }

    @Test
    void deveRetornarIdadeQuandoPessoaExistentePedeOCalculo() {
        Repository repository = new Repository();
        Service service = new Service(repository);
        LocalDate dataHoje = LocalDate.now();

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.criarUsuario("Joao", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        Pessoa p = service.buscarPessoaPorId(1);

        int idadeEsperada = Period.between(p.getDataNasc(), dataHoje).getYears();
        int idadeCalculada = service.calcularIdade(1);

        assertEquals(idadeEsperada, idadeCalculada);
    }
}