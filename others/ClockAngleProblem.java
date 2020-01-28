public class ClockAngleProblem {
    private double calculateAngle(int hours, int minutes) {
        // check corner cases
        // if (hours < 0 || minutes < 0 || hours > 24 || minutes > 60) {
        if (hours < 0 || minutes < 0) {
            return -1;
        }

        hours = (hours >= 24) ? (hours - 24) : hours;
        hours = hours >= 12 ? hours - 12 : hours;
        minutes = (minutes >= 60) ? (minutes - 60) : minutes;
        double hoursMultipler = 60 / 12;
        double minutePerDegree = 6.0;
        double hoursMove = (hours + (double)minutes / 60) * hoursMultipler;
        double angle = Math.abs(hoursMove - (double) minutes) * minutePerDegree;
        return angle > 180 ? angle - 180 : angle;
    }

    public static void main(String[] args) {
        ClockAngleProblem clockAngleProblem = new ClockAngleProblem();
        int[][] times = {
            {3, 30}, {12, 30}, {12, 45}, {12, 0}, {3, 30}, {2, 23}, {0, 0}
        };

        for (int i = 0; i < times.length; i++) {
            int[] time = times[i];
            int hours = time[0];
            int minutes = time[1];
            double angle = clockAngleProblem.calculateAngle(hours, minutes);
            System.out.println("Angle of " + hours + ":" + minutes + " is " + angle);
        }
    }
}