import news.Editor;
import news.NewsArticle;
import news.NewsSystem;
import news.Reader;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        NewsSystem newsSystem = new NewsSystem();

        NewsArticle newsArticle1 = new NewsArticle(
                "Alegeri pe 6 Decembrie",
                "Ilie Iliescu",
                "Politica"
        );
        NewsArticle newsArticle2 = new NewsArticle(
                        "Stadionul Dan Paltinisanu se inchide",
                        "Paul Popescu",
                        "Sport"
        );

        newsSystem.addNewsArticleManually(newsArticle1);
        newsSystem.addNewsArticleManually(newsArticle2);

        Reader reader1 = new Reader(newsSystem);
        Reader reader2 = new Reader(newsSystem);

        Editor editor1 = new Editor(newsSystem);
        Editor editor2 = new Editor(newsSystem);

        reader1.readNewsArticle(newsArticle1);
        reader1.readNewsArticle(newsArticle1);
        reader1.readNewsArticle(newsArticle2);
        reader1.readNewsArticle(newsArticle1);
        reader1.readNewsArticle(newsArticle2);
        reader2.readNewsArticle(newsArticle1);
        reader2.readNewsArticle(newsArticle1);

    }

}
