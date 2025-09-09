import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "Java is great and Java is powerful. Java is widely used.";

        // Split paragraph into words (convert to lowercase & remove punctuation)
        List<String> words = Arrays.stream(paragraph.toLowerCase()
                        .replaceAll("[^a-z ]", "")  // keep only letters & spaces
                        .split("\\s+"))             // split by spaces
                .collect(Collectors.toList());

        // Count word frequencies using Collectors.toMap
        Map<String, Integer> wordCount = words.stream()
                .collect(Collectors.toMap(
                        word -> word,      // key = word
                        word -> 1,         // initial count = 1
                        Integer::sum       // merge function: add counts
                ));

        // Print result
        wordCount.forEach((word, count) -> 
                System.out.println(word + " -> " + count));
    }
}
