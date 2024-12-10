import java.util.ArrayList;
import java.util.List;

public class Day9 {

    public static void main(String[] args) {

        List<String> lines = Utils.getLinesFromFile();

        long part1Result;

        part1Result = part1Result(lines);

        System.out.println("Part 1 result: " + part1Result);

    }

    private static long part1Result(List<String> lines) {

        List<List<Long>> arr = makeArray(lines);
        boolean doesHasNegative;
        long negativeIndex, sum = 0, index = 0;

        for (int i = arr.size() - 1; i >= 0; i--) {
            if (arr.get(i).getFirst() != -1) {
                for (int j = 0; j < i; j++) {

                    doesHasNegative = false;
                    negativeIndex = -1;

                    for (int k = 0; k < arr.get(j).size(); k++) {
                        if (arr.get(j).get(k) == -1) {
                            doesHasNegative = true;
                            negativeIndex = k;
                            break;
                        }
                    }

                    if (doesHasNegative && (arr.get(j).size() - negativeIndex) >= arr.get(i).size()) {

                        List<Long> temp = List.copyOf(arr.get(i));

                        for (int k = 0; k < temp.size(); k++) {
                            arr.get(i).set(k, -1L);
                        }

                        for (int k = 0; k < temp.size(); k++) {
                            arr.get(j).set((int) (negativeIndex + k), temp.get(k));
                        }

                        break;
                    }
                }
            }
        }

        for (List<Long> longs : arr) {
            for (Long aLong : longs) {
                if (aLong != -1) {
                    sum += aLong * index;
                }

                index++;
            }
        }

        return sum;
    }

    private static List<List<Long>> makeArray(List<String> lines) {

        List<List<Long>> arr = new ArrayList<>();
        String line = lines.getFirst();
        long tempNum, index = 0;

        for (int i = 0; i < line.length(); i += 2) {

            List<Long> inner = new ArrayList<>();

            tempNum = Long.parseLong(line.charAt(i) + "");

            for (int j = 0; j < tempNum; j++) {
                inner.add(index);
            }

            arr.add(inner);
            inner = new ArrayList<>();

            if (i != line.length() - 1) {

                tempNum = Long.parseLong(line.charAt(i + 1) + "");

                for (int j = 0; j < tempNum; j++) {
                    inner.add(-1L);
                }

            } else {
                break;
            }

            if (!inner.isEmpty()) {
                arr.add(inner);
            }
            index++;

        }


        return arr;
    }

}
