package types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperatingHours {
    private Date[] monday;
    private Date[] tuesday;
    private Date[] wednesday;
    private Date[] thursday;
    private Date[] friday;
    private Date[] saturday;
    private Date[] sunday;

    public Date[] getMonday() {
        return monday;
    }

    @JsonProperty("Monday")
    public void setMonday(String value) {
        try {
            this.monday = stringToDateArray(value);
        } catch (IllegalArgumentException e) {
            // If the inputted time is illegal, just set the restaurant to closed
            this.monday = new Date[]{new Date("Closed")};
        }
    }

    public Date[] getTuesday() {
        return tuesday;
    }

    @JsonProperty("Tuesday")
    public void setTuesday(String value) {
        try {
            this.tuesday = stringToDateArray(value);
        } catch (IllegalArgumentException e) {
            // If the inputted time is illegal, just set the restaurant to closed
            this.tuesday = new Date[]{new Date("Closed")};
        }
    }

    public Date[] getWednesday() {
        return wednesday;
    }

    @JsonProperty("Wednesday")
    public void setWednesday(String value) {
        try {
            this.wednesday = stringToDateArray(value);
        } catch (IllegalArgumentException e) {
            // If the inputted time is illegal, just set the restaurant to closed
            this.wednesday = new Date[]{new Date("Closed")};
        }
    }

    public Date[] getThursday() {
        return thursday;
    }

    @JsonProperty("Thursday")
    public void setThursday(String value) {
        try {
            this.thursday = stringToDateArray(value);
        } catch (IllegalArgumentException e) {
            // If the inputted time is illegal, just set the restaurant to closed
            this.thursday = new Date[]{new Date("Closed")};
        }
    }

    public Date[] getFriday() {
        return friday;
    }

    @JsonProperty("Friday")
    public void setFriday(String value) {
        try {
            this.friday = stringToDateArray(value);
        } catch (IllegalArgumentException e) {
            // If the inputted time is illegal, just set the restaurant to closed
            this.friday = new Date[]{new Date("Closed")};
        }
    }

    public Date[] getSaturday() {
        return saturday;
    }

    @JsonProperty("Saturday")
    public void setSaturday(String value) {
        try {
            this.saturday = stringToDateArray(value);
        } catch (IllegalArgumentException e) {
            // If the inputted time is illegal, just set the restaurant to closed
            this.tuesday = new Date[]{new Date("Closed")};
        }
    }

    public Date[] getSunday() {
        return sunday;
    }

    @JsonProperty("Sunday")
    public void setSunday(String value) {
        try {
            this.sunday = stringToDateArray(value);
        } catch (IllegalArgumentException e) {
            // If the inputted time is illegal, just set the restaurant to closed
            this.sunday = new Date[]{new Date("Closed")};
        }
    }

    public Date[] stringToDateArray(String value) {
        if (value.equals("Open 24 hours")) {
            Date[] hours = new Date[]{new Date("0:00 am - 12:00 pm")};
            return hours;
        }
        String[] split = value.split(",");
        Date[] hours = new Date[split.length];
        for (int i = 0; i < split.length; i++) {
            hours[i] = new Date(split[i]);
        }
        return hours;
    }
}
