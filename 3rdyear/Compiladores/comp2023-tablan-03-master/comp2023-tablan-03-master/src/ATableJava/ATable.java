package ATableJava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Console;
import java.util.function.Function;

public class ATable {
    private List<Map<String, Object>> rows = new ArrayList<>();
    private Map<String, String> columnTitles = new HashMap<>();
    private ColumnType[] columnTypes;
    private String[] headers;
    private String title;
    private Map<String, Function<Map<String, Object>, Object>> columnFormulas = new HashMap<>();

    public ATable(String[] headers, ColumnType[] columnTypes,
            Map<String, Function<Map<String, Object>, Object>> columnFormulas) {
        this.headers = headers;
        this.columnTypes = columnTypes;
        this.columnFormulas = columnFormulas;
    }

    public ATable(String[] headers, ColumnType[] columnTypes) {
        this.headers = headers;
        this.columnTypes = columnTypes;
    }

    public ATable(String[] headers, ColumnType[] columnTypes, String title) {
        this.headers = headers;
        this.columnTypes = columnTypes;
        this.title = title;
    }

    public ATable(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTile() {
        return title;
    }

    public Object get(String columnName) {
        if (rows.size() == 0) {
            return null;
        }
        return rows.get(0).get(columnName);
    }
    

    public List<Map<String, Object>> getRows() {
        if (rows == null) {
            return new ArrayList<>();
        }
        return rows;
    }

    public ATable view(String columnName) {
        // Primeiro, encontramos o índice da coluna desejada.
        int columnIndex = -1;
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equalsIgnoreCase(columnName)) {
                columnIndex = i;
                break;
            }
        }

        if (columnIndex == -1) {
            throw new IllegalArgumentException("No such column: " + columnName);
        }

        // criamos a nova tabel com apenas uma coluna
        String[] newHeaders = { columnName };
        ColumnType[] newColumnTypes = { columnTypes[columnIndex] };
        ATable newTable = new ATable(newHeaders, newColumnTypes);

        for (Map<String, Object> row : rows) {
            Object value = row.get(headers[columnIndex]);
            newTable.addRow(value);
        }

        return newTable;
    }

    public void setColumnTitle(String columnName, String title) {
        for (String header : headers) {
            if (header.equalsIgnoreCase(columnName)) {
                columnTitles.put(columnName, title);
                return;
            }
        }
        throw new IllegalArgumentException("No such column: " + columnName);
    }

    public void setColumnTitle(int columnIndex, String title) {
        if (columnIndex < 1 || columnIndex > headers.length) {
            throw new IllegalArgumentException("Invalid column index: " + columnIndex);
        }

        String columnName = headers[columnIndex - 1];
        setColumnTitle(columnName, title);
    }

    public void addRow(Object... row) {
        if (row.length > this.headers.length - columnFormulas.size()) {
            throw new IllegalArgumentException("Row length must not exceed the number of non-formula columns");
        }
        Map<String, Object> newRow = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            newRow.put(this.headers[i], row[i]);
        }
        for (int i = row.length; i < this.headers.length - columnFormulas.size(); i++) {
            newRow.put(this.headers[i], null);
        }
        for (Map.Entry<String, Function<Map<String, Object>, Object>> formula : columnFormulas.entrySet()) {
            newRow.put(formula.getKey(), formula.getValue().apply(newRow));
        }
        rows.add(newRow);
    }

    public void addRowFromConsole() {
        Console console = System.console();
        List<Double> rowData = new ArrayList<>();
        for (int i = 0; i < headers.length; i++) {
            String columnName = headers[i];
            String prompt = "";
            Double value = 0.0;

            if (columnFormulas.containsKey(columnName)) {
                continue;
            }

            if (!columnName.equals("average")) {
                prompt = columnName + ": ";
                String userInput = console.readLine(prompt);
                value = Double.parseDouble(userInput);
            } else {
                value = calculateAvg(rowData.get(0), rowData.get(1));
            }
            rowData.add(value);
        }
        addRow(rowData.toArray());
    }

    private double calculateAvg(double n1, double n2) {
        return (n1+n2)/2;
    }

    public String toString() {
        int[] maxColumnWidths = new int[headers.length];
        Arrays.fill(maxColumnWidths, 0);

        // Determina a largura máxima de cada coluna
        for (Map<String, Object> row : rows) {
            for (int i = 0; i < headers.length; i++) {
                Object valueObj = row.get(headers[i]);
                if (valueObj == null) {
                    continue;
                }
                String value = valueObj.toString();
                if (value.length() > maxColumnWidths[i]) {
                    maxColumnWidths[i] = value.length();
                }
            }
        }

        // Define a largura mínima para a largura do cabeçalho da coluna
        for (int i = 0; i < headers.length; i++) {
            maxColumnWidths[i] = Math.max(maxColumnWidths[i], headers[i].length());
        }

        // Formata a tabela com largura máxima
        StringBuilder sb = new StringBuilder();
        if (title != null) {
            sb.append(title).append("\n");
        }

        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            if (columnTitles.containsKey(header)) {
                header = columnTitles.get(header);
            }
            sb.append(String.format("%-" + maxColumnWidths[i] + "s ", header));
        }

        sb.append("\n");
        for (Map<String, Object> row : rows) {
            for (int i = 0; i < headers.length; i++) {
                String formatString = "%-" + maxColumnWidths[i] + "s ";
                Object valueObj = row.get(headers[i]);
                if (valueObj == null) {
                    sb.append(String.format(formatString, ""));
                    continue;
                }
                switch (columnTypes[i]) {
                    case String:
                        sb.append(String.format(formatString, valueObj.toString()));
                        break;
                    case Integer:
                        sb.append(String.format(formatString, valueObj));
                        break;
                    case Double:
                        sb.append(String.format(formatString, valueObj));
                        break;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // public String center(String str, int totalWidth) {
    //     int padSize = totalWidth - str.length();
    //     int padStart = padSize / 2 + str.length();
    //     StringBuilder sb = new StringBuilder(str);
    //     sb.insert(0, String.format("%" + padStart + "s", ""));
    //     sb.append(String.format("%" + (totalWidth - sb.length()) + "s", ""));
    //     return sb.toString();
    // }

    
    public String center(String message, ATable t) {
        // tamanho máximo de cada coluna
        int[] maxColumnWidths = new int[headers.length];
        for (Map<String, Object> row : rows) {
            for (int i = 0; i < headers.length; i++) {
                Object valueObj = row.get(headers[i]);
                if (valueObj == null) {
                    continue;
                }
                String value = valueObj.toString();
                if (value.length() > maxColumnWidths[i]) {
                    maxColumnWidths[i] = value.length();
                }
            }
        }

        // comprimento total da tabela
        int totalLength = 0;
        for (int i = 0; i < headers.length; i++) {
            totalLength += maxColumnWidths[i] + 3; // +3 para espaços extra
        }

        // espaços antes e depois da mensagem
        int paddingSize = (totalLength - message.length()) / 2;
        String padding = String.format("%" + paddingSize + "s", "");

        // string centralizada
        String centeredString = padding + message + padding + "\n" + t.toString();

        return centeredString;
    }

    // para o metodo read do arquivo
    public static ATable parse(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line[] = br.readLine().split("\\,");
        String headers[];
        ColumnType[] columnTypes;

        if (Character.isDigit(line[0].charAt(0))) {
            headers = new String[] { "number", "name" };
            columnTypes = new ColumnType[] { ColumnType.Integer, ColumnType.String };
        } else {
            headers = line;
            columnTypes = new ColumnType[] { ColumnType.Integer, ColumnType.String };
            line = br.readLine().split("\\,");
        }

        ATable t = new ATable(headers, columnTypes);
        t.addRow(line[0], line[1]);

        String nLine;
        while ((nLine = br.readLine()) != null) {
            String[] ln = nLine.split("\\,");
            t.addRow(ln[0], ln[1]);
        }
        br.close();
        return t;
    }

    // para criar tipos de colunas
    public enum ColumnType {
        Integer,
        Double,
        String;
    }
}
