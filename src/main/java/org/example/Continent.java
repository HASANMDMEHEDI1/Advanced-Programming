package org.example;

public class Continent {
    private int id;
    private String name;

    public Continent(int id, String name) { this.id = id; this.name = name; }
    public Continent(String name) { this(0, name); }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setId(int id) { this.id = id; }

    @Override
    public String toString() { return id + ": " + name; }
}
