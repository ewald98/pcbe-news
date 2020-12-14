package news;

import events.Event;
import events.EventDispatcher;
import events.EventHandler;
import news.actors.Reader;
import news.events.NewsEvent;
import news.events.NewsFilter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class NewsSystem extends Thread implements EventHandler {

    private EventDispatcher dispatcher = new EventDispatcher();
    private HashMap<NewsArticle, Integer> news = new HashMap<>();

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

    public NewsSystem() {
        dispatcher.registerListener(NewsEvent.NewsType.READ, this);
    }

    public Set<NewsArticle> getAllNews() {
        return news.keySet();
    }

    public void subscribe(Reader reader, NewsArticle newsArticle) {
        dispatcher.registerListener(NewsEvent.NewsType.UPDATED, reader, new NewsFilter(newsArticle));
    }

    public void subscribe(Reader reader, String section) {
        dispatcher.registerListener(NewsEvent.NewsType.PUBLISHED, reader, new NewsFilter(section));
    }

    public void subscribe(Reader reader, LocalDateTime publishDate)
    {
        dispatcher.registerListener(NewsEvent.NewsType.PUBLISHED, reader, new NewsFilter(publishDate));
    }

    public int getNoViews(NewsArticle newsArticle) {
        return news.get(newsArticle);
    }

    @Override
    public void handleEvent(Event event) {
        // eventType will be: READ
        NewsArticle readArticle = ((NewsEvent) event).getNewsArticle();

        int count = news.get(readArticle);
        news.put(readArticle, count + 1);
    }

    public void addNewsArticle(NewsArticle newsArticle) {
        news.put(newsArticle, 0);
        dispatcher.addEvent(new NewsEvent(NewsEvent.NewsType.PUBLISHED, newsArticle));
    }

    // used to populate news hashmap (for debugging mostly)
    public void addNewsArticleManually(NewsArticle newsArticle) {
        news.put(newsArticle, 0);
    }

    public void updateNewsArticle(NewsArticle newsArticle) {
        newsArticle.setTitle("This title has been changed! <" + new Random().nextInt(Integer.MAX_VALUE) + ">");
        dispatcher.addEvent(new NewsEvent(NewsEvent.NewsType.UPDATED, newsArticle));
    }

    public synchronized void run()
    {
        dispatcher.start();
    }

}

