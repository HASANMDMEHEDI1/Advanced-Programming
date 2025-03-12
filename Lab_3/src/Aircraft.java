public abstract class Aircraft implements Comparable<Aircraft> {
    protected String name;
    protected String model;
    protected String tailNumber;

    public Aircraft(String name, String model, String tailNumber) {
        this.name = name;
        this.model = model;
        this.tailNumber = tailNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Aircraft other) {
        return this.name.compareTo(other.name);
    }

}