package news;

import events.Event;
import events.EventHandler;

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

}
