import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Day8 {

  public static void main(String[] args) {
    List<String> lines = Utils.getLinesFromFile();
    List<List<Long>> positions = new ArrayList<>();

    parseInput(lines, positions);

    long part1Result, part2Result;

    part1Result = part1Result(positions);
    part2Result = part2Result(positions);

    System.out.println("Part 1 result: " + part1Result);
    System.out.println("Part 2 result: " + part2Result);
  }

  private static long part1Result(List<List<Long>> positions) {
    List<Map<BigDecimal, List<Integer>>> distances = new ArrayList<>();
    List<List<Integer>> circuits = new ArrayList<>();

    boolean result = false;
    Long sum = 1L,
      max = -1L;
    int index = 0,
      index1 = 0,
      index2 = 0,
      count = 0;
    BigDecimal distance = BigDecimal.valueOf(Long.MAX_VALUE);
    BigDecimal temp = BigDecimal.ZERO;

    for (int i = 0; i < positions.size(); i++) {
      for (int j = i + 1; j < positions.size(); j++) {
        temp = BigDecimal.valueOf(
          Math.sqrt(
            Math.pow(positions.get(i).get(0) - positions.get(j).get(0), 2) +
              Math.pow(positions.get(i).get(1) - positions.get(j).get(1), 2) +
              Math.pow(positions.get(i).get(2) - positions.get(j).get(2), 2)
          )
        );

        Map<BigDecimal, List<Integer>> distanceMap = new HashMap<>();
        distanceMap.put(temp, Arrays.asList(i, j));
        distances.add(distanceMap);
      }
    }

    distances.sort((map1, map2) -> {
      BigDecimal key1 = map1.keySet().iterator().next();
      BigDecimal key2 = map2.keySet().iterator().next();
      return key1.compareTo(key2);
    });

    for (int i = 0; i < distances.size(); i++) {
      Map<BigDecimal, List<Integer>> distanceMap = distances.get(i);
      BigDecimal distance1 = distanceMap.keySet().iterator().next();
      List<Integer> indices = distanceMap.get(distance1);
    }

    while (count < 1000) {
      Map<BigDecimal, List<Integer>> currentMap = distances.get(index);
      BigDecimal currentDistance = currentMap.keySet().iterator().next();
      List<Integer> indices = currentMap.get(currentDistance);

      distance = currentDistance;
      index1 = indices.get(0);
      index2 = indices.get(1);
      connect(index1, index2, circuits);

      index++;
      count++;
    }

    count = 3;

    while (count > 0) {
      for (int i = 0; i < circuits.size(); i++) {
        if (((long) circuits.get(i).size()) > max) {
          max = (long) circuits.get(i).size();
          index = i;
        }
      }

      circuits.remove(index);
      sum *= max;
      max = -1L;

      count--;
    }

    return sum;
  }

  private static long part2Result(List<List<Long>> positions) {
    Long latestConnectedPairOneX = null;
    Long latestConnectedPairTwoX = null;
    List<Map<BigDecimal, List<Integer>>> distances = new ArrayList<>();
    List<List<Integer>> circuits = new ArrayList<>();

    boolean result = false;
    Long sum = 1L,
      max = -1L;
    int index = 0,
      index1 = 0,
      index2 = 0,
      count = 0;
    BigDecimal distance = BigDecimal.valueOf(Long.MAX_VALUE);
    BigDecimal temp = BigDecimal.ZERO;

    for (int i = 0; i < positions.size(); i++) {
      for (int j = i + 1; j < positions.size(); j++) {
        temp = BigDecimal.valueOf(
          Math.sqrt(
            Math.pow(positions.get(i).get(0) - positions.get(j).get(0), 2) +
              Math.pow(positions.get(i).get(1) - positions.get(j).get(1), 2) +
              Math.pow(positions.get(i).get(2) - positions.get(j).get(2), 2)
          )
        );

        Map<BigDecimal, List<Integer>> distanceMap = new HashMap<>();
        distanceMap.put(temp, Arrays.asList(i, j));
        distances.add(distanceMap);
      }
    }

    distances.sort((map1, map2) -> {
      BigDecimal key1 = map1.keySet().iterator().next();
      BigDecimal key2 = map2.keySet().iterator().next();
      return key1.compareTo(key2);
    });

    for (int i = 0; i < distances.size(); i++) {
      Map<BigDecimal, List<Integer>> distanceMap = distances.get(i);
      BigDecimal distance1 = distanceMap.keySet().iterator().next();
      List<Integer> indices = distanceMap.get(distance1);
    }

    while (true) {
      Map<BigDecimal, List<Integer>> currentMap = distances.get(index);
      BigDecimal currentDistance = currentMap.keySet().iterator().next();
      List<Integer> indices = currentMap.get(currentDistance);

      distance = currentDistance;
      index1 = indices.get(0);
      index2 = indices.get(1);
      connect(index1, index2, circuits);

      if (circuits.size() == 1 && circuits.get(0).size() == positions.size()) {
        latestConnectedPairOneX = positions.get(index1).get(0);
        latestConnectedPairTwoX = positions.get(index2).get(0);
        break;
      }

      index++;

      if (index == distances.size()) {
        index = 0;
      }
    }

    return latestConnectedPairOneX * latestConnectedPairTwoX;
  }

  private static boolean connect(
    int index1,
    int index2,
    List<List<Integer>> circuits
  ) {
    boolean found1 = false,
      found2 = false;
    int found1Index = -1,
      found2Index = -1;

    for (int i = 0; i < circuits.size(); i++) {
      if (
        circuits.get(i).contains(index1) && circuits.get(i).contains(index2)
      ) {
        return true;
      }

      if (circuits.get(i).contains(index1)) {
        found1 = true;
        found1Index = i;

        if (found2) {
          break;
        }
      }

      if (circuits.get(i).contains(index2)) {
        found2 = true;
        found2Index = i;

        if (found1) {
          break;
        }
      }
    }

    if (found1 && found2) {
      circuits.get(found1Index).addAll(circuits.get(found2Index));
      circuits.remove(found2Index);
    } else if (found1) {
      circuits.get(found1Index).add(index2);
    } else if (found2) {
      circuits.get(found2Index).add(index1);
    } else {
      circuits.add(new ArrayList<>(Arrays.asList(index1, index2)));
    }

    return false;
  }

  private static void parseInput(
    List<String> lines,
    List<List<Long>> positions
  ) {
    for (String line : lines) {
      String[] parts = line.split(",");
      List<Long> position = new ArrayList<>();
      position.add(Long.parseLong(parts[0]));
      position.add(Long.parseLong(parts[1]));
      position.add(Long.parseLong(parts[2]));
      positions.add(position);
    }
  }
}
