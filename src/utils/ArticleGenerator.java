package utils;

import news.NewsArticle;

import java.time.LocalDateTime;
import java.util.Random;

public class ArticleGenerator {

    private static final String[] newsSections = {"painting", "storage", "safety", "product", "music", "nature",
            "salad",
            "student", "driver", "bonus", "cookie", "player", "unit", "member", "highway", "guidance"};

    public static String generateRandomWord() {
        Random rng = new Random();
        int wordLength = rng.nextInt(10 - 3 + 1) + 3;

        char[] letters = new char[wordLength];
        for (int index = 0; index < wordLength; index++) {
            letters[index] = (char) (rng.nextInt('z' - 'a' + 1) + 'a');
        }

        return new String(letters);
    }

    public static String generateRandomWord(int length) {
        Random rng = new Random();

        char[] letters = new char[length];
        for (int index = 0; index < length; index++) {
            letters[index] = (char) (rng.nextInt('z' - 'a' + 1) + 'a');
        }

        return new String(letters);
    }

    public static String generateRandomTitle() {
        Random rng = new Random();
        int titleLength = rng.nextInt(6) + 1;

        String title = "";
        for (int index = 0; index < titleLength; index++) {
            title = title.concat(generateRandomWord());
            if (index != titleLength - 1) {
                title = title.concat(" ");
            }
        }

        return title;
    }

    public static String generateRandomAuthor() {
        return generateRandomWord() + " " + generateRandomWord();
    }

    public static String generateRandomSection() {
        Random randomSectionGenerator = new Random();
        int sectionIndex = randomSectionGenerator.nextInt(newsSections.length);

        return newsSections[sectionIndex];
    }

    public static LocalDateTime generateRandomPastDate() {
        Random randomTimeGenerator = new Random();
        int pastTime = randomTimeGenerator.nextInt(10) + 1; /* 10 days in the past */

        return LocalDateTime.now().minusDays(pastTime);
    }

    public static NewsArticle generateRandomNewsArticle() {
        return new NewsArticle(generateRandomTitle(), generateRandomAuthor(), generateRandomSection(), generateRandomPastDate());
    }
}
