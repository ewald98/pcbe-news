package news.actors;

import events.Event;
import events.EventHandler;
import news.NewsArticle;
import news.NewsSystem;
import news.events.NewsEvent;

public class Reader implements EventHandler {

    NewsSystem newsSystem;

    public Reader(NewsSystem newsSystem) {
        this.newsSystem = newsSystem;
    }

    // TODO: Implement
    public void subscribe(NewsArticle newsArticle) {
        newsSystem.subscribe(this, newsArticle);
    }

    public void subscribe(String section) {
        newsSystem.subscribe(this, section);
    }

    @Override
    public void handleEvent(Event event) {
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

}
