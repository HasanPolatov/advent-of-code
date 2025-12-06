import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

  public static void main(String[] args) {
    List<String> lines = Utils.getLinesFromFile();

    BigInteger part1Result, part2Result;

    List<List<Long>> numbers = new ArrayList<>();
    List<String> amal = new ArrayList<>();
    List<List<BigInteger>> numbers2 = new ArrayList<>();
    List<String> amal2 = new ArrayList<>();

    parseInput(lines, numbers, amal);

    part1Result = part1Result(numbers, amal);
    part2Result = part2Result(lines, numbers2, amal2);

    System.out.println("Part 1 result: " + part1Result);
    System.out.println("Part 2 result: " + part2Result);
  }

  private static BigInteger part1Result(
    List<List<Long>> numbers,
    List<String> amal
  ) {
    BigInteger result = BigInteger.ZERO;
    List<BigInteger> results = new ArrayList<>();

    for (int i = 0; i < numbers.size(); i++) {
      for (int j = 0; j < numbers.get(i).size(); j++) {
        if (amal.get(j).equals("*")) {
          if (results.size() <= j) {
            results.add(BigInteger.valueOf(numbers.get(i).get(j)));
          } else {
            results.set(
              j,
              results.get(j).multiply(BigInteger.valueOf(numbers.get(i).get(j)))
            );
          }
        } else if (amal.get(j).equals("+")) {
          if (results.size() <= j) {
            results.add(BigInteger.valueOf(numbers.get(i).get(j)));
          } else {
            results.set(
              j,
              results.get(j).add(BigInteger.valueOf(numbers.get(i).get(j)))
            );
          }
        }
      }
    }

    for (BigInteger bigInt : results) {
      result = result.add(bigInt);
    }

    return result;
  }

  private static void parseInput(
    List<String> lines,
    List<List<Long>> numbers,
    List<String> amal
  ) {
    boolean isAmalStart = false;
    for (String line : lines) {
      if (line.charAt(0) == '*' || line.charAt(0) == '+') {
        isAmalStart = true;
      }

      if (!isAmalStart) {
        String[] parts = line.trim().split(" ");
        List<Long> inner = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
          if (parts[i].trim().isEmpty()) {
            continue;
          }
          inner.add(Long.parseLong(parts[i].trim()));
        }
        numbers.add(inner);
      } else {
        String[] parts = line.split("  ");
        for (int l = 0; l < parts.length; l++) {
          if (parts[l].trim().isEmpty()) {
            continue;
          }
          amal.add(parts[l].trim());
        }
      }
    }
  }

  private static BigInteger part2Result(
    List<String> lines,
    List<List<BigInteger>> numbers2,
    List<String> amal2
  ) {
    List<Integer> indexes = new ArrayList<>();
    List<List<String>> horizontal = new ArrayList<>();
    String number;
    boolean skip = false;
    int index = 0,
      index2 = 0;

    for (int k = 0; k < lines.get(lines.size() - 1).length(); k++) {
      if (lines.get(lines.size() - 1).charAt(k) != ' ') {
        indexes.add(k);
        amal2.add(lines.get(lines.size() - 1).charAt(k) + "");
      }
    }

    for (int i = 0; i < lines.size() - 1; i++) {
      horizontal.add(new ArrayList<>());

      for (int j = 0; j < indexes.size(); j++) {
        horizontal
          .get(i)
          .add(
            lines
              .get(i)
              .substring(
                indexes.get(j),
                (j != indexes.size() - 1
                  ? indexes.get(j + 1) - 1
                  : lines.get(i).length())
              )
          );
      }
    }

    for (int i = horizontal.get(0).size() - 1; i >= 0; i--) {
      numbers2.add(new ArrayList<>());
      for (int j = 0; j < horizontal.size(); j++) {
        number = horizontal.get(j).get(i);
        for (int k = 0; k < number.length(); k++) {
          if (number.charAt(k) != ' ') {
            if (numbers2.get(horizontal.get(0).size() - 1 - i).size() < k + 1) {
              for (
                int l = numbers2.get(horizontal.get(0).size() - 1 - i).size();
                l < k + 1;
                l++
              ) {
                numbers2
                  .get(horizontal.get(0).size() - 1 - i)
                  .add(BigInteger.ZERO);
              }
            }
            numbers2
              .get(horizontal.get(0).size() - 1 - i)
              .set(
                k,
                numbers2
                  .get(horizontal.get(0).size() - 1 - i)
                  .get(k)
                  .multiply(BigInteger.TEN)
                  .add(BigInteger.valueOf(number.charAt(k) - '0'))
              );
          }
        }
      }
    }

    BigInteger result = BigInteger.ZERO;
    BigInteger sum = BigInteger.ZERO;

    for (int i = 0; i < numbers2.size(); i++) {
      for (int j = 0; j < numbers2.get(i).size(); j++) {
        if (amal2.get(numbers2.size() - 1 - i).equals("+")) {
          if (sum.compareTo(BigInteger.ZERO) > 0) {
            sum = sum.add(numbers2.get(i).get(j));
          } else {
            sum = numbers2.get(i).get(j);
          }
        } else if (amal2.get(numbers2.size() - 1 - i).equals("*")) {
          if (sum.compareTo(BigInteger.ZERO) > 0) {
            sum = sum.multiply(numbers2.get(i).get(j));
          } else {
            sum = numbers2.get(i).get(j);
          }
        }
      }

      result = result.add(sum);
      sum = BigInteger.ZERO;
    }

    return result;
  }
}
