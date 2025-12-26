import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(Controller controller) {
        this.controller = controller;
    }

    private int lerInt(String msg){
        System.out.println(msg);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private String lerString(String msg){
        System.out.println(msg);
        return scanner.nextLine();
    }

    public void ativaMenu(){
        int opcao;
        do {
            opcao = controlaMenus();
        } while (opcao != 0);
    }

    public int controlaMenus(){
        int opcaoMenu = lerInt("""
                (1) Opções de alteração (Criar, alterar, e excluir).
                (2) Visualizar informações (Lista de usuários ou endereços, e idade).
                (0) Sair.""");

        switch (opcaoMenu){
            case 1:
                menuAlterar();
                break;
            case 2:
                menuMostrar();
                break;
        }
        return opcaoMenu;
    }

    public void menuAlterar(){
        int opcaoMenuA;
        do {
            opcaoMenuA = lerInt("""
                    (1) Criar usuário com seu primeiro endereço.
                    (2) Adicionar novo endereço a um usuário.
                    (3) Atualizar dados.
                    (4) Deletar usuário.
                    (-1) Voltar.""");
            switch (opcaoMenuA) {
                case 1:
                    controller.criarUsuario();
                    break;
                case 2: {
                    int id = lerInt("Id do usuário: ");

                    controller.addNovoEnderecoAUmUsuario(id);
                    break;
                }
                case 3: {
                    int id = lerInt("Id: ");

                    String nome = lerString("Nome: ");

                    System.out.println("Data de nascimento (aaaa-mm-dd): ");
                    LocalDate dataNasc = LocalDate.parse(scanner.nextLine());

                    String cpf = lerString("CPF: ");

                    controller.atualizarDadosPorId(id, nome, dataNasc, cpf);
                    break;
                }
                case 4: {
                    int id = lerInt("Id do usuário: ");

                    controller.removerUsuario(id);
                    break;
                }
            }
        } while (opcaoMenuA != -1);
    }

    public void menuMostrar(){
        int opcaoMenuM;
        do {
            opcaoMenuM = lerInt("""
                    (1) Ver todos os usuários.
                    (2) Ver idade de um usuário.
                    (3) Ver todos os endereços de um usuário.
                    (-1) Voltar.""");
            switch (opcaoMenuM) {
                case 1:
                    System.out.println(controller.mostrarTodosUsuarios());
                    break;
                case 2: {
                    int id = lerInt("Id do usuário: ");

                    System.out.println(controller.mostrarIdade(id));
                    break;
                }
                case 3: {
                    int id = lerInt("Id do usuário: ");

                    System.out.println(controller.mostrarEnderecosPorId(id));
                    break;
                }
            }
        } while (opcaoMenuM != -1);
    }
}