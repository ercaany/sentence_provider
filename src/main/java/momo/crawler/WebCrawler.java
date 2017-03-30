package momo.crawler;

import momo.content.ContentHandler;
import momo.content.UrlContent;
import momo.saver.ContentSaver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ercan on 30.03.2017.
 */
public class WebCrawler {
    private final int MAX_PAGE_COUNT_TO_SEARCH = 200;
    private List<String> unvisitedPageList;
    private Set<String> visitedPageSet;
    private ContentSaver contentSaver;

    public WebCrawler(ContentSaver contentSaver){
        this.contentSaver = contentSaver;
        unvisitedPageList = new ArrayList<String>();
        visitedPageSet = new HashSet<String>();
    }

    public void crawl(String seed){
        UrlContent urlContent;
        unvisitedPageList.add(seed);


        String nextUrl;
        int pageCount = 0;
        while(!unvisitedPageList.isEmpty() && pageCount < MAX_PAGE_COUNT_TO_SEARCH ){
            try{
                nextUrl = getNextUrl();
                visitedPageSet.add(nextUrl);

                urlContent = new UrlContent();
                ContentHandler contentHandler = new ContentHandler(nextUrl, urlContent);
                contentHandler.fetchContent();
                contentSaver.save(contentHandler.getSentences(), nextUrl);

                System.out.println("##INFO## PAGE_NUMBER#" + pageCount +
                        " Succesfully connected to: " + nextUrl +
                        " Now collecting data.. ##INFO##");

                List<String> linksOnPage = urlContent.extractLinks();
                for(String link: linksOnPage){
                    if(!visitedPageSet.contains(link)){
                        unvisitedPageList.add(link);
                    }
                }
                pageCount++;

            }catch (Exception ex){
                System.out.println("##ERROR## There was an error connecting to this web page.. " +
                        "Moving to the next link.. ##ERROR##");
            }
        }
    }

    private String getNextUrl(){
        String nextUrl;
        do{
            nextUrl = unvisitedPageList.remove(0);
        }
        while(visitedPageSet.contains(nextUrl));
        visitedPageSet.add(nextUrl);

        return nextUrl;
    }

}
