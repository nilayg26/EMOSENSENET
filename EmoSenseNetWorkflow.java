import java.util.Scanner;

public class EmoSenseNetWorkflow {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text with emojis: ");
        String input = scanner.nextLine();

        System.out.println("\n--- Input Collection ---");
        System.out.println("Input text: " + input);

        String preprocessedText = extractText(input);
        System.out.println("\n--- Text Preprocessing ---");
        System.out.println("Preprocessed text: " + preprocessedText);

        String detectedEmojis = extractEmojis(input);
        System.out.println("\n--- Emoji Detection ---");
        System.out.println("Detected emojis: " + detectedEmojis);

        double textFeatureScore = extractTextFeatures(preprocessedText);
        double emojiFeatureScore = extractEmojiFeatures(detectedEmojis);
        System.out.println("\n--- Feature Extraction ---");
        System.out.println("Extracted Text Feature Score: " + textFeatureScore);
        System.out.println("Extracted Emoji Feature Score: " + emojiFeatureScore);

        double fusedScore = evaluateModelFusion(textFeatureScore, emojiFeatureScore);
        System.out.println("\n--- Model Evaluation ---");
        System.out.println("Intermediate Decision (Fused Score Z): " + fusedScore);

        String finalJudgment = generateFinalDecision(fusedScore);
        System.out.println("\n--- Final Decision Generation ---");
        System.out.println("Final Judgment: " + finalJudgment);
    }

    public static String extractText(String input) {
        StringBuilder textBuilder = new StringBuilder();
        int[] codePoints = input.codePoints().toArray();

        for (int cp : codePoints) {
            if (cp < 256) {
                textBuilder.appendCodePoint(cp);
            }
        }

        return textBuilder.toString().trim();
    }

    public static String extractEmojis(String input) {
        StringBuilder emojiBuilder = new StringBuilder();
        int[] codePoints = input.codePoints().toArray();

        for (int cp : codePoints) {
            if (cp >= 256) {
                emojiBuilder.appendCodePoint(cp);
            }
        }

        return emojiBuilder.toString().trim();
    }

    public static double extractTextFeatures(String text) {
        java.util.Map<String, Double> textEmbeddings = new java.util.HashMap<>();
        textEmbeddings.put("love", 1.0);
        textEmbeddings.put("excellent", 1.0);
        textEmbeddings.put("awesome", 0.9);
        textEmbeddings.put("amazing", 0.9);
        textEmbeddings.put("best", 0.9);
        textEmbeddings.put("great", 0.8);
        textEmbeddings.put("happy", 0.8);
        textEmbeddings.put("good", 0.5);
        textEmbeddings.put("nice", 0.4);
        textEmbeddings.put("okay", 0.0);
        textEmbeddings.put("fine", 0.0);
        textEmbeddings.put("alright", 0.0);
        textEmbeddings.put("bad", -0.5);
        textEmbeddings.put("sad", -0.6);
        textEmbeddings.put("terrible", -0.8);
        textEmbeddings.put("awful", -0.8);
        textEmbeddings.put("disappointed", -0.8);
        textEmbeddings.put("hate", -1.0);
        textEmbeddings.put("worst", -1.0);
        textEmbeddings.put("angry", -1.0);

        double score = 0.0;
        String[] words = text.toLowerCase().split("\\W+");
        for (String word : words) {
            score += textEmbeddings.getOrDefault(word, 0.0);
        }
        return score;
    }

    public static double extractEmojiFeatures(String emojis) {
        java.util.Map<String, Double> emojiEmbeddings = new java.util.HashMap<>();
        emojiEmbeddings.put("😍", 1.0);
        emojiEmbeddings.put("❤️", 1.0);
        emojiEmbeddings.put("💯", 0.9);
        emojiEmbeddings.put("✨", 0.8);
        emojiEmbeddings.put("😊", 0.8);
        emojiEmbeddings.put("😂", 0.6);
        emojiEmbeddings.put("🤣", 0.6);
        emojiEmbeddings.put("👍", 0.5);
        emojiEmbeddings.put("😐", 0.0);
        emojiEmbeddings.put("😕", -0.3);
        emojiEmbeddings.put("🙄", -0.4);
        emojiEmbeddings.put("😢", -0.8);
        emojiEmbeddings.put("😭", -0.9);
        emojiEmbeddings.put("💔", -0.9);
        emojiEmbeddings.put("💀", -0.9);
        emojiEmbeddings.put("😡", -1.0);
        emojiEmbeddings.put("🤮", -1.0);
        emojiEmbeddings.put("🤡", -1.0);

        double score = 0.0;
        int[] codePoints = emojis.codePoints().toArray();
        for (int cp : codePoints) {
            String emoji = new StringBuilder().appendCodePoint(cp).toString();
            score += emojiEmbeddings.getOrDefault(emoji, 0.0);
        }
        return score;
    }

    public static double evaluateModelFusion(double textScore, double emojiScore) {
        double textAttentionWeight = 0.35;
        double emojiAttentionWeight = 0.65;
        return (textScore * textAttentionWeight) + (emojiScore * emojiAttentionWeight);
    }

    public static String generateFinalDecision(double fusedScore) {
        if (fusedScore > 0.1) {
            return "Positive";
        } else if (fusedScore < -0.1) {
            return "Negative";
        }
        return "Neutral";
    }
}