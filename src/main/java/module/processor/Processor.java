package module.processor;

import module.crawler.WebCrawler;
import module.crawler.WebPage;
import model.Sentence;
import model.Source;
import content.ContentHandler;

import java.util.*;

/**
 * Created by mustafa on 23.04.2017.
 */
public class Processor extends Thread {
    private WebCrawler crawler;
    private Queue<Source> dataToSaveQueue;
    private ContentHandler contentHandler;
    public int waitingSize = 0;

    public Processor(WebCrawler crawler) {
        this.crawler = crawler;
        dataToSaveQueue = new LinkedList<Source>();
        contentHandler = new ContentHandler();
    }
    public void run() {
        WebPage webPage;

        while(crawler.isAlive() && (webPage = crawler.getWebPageQueue().poll()) != null) {
            Source source = new Source();
            source.setSentenceSet(buildSentences(webPage.getContent()));

            if(true) { //doküman kayda değer mi ona bakılacak
                dataToSaveQueue.offer(source);
                waitingSize++;
            }

            //cümle listesini oluştur tik
            //kayda değer cümle listelerini oluştur
            //doküman kayıt etmeye değer mi ona karar ver
            //evetse listeye ekle
        }

        //crawler bittiyse elindekileri işle öyle kapan
    }

    //DOĞRUDAN KAYDA DEĞER CÜMLE LİSTESİNİ DÖNECEK - İŞLENMİŞ OLACAK
    private Set<Sentence> buildSentences(String content) {
        List<String> sentences = contentHandler.getSentencesFromParagraph(content);
        Set<Sentence> sentenceSet = new HashSet<Sentence>();

        for(String sentence: sentences) {
            //düzenlecek
            sentenceSet.add(new Sentence(sentence));
        }

        return sentenceSet;
    }

    private boolean isSaveable(String content) {
        return false;
    }

    public Queue<Source> getDataToSaveQueue() {
        return dataToSaveQueue;
    }

    public void setDataToSaveQueue(Queue<Source> dataToSaveQueue) {
        this.dataToSaveQueue = dataToSaveQueue;
    }
}
