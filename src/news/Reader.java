package news;

import events.Event;
import events.EventHandler;
import news.NewsArticle;

public class Reader implements EventHandler {

    NewsSystem newsSystem;

    public Reader(NewsSystem newsSystem) {
        this.newsSystem = newsSystem;
    }

    private void subscribe(NewsArticle newsArticle) {
        newsSystem.subscribe(this, newsArticle);
    }

    private void subscribe(String author) {
        // TODO: implement filters
    }

    @Override
    public void handleEvent(Event event) {
        if (event.getType() == NewsEvent.NewsType.PUBLISHED) {

        } else if (event.getType() == NewsEvent.NewsType.UPDATED) {

        }
    }

    public void readNewsArticle(NewsArticle newsArticle) {
        newsSystem.getDispatcher().dispatch(new NewsEvent(NewsEvent.NewsType.READ, newsArticle));
    }

}
