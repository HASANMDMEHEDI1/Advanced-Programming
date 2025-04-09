import java.util.*;

public class Player implements Runnable {
    private final String name;
    private final Bag bag;
    private final Dictionary dictionary;
    private final List<Tile> rack = new ArrayList<>();
    private int score = 0;

    public Player(String name, Bag bag, Dictionary dictionary) {
        this.name = name;
        this.bag = bag;
        this.dictionary = dictionary;
    }

    @Override
    public void run() {
        rack.addAll(bag.extractTiles(7));
        while (!bag.isEmpty()) {
            String word = tryCreateWord();
            if (word != null) {
                int points = calculateScore(word);
                score += points;
                System.out.println(name + " formed: " + word + " for " + points + " points. Total: " + score);
                rack.addAll(bag.extractTiles(word.length()));
            } else {
                rack.clear();
                rack.addAll(bag.extractTiles(7));
                System.out.println(name + " couldn't form a word. Turn skipped.");
            }

            try {
                Thread.sleep(500); // simulate thinking time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(name + " finished with score: " + score);
    }

    private String tryCreateWord() {
        List<Character> letters = new ArrayList<>();
        for (Tile t : rack) letters.add(t.getLetter());

        for (String w : dictionary.words) {
            if (canFormWord(w, letters)) {
                for (char c : w.toCharArray()) {
                    rack.removeIf(t -> t.getLetter() == c); // remove used letters
                }
                return w;
            }
        }
        return null;
    }

    private boolean canFormWord(String word, List<Character> letters) {
        List<Character> temp = new ArrayList<>(letters);
        for (char c : word.toCharArray()) {
            if (!temp.remove((Character) c)) return false;
        }
        return true;
    }

    private int calculateScore(String word) {
        int total = 0;
        for (char c : word.toCharArray()) {
            for (Tile t : rack) {
                if (t.getLetter() == c) {
                    total += t.getPoints();
                    break;
                }
            }
        }
        return total;
    }
}
