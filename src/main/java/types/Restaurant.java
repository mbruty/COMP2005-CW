package types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Restaurant implements Cloneable{
    private long id;
    private String name;
    private int DOHMH_inspection_score;
    private Neighborhood neighborhood;
    private String photograph;
    private String address;
    private Latlng latlng;
    private String cuisineType;
    private OperatingHours operatingHours;
    private Review[] reviews;

    public Restaurant clone() throws CloneNotSupportedException {
        return (Restaurant) super.clone();
    }

    // Empty constructor for deserialization
    public Restaurant() { }

    // Constructor for Cuisine in neighbourhood test
    public Restaurant(Neighborhood nbhood, String cuisine) {
        this.neighborhood = nbhood;
        this.cuisineType = cuisine;
    }

    public Restaurant(Review[] reviews, Neighborhood nbhood) {
        this.reviews = reviews;
        this.neighborhood = nbhood;
    }

    public Restaurant(OperatingHours hours) {
        this.operatingHours = hours;
    }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public int getDohmhInspectionScore() { return DOHMH_inspection_score; }
    @JsonProperty("DOHMH_inspection_score")
    public void setDohmhInspectionScore(String value) {
        if(value.equals("")) this.DOHMH_inspection_score = -1;
        else this.DOHMH_inspection_score = Integer.parseInt(value);
    }

    public Neighborhood getNeighborhood() { return neighborhood; }
    public void setNeighborhood(Neighborhood value) { this.neighborhood = value; }

    public String getPhotograph() { return photograph; }
    public void setPhotograph(String value) { this.photograph = value; }

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }

    public Latlng getLatlng() { return latlng; }
    public void setLatlng(Latlng value) { this.latlng = value; }

    public String getCuisineType() { return cuisineType; }
    @JsonProperty("cuisine_type")
    public void setCuisineType(String value) { this.cuisineType = value; }

    public OperatingHours getOperatingHours() { return operatingHours; }
    @JsonProperty("operating_hours")
    public void setOperatingHours(OperatingHours value) { this.operatingHours = value; }

    public Review[] getReviews() { return reviews; }
    public void setReviews(Review[] value) { this.reviews = value; }
}