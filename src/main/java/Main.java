public class Main {
    public static void main(String[] args) {
        DatabaseInitService databaseInitService = new DatabaseInitService();
        databaseInitService.initDB();

        ClientService clientService = new ClientService();
        System.out.println(clientService.listAll());

        //create
        System.out.println(clientService.create("Apple"));

        //deleteById
        System.out.println(clientService.getById(6L));

        //setName
        clientService.setName(6L, "Google");
        System.out.println(clientService.getById(6L));

        //deleteById
        clientService.deleteById(4L);
        System.out.println(clientService.getById(4L));

        //listAll
        System.out.println(clientService.listAll());
    }
}

