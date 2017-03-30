package momo.test;

import momo.crawler.WebCrawler;
import momo.saver.FileContentSaver;

/**
 * Created by ercan on 23.03.2017.
 */
public class Main {
    public static void main(String [] args){
        String url = "http://facebook.com";
        /*ContentHandler contentHandler = new ContentHandler(url, new UrlContent());
        String content = contentHandler.fetchContent();

        FileContentSaver fileContentSaver = new FileContentSaver(1);
        for(int i = 0; i < 5; i++){
            fileContentSaver.save(contentHandler.getSentences(), url);
            fileContentSaver.setFileNameIndex(fileContentSaver.getFileNameIndex() + 1);
        }*/

        WebCrawler webCrawler = new WebCrawler(new FileContentSaver(1));
        webCrawler.crawl(url);

    }
}
