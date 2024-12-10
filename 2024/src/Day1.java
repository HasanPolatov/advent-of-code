import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {

        List<String> lines = Utils.getLinesFromFile();

        long part1Result, part2Result;

        List<Long> listA = new ArrayList<>();
        List<Long> listB = new ArrayList<>();

        assignNumbersToLists(lines, listA, listB);

        part1Result = part1Result(listA, listB);
        part2Result = part2Result(listA, listB);

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);
    }

    private static long part1Result(List<Long> listA, List<Long> listB) {

        listA.sort(Long::compareTo);
        listB.sort(Long::compareTo);

        long distanceSum = 0;
        for (int i = 0; i < listA.size(); i++) {
            distanceSum += Math.abs(listA.get(i) - listB.get(i));
        }
        return distanceSum;
    }

    private static long part2Result(List<Long> listA, List<Long> listB) {

        long result = 0;
        int count;

        for (long numA: listA) {

            count = 0;

            for (long numB: listB) {

                if (numA == numB) {
                    count++;
                }

            }

            result += numA * count;
        }

        return result;
    }

    private static void assignNumbersToLists(List<String> lines, List<Long> listA, List<Long> listB) {

        for (String line : lines) {
            String[] parts = line.split(" {3}");
            listA.add(Long.parseLong(parts[0]));
            listB.add(Long.parseLong(parts[1]));
        }

    }

}
