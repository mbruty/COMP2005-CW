package types;

public class Latlng {
    private Double lat;
    private Double lng;

    public Latlng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    // Empty constructor for deserialization
    public Latlng() {
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(double value) {
        this.lat = value;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(double value) {
        this.lng = value;
    }
}