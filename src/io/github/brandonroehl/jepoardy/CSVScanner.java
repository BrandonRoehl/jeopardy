package io.github.brandonroehl.jepoardy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by BrandonRoehl on 10/17/16.
 */
public class CSVScanner {
    private final Scanner scan;

    public CSVScanner(File file) throws FileNotFoundException {
        scan = new Scanner(file);
    }

    public List<String> nextRow() {
        Scanner line = new Scanner(scan.nextLine());
        line.useDelimiter(",");

        List<String> result = new ArrayList<>();
        while (line.hasNext()) {
            String tmp = line.next();
            if (tmp.startsWith("\"")) {
                while ((!tmp.endsWith("\"") || tmp.endsWith("\"\"")) && line.hasNext()) {
                    if (tmp.endsWith("\"\"")) tmp += ",";
                    tmp += line.next();
                }
                tmp = tmp.substring(1, tmp.length() - 1);
                tmp = tmp.replaceAll("\"\"", "\"");
            }
            result.add(tmp);
        }

        return result;
    }

    public boolean hasNextRow() {
        return scan.hasNextLine();
    }
}
