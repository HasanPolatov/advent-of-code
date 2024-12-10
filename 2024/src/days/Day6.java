package days;

import java.util.ArrayList;
import java.util.List;

public class Day6 {

    public void solve(List<String> lines) {

        long part1Result, part2Result;

//        part1Result = part1Result(lines);
        part2Result = part2Result(lines);

//        System.out.println("Part 1 result: " + part1Result);
        System.out.println("Part 2 result: " + part2Result);

    }

    private long part1Result(List<String> lines) {

        int countOfUsedBoxes = 0;

        char[][] arr = new char[lines.size()][lines.get(0).length()];

        makeArray(lines, arr);

//        countOfUsedBoxes = countUsedBoxes(arr, countOfUsedBoxes);

        return countOfUsedBoxes;
    }

    private long part2Result(List<String> lines) {

        int countOfUsedBoxes = 0;
        int holeCount = 0, x = -1, y = -1;


        char[][] arr = new char[lines.size()][lines.get(0).length()];
        int[] newCoordinates = new int[2];
        boolean isHoleWorked;

        makeArray(lines, arr);

        do {

            isHoleWorked = false;

//            System.out.println("XX: " + x + " YY: " + y);

            makeArray(lines, arr);

            newCoordinates = putHole(arr, x, y);


            x = newCoordinates[0];
            y = newCoordinates[1];

            arr[x][y] = 'O';

            isHoleWorked = countUsedBoxes(arr, countOfUsedBoxes, isHoleWorked);

            if (isHoleWorked) {

//                System.out.println("Yes");

                holeCount++;

//                for (int i = 0; i < arr.length; i++) {
//                    for (int j = 0; j < arr[i].length; j++) {
//                        System.out.print(arr[i][j]);
//                    }
//                    System.out.println();
//                }

//                System.out.println("---------------------------------------------------------------");


            }

            // print arr


            arr[x][y] = '.';


        } while (x != arr.length - 1 || y != arr[0].length - 1);


        return holeCount;
    }

    private int[] putHole(char[][] arr, int x, int y) {

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

    private boolean isNotNext(int i, int j, int x, int y) {

        // 1:0 0:9
//        System.out.println("i: " + i + " j: " + j + " x: " + x + " y: " + y);

        // is (i, j) nexter than (x, y)
        if (i < x) {
            return false;
        } else if (i == x) {
            return j > y;
        } else {
            return true;
        }

    }

    private boolean countUsedBoxes(char[][] arr, int countOfUsedBoxes, boolean isHoleWorked) {

        int count = 0;

        String flag = "up";
        boolean isOver = false;
        int x = 0, y = 0, x1 = 0, y1 = 0;

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

                        count++;


                    }

                }
            }

            if (isOver) {
                break;
            }

        }

//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                if (arr[i][j] == 'X') {
//                    countOfUsedBoxes++;
//                }
//            }
//        }

        return false;
    }

    private boolean isUsedBox(List<Integer> xCoordinates, List<Integer> yCoordinates, int p, int y, List<String> flags, String flag) {

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

//    private long part2Result(List<String> lines) {
//
//        int countOfSpecialWords = 0;
//
//        char[][] arr = new char[lines.size()][lines.get(0).length()];
//
//        makeArray(lines, arr);
//
//        countOfSpecialWords = searchForX_MAS(arr, countOfSpecialWords);
//
//        return countOfSpecialWords;
//    }

    private void makeArray(List<String> lines, char[][] arr) {
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                arr[i][j] = lines.get(i).charAt(j);
            }
        }
    }

}
