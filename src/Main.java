void main() {
    Repository repository = new Repository();
    Service service = new Service(repository);
    Controller controller = new Controller(service);
    Menu menu = new Menu(controller);
    menu.ativaMenu();
}