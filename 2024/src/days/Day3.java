package days;

import java.util.List;

public class Day3 {

    public void solve(List<String> lines) {

        long part1Result, part2Result;

        part1Result = part1Result(lines);
        part2Result = part2Result(lines);

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);
    }

    private long part1Result(List<String> lines) {

        long sum = 0;

        for (String line : lines) {

            sum = calculateSum(line, sum);

        }

        return sum;
    }

    public long part2Result(List<String> lines) {

        boolean isForDont = true;
        int index, startIndex = 0;
        String tempLine;
        long sum = 0;

        for (String line : lines) {

            tempLine = line;

            while (true) {

                if (isForDont) {

                    index = tempLine.indexOf("don't()");
                    if (index == -1) {
                        sum = calculateSum(tempLine.substring(startIndex), sum);
                        break;
                    }
                    sum = calculateSum(tempLine.substring(startIndex, index), sum);
                    tempLine = tempLine.substring(index + 7);
                    isForDont = false;

                } else {

                    index = tempLine.indexOf("do()");
                    if (index == -1) {
                        break;
                    }
                    tempLine = tempLine.substring(index + 4);
                    isForDont = true;

                }

            }

        }

        return sum;

    }


    private long calculateSum(String line, long sum) {

        String delimiter = "mul\\(";
        String numberOne;
        String numberTwo;
        long numberOneLong;
        long numberTwoLong;
        boolean firstNumberComplete;
        boolean firstLine = true;

        String[] parts = line.split(delimiter);


        for (String part : parts) {

            System.out.println("part: " + part);

            if (firstLine) {
                firstLine = false;
                continue;
            }

            numberOne = "";
            numberTwo = "";
            firstNumberComplete = false;

            for (int i = 0; i < part.length(); i++) {

                if (part.charAt(i) == ',') {
                    firstNumberComplete = true;
                    continue;
                } else if (part.charAt(i) == ']') {
                    break;
                } else if (part.charAt(i) == ')') {

                    if (isNumber(numberOne) && isNumber(numberTwo)) {
                        numberOneLong = Long.parseLong(numberOne);
                        numberTwoLong = Long.parseLong(numberTwo);

                        sum += numberOneLong * numberTwoLong;
                    }

                    break;
                }

                if (firstNumberComplete) {
                    numberTwo = numberTwo + part.charAt(i);
                } else {
                    numberOne = numberOne + part.charAt(i);
                }

            }

        }

        return sum;
    }

    private boolean isNumber(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
