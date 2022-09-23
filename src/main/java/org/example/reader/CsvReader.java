package org.example.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private final char delimiter;
    private final File csv;
    private String[] headers;

    public CsvReader(char delimiter, File csv) {
        this.delimiter = delimiter;
        this.csv = csv;
    }

    public CsvFile read(Charset encoding) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = Files.newBufferedReader(csv.toPath(), encoding)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (headers == null) {
                    headers = line.split(String.valueOf(delimiter));
                    continue;
                }
                sb.append(line).append(delimiter);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return new CsvFile(headers, split(sb.toString()));
    }

    private String[] split(String text) {
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == delimiter) {
                if (!inQuotes) {
                    tokens.add(sb.toString());
                    sb.setLength(0);
                    continue;
                }
            } else if (c == '\"') {
                inQuotes = !inQuotes;
            }
            sb.append(c);
        }
        tokens.add(sb.toString());
        return tokens.toArray(new String[0]);
    }

}
