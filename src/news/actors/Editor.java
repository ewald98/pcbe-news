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

    // TODO: updateNewsArticle
    // TODO 2: should update some of the overall logic: if a reader is subscribed to a certain section and an article in that section is modified (but reader is not directly subscribed to that article), should he be notified?
    public void updateNewsArticle(NewsArticle newsArticle) {
    }

}
