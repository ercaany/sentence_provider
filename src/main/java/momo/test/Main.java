package momo.test;

import momo.application.Application;

import java.io.IOException;

/**
 * Created by ercan on 23.03.2017.
 */
public class Main {
    public static void main(String [] args) throws IOException {
        String seed = "https://tr.wikipedia.org/wiki/Bizans_%C4%B0mparatorlu%C4%9Fu";
        Application application = new Application("tp", "source", "unique_word");
        //Set<WebPage> webPages = application.getWebPagesFromCrawler(seed);
        //application.saveWebPageData(webPages);
    }
}




