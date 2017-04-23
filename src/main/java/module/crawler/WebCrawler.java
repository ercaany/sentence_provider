package module.crawler;

import application.GlobalParameter;
import content.UrlContent;

import java.util.*;

/**
 * Created by mustafa on 23.04.2017.
 */
public class WebCrawler extends Thread {
    private final int MAX_PAGE_COUNT_TO_SEARCH = 10;
    private Queue<String> unvisitedPageUrls;
    private Set<WebPage> crawledWebPageSet;
    private Queue<WebPage> webPageQueue;
    private UrlContent urlContent;

    public WebCrawler(){
        unvisitedPageUrls = new LinkedList<String>();
        crawledWebPageSet = new HashSet<WebPage>();
        webPageQueue = new LinkedList<WebPage>();
        urlContent = new UrlContent();
    }

    public void run() {
        crawl();
    }

    public void crawl() {
        WebPage webPage;
        String url;

        while((url = unvisitedPageUrls.poll()) != null) {
            try{
                webPage = new WebPage(url);
                webPage.setContent(urlContent.fetchContent(webPage.getUrl()));
                webPageQueue.offer(webPage);
                updateUnvisitedPageUrls();

                System.out.println("##INFO## "  +
                        " Succesfully connected to: " + url +
                        " Now collecting data.. ##INFO##");
            }catch (Exception ex){
                errorLog();
            }
        }

        //threadleri kapat
    }

    private void updateUnvisitedPageUrls(){
        List<String> linksOnPage = urlContent.extractLinks();
        for(String link: linksOnPage){
            if(!GlobalParameter.visitedUrlSet.contains(link)){
                GlobalParameter.visitedUrlSet.add(link);

                if(isAcceptable(link))
                    unvisitedPageUrls.add(link);
            }
        }
    }

    private boolean isAcceptable(String url) {
        if(!url.contains("#"))
            return true;
        return false;
    }

    private void errorLog(){
        System.out.println("##ERROR## There was an error connecting to this web page.. " +
                "Moving to the next link.. ##ERROR##");
    }

    public Set<WebPage> getCrawledWebPageSet() {
        return crawledWebPageSet;
    }

    public void addUrl(String url) {
        unvisitedPageUrls.add(url);
    }

    public Queue<WebPage> getWebPageQueue() {
        return webPageQueue;
    }

    public void setWebPageQueue(Queue<WebPage> webPageQueue) {
        this.webPageQueue = webPageQueue;
    }
}
