package news.events;

import events.Event;
import events.EventFilter;
import news.NewsArticle;

public class NewsFilter extends EventFilter {

    private NewsArticle newsArticle = null;
    // TODO: add more filters and rework filterEvent method
    private String section = null;

    public NewsFilter(String section) {
        this.section = section;
    }

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

        return true;
    }

}
