package org.example;

public class City {
    private int id;
    private Country country;
    private String name;
    private boolean capital;
    private double latitude;
    private double longitude;

    public City(int id, Country country, String name, boolean capital, double latitude, double longitude) {
        this.id = id; this.country = country; this.name = name;
        this.capital = capital; this.latitude = latitude; this.longitude = longitude;
    }

    public City(Country country, String name, boolean capital, double latitude, double longitude) {
        this(0, country, name, capital, latitude, longitude);
    }

    public int getId() { return id; }
    public Country getCountry() { return country; }
    public String getName() { return name; }
    public boolean isCapital() { return capital; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    public void setId(int id) { this.id = id; }

    public double distanceTo(City other) {
        final int EARTH_RADIUS_KM = 6371; // Earth's radius in kilometers

        double lat1Rad = Math.toRadians(this.latitude);
        double lat2Rad = Math.toRadians(other.latitude);
        double lon1Rad = Math.toRadians(this.longitude);
        double lon2Rad = Math.toRadians(other.longitude);

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                   Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return EARTH_RADIUS_KM * c;
    }

    @Override
    public String toString() {
        return name + " (" + (capital ? "Capital" : "City") + ") at [" + latitude + ", " + longitude + "]";
    }
}
