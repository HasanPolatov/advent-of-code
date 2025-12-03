import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void main(String[] args) {
        List<String> lines = Utils.getLinesFromFile();

        long part1Result, part2Result;

        // part1Result = part1Result(lines);
        part2Result = part2Result(lines);

        // System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);
    }

    private static long part2Result(List<String> lines) {

        long result = 0;
        long sum = 0, temp;
        List<Long> numbers = new ArrayList<>();
        List<Long> tempNumbers = new ArrayList<>();
        boolean added = false;

        for (String line : lines) {
            sum = 0;
            numbers.clear();
            for (int i = 0; i < line.length(); i++) {

                if (numbers.isEmpty()) {
                    numbers.add(Long.parseLong(line.substring(i, i + 1)));
                } else {
                    temp = Long.parseLong(line.substring(i, i + 1));
                    if ((line.length() - (i + 1)) == (11 - numbers.size())) {
                        numbers.add(temp);
                    } else {
                        added = false;
                        for (int j = 0; j < numbers.size(); j++) {
                            if (temp > numbers.get(j)
                                    && ((line.length() - (i + 1)) >= (12 - (j + 1)))) {
                                if (j == 0) {
                                    numbers.clear();
                                    numbers.add(temp);
                                } else {
                                    tempNumbers.clear();
                                    tempNumbers.addAll(numbers);
                                    numbers.clear();
                                    for (int k = 0; k < j; k++) {
                                        numbers.add(tempNumbers.get(k));
                                    }
                                    numbers.add(temp);
                                }
                                added = true;
                                break;
                            }
                        }

                        if (!added && numbers.size() < 12) {
                            numbers.add(temp);
                        }
                    }
                }
            }

            for (long number : numbers) {
                sum = sum * 10 + number;
            }
            result += sum;
        }

        return result;
    }

    private static long part1Result(List<String> ranges) {

        return 0L;
    }
}
