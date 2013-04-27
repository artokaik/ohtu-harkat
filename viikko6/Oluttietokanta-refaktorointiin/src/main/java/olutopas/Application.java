package olutopas;


import olutopas.commands.Login;
import olutopas.services.Datamapper;

public class Application {

    private Datamapper mapper;
    private IO io;

    public Application(Datamapper mapper, IO io) {

        this.mapper = mapper;
        this.io = io;
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            mapper.seedDatabase();
        }
        CommandHandler ch = new CommandHandler(mapper, io, new Login(io, mapper).login());
        while (true) {
            menu();
            io.print("> ");
            String command = io.read();
            if (command.equals("q")) {
                break;
            } else {
                ch.suorita(command);
            }
            io.print("\npress enter to continue");
            io.read();
        }

        io.print("bye");
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find/rate beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   delete beer");
        //
        System.out.println("6   list beers");
        System.out.println("7   delete brewery");
        System.out.println("8   add brewery");
        //

        //
        System.out.println("9   show my ratings");
        System.out.println("0   list users");
        //
        System.out.println("q   quit");
        System.out.println("");
    }





}