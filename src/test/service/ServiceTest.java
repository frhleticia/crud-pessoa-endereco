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

    public static void main(String[] args) {
        deveLancarExcecaoQuandoNomeNulo();
        deveLancarExcecaoQuandoNomeBlank();
    }
}