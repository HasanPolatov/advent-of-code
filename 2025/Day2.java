import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        List<String> lines = Utils.getLinesFromFile();

        long part1Result, part2Result;

        List<String> ranges = Arrays.asList(lines.toArray(new String[0])[0].split(","));

        part1Result = part1Result(ranges);
        part2Result = part2Result(ranges);

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);
    }

    private static long part1Result(List<String> ranges) {

        long sum = 0L;
        int count = 0;

        for (String range : ranges) {

            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            for (long i = start; i <= end; i++) {
                long temp = i;
                count = 0;

                while (temp > 0) {
                    temp /= 10;
                    count++;
                }

                if (count % 2 == 0
                        && (long) (i % Math.pow(10, count / 2))
                                == (long)
                                        ((i - (i % Math.pow(10, count / 2)))
                                                / Math.pow(10, count / 2))) {
                    sum += i;
                }
            }
        }

        return sum;
    }

    private static long part2Result(List<String> ranges) {

        long sum = 0L;
        int count = 0;

        for (String range : ranges) {

            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            for (long i = start; i <= end; i++) {
                long temp = i;
                count = 0;

                while (temp > 0) {
                    temp /= 10;
                    count++;
                }

                for (int j = 1; j < count; j++) {
                    if (count % j == 0) {
                        String number = String.valueOf(i);
                        List<String> partsByCount = new ArrayList<>();
                        boolean flag = true;
                        for (int k = 0; k < number.length(); k += j) {
                            partsByCount.add(number.substring(k, k + j));
                            if (partsByCount.size() > 1
                                    && !partsByCount
                                            .get(partsByCount.size() - 1)
                                            .equals(partsByCount.get(partsByCount.size() - 2))) {
                                flag = false;
                                break;
                            }
                        }

                        if (flag) {
                            sum += i;
                            break;
                        }
                    }
                }
            }
        }

        return sum;
    }
}
