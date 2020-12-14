package news.actors;

import news.NewsArticle;
import news.NewsSystem;
import utils.ArticleGenerator;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;

public class Editor extends Thread {

    private final boolean isActive = true;
    private final int publishingDelayLowerBound = 1000; /* milliseconds */
    private final int publishingDelayUpperBound = 2000; /* milliseconds */
    private final String name = ArticleGenerator.generateRandomWord(5);

    NewsSystem newsSystem;

    public Editor(NewsSystem newsSystem) {
        this.newsSystem = newsSystem;
    }

    public int getNoViews(NewsArticle newsArticle) {
        return newsSystem.getNoViews(newsArticle);
    }

    public void addNewsArticle(NewsArticle newsArticle) {
        newsSystem.addNewsArticle(newsArticle);
    }

    public void updateNewsArticle(NewsArticle newsArticle) {
        newsSystem.updateNewsArticle(newsArticle);
    }

    public synchronized void run() {
        while (isActive) {
            Set<NewsArticle> newsArticleSet = newsSystem.getAllNews();

            Random randomActionGenerator = new Random();
            int action = randomActionGenerator.nextInt(4);
            switch (action) {
                case 0:
                    /* do nothing */
                    break;

                case 1: /* add a new random news article */
                    NewsArticle someNewsArticle = ArticleGenerator.generateRandomNewsArticle();
                    addNewsArticle(someNewsArticle);
                    System.out.println("__  E" + name + "_GENERATE  __:\t" + someNewsArticle);
                    break;

                case 2: /* update a random existing news article */
                    if (newsArticleSet.size() > 0) {
                        Random randomArticleGenerator = new Random();
                        int targetArticleIndex, currentArticleIndex;
                        targetArticleIndex = randomArticleGenerator.nextInt(newsArticleSet.size());
                        currentArticleIndex = 0;

                        for (NewsArticle newsArticle : newsArticleSet) {
                            if (currentArticleIndex == targetArticleIndex) {
                                updateNewsArticle(newsArticle);
                                System.out.println("__   E" + name + "_UPDATE   __:\t" + newsArticle);
                                break;
                            }
                            currentArticleIndex++;
                        }
                    }
                    break;

                case 3: /* get the number of views for a random article */
                    if (newsArticleSet.size() > 0) {
                        Random randomArticleGenerator = new Random();
                        int targetArticleIndex, currentArticleIndex;
                        targetArticleIndex = randomArticleGenerator.nextInt(newsArticleSet.size());
                        currentArticleIndex = 0;

                        for (NewsArticle newsArticle : newsArticleSet) {
                            if (currentArticleIndex == targetArticleIndex) {
                                System.out.println("__   E" + name + "_QUERY    __:\t" + newsArticle + " has " + getNoViews(newsArticle) + " views");
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