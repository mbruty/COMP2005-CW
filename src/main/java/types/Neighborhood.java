package types;

import java.io.IOException;

public enum Neighborhood {
    Brooklyn, Manhattan, Queens;

    public String toValue() {
        return switch (this) {
            case Brooklyn -> "Brooklyn";
            case Manhattan -> "Manhattan";
            case Queens -> "Queens";
        };
    }

    public static Neighborhood forValue(String value) throws IOException {
        if (value.equals("Brooklyn")) return Brooklyn;
        if (value.equals("Manhattan")) return Manhattan;
        if (value.equals("Queens")) return Queens;
        throw new IOException("Cannot deserialize Neighborhood");
    }
}