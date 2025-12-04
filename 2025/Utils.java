import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static final String filePath = "input.txt";

    public static List<String> getLinesFromFile() {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllLines(path);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return List.of();
        }
    }

    public static List<List<Character>> parseToMatrix(List<String> lines) {
        return lines.stream()
                .map(line -> line.split("\\s+"))
                .map(arr -> arr[0].chars().mapToObj(c -> (char) c).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static List<List<Integer>> parseToIntMatrix(List<String> lines) {
        return lines.stream()
                .map(line -> line.split("\\s+"))
                .map(
                        arr ->
                                arr[0].chars()
                                        .mapToObj(c -> Integer.parseInt(String.valueOf((char) c)))
                                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static void printCharMatrix(List<List<Character>> matrix) {
        for (List<Character> row : matrix) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public static void printIntMatrix(List<List<Integer>> matrix) {
        for (List<Integer> row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
