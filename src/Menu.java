import java.util.Scanner;

public class Menu {
    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);
    private int opcaoMenu;
    private int opcaoMenuA;
    private int opcaoMenuM;

    public Menu(Controller controller) {
        this.controller = controller;
    }

    public void ativaMenu(){
        do {
            controlaMenus();
        } while (opcaoMenu != 0);
    }

    public void controlaMenus(){
        System.out.println("""
                (1) Opções de alteração (Criar, alterar, e excluir).
                (2) Visualizar informações (Lista de usuários ou endereços, e idade).
                (0) Sair.""");
        opcaoMenu = scanner.nextInt();
        switch (opcaoMenu){
            case 1:
                do {
                    menuAlterar();
                } while (opcaoMenuA != 0);
                break;
            case 2:
                do {
                    menuMostrar();
                } while (opcaoMenuM != 0);
                break;
            default:
                System.out.println("Opção inválida. Digite 1, 2, ou 3 de acordo com as intruções.");
                break;
        }
    }

    public void menuAlterar(){
        System.out.println("""
                (1) Criar usuário com seu primeiro endereço.
                (2) Atualizar (todos) os dados.
                (3) Deletar usuário.
                (0) Sair.""");
        opcaoMenuA = scanner.nextInt();
        switch (opcaoMenuA){
            case 1:
                controller.criarUsuario();
                break;
            case 2:
                System.out.println("Id do usuário que terá todos os dados atualizados: ");
                int pessoaIdParaAtualizar = scanner.nextInt();
                Pessoa dadosNovos = controller.criarUsuario();

                controller.atualizarDadosPorId(pessoaIdParaAtualizar, dadosNovos);
                break;
            case 3:
                System.out.println("Id do usuário que terá todos os dados atualizados: ");
                int pessoaIdParaDeletar = scanner.nextInt();

                controller.removerUsuario(pessoaIdParaDeletar);
                break;
            default:
                System.out.println("Opção inválida. Digite 1, 2, ou 3 de acordo com as intruções.");
                break;
        }
    }

    public void menuMostrar(){
        System.out.println("""
                (1) Ver todos os usuários.
                (2) Ver todos os endereços por usuário.
                (3) Ver idade de um usuário.
                (0) Sair.""");
        opcaoMenuM = scanner.nextInt();
        switch (opcaoMenuM){
            case 1:
                controller.mostrarTodosUsuarios();
                break;
            case 2:
                System.out.println("Id: ");
                int pessoaIdMostrarEndereco = scanner.nextInt();

                controller.mostrarEnderecosPorId(pessoaIdMostrarEndereco);
                break;
            case 3:
                System.out.println("Id: ");
                int pessoaIdMostrarIdade = scanner.nextInt();

                controller.mostrarIdade(pessoaIdMostrarIdade);
                break;
        }
    }
}
