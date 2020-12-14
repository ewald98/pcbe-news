package news.events;

import events.Event;
import events.EventFilter;
import news.NewsArticle;

import java.time.LocalDateTime;

public class NewsFilter extends EventFilter {

    private NewsArticle newsArticle = null;
    private String section = null;
    private String author = null;
    private LocalDateTime publishDate = null;

    public NewsFilter(String section) {
        this.section = section;
    }

    public NewsFilter(String author, Object dummy) {
        this.author = author;
    }

    public NewsFilter(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public NewsFilter(NewsArticle newsArticle) {
        this.newsArticle = newsArticle;
    }

    @Override
    public boolean filterEvent(Event event) {
        NewsArticle eventNewsArticle = ((NewsEvent) event).getNewsArticle();

        /* filter by an actual news article */
        if (newsArticle != null && !(newsArticle == eventNewsArticle))
            return false;

        /* filter by section */
        if (section != null && !section.equals(eventNewsArticle.getSection()))
            return false;

        /* filter by author */
        if (author != null && !author.equals(eventNewsArticle.getAuthor()))
            return false;

        /* filter by publish date, plus or minus one day */
        if (publishDate != null && !(publishDate.isAfter(eventNewsArticle.getPublishDate().minusDays(1)) && publishDate.isBefore(eventNewsArticle.getPublishDate().plusDays(1))))
            return false;

        return true;
    }

}
