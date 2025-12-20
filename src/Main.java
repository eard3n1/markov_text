import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Path filePath = Paths.get("../data/input.txt");
            String text = Files.readString(filePath);
            text = MarkovGenerator.preprocess(text);
            String[] words = text.split("\\s+");

            System.out.print("Enter n-gram size (2-5 recommended): ");
            int n = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter max sentence length: ");
            int maxLength = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter number of sentences to generate: ");
            int numSentences = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter seed word/sentence (or leave blank for random): ");
            String seedInput = scanner.nextLine().trim();
            String seed = seedInput.isEmpty() ? null : seedInput;

            MarkovGenerator generator = new MarkovGenerator(n);
            generator.train(words);

            Path outputPath = Paths.get("../data/generated.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toFile()))) {
                System.out.println("\nGenerated Sentences:\n");
                for (int i = 0; i < numSentences; i++) {
                    String sentence = generator.generateSentence(maxLength, seed);
                    System.out.println((i + 1) + ". " + sentence);
                    writer.write(sentence);
                    writer.newLine();
                }
            }
            System.out.println("\nUnique n-grams: " + generator.getUniqueNGrams());
            if (seed != null && generator.mostCommonNextWord(seed) != null) {
                System.out.println("Most common following word for '" + seed + "': " + generator.mostCommonNextWord(seed));
            }
            System.out.println("\nSaved to: data/generated.txt");

        } catch (IOException e) {
            System.out.println("Error reading/writing files: " + e.getMessage());
        }
        scanner.close();
    }
}