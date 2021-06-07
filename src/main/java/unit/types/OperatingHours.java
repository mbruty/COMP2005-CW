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

    public Date[] getMonday() { return monday; }
    @JsonProperty("Monday")
    public void setMonday(String value) { this.monday = stringToDateArray(value); }

    public Date[] getTuesday() { return tuesday; }
    @JsonProperty("Tuesday")
    public void setTuesday(String value) { this.tuesday = stringToDateArray(value); }

    public Date[] getWednesday() { return wednesday; }
    @JsonProperty("Wednesday")
    public void setWednesday(String value) { this.wednesday = stringToDateArray(value); }

    public Date[] getThursday() { return thursday; }
    @JsonProperty("Thursday")
    public void setThursday(String value) { this.thursday = stringToDateArray(value); }

    public Date[] getFriday() { return friday; }
    @JsonProperty("Friday")
    public void setFriday(String value) { this.friday = stringToDateArray(value); }

    public Date[] getSaturday() { return saturday; }
    @JsonProperty("Saturday")
    public void setSaturday(String value) { this.saturday = stringToDateArray(value); }

    public Date[] getSunday() { return sunday; }
    @JsonProperty("Sunday")
    public void setSunday(String value) { this.sunday = stringToDateArray(value); }

    public Date[] stringToDateArray(String value) {
        if(value.equals("Open 24 hours")) {
            Date[] hours = new Date[] { new Date("0:00 am - 12:00 pm") };
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
