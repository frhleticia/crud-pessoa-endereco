import controller.Controller;
import menu.Menu;
import repository.Repository;
import service.Service;

public class Main {

    public static void main(String[] args) {
        Repository repository = new Repository();
        Service service = new Service(repository);
        Controller controller = new Controller(service);

        Menu menu = new Menu(controller);
        menu.ativaMenu();
    }
}