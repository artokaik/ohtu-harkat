package olutopas;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.Pub;
import olutopas.model.Rating;
import olutopas.model.User;

public class Application {

    private EbeanServer server;
    private Scanner scanner = new Scanner(System.in);

    public Application(EbeanServer server) {
        this.server = server;
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }

        User user = login();

        System.out.println("Welcome to ratebeer, " + user.getUsername() + "!");

        while (true) {
            menu();
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("0")) {
                break;
            } else if (command.equals("1")) {
                findBrewery();
            } else if (command.equals("2")) {
                findBeer(user);
            } else if (command.equals("3")) {
                addBeer();
            } else if (command.equals("4")) {
                listBreweries();
            } else if (command.equals("5")) {
                deleteBeer();
            } else if (command.equals("6")) {
                this.listBeers();
            } else if (command.equals("7")) {
                this.addBrewery();
            } else if (command.equals("8")) {
                this.deleteBrewery();
            } else if (command.equals("9")) {
                this.addPub();
            } else if (command.equals("b")) {
                this.addBeerToPub();
            } else if (command.equals("s")) {
                this.showBeersInPub();
            } else if (command.equals("r")) {
                this.removeBeerFromPub();
            } else if (command.equals("t")) {
                this.showRatings(user);
            } else if (command.equals("y")) {
                this.listUsers();
            } else {
                System.out.println("unknown command");
            }

            System.out.print("\npress enter to continue");
            scanner.nextLine();
        }

        System.out.println("bye");
    }

    private void menu() {
        System.out.println("");
        System.out.println("1   find brewery");
        System.out.println("2   find beer");
        System.out.println("3   add beer");
        System.out.println("4   list breweries");
        System.out.println("5   delete beer");
        System.out.println("6   list beers");
        System.out.println("7   add brewery");
        System.out.println("8   delete brewery");
        System.out.println("9   add pub");
        System.out.println("b   add beer to pub");
        System.out.println("s   show beers in pub");
        System.out.println("s   remove beers from pub");
        System.out.println("t   delete brewery");
        System.out.println("y   list users");
        System.out.println("0   quit");
        System.out.println("");
    }

    // jos kanta on luotu uudelleen, suoritetaan tämä ja laitetaan kantaan hiukan dataa
    private void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);

        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);

        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());
        brewery = server.find(Brewery.class, brewery.getId());
        brewery.addBeer(b);
        server.save(brewery);

        server.save(new Brewery("Paulaner"));
    }

    private void findBeer(User user) {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBeer);
        System.out.println("  ratings given " + this.numberOfRatings(foundBeer) + " average given " + this.averageRating(foundBeer));
        if (this.userRating(user, foundBeer) == null) {
            System.out.println("  not available currently!");
            rateBeer(user, foundBeer);
        } else {
            System.out.println(userRating(user, foundBeer));
        }


    }

    private String userRating(User user, Beer beer) {
        Rating rating = server.find(Rating.class).where().eq("beer", beer).eq("user", user).findUnique();
        if (rating == null) {
            return null;
        } else {
            return rating.getValue() + " points";
        }

    }

    private void rateBeer(User user, Beer beer) {
        while (true) {
            System.out.print("give rating (leave emtpy if not): ");
            String rate = scanner.nextLine();
            System.out.println("");
            if (rate.isEmpty()) {
                return;
            }
            try {
                Integer value = Integer.parseInt(rate);
                if (value >= 0 && value <= 10) {
                    Rating rating = new Rating();
                    rating.setBeer(beer);
                    rating.setUser(user);
                    rating.setValue(value);
                    server.save(rating);
                    return;
                }
            } catch (Exception e) {
            }
            System.out.println("Enter value between 0 and 10");
        }
    }

    private void findBrewery() {
        System.out.print("brewery to find: ");
        String n = scanner.nextLine();
        Brewery foundBrewery = server.find(Brewery.class).where().like("name", n).findUnique();

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBrewery);
        for (Beer bier : foundBrewery.getBeers()) {
            System.out.println("   " + bier.getName());
        }
    }

    private void listBreweries() {
        List<Brewery> breweries = server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }

    private void addBeer() {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = server.find(Beer.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        server.save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }

    private void deleteBeer() {
        System.out.print("beer to delete: ");
        String n = scanner.nextLine();
        Beer beerToDelete = server.find(Beer.class).where().like("name", n).findUnique();

        if (beerToDelete == null) {
            System.out.println(n + " not found");
            return;
        }

        server.delete(beerToDelete);
        System.out.println("deleted: " + beerToDelete);

    }

    private void listBeers() {
        List<Beer> beers = server.find(Beer.class).findList();
        for (Beer beer : beers) {
            System.out.println(beer);
            System.out.println("  ratings given " + this.numberOfRatings(beer) + " average given " + this.averageRating(beer));
        }
    }

    private void addBrewery() {
        System.out.print("Name of the brewery: ");
        String name = scanner.nextLine();

        Brewery exists = server.find(Brewery.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        Brewery brewery = new Brewery();
        brewery.setName(name);

        server.save(brewery);
        System.out.println(name + " added ");
    }

    private void deleteBrewery() {
        System.out.print("brewery to delete: ");
        String brewery = scanner.nextLine();
        Brewery breweryToDelete = server.find(Brewery.class).where().like("name", brewery).findUnique();

        if (breweryToDelete == null) {
            System.out.println(brewery + " not found");
            return;
        }

        server.delete(breweryToDelete);
        System.out.println("deleted: " + breweryToDelete);

    }

    private boolean addUser() {
        System.out.println("Register a new user");
        System.out.print("give username: ");
        String name = this.scanner.nextLine();

        User newUser = server.find(User.class).where().like("username", name).findUnique();

        if (newUser == null) {
            newUser = new User();
            newUser.setUsername(name);
            server.save(newUser);
            System.out.println("user created!");
            return true;
        }
        System.out.println("Username " + name + " is already in the system");
        return false;
    }

    private User login() {
        while (true) {
            System.out.println("Login (give ? to register a new user)");
            System.out.print("username: ");
            String username = this.scanner.nextLine();
            User user = server.find(User.class).where().like("username", username).findUnique();

            if (username.equals("?")) {
                while (!addUser()) {
                }
            } else if (user == null) {
                System.out.println("Username " + username + " is not in the system");
            } else {
                return user;
            }
        }

    }

    private void listUsers() {
        List<User> users = server.find(User.class).findList();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private double averageRating(Beer beer) {
        List<Rating> ratings = server.find(Rating.class).where().eq("beer", beer).findList();
        double total = 0;
        for (Rating rating : ratings) {
            total += rating.getValue();
        }
        return total / ratings.size();
    }

    private int numberOfRatings(Beer beer) {
        List<Rating> ratings = server.find(Rating.class).where().eq("beer", beer).findList();
        return ratings.size();
    }

    private void showRatings(User user) {
        System.out.println("Ratings by " + user.getUsername());
        List<Rating> ratings = server.find(Rating.class).where().eq("user", user).findList();
        for (Rating rating : ratings) {
            System.out.println(rating.getBeer() + " " + rating.getValue() + " points");
        }
    }

    private void addPub() {
        System.out.print("pub to add: ");

        String name = scanner.nextLine();

        Pub exists = server.find(Pub.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        server.save(new Pub(name));
    }

    private void addBeerToPub() {
        System.out.print("beer: ");
        String name = scanner.nextLine();
        Beer beer = server.find(Beer.class).where().like("name", name).findUnique();

        if (beer == null) {
            System.out.println("does not exist");
            return;
        }

        System.out.print("pub: ");
        name = scanner.nextLine();
        Pub pub = server.find(Pub.class).where().like("name", name).findUnique();

        if (pub == null) {
            System.out.println("does not exist");
            return;
        }

        pub.addBeer(beer);
        server.save(pub);
    }

    private void showBeersInPub() {
        System.out.print("pub: ");
        String name = scanner.nextLine();
        Pub pub = server.find(Pub.class).where().like("name", name).findUnique();
        List<Beer> beers = server.find(Beer.class).where().eq("pubs", pub).findList();
        for (Beer beer : beers) {
            System.out.println(beer);
        }
    }

    private void ListPubs() {
        List<Pub> pubs = server.find(Pub.class).findList();
        for (Pub pub : pubs) {
            System.out.println(pub);
        }
    }

    private void removeBeerFromPub() {
        System.out.print("beer: ");
        String name = scanner.nextLine();
        Beer beer = server.find(Beer.class).where().like("name", name).findUnique();

        if (beer == null) {
            System.out.println("does not exist");
            return;
        }

        System.out.print("pub: ");
        name = scanner.nextLine();
        Pub pub = server.find(Pub.class).where().like("name", name).findUnique();

        if (pub == null) {
            System.out.println("does not exist");
            return;
        }

        pub.removeBeer(beer);
        server.save(pub);

    }
}
