package news;

import java.time.LocalDateTime;
import java.util.Calendar;

public class NewsArticle {

    private String title;
    private String author;
    private String section; /* filter */
    private LocalDateTime publishDate; /* filter */

    public NewsArticle(String title, String author, String section, LocalDateTime publishDate) {
        this.title = title;
        this.author = author;
        this.section = section;
        this.publishDate = publishDate;
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

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", section='" + section + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
