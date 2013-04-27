package statistics;

import statistics.matcher.*;
import statistics.queries.QueryBuilder;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));

        Matcher m = new And(new HasAtLeast(10, "goals"),
                new HasAtLeast(10, "assists"),
                new PlaysIn("PHI"));

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println("builder");
        QueryBuilder query = new QueryBuilder();

        m = query.hasAtLeast(10, "goals").hasAtLeast(10, "assists").playsIn("PHI").build();

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println("or");

        m = new Or(new And(new HasAtLeast(10, "goals"),
                new HasAtLeast(10, "assists"),
                new PlaysIn("PHI")), new And(new HasAtLeast(10, "goals"),
                new HasAtLeast(10, "assists"),
                new PlaysIn("OTT")));

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        System.out.println("or build");
         m = query.oneOf(query.hasAtLeast(10, "goals").hasAtLeast(10, "assists").playsIn("PHI").build(), 
                 query.hasAtLeast(10, "goals").hasAtLeast(10, "assists").playsIn("OTT").build()).build();

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
