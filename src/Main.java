void main() {
    Service service = new Service();
    Controller controller = new Controller(service);
    Menu menu = new Menu(controller);
    menu.ativaMenu();
}
