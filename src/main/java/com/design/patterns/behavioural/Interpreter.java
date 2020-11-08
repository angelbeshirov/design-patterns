package design.behavioural;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author angel.beshirov
 */
interface Expression {

    List<String> interpret(Context context);
}

class Row {
    private String col1;
    private String col2;
    private String col3;

    public Row(String col1, String col2, String col3) {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
    }

    public String getCol1() {
        return col1;
    }

    public String getCol2() {
        return col2;
    }

    public String getCol3() {
        return col3;
    }

    @Override
    public String toString() {
        return col1 + ',' + col2 + ',' + col3;
    }
}

class Select implements Expression {
    private Integer column;
    private Expression from;

    public Select(Integer column, Expression from) {
        this.column = column;
        this.from = from;
    }

    @Override
    public List<String> interpret(Context context) {
        context.setColumn(column);
        return from.interpret(context);
    }
}

class From implements Expression {
    private String table;
    private Expression where;

    public From(String table, Expression where) {
        this.table = table;
        this.where = where;
    }

    @Override
    public List<String> interpret(Context context) {
        context.setTable(table);
        if(where == null) {
            return context.search();
        }

        return where.interpret(context);
    }
}

class Where implements Expression {
    private Predicate<Row> filter;

    public Where(Predicate<Row> filter) {
        this.filter = filter;
    }

    @Override
    public List<String> interpret(Context context) {
        context.setFilter(filter);
        return context.search();
    }
}

class Context {
    private static Map<String, List<Row>> inMemoryDatabase = new HashMap<>();

    static {
        List<Row> people = new ArrayList<>();
        people.add(new Row("Ivan", "Test", "Something"));
        people.add(new Row("Georgi", "Test1", "Something4"));
        people.add(new Row("Todor", "Test2", "Something3"));
        inMemoryDatabase.put("people", people);
    }

    private String table;
    private Integer column;
    private Predicate<Row> filter = (row -> true);;

    public void setTable(String table) {
        this.table = table;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public void setFilter(Predicate<Row> filter) {
        this.filter = filter;
    }

    public List<String> search() {
        Function<Row, Stream<String>> columnMapper = (Row row) -> { // function R -> K
            String result = "";
            if(column == 1) {
                result = row.getCol1();
            }

            if(column == 2) {
                result = row.getCol2();
            }

            if(column == 3) {
                result = row.getCol3();
            }

            return Stream.of(result);
        };

        List<String> result = inMemoryDatabase.entrySet()
                .stream()
                .filter((Map.Entry<String, List<Row>> entry) -> entry.getKey().equals(table))
                .flatMap(entry -> Stream.of(entry.getValue()))
                .flatMap(Collection::stream)
                .filter(filter)
                .flatMap(columnMapper)
                .collect(Collectors.toList());

        clear();

        return result;
    }

    void clear() {
        this.column = null;
        this.filter = (row -> true);
        this.table = null;
    }

    // we want to be able to do search based on conditions

    public static void main(String... args) {
        Context context = new Context();
        Expression expression = new Select(1, new From("people", null));

        expression.interpret(context).stream().forEach(System.out::println);

        Expression expression2 = new Select(1, new From("people", new Where((row) -> row.getCol1().equals("Ivan"))));
        expression2.interpret(context).stream().forEach(System.out::println);
    }
}

