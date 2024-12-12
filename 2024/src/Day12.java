import java.util.ArrayList;
import java.util.List;

public class Day12 {

    public static void main(String[] args) {

        List<String> lines = Utils.getLinesFromFile();

        long part1Result;

//        System.out.println(lines);

        part1Result = part1Result(lines);

        System.out.println("Part 1 result: " + part1Result);

    }

    private static long part1Result(List<String> lines) {

        char[][] arr = new char[lines.size()][lines.getFirst().length()];
        makeArray(lines, arr);
        List<Character> visited = new ArrayList<>();
        List<List<Integer>> groupsX = new ArrayList<>();
        List<List<Integer>> groupsY = new ArrayList<>();

        int sum = 0, area = 0, perimeter = 0;
        boolean isGroup = false;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                if (!visited.contains(arr[i][j])) {

                    groupsX.clear();
                    groupsY.clear();

                    for (int k = 0; k < arr.length; k++) {
                        for (int l = 0; l < arr[i].length; l++) {

//                            System.out.println("k: " + k + " l: " + l);

                            isGroup = false;

                            if (arr[i][j] == arr[k][l] && !isAlreadyHaveGroup(groupsX, groupsY, k, l)) {

//                                List<Integer> tempX = new ArrayList<>();
//                                List<Integer> tempY = new ArrayList<>();
//                                tempX.add(k);
//                                tempY.add(l);

                                for (int i1 = i; i < arr.length; i++) {
                                    for (int j1 = 0; j < arr[i].length; j++) {
                                        if (arr[i1][j1] == arr[i][j] && isNeighbour(i, j, i1, j1, arr)) {
                                            area++;
                                        }
                                    }
                                }

                                for (int m = 0; m < groupsX.size(); m++) {
                                    if (isGroupM(groupsX.get(m), groupsY.get(m), k, l, arr[i][j], arr)) {
                                        groupsX.get(m).add(k);
                                        groupsY.get(m).add(l);
                                        isGroup = true;
                                        break;
                                    }
                                }

                                if (!isGroup) {
                                    List<Integer> tempX = new ArrayList<>();
                                    List<Integer> tempY = new ArrayList<>();
                                    tempX.add(k);
                                    tempY.add(l);
                                    groupsX.add(tempX);
                                    groupsY.add(tempY);
                                }



                            }

                        }
                    }

                    //System.out.println("Character: " + arr[i][j] + " Area: " + area + " Perimeter: " + perimeter);


//                    System.out.println("Groups: " + groupsX.size());

                    // groups
//                    for (int m = 0; m < groupsX.size(); m++) {
//
//                        for (int n = 0; n < groupsX.get(m).size(); n++) {
//                            System.out.print("X: " + groupsX.get(m).get(n) + " Y: " + groupsY.get(m).get(n) + " ");
//                        }
//                        System.out.println();
//                    }

                    for (int k = 0; k < groupsX.size(); k++) {

//                        System.out.println("Group: " + k);

                        for (int l = 0; l < groupsX.get(k).size(); l++) {

//                            System.out.print("X: " + groupsX.get(k).get(l) + " Y: " + groupsY.get(k).get(l) + " ");

                            area++;
                            perimeter += getPerimeter(arr, groupsX.get(k).get(l), groupsY.get(k).get(l));
                        }

//                        System.out.println();

                        System.out.println(i + " " + j + "Character: " + arr[i][j] + " Area: " + area + " Perimeter: " + perimeter);

                        sum += area * perimeter;
                        area = 0;
                        perimeter = 0;
                    }


//                    sum += area * perimeter;
                    visited.add(arr[i][j]);
//                    area = 0;
//                    perimeter = 0;
                }

            }
        }

        return sum;
    }

    private static int getPerimeter(char[][] arr, int i, int j) {
        int perimeter = 0;

        if ((i - 1 >= 0 && arr[i - 1][j] != arr[i][j]) || i == 0) {
            perimeter++;
        }

        if ((i + 1 < arr.length && arr[i + 1][j] != arr[i][j]) || i == arr.length - 1) {
            perimeter++;
        }

        if ((j - 1 >= 0 && arr[i][j - 1] != arr[i][j]) || j == 0) {
            perimeter++;
        }

        if ((j + 1 < arr[i].length && arr[i][j + 1] != arr[i][j]) || j == arr[i].length - 1) {
            perimeter++;
        }

        return perimeter;
    }

    private static boolean isGroupM(List<Integer> groupX, List<Integer> groupY, int x, int y, char c, char[][] arr) {

        int xC, yC, xCC, yCC;

        for (int i = 0; i < groupX.size(); i++) {

            xC = groupX.get(i);
            yC = groupY.get(i);

            if ((xC - 1 == x && yC == y) || (xC + 1 == x && yC == y) || (xC == x && yC - 1 == y) || (xC == x && yC + 1 == y)) {
                return true;
            }
        }

        for (int i = 0; i < 4; i++) {

            if (i == 0) {
                if (x - 1 >= 0 && arr[x - 1][y] == c) {
                    xCC = x - 1;
                    yCC = y;

                    for (int j = 0; j < groupX.size(); j++) {
                        if ((xCC - 1 == groupX.get(j) && yCC == groupY.get(j)) || (xCC + 1 == groupX.get(j) && yCC == groupY.get(j)) || (xCC == groupX.get(j) && yCC - 1 == groupY.get(j)) || (xCC == groupX.get(j) && yCC + 1 == groupY.get(j))) {
                            return true;
                        }
                    }

                }
            } else if (i == 1) {
                if (x + 1 < arr.length && arr[x + 1][y] == c) {
                    xCC = x + 1;
                    yCC = y;

                    for (int j = 0; j < groupX.size(); j++) {
                        if ((xCC - 1 == groupX.get(j) && yCC == groupY.get(j)) || (xCC + 1 == groupX.get(j) && yCC == groupY.get(j)) || (xCC == groupX.get(j) && yCC - 1 == groupY.get(j)) || (xCC == groupX.get(j) && yCC + 1 == groupY.get(j))) {
                            return true;
                        }
                    }
                }
            } else if (i == 2) {
                if (y - 1 >= 0 && arr[x][y - 1] == c) {
                    xCC = x;
                    yCC = y - 1;

                    for (int j = 0; j < groupX.size(); j++) {
                        if ((xCC - 1 == groupX.get(j) && yCC == groupY.get(j)) || (xCC + 1 == groupX.get(j) && yCC == groupY.get(j)) || (xCC == groupX.get(j) && yCC - 1 == groupY.get(j)) || (xCC == groupX.get(j) && yCC + 1 == groupY.get(j))) {
                            return true;
                        }
                    }
                }
            } else if (i == 3) {
                if (y + 1 < arr[x].length && arr[x][y + 1] == c) {
                    xCC = x;
                    yCC = y + 1;

                    for (int j = 0; j < groupX.size(); j++) {
                        if ((xCC - 1 == groupX.get(j) && yCC == groupY.get(j)) || (xCC + 1 == groupX.get(j) && yCC == groupY.get(j)) || (xCC == groupX.get(j) && yCC - 1 == groupY.get(j)) || (xCC == groupX.get(j) && yCC + 1 == groupY.get(j))) {
                            return true;
                        }
                    }
                }
            }

        }

        return false;
    }

    private static boolean isNeighbour(int i, int j, int i1, int j1, char[][] arr) {
        if ((i1 - 1 == i && j1 == j) || (i1 + 1 == i && j1 == j) || (i1 == i && j1 - 1 == j) || (i1 == i && j1 + 1 == j)) {
            return true;
        }
        return false;
    }

    private static boolean isAlreadyHaveGroup(List<List<Integer>> groupsX, List<List<Integer>> groupsY, int x, int y) {
        for (int i = 0; i < groupsX.size(); i++) {
            for (int j = 0; j < groupsX.get(i).size(); j++) {
                if (groupsX.get(i).get(j) == x && groupsY.get(i).get(j) == y) {
                    return true;
                }
            }
        }
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
