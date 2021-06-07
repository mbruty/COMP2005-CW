package filter_stratergies;

import types.Date;
import types.OperatingHours;
import types.Restaurant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DayAndHour implements IFilter {
    private final String day;
    private final int hour;
    private final int min;

    public DayAndHour(String day, int hour, int min) {
        this.day = day;
        this.hour = hour;
        this.min = min;
    }

    public DayAndHour(String day, String hour, String min) {
        this.day = day;
        this.hour = Integer.parseInt(hour);
        this.min = Integer.parseInt(min);
    }

    @Override
    public boolean doCompare(Restaurant restaurant) {
        if (restaurant.getOperatingHours() == null) return false;
        // Convert the day to first letter capitalised, i.e. Monday
        String day = this.day.substring(0, 1).toUpperCase() + this.day.substring(1).toLowerCase();
        Method[] methods = OperatingHours.class.getDeclaredMethods();
        Date[] time = null;
        for (Method m :
                methods) {
            if (m.getName().equals("get" + day)) {
                try {
                    time = (Date[]) m.invoke(restaurant.getOperatingHours());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        if (time != null) {
            for (Date d :
                    time) {
                if (d.isInsideTime(this.hour, this.min)) {
                    return true;
                }
            }
        }
        return false;
    }
}
