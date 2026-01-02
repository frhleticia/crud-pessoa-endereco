package service;

import entity.Endereco;
import entity.Pessoa;
import service.Service;
import repository.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        }
    }

    static void deveLancarExcecaoQuandoCPFNulo() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        try {
            service.criarUsuario("João", LocalDate.of(2007, 12, 31), null, endereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
        }
    }

    static void deveLancarExcecaoQuandoCPFBlank() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        try {
            service.criarUsuario("João", LocalDate.of(2007, 12, 31), " ", endereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
        }
    }

    static void deveLancarExcecaoQuandoCPFRepetido() {
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
        }
    }

    static void deveLancarExcecaoQuandoCPFTamanhoInvalido() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345678");

        try {
            service.criarUsuario("João", LocalDate.of(2007, 12, 31), "123456789", endereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
        }
    }

    static void deveLancarExcecaoQuandoCEPTamanhoInvalido() {
        Repository repository = new Repository();
        Service service = new Service(repository);

        Endereco endereco = new Endereco("Rua Gomes", 231L, "Limoeiro", "Porto", "BA", "12345");

        try {
            service.criarUsuario("João", LocalDate.of(2007, 12, 31), "123456789", endereco);
            throw new RuntimeException(
                    "Teste falhou: deveria ter lançado exceção");

        } catch (RuntimeException e) {
        }
    }

    public static void main(String[] args) {
        deveLancarExcecaoQuandoNomeNulo();
        deveLancarExcecaoQuandoNomeBlank();
        deveLancarExcecaoQuandoCPFNulo();
        deveLancarExcecaoQuandoCPFBlank();
        deveLancarExcecaoQuandoCPFRepetido();
        deveLancarExcecaoQuandoCPFTamanhoInvalido();
    }
}