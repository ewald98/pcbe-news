package news.actors;

import events.Event;
import events.EventHandler;
import news.NewsArticle;
import news.NewsSystem;
import news.events.NewsEvent;

import java.util.Random;
import java.util.Set;

public class Reader extends Thread implements EventHandler {

    private final boolean isActive;
    NewsSystem newsSystem;
    private final int readingDelayLowerBound = 1500; /* milliseconds */
    private final int readingDelayUpperBound = 2500; /* milliseconds */

    public Reader(NewsSystem newsSystem) {
        this.newsSystem = newsSystem;
        this.isActive = true;
    }

    // TODO: Implement
    public void subscribe(NewsArticle newsArticle) {
        newsSystem.subscribe(this, newsArticle);
    }

    public void subscribe(String section) {
        newsSystem.subscribe(this, section);
    }

    @Override
    public synchronized void handleEvent(Event event) {
        if (event.getType() == NewsEvent.NewsType.PUBLISHED) {
            System.out.println("Reader got notified of event:" + ((NewsEvent)event).getNewsArticle().getTitle());
            readNewsArticle(((NewsEvent)event).getNewsArticle());
        } else if (event.getType() == NewsEvent.NewsType.UPDATED) {
            System.out.println("Updated news article" + ((NewsEvent)event).getNewsArticle().getTitle());
            readNewsArticle(((NewsEvent)event).getNewsArticle());
        }
    }

    public void readNewsArticle(NewsArticle newsArticle) {
        newsSystem.getDispatcher().dispatch(new NewsEvent(NewsEvent.NewsType.READ, newsArticle));
    }

    public synchronized void run()
    {
        while(isActive)
        {
            Set<NewsArticle> newsArticleSet = newsSystem.getAllNews();

            Random randomActionGenerator = new Random();
            int action = randomActionGenerator.nextInt(2);
            switch(action)
            {
                case 0:
                    /* do nothing */
                    break;

                case 1: /* read a random existing news article */
                    if (newsArticleSet.size() > 0) {
                        Random randomArticleGenerator = new Random();
                        int targetArticleIndex, currentArticleIndex;
                        targetArticleIndex = randomArticleGenerator.nextInt(newsArticleSet.size());
                        currentArticleIndex = 0;

                        for (NewsArticle newsArticle : newsArticleSet) {
                            currentArticleIndex++;
                            if (currentArticleIndex == targetArticleIndex) {
                                System.out.println("##READER_READ:\t\t" + newsArticle);
                                readNewsArticle(newsArticle);
                                break;
                            }
                        }
                    }
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
