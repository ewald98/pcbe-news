package news.events;

import events.Event;
import news.NewsArticle;

public class NewsEvent extends Event {

    // For filters to work properly, we need information about newsArticle
    public enum NewsType implements Type {
        PUBLISHED,
        UPDATED,
        READ,
    }

    private NewsArticle newsArticle;

    public NewsEvent(Type type, NewsArticle newsArticle) {
        super(type);
        this.newsArticle = newsArticle;
    }

    public NewsArticle getNewsArticle() {
        return newsArticle;
    }
}
