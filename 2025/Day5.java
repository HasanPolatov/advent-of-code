import java.util.ArrayList;
import java.util.List;

public class Day5 {

    public static void main(String[] args) {
        List<String> lines = Utils.getLinesFromFile();

        long part1Result, part2Result;
        boolean idsBegin = false;

        List<Long> rangeLeft = new ArrayList<>();
        List<Long> rangeRight = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        for (String line : lines) {
            if (line.isEmpty()) {
                idsBegin = true;
                continue;
            }

            if (idsBegin) {
                ids.add(Long.parseLong(line));
            } else {
                String[] parts = line.split("-");
                rangeLeft.add(Long.parseLong(parts[0]));
                rangeRight.add(Long.parseLong(parts[1]));
            }
        }

        part1Result = part1Result(rangeLeft, rangeRight, ids);
        part2Result = part2Result(rangeLeft, rangeRight, ids);

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);
    }

    private static long part1Result(List<Long> rangeLeft, List<Long> rangeRight, List<Long> ids) {

        int result = 0;

        for (int i = 0; i < ids.size(); i++) {
            for (int j = 0; j < rangeLeft.size(); j++) {
                if (ids.get(i) >= rangeLeft.get(j) && ids.get(i) <= rangeRight.get(j)) {
                    result++;
                    break;
                }
            }
        }

        return result;
    }

    private static long part2Result(List<Long> rangeLeft, List<Long> rangeRight, List<Long> ids) {

        long result = 0, merged = 0, c = 0;
        List<Long> newRangeLeft = new ArrayList<>();
        List<Long> newRangeRight = new ArrayList<>();
        boolean skip = false;

        while (true) {

            merged = 0;
            for (int i = 0; i < rangeLeft.size(); i++) {
                skip = false;
                for (int j = 0; j < newRangeLeft.size(); j++) {

                    if (rangeLeft.get(i) >= newRangeLeft.get(j)
                            && rangeRight.get(i) > newRangeRight.get(j)
                            && rangeLeft.get(i) <= newRangeRight.get(j)) {
                        newRangeRight.set(j, rangeRight.get(i));
                        merged++;
                        skip = true;
                        break;
                    } else if (rangeLeft.get(i) < newRangeLeft.get(j)
                            && rangeRight.get(i) <= newRangeRight.get(j)
                            && rangeRight.get(i) >= newRangeLeft.get(j)) {
                        newRangeLeft.set(j, rangeLeft.get(i));
                        merged++;
                        skip = true;
                        break;
                    } else if (rangeLeft.get(i) < newRangeLeft.get(j)
                            && rangeRight.get(i) > newRangeRight.get(j)) {
                        newRangeLeft.set(j, rangeLeft.get(i));
                        newRangeRight.set(j, rangeRight.get(i));
                        merged++;
                        skip = true;
                        break;
                    } else if (rangeLeft.get(i) >= newRangeLeft.get(j)
                            && rangeRight.get(i) <= newRangeRight.get(j)) {
                        merged++;
                        skip = true;
                        break;
                    }
                }

                if (!skip) {
                    newRangeLeft.add(rangeLeft.get(i));
                    newRangeRight.add(rangeRight.get(i));
                }
            }

            if (merged == 0) {
                break;
            }

            rangeLeft.clear();
            rangeRight.clear();

            rangeLeft.addAll(newRangeLeft);
            rangeRight.addAll(newRangeRight);

            newRangeLeft.clear();
            newRangeRight.clear();
        }

        for (int i = 0; i < newRangeLeft.size(); i++) {
            result += newRangeRight.get(i) - newRangeLeft.get(i) + 1;
        }

        return result;
    }
}
