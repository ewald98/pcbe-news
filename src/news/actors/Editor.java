package news.actors;

import events.Event;
import events.EventHandler;
import news.NewsArticle;
import news.NewsSystem;

public class Editor {

    NewsSystem newsSystem;

    public Editor(NewsSystem newsSystem) {
        this.newsSystem = newsSystem;
    }

    public void getNoViews(NewsArticle newsArticle) {
        System.out.println(
                "No of views requested by editor: " +
                newsSystem.getNoViews(newsArticle));
    }

    public void addNewsArticle(NewsArticle newsArticle) {
        newsSystem.addNewsArticle(newsArticle);
    }

}
