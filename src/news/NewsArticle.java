package news;

import java.time.LocalDateTime;
import java.util.Calendar;

public class NewsArticle {

    private String title;
    private String author;
    // NOTE: I don't think we need a news body, it's the thing that would be modified when a modification takes place,
    // but it doesn't matter in what we're trying to do here.

    private String section;
    // TODO: private String subSection;

//    TODO: add dateTimes (or whatever you want to store datetime)
//    private LocalDateTime publishDateTime;
//    private LocalDateTime lastModifiedDateTime;
    // TODO: add more fields?

    public NewsArticle(String title, String author, String section) {
        this.title = title;
        this.author = author;
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
