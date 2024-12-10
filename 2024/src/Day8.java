import java.util.ArrayList;
import java.util.List;

public class Day8 {

    public static void main(String[] args) {

        List<String> lines = Utils.getLinesFromFile();

        long part1Result, part2Result;

        part1Result = part1Result(lines);
        part2Result = part2Result();

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);

    }

    private static long part1Result(List<String> lines) {

        char[][] arr = new char[lines.size()][lines.getFirst().length()];
        makeArray(lines, arr);


        int count = countNodes(arr);

        printArray(arr);

        return count;
    }

    private static long part2Result() {

        return 0;
    }

    private static int countNodes(char[][] arr) {

        List<Long> xCoordinates = new ArrayList<>();
        List<Long> yCoordinates = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                if (arr[i][j] != '.') {

                    for (int k = 0; k < arr.length; k++) {
                        for (int l = 0; l < arr[i].length; l++) {

                            if (k != i && l != j && arr[k][l] == arr[i][j]) {

                                for (int f = i, g = j; f < arr.length && g < arr[i].length; f += k - i, g += l - j) {

                                    if (f < 0 || g < 0) {
                                        break;
                                    }

                                    if(addNodesIfNotExists(xCoordinates, yCoordinates, f, g)) {
                                        System.out.println(f + " " + g);
                                    }
                                }

                                for (int f = i, g = j; f >= 0 && g >= 0; f -= k - i, g -= l - j) {

                                    if (f >= arr.length || g >= arr[i].length) {
                                        break;
                                    }

                                    if(addNodesIfNotExists(xCoordinates, yCoordinates, f, g)) {
                                        System.out.println(f + " " + g);
                                    }
                                }

                            }

                        }
                    }

                }

            }
        }

        return xCoordinates.size();
    }

    private static void printArray(char[][] arr) {
        for (char[] chars : arr) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private static boolean addNodesIfNotExists(List<Long> xCoordinates, List<Long> yCoordinates, int m, int n) {

        boolean exists = false;

        for (int i = 0; i < xCoordinates.size(); i++) {
            if (xCoordinates.get(i) == m && yCoordinates.get(i) == n) {
                exists = true;
                break;
            }
        }

        if (!exists) {

            xCoordinates.add((long) m);
            yCoordinates.add((long) n);
        }

        return exists;
    }


    private static void makeArray(List<String> lines, char[][] arr) {
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                arr[i][j] = lines.get(i).charAt(j);
            }
        }
    }

}