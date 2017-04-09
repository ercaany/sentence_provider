package momo.crawler;

import momo.content.ContentHandler;
import momo.content.UrlContent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ercan on 30.03.2017.
 */
public class WebCrawler {
    private final int MAX_PAGE_COUNT_TO_SEARCH = 10;
    private List<String> unvisitedPageUrls;
    private Set<String> visitedPageUrlSet;
    private Set<WebPage> crawledWebPageSet;
    private String nextUrl;

    public WebCrawler(){
        unvisitedPageUrls = new ArrayList<String>();
        visitedPageUrlSet = new HashSet<String>();
        crawledWebPageSet = new HashSet<WebPage>();
    }

    public void crawl(String seed){
        UrlContent urlContent;
        unvisitedPageUrls.add(seed);

        int pageCount = 0;
        while(!unvisitedPageUrls.isEmpty() && pageCount < MAX_PAGE_COUNT_TO_SEARCH ){
            try{
                nextUrl = getNextUrl();
                visitedPageUrlSet.add(nextUrl);

                urlContent = new UrlContent();
                handleContent(urlContent);

                System.out.println("##INFO## PAGE_NUMBER#" + pageCount +
                        " Succesfully connected to: " + nextUrl +
                        " Now collecting data.. ##INFO##");

                updateUnvisitedPageUrls(urlContent);
                pageCount++;
            }catch (Exception ex){
                errorLog();
            }
        }
    }

    private void handleContent(UrlContent urlContent){
        ContentHandler contentHandler = new ContentHandler(nextUrl, urlContent);
        contentHandler.fetchContent();

        WebPage webPage = new WebPage(nextUrl);
        webPage.setSentences(contentHandler.getSentences());
        crawledWebPageSet.add(webPage);
    }

    private void updateUnvisitedPageUrls(UrlContent urlContent){
        List<String> linksOnPage = urlContent.extractLinks();
        for(String link: linksOnPage){
            if(!visitedPageUrlSet.contains(link) && !link.contains("#")){
                unvisitedPageUrls.add(link);
            }
        }
    }

    private String getNextUrl(){
        String nextUrl;
        do{
            nextUrl = unvisitedPageUrls.remove(0);
        }
        while(visitedPageUrlSet.contains(nextUrl));
        visitedPageUrlSet.add(nextUrl);

        return nextUrl;
    }

    private void errorLog(){
        System.out.println("##ERROR## There was an error connecting to this web page.. " +
                "Moving to the next link.. ##ERROR##");
    }

    public Set<WebPage> getCrawledWebPageSet() {
        return crawledWebPageSet;
    }
}
