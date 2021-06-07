package types;

public class Review {
    private String name;
    private String date;
    private long rating;
    private String comments;

    // Empty constructor for deserializer
    public Review() {
    }

    public Review(long rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String value) {
        this.date = value;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long value) {
        this.rating = value;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String value) {
        this.comments = value;
    }
}