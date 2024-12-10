import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day5 {

    public static void main(String[] args) {

        List<String> lines = Utils.getLinesFromFile();

        List<Long> pageNumbersOne = new ArrayList<>();
        List<Long> pageNumbersTwo = new ArrayList<>();
        List<List<Long>> givenOrder = new ArrayList<>();
        int i, part1Result = 0, part2Result = 0;

        for (i = 0; i < lines.size(); i++) {

            if (lines.get(i).isEmpty()) {
                break;
            }

            String[] parts = lines.get(i).split("\\|");
            long pageNumber = Long.parseLong(parts[0]);
            long beforePageNumber = Long.parseLong(parts[1]);
            pageNumbersOne.add(pageNumber);
            pageNumbersTwo.add(beforePageNumber);
        }

        for (int j = i + 1; j < lines.size(); j++) {

            givenOrder.add(new ArrayList<>());

            String[] parts = lines.get(j).split(",");

            for (String part : parts) {
                givenOrder.get(j - i - 1).add(Long.parseLong(part));
            }


        }

        boolean isRight;

        for (List<Long> order : givenOrder) {

            isRight = true;

            for (int j = 0; j < order.size(); j++) {
                for (int k = j + 1; k < order.size(); k++) {
                    for (int l = 0; l < pageNumbersOne.size(); l++) {
                        if (Objects.equals(pageNumbersOne.get(l), order.get(k)) && Objects.equals(pageNumbersTwo.get(l), order.get(j))) {
                            isRight = false;
                            break;
                        }
                    }
                }
            }

            if (!isRight) {
                reorderIt(order, pageNumbersOne, pageNumbersTwo);
                part2Result += order.get(order.size() / 2);
            } else {
                part1Result += order.get(order.size() / 2);
            }

        }

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);

    }

    private static void reorderIt(List<Long> order, List<Long> pageNumbersOne, List<Long> pageNumbersTwo) {

        boolean isRight = false;


        while(!isRight) {

            for (int j = 0; j < order.size(); j++) {
                for (int k = j + 1; k < order.size(); k++) {

                    isRight = true;

                    for (int l = 0; l < pageNumbersOne.size(); l++) {
                        if (Objects.equals(pageNumbersOne.get(l), order.get(k)) && Objects.equals(pageNumbersTwo.get(l), order.get(j))) {
                            long temp = order.get(j);
                            order.set(j, order.get(k));
                            order.set(k, temp);
                            isRight = false;
                            break;
                        }
                    }

                }
            }

        }

    }

}

