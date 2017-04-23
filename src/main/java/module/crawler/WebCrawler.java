package module.crawler;

import application.GlobalParameter;
import content.UrlContent;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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

                if(checkAcceptanceOfDocument(urlContent.getDocument())){
                    webPageQueue.offer(webPage);
                    updateUnvisitedPageUrls();

                    System.out.println("##INFO## "  +
                            " Succesfully connected to: " + url +
                            " Now collecting data.. ##INFO##");
                }
            } catch (Exception ex){
                errorLog();
            }
        }

        //threadleri kapat
    }

    private boolean checkAcceptanceOfDocument(Document document) {
        Element htmlTag = document.select("html").first();
        String langAttribute = htmlTag.attr("lang");

        return langAttribute != null && (langAttribute.equals("tr-TR") || langAttribute.equals("tr"));
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
        return !url.contains("#") && !url.contains("action=edit") && !url.contains("action=history")
                && !url.contains("veaction=edit") && !url.contains(".jpg") && !url.contains(".png");
    }

    private void errorLog(){
        System.out.println("##ERROR## There was an error connecting to this web page.. " +
                "Moving to the next link.. ##ERROR##");
    }

    /*public static void main(String[] args){
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.getUnvisitedPageUrls().add("https://tr.wikipedia.org/wiki/Arp_zehirlenmesi");
        webCrawler.run();
    }*/


    // getters and setters
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

    public Queue<String> getUnvisitedPageUrls() {
        return unvisitedPageUrls;
    }

    public void setUnvisitedPageUrls(Queue<String> unvisitedPageUrls) {
        this.unvisitedPageUrls = unvisitedPageUrls;
    }
}
