import java.util.List;

public class Day13 {

    public static void main(String[] args) {

        List<String> lines = Utils.getLinesFromFile();

        long part1Result;

        part1Result = part1Result(lines);

        System.out.println("Part 1 result: " + part1Result);

    }

    private static long part1Result(List<String> lines) {

        long sum = 0;

        Data data = new Data();

        for (int i = 0; i < lines.size(); i += 4) {

            data.setButtonAX(Long.parseLong(lines.get(i).substring(lines.get(i).indexOf('+') + 1, lines.get(i).indexOf(','))));
            data.setButtonAY(Long.parseLong(lines.get(i).substring(lines.get(i).lastIndexOf('+') + 1)));
            data.setButtonBX(Long.parseLong(lines.get(i + 1).substring(lines.get(i + 1).indexOf('+') + 1, lines.get(i + 1).indexOf(','))));
            data.setButtonBY(Long.parseLong(lines.get(i + 1).substring(lines.get(i + 1).lastIndexOf('+') + 1)));
            data.setPrizeX(Long.parseLong(lines.get(i + 2).substring(lines.get(i + 2).indexOf('=') + 1, lines.get(i + 2).indexOf(','))) + 10000000000000L);
            data.setPrizeY(Long.parseLong(lines.get(i + 2).substring(lines.get(i + 2).lastIndexOf('=') + 1)) + 10000000000000L);

//            System.out.println(data.getButtonAX() + " " + data.getButtonAY() + " " + data.getButtonBX() + " " + data.getButtonBY() + " " + data.getPrizeX() + " " + data.getPrizeY());

            sum += getButtonPressCount(data);

        }

        return sum;
    }

    private static long getButtonPressCount(Data data) {

//        System.out.println(data.getButtonAX() + " " + data.getButtonAY() + " " + data.getButtonBX() + " " + data.getButtonBY() + " " + data.getPrizeX() + " " + data.getPrizeY());

        long xCount = 101, yCount = 101;

        // buttonAX * x + buttonBX * y = prizeX
        // buttonAY * x + buttonBY * y = prizeY
        // x = (prizeX * buttonBY - prizeY * buttonBX) / (buttonAX * buttonBY - buttonAY * buttonBX)
        // y = (prizeY * buttonAX - prizeX * buttonAY) / (buttonAX * buttonBY - buttonAY * buttonBX)

        yCount = (data.getPrizeY() * data.getButtonAX() - data.getPrizeX() * data.getButtonAY()) / (data.getButtonAX() * data.getButtonBY() - data.getButtonAY() * data.getButtonBX());
        xCount = (data.getPrizeX() * data.getButtonBY() - data.getPrizeY() * data.getButtonBX()) / (data.getButtonAX() * data.getButtonBY() - data.getButtonAY() * data.getButtonBX());

//        System.out.println(xCount + " " + yCount);

        if (xCount * data.getButtonAX() + yCount * data.getButtonBX() == data.getPrizeX() && xCount * data.getButtonAY() + yCount * data.getButtonBY() == data.getPrizeY()) {
//            System.out.println(data.prizeX + " " + data.prizeY + " " + xCount + " " + yCount);
            return xCount * 3 + yCount;
        } else {
            return 0;
        }

    }

    private static class Data {

        long buttonAX;
        long buttonAY;
        long buttonBX;
        long buttonBY;
        long prizeX;
        long prizeY;

        public long getButtonAX() {
            return buttonAX;
        }

        public void setButtonAX(long buttonAX) {
            this.buttonAX = buttonAX;
        }

        public long getButtonAY() {
            return buttonAY;
        }

        public void setButtonAY(long buttonAY) {
            this.buttonAY = buttonAY;
        }

        public long getButtonBX() {
            return buttonBX;
        }

        public void setButtonBX(long buttonBX) {
            this.buttonBX = buttonBX;
        }

        public long getButtonBY() {
            return buttonBY;
        }

        public void setButtonBY(long buttonBY) {
            this.buttonBY = buttonBY;
        }

        public long getPrizeX() {
            return prizeX;
        }

        public void setPrizeX(long prizeX) {
            this.prizeX = prizeX;
        }

        public long getPrizeY() {
            return prizeY;
        }

        public void setPrizeY(long prizeY) {
            this.prizeY = prizeY;
        }
    }

}