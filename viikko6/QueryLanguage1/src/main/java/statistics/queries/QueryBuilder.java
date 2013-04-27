/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.queries;

import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Not;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

/**
 *
 * @author Arto
 */
public class QueryBuilder {

    private QueryBuilder builder;
    private Matcher matcher;

    public QueryBuilder(QueryBuilder builder, Matcher matcher) {
        this.matcher = matcher;
        this.builder = builder;
    }

    public QueryBuilder() {
    }

    public QueryBuilder playsIn(String team) {
        return new QueryBuilder(this, new PlaysIn(team));
    }

    public QueryBuilder hasAtLeast(int value, String fieldName) {
        return new QueryBuilder(this, new HasAtLeast(value, fieldName));
    }

    public QueryBuilder hasFewerThan(int value, String fieldName) {
        return new QueryBuilder(this, new HasFewerThan(value, fieldName));
    }

    public QueryBuilder oneOf(Matcher... args) {
        Matcher m = new Or(args);
        return new QueryBuilder(this, m);
    }

    public QueryBuilder noneOf(Matcher... args) {
        Matcher m = new Not(args);
        return new QueryBuilder(this, m);
    }

    public Matcher build() {
        if (this.builder.matcher == null) {
            return this.matcher;
        }
        return new And(this.matcher, this.builder.build());
    }
}
