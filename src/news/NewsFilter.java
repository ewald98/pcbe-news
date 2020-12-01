package news;

import events.Event;
import events.EventFilter;

public class NewsFilter extends EventFilter {

    // TODO: add more filters and rework filterEvent method
    private String section;

    public NewsFilter(String section) {
        this.section = section;
    }

    @Override
    public boolean filterEvent(Event event) {
        NewsArticle newsArticle = ((NewsEvent)event).getNewsArticle();

        if (!section.equals(newsArticle.getSection()))
            return false;

        return true;
    }

}
