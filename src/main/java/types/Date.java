package types;

import java.util.Arrays;

public class Date {
    private int openHours;
    private int openMins;

    private int closeHours;
    private int closeMins;

    private boolean isClosed = false;

    public Date(String time) {
        if(time.equals("Closed")) {
            this.isClosed = true;
            return;
        }
        String open = time.split("-")[0];
        String close = time.split("-")[1];

        try {
            int[] opens = stringToHoursMins(open);
            this.openHours = opens[0];
            this.openMins = opens[1];
        } catch (Exception e) {
            this.openHours = 0;
            this.openMins = 0;
        }
        try {
            int[] closes = stringToHoursMins(close);
            this.closeHours = closes[0];
            this.closeMins = closes[1];
        } catch (Exception e) {
            this.openHours = 24;
            this.openMins = 0;
        }
    }

    private int[] stringToHoursMins(String value) throws Exception {
        value = value.trim();
        String[] data = value.split(" ");
        boolean isPm;
        try{
             isPm = data[1].equals("pm");
        } catch (Exception e) {
            throw new Exception("Provided is not a number");
        }
        int hours = 0;
        int mins = 0;
        try{
            hours = Integer.parseInt(data[0].split(":")[0]);
            mins = openMins = Integer.parseInt(data[0].split(":")[1]);
        } catch (NumberFormatException e) {
            System.out.println(Arrays.toString(data));
        }


        if(isPm) {
            hours += 12;
        }
        return new int[] { hours, mins };
    }

    public String toString() {
        return this.openHours +
                ":" +
                this.openMins +
                " - " +
                this.closeHours +
                ":" +
                this.closeMins;
    }

    public boolean isInsideTime(int hour, int min) {
        if(this.isClosed) {
            return false;
        }
        if(hour == this.openHours) {
            return min < this.openMins;
        }
        if(hour == this.closeHours) {
            return min > this.openMins;
        }

        return this.openHours < hour && hour < this.closeHours;
    }
}
