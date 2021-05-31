package types;

public class Latlng {
    private double lat;
    private double lng;

    public Latlng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    // Empty constructor for deserialization
    public Latlng() { }

    public double getLat() { return lat; }
    public void setLat(double value) { this.lat = value; }

    public double getLng() { return lng; }
    public void setLng(double value) { this.lng = value; }
}