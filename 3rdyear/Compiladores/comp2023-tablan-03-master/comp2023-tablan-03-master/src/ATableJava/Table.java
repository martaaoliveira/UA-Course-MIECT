package ATableJava;


public class Table extends ATable {
    public Table(String[] headers, ColumnType[] columnTypes) {
        super(headers, columnTypes);
    }

    public Table(String[] headers, ColumnType[] columnTypes, String title) {
        super(headers, columnTypes, title);
    }

    public Table(String title) {
        super(title);
    }

}