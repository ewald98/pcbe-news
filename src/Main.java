import news.actors.Editor;
import news.NewsSystem;
import news.actors.Reader;

public class Main {

    public static void main(String[] args) {
        NewsSystem newsSystem = new NewsSystem();
        newsSystem.start();

        Editor editor1 = new Editor(newsSystem);
        Editor editor2 = new Editor(newsSystem);

        Reader reader1 = new Reader(newsSystem);
        Reader reader2 = new Reader(newsSystem);
        Reader reader3 = new Reader(newsSystem);
        Reader reader4 = new Reader(newsSystem);

        editor1.start();
        editor2.start();

        reader1.start();
        reader2.start();
        reader3.start();
        reader4.start();

    }

}
