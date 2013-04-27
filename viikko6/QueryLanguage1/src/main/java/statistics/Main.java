package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));

        Matcher m = new And(new HasAtLeast(10, "goals"),
                new HasAtLeast(10, "assists"),
                new PlaysIn("PHI"));

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println("or");
        m = new Or(new HasAtLeast(20, "goals"),
                new HasAtLeast(30, "assists"),
                new PlaysIn("PHI"));

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println("not");
        m = new And(new Not(new HasFewerThan(10, "goals"),
                new HasFewerThan(10, "assists")),
                new PlaysIn("PHI"));

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}
