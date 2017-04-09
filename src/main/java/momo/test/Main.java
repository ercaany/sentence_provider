package momo.test;

import momo.preprocess.PreprocessHandler;
import momo.preprocess.PreprocessedSentence;
import momo.validation.ValidationHandler;
import zemberek.morphology.analysis.tr.TurkishMorphology;

import java.io.IOException;

/**
 * Created by ercan on 23.03.2017.
 */
public class Main {
    public static void main(String [] args) throws IOException {
        /*String url = "https://tr.wikipedia.org/wiki/Bizans_%C4%B0mparatorlu%C4%9Fu";
        ContentHandler contentHandler = new ContentHandler(url, new UrlContent());
        String content = contentHandler.fetchContent();

        FileContentSaver fileContentSaver = new FileContentSaver(1);
        for(int i = 0; i < 5; i++){
            fileContentSaver.save(contentHandler.getSentences(), url);
            fileContentSaver.setFileNameIndex(fileContentSaver.getFileNameIndex() + 1);
        }*/

        /*WebCrawler webCrawler = new WebCrawler();
        webCrawler.crawl(url);
        Set<WebPage> crawledWebPages = webCrawler.getCrawledWebPageSet();*/
        String sentence = "Ali bilgisayarını masaya koydu fakat kitabını dolaba koymayı unuttu.";

        ValidationHandler validationHandler = new ValidationHandler();
        if(validationHandler.validate(sentence)) {
            //kayıt edilecek
        }

        PreprocessHandler preprocessHandler = new PreprocessHandler();
        PreprocessedSentence preprocessedSentence = preprocessHandler.process(sentence);

        System.out.println(preprocessedSentence.getOriginalSentence());
        for(String word: preprocessedSentence.getStemList()){
            System.out.println(word);
        }
        for(String word: preprocessedSentence.getWordCountMap().keySet()){
            System.out.println(word + " " + preprocessedSentence.getWordCountMap().get(word));
        }
    }
}
