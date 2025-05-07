package org.example;

public class Country {
    private int id;
    private String name;
    private String code;
    private Continent continent;

    public Country(int id, String name, String code, Continent continent) {
        this.id = id; this.name = name; this.code = code; this.continent = continent;
    }
    public Country(String name, String code, Continent continent) {
        this(0, name, code, continent);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public Continent getContinent() { return continent; }
    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return id + ": " + name + " (" + code + ") in " + continent.getName();
    }
}
