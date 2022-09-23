package org.example.reader;

public class CsvFile {
    private final String[] lines;
    private final String[] headers;

    public CsvFile(String[] headers, String[] lines) {
        this.headers = headers;
        this.lines = lines;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String[] getLines() {
        return lines;
    }
}
