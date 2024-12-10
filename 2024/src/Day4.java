import java.util.List;

public class Day4 {

    private static final char[] specialChars = {'M', 'A', 'S'};

    public static void main(String[] args) {

        List<String> lines = Utils.getLinesFromFile();

        long part1Result, part2Result;

        part1Result = part1Result(lines);
        part2Result = part2Result(lines);

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);

    }

    private static long part1Result(List<String> lines) {

        int countOfSpecialWords = 0;

        char[][] arr = new char[lines.size()][lines.getFirst().length()];

        makeArray(lines, arr);

        countOfSpecialWords = searchForXMAS(arr, countOfSpecialWords);

        return countOfSpecialWords;
    }

    private static long part2Result(List<String> lines) {

        int countOfSpecialWords = 0;

        char[][] arr = new char[lines.size()][lines.getFirst().length()];

        makeArray(lines, arr);

        countOfSpecialWords = searchForX_MAS(arr, countOfSpecialWords);

        return countOfSpecialWords;
    }

    private static int searchForXMAS(char[][] arr, int countOfSpecialWords) {

        int index;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                if (arr[i][j] == 'X') {

                    index = 0;

                    // go right
                    for (int k = j + 1; k < arr[i].length; k++) {

                        if (arr[i][k] == specialChars[index]) {
                            index++;

                            if (index == 3) {
                                countOfSpecialWords++;
                                break;
                            }

                        } else {
                            break;
                        }

                    }

                    index = 0;

                    // go left
                    for (int k = j - 1; k >= 0; k--) {

                        if (arr[i][k] == specialChars[index]) {
                            index++;

                            if (index == 3) {
                                countOfSpecialWords++;
                                break;
                            }

                        } else {
                            break;
                        }

                    }

                    index = 0;

                    // go down
                    for (int k = i + 1; k < arr.length; k++) {

                        if (arr[k][j] == specialChars[index]) {
                            index++;

                            if (index == 3) {
                                countOfSpecialWords++;
                                break;
                            }

                        } else {
                            break;
                        }

                    }

                    index = 0;

                    // go up
                    for (int k = i - 1; k >= 0; k--) {

                        if (arr[k][j] == specialChars[index]) {
                            index++;

                            if (index == 3) {
                                countOfSpecialWords++;
                                break;
                            }

                        } else {
                            break;
                        }
                    }

                    index = 0;

                    // go right down
                    for (int k = i + 1, l = j + 1; k < arr.length && l < arr[i].length; k++, l++) {

                        if (arr[k][l] == specialChars[index]) {
                            index++;

                            if (index == 3) {
                                countOfSpecialWords++;
                                break;
                            }

                        } else {
                            break;
                        }

                    }

                    index = 0;

                    // go left down
                    for (int k = i + 1, l = j - 1; k < arr.length && l >= 0; k++, l--) {

                        if (arr[k][l] == specialChars[index]) {
                            index++;

                            if (index == 3) {
                                countOfSpecialWords++;
                                break;
                            }

                        } else {
                            break;
                        }

                    }

                    index = 0;

                    // go right up
                    for (int k = i - 1, l = j + 1; k >= 0 && l < arr[i].length; k--, l++) {

                        if (arr[k][l] == specialChars[index]) {
                            index++;

                            if (index == 3) {
                                countOfSpecialWords++;
                                break;
                            }

                        } else {
                            break;
                        }

                    }

                    index = 0;

                    // go left up
                    for (int k = i - 1, l = j - 1; k >= 0 && l >= 0; k--, l--) {

                        if (arr[k][l] == specialChars[index]) {
                            index++;

                            if (index == 3) {
                                countOfSpecialWords++;
                                break;
                            }

                        } else {
                            break;
                        }
                    }

                }

            }
        }

        return countOfSpecialWords;
    }

    private static int searchForX_MAS(char[][] arr, int countOfSpecialWords) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                if (arr[i][j] == 'A' && i + 1 < arr.length && j + 1 < arr[i].length && i - 1 >= 0 && j - 1 >= 0) {

                    if (arr[i - 1][j - 1] == 'S' && arr[i - 1][j + 1] == 'S' && arr[i + 1][j - 1] == 'M' && arr[i + 1][j + 1] == 'M') {
                        countOfSpecialWords++;
                    }

                    if (arr[i - 1][j - 1] == 'S' && arr[i + 1][j - 1] == 'S' && arr[i - 1][j + 1] == 'M' && arr[i + 1][j + 1] == 'M') {
                        countOfSpecialWords++;
                    }

                    if (arr[i + 1][j - 1] == 'S' && arr[i + 1][j + 1] == 'S' && arr[i - 1][j - 1] == 'M' && arr[i - 1][j + 1] == 'M') {
                        countOfSpecialWords++;
                    }

                    if (arr[i - 1][j + 1] == 'S' && arr[i + 1][j + 1] == 'S' && arr[i - 1][j - 1] == 'M' && arr[i + 1][j - 1] == 'M') {
                        countOfSpecialWords++;
                    }

                }

            }
        }

        return countOfSpecialWords;
    }

    private static void makeArray(List<String> lines, char[][] arr) {
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                arr[i][j] = lines.get(i).charAt(j);
            }
        }
    }

}
