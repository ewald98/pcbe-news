package news;

import events.Event;
import events.EventDispatcher;
import events.EventFilter;
import events.EventHandler;
import news.actors.Reader;
import news.events.NewsEvent;
import news.events.NewsFilter;

import java.util.HashMap;
import java.util.Set;

public class NewsSystem implements EventHandler {

    private EventDispatcher dispatcher = new EventDispatcher();
    private HashMap<NewsArticle, Integer> news= new HashMap<>();

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

    public NewsSystem() {
        dispatcher.registerListener(NewsEvent.NewsType.READ, this);
    }

    public Set<NewsArticle> getAllNews() {
        return news.keySet();
    }

    // TODO: Implement
    public void subscribe(Reader reader, NewsArticle newsArticle) {
        dispatcher.registerListener(NewsEvent.NewsType.UPDATED, reader);
    }

    public void subscribe(Reader reader, String section) {
        dispatcher.registerListener(NewsEvent.NewsType.PUBLISHED, reader, new NewsFilter(section));
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

    public void addNewsArticle(NewsArticle newsArticle) {
        news.put(newsArticle, 0);
        dispatcher.dispatch(new NewsEvent(NewsEvent.NewsType.PUBLISHED, newsArticle));
    }


    // used to populate news hashmap (for debugging mostly)
    public void addNewsArticleManually(NewsArticle newsArticle) {
        news.put(newsArticle, 0);
    }

}

