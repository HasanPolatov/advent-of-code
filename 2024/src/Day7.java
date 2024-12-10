import java.util.Arrays;
import java.util.List;

public class Day7 {

    public static void main(String[] args) {

        List<String> lines = Utils.getLinesFromFile();

        long part1Result, part2Result;

        part1Result = part1Result(lines);
        part2Result = part2Result();

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);

    }

    private static long part1Result(List<String> lines) {

        long sum = 0, colonIndex, possibleResult;

        for (String line : lines) {

            colonIndex = line.indexOf(':');
            possibleResult =  Long.parseLong(line.substring(0, (int)colonIndex));

            List<Long> number = Arrays.stream(line.substring((int)(colonIndex + 2)).split(" "))
                    .map(Long::parseLong).toList();

            if (check(number, possibleResult)) {
                sum += possibleResult;
            }



        }

        return sum;
    }

    private static long part2Result() {

        return 0;
    }

    private static boolean check(List<Long> numbers, long target) {

        String[] operations = {"*", "+", "||"};
        long sum, size = numbers.size(), variantsCount = (long) Math.pow(3, size);

        for (int i = 0; i < variantsCount; i++) {
            int temp = i;
            sum = 0;

            for (Long number : numbers) {

                if (operations[temp % 3].equals("||")) {
                    sum = addNextTo(sum, number);
                } else if (operations[temp % 3].equals("*")) {
                    sum *= number;
                } else {
                    sum += number;
                }

                temp /= 3;
            }

            if (sum == target) {
                return true;
            }

        }

        return false;
    }

    private static long addNextTo(long sum, long number) {
        int numberSize = String.valueOf(number).length();
        return sum * (long) Math.pow(10, numberSize) + number;
    }


}
