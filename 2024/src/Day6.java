import java.util.ArrayList;
import java.util.List;

public class Day6 {

    public static void main(String[] args) {

        List<String> lines = Utils.getLinesFromFile();

        long part1Result, part2Result;

        part1Result = part1Result(lines);
        part2Result = part2Result(lines);

        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);

    }

    private static long part1Result(List<String> lines) {

        int countOfUsedBoxes = 0;

        char[][] arr = new char[lines.size()][lines.getFirst().length()];

        makeArray(lines, arr);

        return countOfUsedBoxes;
    }

    private static long part2Result(List<String> lines) {

        int holeCount = 0, x = -1, y = -1;


        char[][] arr = new char[lines.size()][lines.getFirst().length()];
        int[] newCoordinates;
        boolean isHoleWorked;

        makeArray(lines, arr);

        do {

            makeArray(lines, arr);

            newCoordinates = putHole(arr, x, y);


            x = newCoordinates[0];
            y = newCoordinates[1];

            arr[x][y] = 'O';

            isHoleWorked = countUsedBoxes(arr);

            if (isHoleWorked) {

                holeCount++;

            }

            // print arr


            arr[x][y] = '.';


        } while (x != arr.length - 1 || y != arr[0].length - 1);


        return holeCount;
    }

    private static int[] putHole(char[][] arr, int x, int y) {

        int[] newCoordinates = new int[2];
        newCoordinates[0] = arr.length - 1;
        newCoordinates[1] = arr[0].length - 1;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                if (i == x && j == y) {
                    continue;
                }

                if (arr[i][j] != '^' && arr[i][j] != '#') {

                    if (!isNotNext(i, j, x, y) && x != -1 && y != -1) {
                        continue;
                    }


                    newCoordinates[0] = i;
                    newCoordinates[1] = j;
                    return newCoordinates;

                }

            }

        }

        return newCoordinates;
    }

    private static boolean isNotNext(int i, int j, int x, int y) {

        if (i < x) {
            return false;
        } else if (i == x) {
            return j > y;
        } else {
            return true;
        }

    }

    private static boolean countUsedBoxes(char[][] arr) {

        String flag = "up";
        boolean isOver = false;
        int x, y;

        List<Integer> xCoordinates = new ArrayList<>();
        List<Integer> yCoordinates = new ArrayList<>();
        List<String> flags = new ArrayList<>();


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == '^') {

                    x = i;
                    y = j;

                    while (!isOver) {

                        switch (flag) {
                            case "up" -> {
                                for (int p = x; p >= 0; p--) {
                                    if (arr[p][y] != '#' && arr[p][y] != 'O') {
                                        arr[p][y] = 'X';
                                    } else {

                                        if (isUsedBox(xCoordinates, yCoordinates, p, y, flags, flag)) {
                                            return true;
                                        }
                                        x = p + 1;
                                        flag = "right";
                                        break;
                                    }

                                    if (p == 0) {
                                        isOver = true;
                                        break;
                                    }

                                }
                            }
                            case "right" -> {
                                for (int p = y; p < arr[i].length; p++) {
                                    if (arr[x][p] != '#' && arr[x][p] != 'O') {
                                        arr[x][p] = 'X';


                                    } else {

                                        if (isUsedBox(xCoordinates, yCoordinates, x, p, flags, flag)) {
                                            return true;
                                        }

                                        y = p - 1;
                                        flag = "down";
                                        break;
                                    }

                                    if (p == arr[i].length - 1) {
                                        isOver = true;
                                        break;
                                    }

                                }
                            }
                            case "down" -> {
                                for (int p = x; p < arr.length; p++) {
                                    if (arr[p][y] != '#' && arr[p][y] != 'O') {
                                        arr[p][y] = 'X';


                                    } else {

                                        if (isUsedBox(xCoordinates, yCoordinates, p, y, flags, flag)) {
                                            return true;
                                        }
                                        x = p - 1;
                                        flag = "left";
                                        break;
                                    }

                                    if (p == arr.length - 1) {
                                        isOver = true;
                                        break;
                                    }

                                }
                            }
                            case "left" -> {
                                for (int p = y; p >= 0; p--) {
                                    if (arr[x][p] != '#' && arr[x][p] != 'O') {
                                        arr[x][p] = 'X';
                                    } else {

                                        if (isUsedBox(xCoordinates, yCoordinates, x, p, flags, flag)) {
                                            return true;
                                        }
                                        y = p + 1;
                                        flag = "up";
                                        break;
                                    }

                                    if (p == 0) {
                                        isOver = true;
                                        break;
                                    }

                                }
                            }
                        }


                    }

                }
            }

            if (isOver) {
                break;
            }

        }

        return false;
    }

    private static boolean isUsedBox(List<Integer> xCoordinates, List<Integer> yCoordinates, int p, int y, List<String> flags, String flag) {

        for (int i = 0; i < xCoordinates.size(); i++) {
            if (xCoordinates.get(i) == p && yCoordinates.get(i) == y && flags.get(i).equals(flag)) {

                System.out.println("X: " + p + " Y: " + y + " FLAG: " + flag);

                return true;
            }
        }

        xCoordinates.add(p);
        yCoordinates.add(y);
        flags.add(flag);

        return false;

    }

    private static void makeArray(List<String> lines, char[][] arr) {
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                arr[i][j] = lines.get(i).charAt(j);
            }
        }
    }

}
