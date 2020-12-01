package news;

import events.Event;
import events.EventDispatcher;
import events.EventHandler;
import news.NewsArticle;
import news.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class NewsSystem implements EventHandler {

    private EventDispatcher dispatcher = new EventDispatcher();
    private HashMap<NewsArticle, Integer> news= new HashMap<>();

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

    public NewsSystem() {
        dispatcher.registerListener(Event.Type.READ, this);
    }

    public Set<NewsArticle> getAllNews() {
        return news.keySet();
    }

    public void subscribe(Reader reader, NewsArticle newsArticle) {
        // TODO: add filter to subscribe just to that specific newsArticle
        dispatcher.registerListener(Event.Type.UPDATED, reader);
    }

    public int getNoViews(NewsArticle newsArticle) {
        return news.get(newsArticle);
    }

    @Override
    public void handleEvent(Event event) {
        // eventType will be: NEWSREAD
        NewsArticle readArticle = ((NewsEvent) event).getNewsArticle();

        int count = news.get(readArticle);
        news.put(readArticle, count + 1);
    }



    // used to populate news hashmap (for debugging mostly)
    public void addNewsArticleManually(NewsArticle newsArticle) {
        news.put(newsArticle, 0);
    }

}

