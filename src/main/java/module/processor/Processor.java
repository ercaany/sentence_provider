package module.processor;

import module.crawler.WebCrawler;
import module.crawler.WebPage;
import model.Sentence;
import model.Source;
import content.ContentHandler;
import module.processor.preprocess.PreprocessHandler;
import module.processor.preprocess.PreprocessedSentence;
import module.processor.validation.ValidationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by mustafa on 23.04.2017.
 */
public class Processor extends Thread {
    private final static Logger logger = LoggerFactory.getLogger(Processor.class);
    private WebCrawler crawler;
    private Queue<Source> dataToSaveQueue;
    private ContentHandler contentHandler;
    private ValidationHandler validationHandler;
    private PreprocessHandler preprocessHandler;
    public int waitingSize = 0;

    public Processor(WebCrawler crawler) {
        this.crawler = crawler;
        dataToSaveQueue = new LinkedList<Source>();
        contentHandler = new ContentHandler();
        validationHandler = new ValidationHandler();
        preprocessHandler = new PreprocessHandler();
    }

    public void run() {
        WebPage webPage;
        logger.trace("Processor running..");

        while(crawler.isAlive()) {
            while((webPage = crawler.getWebPageQueue().poll()) != null) {
                Source source = new Source();
                source.setSentenceSet(buildSentences(webPage.getContent()));
                logger.trace("Processor " + source.getSourceName());


                if(isSourceWorthy(source)) { //doküman kayda değer mi ona bakılacak
                    dataToSaveQueue.offer(source);
                    waitingSize++;
                    logger.info("Processor kayda değer bulundu " + source.getSourceName());
                }

                //cümle listesini oluştur tik
                //kayda değer cümle listelerini oluştur
                //doküman kayıt etmeye değer mi ona karar ver
                //evetse listeye ekle
            }
        }

        //crawler bittiyse elindekileri işle öyle kapan
    }

    private boolean isSourceWorthy(Source source){
        int sentenceCount = source.getSentenceSet().size();

        return sentenceCount > 5;
    }

    //DOĞRUDAN KAYDA HAZIR CÜMLE LİSTESİNİ DÖNECEK - İŞLENMİŞ OLACAK
    private Set<Sentence> buildSentences(String content) {
        List<String> sentences = contentHandler.getSentencesFromParagraph(content);
        Set<Sentence> sentenceSet = new HashSet<Sentence>();

        for(String sentence: sentences) {
            if(validationHandler.validate(sentence)){
                PreprocessedSentence preprocessedSentence = new PreprocessedSentence(sentence);
                preprocessedSentence = preprocessHandler.process(sentence);

                Sentence newSentence = new Sentence(sentence);
                newSentence.setStemmedWordsList(preprocessedSentence.getStemList());
                newSentence.setTokenList(preprocessedSentence.getTokenList());
                // tags ata
                // questions ata
                sentenceSet.add(newSentence);
                logger.trace("Processor buildSentences" + sentence);

            }
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
