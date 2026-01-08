package service;

import entity.Endereco;
import entity.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import repository.Repository;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    private Service service;

    @BeforeEach
    void setUp() {
        Repository repository = new Repository();
        service = new Service(repository);
    }

    //Criar usuário
    @Test
    void deveLancarExcecaoQuandoNomeNulo() {

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        assertThrows(RuntimeException.class, () -> service.criarUsuario(null, LocalDate.of(2007, 12, 31), "12345678901", endereco));
    }

    @Test
    void deveLancarExcecaoQuandoNomeBlank() {

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        assertThrows(RuntimeException.class, () -> service.criarUsuario("", LocalDate.of(2007, 12, 31), "12345678901", endereco));
    }

    @Test
    void deveLancarExcecaoQuandoCpfNulo() {

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        assertThrows(RuntimeException.class, () -> service.criarUsuario("João", LocalDate.of(2007, 12, 31), null, endereco));
    }

    @Test
    void deveLancarExcecaoQuandoCpfBlank() {

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        assertThrows(RuntimeException.class, () -> service.criarUsuario("João", LocalDate.of(2007, 12, 31), " ", endereco));
    }

    @Test
    void deveLancarExcecaoQuandoCpfRepetido() {

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        Endereco outroEndereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "11122233344", endereco);

        assertThrows(RuntimeException.class, () -> service.criarUsuario("Joana", LocalDate.of(2005, 1, 3), "11122233344", outroEndereco));
    }

    @Test
    void deveLancarExcecaoQuandoCpfTamanhoInvalido() {

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        assertThrows(RuntimeException.class, () -> service.criarUsuario("João", LocalDate.of(2007, 12, 31), "123456789", endereco));
    }

    @Test
    void deveSalvarUsuarioQuandoTodosDadosValidos() {

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        Pessoa p = service.validarPessoa(1);

        assertNotNull(p);
    }

    //Atualizar pessoa
    @Test
    void deveLancarExcecaoQuandoCpfRepetidoAoAtualizarPessoa() {

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "11122233344", endereco);

        service.criarUsuario("Joana", LocalDate.of(2002, 2, 10), "55522233344", endereco);

        assertThrows(RuntimeException.class, () -> service.atualizarPessoa(1, "João", LocalDate.of(2005, 1, 3), "55522233344"));
    }

    //Criar endereço
    @Test
    void deveLancarExcecaoQuandoCepTamanhoInvalido() {

        assertThrows(RuntimeException.class, () -> service.criarEndereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345"));
    }

    @Test
    void deveCriarEnderecoQuandoTodosDadosValidos() {

        Endereco e = service.criarEndereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        assertNotNull(e);
    }

    @Test
    void deveAtribuirNovoEnderecoAoUsuarioQuandoTodosDadosValidos() {

        Endereco endereco = new Endereco(null, null, null, null, null, null);
        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        Endereco novoEndereco = service.criarEndereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.atribuirEnderecoAUmUsuario(1, novoEndereco);

        Pessoa p = service.validarPessoa(1);

        assertEquals(2, p.getEnderecos().size());
    }

    //Buscar pessoa por id
    @Test
    void deveRetornarNuloQuandoPessoaInexistente() {

        assertThrows(RuntimeException.class, () -> service.validarPessoa(999));
    }

    @Test
    void deveRetornarPessoaQuandoExistente() {

        Endereco endereco = new Endereco(null, null, null, null, null, null);
        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        Pessoa p = service.validarPessoa(1);

        assertNotNull(p);
    }

    @Test
    void deveLancarExcecaoQuandoListarEnderecosDePessoaInexistente() {

        assertThrows(RuntimeException.class, () -> service.listarEnderecosPorId(999));
    }

    @Test
    void deveRetornarListaDeEnderecosQuandoPessoaExistente() {

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.criarUsuario("João", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        String listaDeEnderecos = service.listarEnderecosPorId(1);

        assertTrue(listaDeEnderecos.contains("Rua Gomes"));
    }

    //Remover pessoa
    @Test
    void deveLancarExcecaoQuandoRemoverPessoaInexistente() {

        assertThrows(RuntimeException.class, () -> service.removerPessoa(999));
    }

    @Test
    void deveRemoverPessoaQuandoPessoaExistenteForRemovida() {

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.criarUsuario("Joao", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        service.removerPessoa(1);

        assertThrows(RuntimeException.class, () -> service.validarPessoa(1));
    }

    @Test
    void deveLancarExcecaoQuandoTentaRemoverComAListaVazia() {

        assertThrows(RuntimeException.class, () -> service.removerPessoa(1));
    }

    //Calcular idade
    @Test
    void deveLancarExcecaoQuandoCalcularIdadeDePessoaInexistente() {

        assertThrows(RuntimeException.class, () -> service.calcularIdade(999));
    }

    @Test
    void deveRetornarIdadeQuandoPessoaExistentePedeOCalculo() {

        LocalDate dataHoje = LocalDate.now();

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");
        service.criarUsuario("Joao", LocalDate.of(2007, 12, 31), "12345678901", endereco);

        Pessoa p = service.validarPessoa(1);

        int idadeEsperada = Period.between(p.getDataNasc(), dataHoje).getYears();
        int idadeCalculada = service.calcularIdade(1);

        assertEquals(idadeEsperada, idadeCalculada);
    }
}