package momo.application;

import module.crawler.WebPage;
import module.processor.model.Source;
import module.processor.model.UniqueWord;
import momo.dao.SourceDAO;
import momo.dao.UniqueWordDAO;
import momo.preprocess.PreprocessHandler;
import momo.preprocess.PreprocessedSentence;
import momo.validation.ValidationHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ercan on 09.04.2017.
 */
public class Application {
    private ValidationHandler validationHandler;
    private Map<String, UniqueWord> uniqueWordMap;
    private SourceDAO sourceDAO;
    private UniqueWordDAO uniqueWordDAO;

    public Application(String keyspace, String sourceTableName, String uniqueWordTableName){
        validationHandler = new ValidationHandler();
        uniqueWordMap = new HashMap<String, UniqueWord>();
        sourceDAO = new SourceDAO(keyspace,  sourceTableName);
        uniqueWordDAO = new UniqueWordDAO(keyspace,  uniqueWordTableName);
    }

    /*public Set<WebPage> getWebPagesFromCrawler(String url){
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.crawl(url);

        return webCrawler.getCrawledWebPageSet();
    }*/

    public void saveWebPageData(Set<WebPage> crawledWebPages){
        uniqueWordDAO.prepareForInsert();
        sourceDAO.prepareForInsert();

        for(WebPage webPage : crawledWebPages){
            Source source = createSourceFromWebPage(webPage);
            sourceDAO.insert(source);

            for(String word : uniqueWordMap.keySet()) {
                uniqueWordDAO.insert(uniqueWordMap.get(word));
            }
        }
    }

    public Source createSourceFromWebPage(WebPage webPage){
        Source source = new Source(webPage.getUrl());

        for(String sentence: webPage.getSentences()){
            if(validationHandler.validate(sentence)) {
                PreprocessedSentence preprocessedSentence = preprocessSentence(sentence);
                source.updateWordCountMap(preprocessedSentence.getWordCountMap());

                for(String word: preprocessedSentence.getWordCountMap().keySet()){
                    updateUniqueWordMap(word, source.getSourceName());
                }
            }
        }

        return source;
    }

    private PreprocessedSentence preprocessSentence(String sentence){
        PreprocessHandler preprocessHandler = new PreprocessHandler();
        PreprocessedSentence preprocessedSentence = preprocessHandler.process(sentence);

        return preprocessedSentence;
    }

    public void updateUniqueWordMap(String word, String sourceName){
        if(uniqueWordMap.get(word) == null){
            UniqueWord uniqueWord = new UniqueWord(word);
            uniqueWord.getDocumentSet().add(sourceName);
            uniqueWordMap.put(word, uniqueWord);
        } else{
            uniqueWordMap.get(word).getDocumentSet().add(sourceName);
        }
    }

}
