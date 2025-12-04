import java.util.ArrayList;
import java.util.List;

public class Day4 {

    public static void main(String[] args) {
        List<String> lines = Utils.getLinesFromFile();

        long part1Result, part2Result;

        List<List<Character>> matrix = Utils.parseToMatrix(lines);

        part1Result = part1Result(matrix);
        part2Result = part2Result(matrix);

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);
    }

    private static long part1Result(List<List<Character>> matrix) {

        int count = 0;
        long result = 0;

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                count = 0;
                if (matrix.get(i).get(j) == '@') {
                    count = getCountOnSides(matrix, i, j);

                    if (count < 4) {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    private static long part2Result(List<List<Character>> matrix) {

        int count = 0;
        long result = 0L, finalCount = 0L;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        while (true) {
            x.clear();
            y.clear();
            result = 0;
            for (int i = 0; i < matrix.size(); i++) {
                for (int j = 0; j < matrix.get(i).size(); j++) {
                    count = 0;
                    if (matrix.get(i).get(j) == '@') {
                        count = getCountOnSides(matrix, i, j);

                        if (count < 4) {
                            result++;
                            x.add(i);
                            y.add(j);
                        }
                    }
                }
            }

            if (result == 0) break;

            for (int i = 0; i < x.size(); i++) {
                int row = x.get(i);
                int col = y.get(i);
                matrix.get(row).set(col, '.');
            }

            finalCount += result;
        }

        return finalCount;
    }

    private static int getCountOnSides(List<List<Character>> matrix, int row, int col) {
        int count = 0;

        if (col > 0 && matrix.get(row).get(col - 1) == '@') {
            count++;
        }

        if (col < matrix.get(row).size() - 1 && matrix.get(row).get(col + 1) == '@') {
            count++;
        }

        if (row > 0 && matrix.get(row - 1).get(col) == '@') {
            count++;
        }

        if (row < matrix.size() - 1 && matrix.get(row + 1).get(col) == '@') {
            count++;
        }

        if (col > 0 && row > 0 && matrix.get(row - 1).get(col - 1) == '@') {
            count++;
        }
        if (col < matrix.get(row).size() - 1
                && row > 0
                && matrix.get(row - 1).get(col + 1) == '@') {
            count++;
        }
        if (col > 0 && row < matrix.size() - 1 && matrix.get(row + 1).get(col - 1) == '@') {
            count++;
        }
        if (col < matrix.get(row).size() - 1
                && row < matrix.size() - 1
                && matrix.get(row + 1).get(col + 1) == '@') {
            count++;
        }

        return count;
    }
}
