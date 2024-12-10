package days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5 {

    public void solve(List<String> lines) {

        long sum = 0;

        List<Long> pageNumbersOne = new ArrayList<>();
        List<Long> pageNumbersTwo = new ArrayList<>();
        List<List<Long>> givenOrder = new ArrayList<>();
        int i = 0;

        for (i = 0; i < lines.size(); i++) {

            if (lines.get(i).isEmpty()) {
                break;
            }

            String[] parts = lines.get(i).split("\\|");

            System.out.println(parts[0] + " " + parts[1]);
            long pageNumber = Long.parseLong(parts[0]);
            long beforePageNumber = Long.parseLong(parts[1]);
            pageNumbersOne.add(pageNumber);
            pageNumbersTwo.add(beforePageNumber);
        }

        System.out.println("\n\n\n\n\n");

        for (int j = i + 1; j < lines.size(); j++) {

            givenOrder.add(new ArrayList<>());

            String[] parts = lines.get(j).split(",");

            for (String part : parts) {
                givenOrder.get(j - i - 1).add(Long.parseLong(part));
            }


        }

//        // sort map by value
//        pageNumbers = pageNumbers.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue())
//                .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

        // print data
//        for (Map.Entry<Long, Long> entry : pageNumbers.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }

        boolean isRight = true;

        for (List<Long> order : givenOrder) {

            isRight = true;

            for (int j = 0; j < order.size(); j++) {
                for (int k = j + 1; k < order.size(); k++) {
                    for (int l = 0; l < pageNumbersOne.size(); l++) {
                        if (pageNumbersOne.get(l) == order.get(k) && pageNumbersTwo.get(l) == order.get(j)) {
                            isRight = false;
                            break;
                        }
                    }
                }
            }

            if (!isRight) {
                reorderIt(order, pageNumbersOne, pageNumbersTwo);

                System.out.print("Reordered: ");
                for (Long pageNumber : order) {
                    System.out.print(pageNumber + " ");
                }

                sum += order.get(order.size() / 2);

                System.out.println();

            }

            if (isRight) {
                System.out.println(order.get(order.size() / 2));
//                sum += order.get(order.size() / 2);
            } else {
                System.out.println("NO");
            }

        }


        System.out.println(sum);


    }

    private void reorderIt(List<Long> order, List<Long> pageNumbersOne, List<Long> pageNumbersTwo) {

        boolean isRight = false;


        while(!isRight) {

            for (int j = 0; j < order.size(); j++) {
                for (int k = j + 1; k < order.size(); k++) {

                    isRight = true;

                    for (int l = 0; l < pageNumbersOne.size(); l++) {
                        if (pageNumbersOne.get(l) == order.get(k) && pageNumbersTwo.get(l) == order.get(j)) {
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

