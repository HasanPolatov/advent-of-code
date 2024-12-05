package days;

import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public void solve(List<String> lines) {

        long part1Result, part2Result;

        List<List<Long>> lists = new ArrayList<>();

        assignNumbersToLists(lines, lists);

        part1Result = part1Result(lists);
        part2Result = part2Result(lists);

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);
    }

    private long part1Result(List<List<Long>> lists) {

        int countOfSafeLines = 0;

        for (List<Long> list : lists) {

            if (isLineSafe(list)) {
                countOfSafeLines++;
            }

        }

        return countOfSafeLines;
    }

    public long part2Result(List<List<Long>> lists) {

        int countOfSafeLines = 0;

        for (List<Long> list : lists) {

            for (int i = 0; i < list.size(); i++) {

                List<Long> newList = new ArrayList<>(list);
                newList.remove(i);

                if (isLineSafe(newList)) {
                    countOfSafeLines++;
                    break;
                }

            }

        }

        return countOfSafeLines;
    }

    private boolean isLineSafe(List<Long> list) {

        boolean isIncrease = false;
        long difference;

        for (int i = 0; i < list.size(); i++) {
            if (i == 1) {

                difference = list.get(i) - list.get(0);

                if (difference == 0 || Math.abs(difference) > 3) {
                    return false;
                } else isIncrease = difference > 0;
            } else if (i > 1) {

                difference = list.get(i) - list.get(i - 1);

                if (difference == 0 || ((difference > 0) != isIncrease)) {
                    return false;
                } else if (Math.abs(difference) > 3) {
                    return false;
                }

            }
        }

        return true;
    }

    private void assignNumbersToLists(List<String> lines, List<List<Long>> lists) {

        for (String line : lines) {
            String[] parts = line.split(" ");
            List<Long> list = new ArrayList<>();
            for (String part : parts) {
                list.add(Long.parseLong(part));
            }
            lists.add(list);
        }

    }

}
