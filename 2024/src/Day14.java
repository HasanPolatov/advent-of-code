import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day14 {

    public static void main(String[] args) {

        List<String> lines = Utils.getLinesFromFile();

        long part1Result;

        part1Result = part1Result(lines);

        System.out.println("Part 1 result: " + part1Result);

    }

    private static long part1Result(List<String> lines) {

        long sum = 0, maxX = 0, maxY = 0, startX = 0, startY = 0;

        List<Data> dataList = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));

            Data data = new Data();
            data.setX(Long.parseLong(lines.get(i).substring(lines.get(i).indexOf('=') + 1, lines.get(i).indexOf(','))));
            data.setY(Long.parseLong(lines.get(i).substring(lines.get(i).indexOf(',') + 1, lines.get(i).indexOf(' '))));
            data.setVx(Long.parseLong(lines.get(i).substring(lines.get(i).lastIndexOf('=') + 1, lines.get(i).lastIndexOf(','))));
            data.setVy(Long.parseLong(lines.get(i).substring(lines.get(i).lastIndexOf(',') + 1)));

            dataList.add(data);

            if (data.getX() > maxX) {
                maxX = data.getX();
            }

            if (data.getY() > maxY) {
                maxY = data.getY();
            }

//            System.out.println("x: " + data.getX() + " y: " + data.getY() + " vx: " + data.getVx() + " vy: " + data.getVy());

        }

        maxX++;
        maxY++;

//        System.out.println("maxX: " + maxX + " maxY: " + maxY);

        long[][] arr = new long[(int) maxY][(int) maxX];

        fillArrayWithZeros(arr);

        for (int i = 0; i < dataList.size(); i++) {

            startX =  dataList.get(i).getY();
            startY = dataList.get(i).getX();

//            if (startX == 4 && startY == 2) {


//                System.out.println("startX: " + startX + " startY: " + startY);

                for (int j = 0; j < 100; j++) {

                    if (j != 0) {
                        arr[(int) startX][(int) startY]--;
                    }
                    startX += dataList.get(i).getVy();
                    startY += dataList.get(i).getVx();

                    if (startX < 0) {
                        startX = maxY + startX;
                    }

                    if (startY < 0) {
                        startY = maxX + startY;
                    }

                    if (startX >= maxY) {
                        startX = startX - maxY;
                    }

                    if (startY >= maxX) {
                        startY = startY - maxX;
                    }

                    arr[(int) startX][(int) startY]++;

                }
//            }

        }

//        System.out.println();

//        for (int i = 0; i < maxY; i++) {
//            for (int j = 0; j < maxX; j++) {
//                System.out.print(arr[i][j]);
//
//                System.out.print(" ");
//
//                if (arr[i][j] >= 0) {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }

        long firstQuad = 0, secondQuad = 0, thirdQuad = 0, fourthQuad = 0;

        // first quadrant
        for (int i = 0; i < maxY / 2; i++) {
            for (int j = 0; j < maxX / 2; j++) {

                    firstQuad += arr[i][j];
            }
        }

        // second quadrant
        for (int i = 0; i < maxY / 2; i++) {
            for (int j = (int) ((maxX + 1) / 2); j < maxX; j++) {
                    secondQuad += arr[i][j];
            }
        }

        // third quadrant
        for (int i = (int) ((maxY + 1) / 2); i < maxY; i++) {
            for (int j = 0; j < maxX / 2; j++) {
                    thirdQuad += arr[i][j];

            }
        }

        // fourth quadrant
        for (int i = (int) ((maxY + 1) / 2); i < maxY; i++) {
            for (int j = (int) ((maxX + 1) / 2); j < maxX; j++) {
//                System.out.println("i: " + i + " j: " + j);
                    fourthQuad += arr[i][j];
            }
        }

//        System.out.println("firstQuad: " + firstQuad + " secondQuad: " + secondQuad + " thirdQuad: " + thirdQuad + " fourthQuad: " + fourthQuad);

        sum = firstQuad * secondQuad * thirdQuad * fourthQuad;

        return sum;
    }

    private static class Data {

        long x;
        long y;
        long vx;
        long vy;

        public long getX() {
            return x;
        }

        public void setX(long x) {
            this.x = x;
        }

        public long getY() {
            return y;
        }

        public void setY(long y) {
            this.y = y;
        }

        public long getVx() {
            return vx;
        }

        public void setVx(long vx) {
            this.vx = vx;
        }

        public long getVy() {
            return vy;
        }

        public void setVy(long vy) {
            this.vy = vy;
        }
    }

    private static void fillArrayWithZeros(long[][] arr) {
        for (long[] longs : arr) {
            Arrays.fill(longs, 0);
        }
    }

}