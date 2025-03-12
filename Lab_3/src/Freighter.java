public class Freighter extends Aircraft implements CargoCapable {
    private double maxPayload;

    public Freighter(String name, String model, String tailNumber, double maxPayload) {
        super(name, model, tailNumber);
        this.maxPayload = maxPayload;
    }


    @Override
    public double getMaxPayload() {
        return maxPayload;
    }

    @Override
    public String toString() {
        return String.format("Freighter: %s (%s) - Tail Number: %s, Max Payload: %.2f kg",
                name, model, tailNumber, maxPayload);
    }
}
