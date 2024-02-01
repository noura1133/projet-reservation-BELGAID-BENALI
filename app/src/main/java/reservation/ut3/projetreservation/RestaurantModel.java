package reservation.ut3.projetreservation;

public class RestaurantModel {
    private String name;
    private String address;
    private int imageResource;

    public RestaurantModel(String name, String address, int imageResource) {
        this.name = name;
        this.address = address;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getImageResource() {
        return imageResource;
    }
}
