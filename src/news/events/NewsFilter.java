package news.events;

import events.Event;
import events.EventFilter;
import news.NewsArticle;

import java.time.LocalDateTime;

public class NewsFilter extends EventFilter {

    private NewsArticle newsArticle = null;
    private String section = null;
    private LocalDateTime publishDate = null;

    public NewsFilter(String section) {
        this.section = section;
    }
    public NewsFilter(LocalDateTime publishDate){this.publishDate = publishDate;}

    public NewsFilter(NewsArticle newsArticle) {
        this.newsArticle = newsArticle;
    }

    @Override
    public boolean filterEvent(Event event) {
        NewsArticle eventNewsArticle = ((NewsEvent)event).getNewsArticle();

        if (newsArticle != null && !(newsArticle == eventNewsArticle))
            return false;
        if (section != null && !section.equals(eventNewsArticle.getSection()))
            return false;
        if(publishDate != null && !publishDate.isEqual(eventNewsArticle.getPublishDate()))
            return false;

        return true;
    }

}
