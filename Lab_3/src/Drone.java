public class Drone extends Aircraft implements CargoCapable {
    private double maxPayload;
    private double batteryLife;

    public Drone(String name, String model, String tailNumber, double maxPayload, double batteryLife) {
        super(name, model, tailNumber);
        this.maxPayload = maxPayload;
        this.batteryLife = batteryLife;
    }

    @Override
    public double getMaxPayload() {
        return maxPayload;
    }

    public double getBatteryLife() {
        return batteryLife;
    }
    @Override
    public String toString() {
        return String.format("Drone: %s (%s) - Tail Number: %s, Max Payload: %.2f kg, Battery Life: %.1f hours",
                name, model, tailNumber, maxPayload, batteryLife);
    }
}