import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

}
