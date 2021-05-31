package types;


public class Hotel {
    private final Neighborhood neighborhood;
    private final Latlng latlng;

    public Hotel(String neighborhood, double lat, double lng) {
        this.neighborhood = Neighborhood.valueOf(neighborhood);
        this.latlng = new Latlng(lat, lng);
    }

    public Latlng getLatlng() {
        return latlng;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }
}
