import java.util.*;

public class Dictionary {
    private final Set<String> words;

    public Dictionary(List<String> wordList) {
        words = new HashSet<>(wordList);
    }

    public boolean isValidWord(String word) {
        return words.contains(word.toUpperCase());
    }
}