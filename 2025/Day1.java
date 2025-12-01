import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {
        List<String> lines = Utils.getLinesFromFile();

        long part1Result, part2Result;

        List<String> directions = new ArrayList<>();
        List<Long> values = new ArrayList<>();

        assignNumbersToLists(lines, directions, values);

        part1Result = part1Result(directions, values);
        part2Result = part2Result(directions, values);

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);
    }

    private static long part1Result(
        List<String> directions,
        List<Long> values
    ) {
        Long sum = 50L,
            count = 0L;

        for (int i = 0; i < directions.size(); i++) {
            if (directions.get(i).charAt(0) == 'R') {
                sum += values.get(i);
                if (sum > 99) {
                    sum = sum % 100;
                }
            } else {
                sum -= values.get(i);
                if (sum < 0) {
                    while (sum < 0) {
                        sum += 100;
                    }
                }
            }

            if (sum == 0) {
                count++;
            }
        }

        return count;
    }

    private static long part2Result(
        List<String> directions,
        List<Long> values
    ) {
        Long sum = 50L,
            count = 0L,
            current = 50L;

        for (int i = 0; i < directions.size(); i++) {
            if (directions.get(i).charAt(0) == 'R') {
                sum += values.get(i);
                if (sum > 99) {
                    count += sum / 100;
                    sum = sum % 100;
                }
            } else {
                current = sum;
                sum -= values.get(i);
                if (sum < 0) {
                    while (sum < 0) {
                        sum += 100;
                        count++;
                    }
                }

                if (current == 0) {
                    count--;
                }

                if (sum == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void assignNumbersToLists(
        List<String> lines,
        List<String> directions,
        List<Long> values
    ) {
        for (String line : lines) {
            directions.add(String.valueOf(line.charAt(0)));
            values.add(Long.parseLong(line.substring(1)));
        }
    }
}
