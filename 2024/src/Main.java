import days.Day1;
import days.Day2;
import days.Day3;
import days.Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    private static final String filePath = "input.txt";

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);

        Day4 day4 = new Day4();
        day4.solve(lines);

    }

}