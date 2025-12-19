import java.util.*;

public class MarkovGenerator {
    private Map<String, List<String>> markovChain = new HashMap<>();
    private Random random = new Random();
    private int n; // n-gram size

    public MarkovGenerator(int n) {
        this.n = n;
    }
    public static String preprocess(String text) {
        return text.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
    }
    public void train(String[] words) {
        if (words.length < n) return;
        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder keyBuilder = new StringBuilder();
            for (int j = 0; j < n - 1; j++) {
                keyBuilder.append(words[i + j]).append(" ");
            }
            String key = keyBuilder.toString().trim();
            String nextWord = words[i + n - 1];
            markovChain.computeIfAbsent(key, k -> new ArrayList<>()).add(nextWord);
        }
    }
    public String generateSentence(int maxWords, String seed) {
        if (markovChain.isEmpty()) return "";

        String key = seed != null && markovChain.containsKey(seed) ? seed :
                new ArrayList<>(markovChain.keySet()).get(random.nextInt(markovChain.size()));

        StringBuilder sentence = new StringBuilder(key);
        String[] keyWords = key.split("\\s+");

        for (int i = 0; i < maxWords - (n - 1); i++) {
            List<String> nextWords = markovChain.get(key);
            if (nextWords == null || nextWords.isEmpty()) break;
            String nextWord = nextWords.get(random.nextInt(nextWords.size()));
            sentence.append(" ").append(nextWord);

            StringBuilder newKey = new StringBuilder();
            for (int j = 1; j < keyWords.length; j++) {
                newKey.append(keyWords[j]).append(" ");
            }
            newKey.append(nextWord);
            key = newKey.toString().trim();
            keyWords = key.split("\\s+");
        }
        return sentence.toString();
    }
    public int getUniqueNGrams() {
        return markovChain.size();
    }
    public String mostCommonNextWord(String key) {
        List<String> nextWords = markovChain.get(key);
        if (nextWords == null || nextWords.isEmpty()) return null;

        Map<String, Integer> freq = new HashMap<>();
        for (String w : nextWords) freq.put(w, freq.getOrDefault(w, 0) + 1);

        return Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}