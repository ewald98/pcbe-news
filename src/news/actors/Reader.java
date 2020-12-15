package news.actors;

import events.Event;
import events.EventHandler;
import news.NewsArticle;
import news.NewsSystem;
import news.events.NewsEvent;
import utils.ArticleGenerator;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;

public class Reader extends Thread implements EventHandler {

    NewsSystem newsSystem;

    private final int readingDelayLowerBound = 3000; /* milliseconds */
    private final int readingDelayUpperBound = 6000; /* milliseconds */
    private final boolean isActive = true;
    private final String name = ArticleGenerator.generateRandomWord(5);

    public Reader(NewsSystem newsSystem) {
        this.newsSystem = newsSystem;
        start();
    }

    public void subscribe(NewsArticle newsArticle) {
        newsSystem.subscribe(this, newsArticle);
    }

    public void subscribe(String section) {
        newsSystem.subscribe(this, section);
    }

    /* dummy variable because I can't overload the above method */
    public void subscribe(String author, Object dummy) {
        newsSystem.subscribe(author, this);
    }

    public void subscribe(LocalDateTime publishDate) {
        newsSystem.subscribe(this, publishDate);
    }

    @Override
    public void handleEvent(Event event) {
        if (event.getType() == NewsEvent.NewsType.PUBLISHED) {
            readNewsArticle(((NewsEvent) event).getNewsArticle());
            System.out.println("@@  R" + name + "_NOTIFIED  @@:\t" + ((NewsEvent) event).getNewsArticle().getTitle());
        } else if (event.getType() == NewsEvent.NewsType.UPDATED) {
            readNewsArticle(((NewsEvent) event).getNewsArticle());
            System.out.println("@@    R" + name + "_READ    @@:\t" + ((NewsEvent) event).getNewsArticle().getTitle());
        }
    }

    public void readNewsArticle(NewsArticle newsArticle) {
        newsSystem.getDispatcher().addEvent(new NewsEvent(NewsEvent.NewsType.READ, newsArticle));
    }

    public void run() {
        while (isActive) {
            Set<NewsArticle> newsArticleSet = newsSystem.getAllNews();

            Random randomActionGenerator = new Random();
            int action = randomActionGenerator.nextInt(5);
            switch (action) {
                case 0:
                    /* do nothing */
                    break;

                case 1: /* subscribe to a random news article */
                    if (newsArticleSet.size() > 0) {
                        Random randomArticleGenerator = new Random();
                        int targetArticleIndex, currentArticleIndex;
                        targetArticleIndex = randomArticleGenerator.nextInt(newsArticleSet.size() + 1);
                        currentArticleIndex = 0;

                        for (NewsArticle newsArticle : newsArticleSet) {
                            if (currentArticleIndex == targetArticleIndex) {
                                System.out.println("@@ R" + name + "_SUBSCRIBED @@:\t" + newsArticle);
                                subscribe(newsArticle);
                                break;
                            }
                            currentArticleIndex++;
                        }
                    }
                    break;

                case 2: /* subscribe to a random news section */
                    if (newsArticleSet.size() > 0) {
                        Random randomArticleGenerator = new Random();
                        int targetArticleIndex, currentArticleIndex;
                        targetArticleIndex = randomArticleGenerator.nextInt(newsArticleSet.size());
                        currentArticleIndex = 0;

                        for (NewsArticle newsArticle : newsArticleSet) {
                            if (currentArticleIndex == targetArticleIndex) {
                                System.out.println("@@ R" + name + "_SUBSCRIBED @@:\t" + newsArticle.getSection());
                                subscribe(newsArticle.getSection());
                                break;
                            }
                            currentArticleIndex++;
                        }
                    }
                    break;

                case 3: /* subscribe to a random author */
                    if (newsArticleSet.size() > 0) {
                        Random randomArticleGenerator = new Random();
                        int targetArticleIndex, currentArticleIndex;
                        targetArticleIndex = randomArticleGenerator.nextInt(newsArticleSet.size());
                        currentArticleIndex = 0;

                        for (NewsArticle newsArticle : newsArticleSet) {
                            if (currentArticleIndex == targetArticleIndex) {
                                System.out.println("@@ R" + name + "_SUBSCRIBED @@:\t" + newsArticle.getAuthor());
                                subscribe(newsArticle.getAuthor(), null);
                                break;
                            }
                            currentArticleIndex++;
                        }
                    }
                    break;

                case 4: /* subscribe to a random publish date */
                    if (newsArticleSet.size() > 0) {
                        Random randomArticleGenerator = new Random();
                        int targetArticleIndex, currentArticleIndex;
                        targetArticleIndex = randomArticleGenerator.nextInt(newsArticleSet.size());
                        currentArticleIndex = 0;

                        for (NewsArticle newsArticle : newsArticleSet) {
                            if (currentArticleIndex == targetArticleIndex) {
                                System.out.println("@@ R" + name + "_SUBSCRIBED @@:\t" + newsArticle.getPublishDate());
                                subscribe(newsArticle.getPublishDate());
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
                int delayMillis =
                        randomDelayGenerator.nextInt(readingDelayUpperBound - readingDelayLowerBound + 1) + readingDelayLowerBound;
                sleep(delayMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
