package news.actors;

import news.NewsArticle;
import news.NewsSystem;

import java.util.Random;
import java.util.Set;

// TODO: implements EventHandler
public class Editor extends Thread {

    private final boolean isActive = true;
    private final int publishingDelayLowerBound = 1000; /* milliseconds */
    private final int publishingDelayUpperBound = 2000; /* milliseconds */

    NewsSystem newsSystem;

    public Editor(NewsSystem newsSystem) {
        this.newsSystem = newsSystem;
    }

    public int getNoViews(NewsArticle newsArticle) {
        return newsSystem.getNoViews(newsArticle);
    }

    private String generateRandomWord() {
        Random rng = new Random();
        int wordLength = rng.nextInt(10 - 3 + 1) + 3;

        char[] letters = new char[wordLength];
        for (int index = 0; index < wordLength; index++) {
            letters[index] = (char) (rng.nextInt('z' - 'a' + 1) + 'a');
        }

        return new String(letters);
    }

    private String generateRandomTitle() {
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

    private String generateRandomAuthor() {
        return generateRandomWord() + " " + generateRandomWord();
    }

    private String generateRandomSection() {
        return generateRandomWord();
    }

    private NewsArticle generateRandomNewsArticle() {
        return new NewsArticle(generateRandomTitle(), generateRandomAuthor(), generateRandomSection());
    }

    public void addNewsArticle(NewsArticle newsArticle) {
        newsSystem.addNewsArticle(newsArticle);
    }

    // TODO: updateNewsArticle
    // TODO 2: should update some of the overall logic: if a reader is subscribed to a certain section and an article in that section is modified (but reader is not directly subscribed to that article), should he be notified?
    public void updateNewsArticle(NewsArticle newsArticle) {
        newsSystem.updateNewsArticle(newsArticle);
    }

    public synchronized void run() {
        while (isActive) {
            Set<NewsArticle> newsArticleSet = newsSystem.getAllNews();

            Random randomActionGenerator = new Random();
            int action = randomActionGenerator.nextInt(3);
            switch (action) {
                case 0:
                    /* do nothing */
                    break;

                case 1: /* add a new random news article */
                    NewsArticle someNewsArticle = generateRandomNewsArticle();
                    System.out.println("##EDITOR_GENERATE:\t\t" + someNewsArticle);
                    addNewsArticle(someNewsArticle);
                    break;

                case 2: /* update a random existing news article */
                    if (newsArticleSet.size() > 0) {
                        Random randomArticleGenerator = new Random();
                        int targetArticleIndex, currentArticleIndex;
                        targetArticleIndex = randomArticleGenerator.nextInt(newsArticleSet.size());
                        currentArticleIndex = 0;

                        for (NewsArticle newsArticle : newsArticleSet) {
                            if (currentArticleIndex == targetArticleIndex) {
                                System.out.println("##EDITOR_UPDATE:\t\t" + newsArticle);
                                updateNewsArticle(newsArticle);
                                break;
                            }
                            currentArticleIndex++;
                        }
                    }
                    break;

                default:
                    System.out.println("Whoops, wrong action!");
                    break;
            }

            try {
                /* wait for a while */
                Random randomDelayGenerator = new Random();
                int delayMillis = randomDelayGenerator.nextInt(publishingDelayUpperBound - publishingDelayLowerBound + 1) + publishingDelayLowerBound;
                sleep(delayMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}