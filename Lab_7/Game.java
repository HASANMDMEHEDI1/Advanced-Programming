import java.util.*;

public class Game {
    public static void main(String[] args) {
        Bag bag = new Bag();
        Dictionary dict = new Dictionary(Arrays.asList("CAT", "DOG", "TREE", "BOOK", "GAME", "JAVA", "CODE"));

        List<Thread> threads = new ArrayList<>();
        List<Player> players = Arrays.asList(
            new Player("Alice", bag, dict),
            new Player("Bob", bag, dict),
            new Player("Charlie", bag, dict)
        );

        for (Player p : players) {
            Thread t = new Thread(p);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Game over.");
    }
}
