import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day7 {

  public static void main(String[] args) {
    List<String> lines = Utils.getLinesFromFile();

    long part1Result, part2Result;

    List<List<Character>> matrix = Utils.parseToMatrix(lines);
    List<List<Long>> longMatrix = parseToLongMatrix(lines);

    part1Result = part1Result(matrix);
    part2Result = part2Result(longMatrix);

    System.out.println("Part 1 result: " + part1Result);
    System.out.println("Part 2 result: " + part2Result);
  }

  private static int part1Result(List<List<Character>> matrix) {
    int count = 0;

    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.get(i).size(); j++) {
        if (matrix.get(i).get(j) == 'S') {
          matrix.get(i).set(j, '|');
          break;
        }

        if (i != 0) {
          if (matrix.get(i - 1).get(j) == '|') {
            if (matrix.get(i).get(j) != '^') {
              matrix.get(i).set(j, '|');
            } else {
              if (j - 1 >= 0) {
                matrix.get(i).set(j - 1, '|');
              }

              if (j + 1 < matrix.get(i).size()) {
                matrix.get(i).set(j + 1, '|');
              }
            }
          }
        }
      }
    }

    for (int i = 1; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.get(i).size(); j++) {
        if (matrix.get(i - 1).get(j) == '|' && matrix.get(i).get(j) == '^') {
          count++;
        }
      }
    }

    return count;
  }

  private static long part2Result(List<List<Long>> matrix) {
    long sum = 0;

    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.get(i).size(); j++) {
        if (i != 0) {
          if (
            matrix.get(i - 1).get(j) != 0L && matrix.get(i - 1).get(j) != -1L
          ) {
            if (matrix.get(i).get(j) == 0L) {
              matrix.get(i).set(j, matrix.get(i - 1).get(j));
            } else if (matrix.get(i).get(j) == -1L) {
              matrix.get(i).set(j - 1, getSum(matrix, i, j - 1));
              matrix.get(i).set(j + 1, getSum(matrix, i, j + 1));
            }
          }
        }
      }
    }

    for (int j = 0; j < matrix.get(matrix.size() - 1).size(); j++) {
      sum += matrix.get(matrix.size() - 1).get(j);
    }

    return sum;
  }

  private static Long getSum(List<List<Long>> matrix, int i, int j) {
    long sum = 0;

    if (
      j - 1 >= 0 &&
      matrix.get(i - 1).get(j - 1) != 0L &&
      matrix.get(i).get(j - 1) == -1L
    ) {
      sum += matrix.get(i - 1).get(j - 1);
    }

    if (matrix.get(i - 1).get(j) != 0L) {
      sum += matrix.get(i - 1).get(j);
    }

    if (
      j + 1 < matrix.get(i).size() &&
      matrix.get(i - 1).get(j + 1) != 0L &&
      matrix.get(i).get(j + 1) == -1L
    ) {
      sum += matrix.get(i - 1).get(j + 1);
    }

    return (long) sum;
  }

  private static List<List<Long>> parseToLongMatrix(List<String> lines) {
    List<List<Long>> matrix = new ArrayList<>();
    for (String line : lines) {
      List<Long> row = new ArrayList<>();
      for (char c : line.toCharArray()) {
        if (c == '.') {
          row.add(0L);
        } else if (c == '^') {
          row.add(-1L);
        } else {
          row.add(1L);
        }
      }
      matrix.add(row);
    }
    return matrix;
  }
}
