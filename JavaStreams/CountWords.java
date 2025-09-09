import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class WordCountTop5 {
    public static void main(String[] args) {
        String fileName = "input.txt"; // your text file

        Map<String, Integer> wordCount = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Normalize text: lowercase + split on non-word chars
                String[] words = line.toLowerCase().split("\\W+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        int totalWords = wordCount.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total words: " + totalWords);

        List<Entry<String, Integer>> sortedWords = new ArrayList<>(wordCount.entrySet());
        sortedWords.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("\nTop 5 most frequent words:");
        for (int i = 0; i < Math.min(5, sortedWords.size()); i++) {
            Entry<String, Integer> entry = sortedWords.get(i);
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
