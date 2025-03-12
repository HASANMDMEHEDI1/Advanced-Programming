import java.util.*;
public static void main(String[] args) {

    Airliner airliner = new Airliner("Boeing 737", "737-800", "ABC123", 180);
    Freighter freighter = new Freighter("Cargolux 747", "747-400F", "DEF456", 100000);
    Drone drone = new Drone("DJI Phantom", "Phantom 4", "GHI789", 10, 2.5);

    List<CargoCapable> cargoAircraft = new ArrayList<>();
    cargoAircraft.add(freighter);
    cargoAircraft.add(drone);

    System.out.println("Cargo-Capable Aircraft:");
    for (CargoCapable aircraft : cargoAircraft) {
        System.out.println(aircraft);
    }
}

