package momo.test;

import module.processor.model.Sentence;
import momo.application.Application;
import module.database.dao.SentenceDAO;
import momo.preprocess.PreprocessHandler;
import momo.preprocess.PreprocessedSentence;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ercan on 23.03.2017.
 */
public class Main {
    public static void main(String [] args) throws IOException {
        String seed = "https://tr.wikipedia.org/wiki/Bizans_%C4%B0mparatorlu%C4%9Fu";
        Application application = new Application("tp", "source", "unique_word");
        //Set<WebPage> webPages = application.getWebPagesFromCrawler(seed);
        //application.saveWebPageData(webPages);

        String sentence = "Adem elmasını masaya koydu fakat karpuzunu dolaba koymayı unuttu.";
        PreprocessHandler preprocessHandler = new PreprocessHandler();
        PreprocessedSentence preprocessedSentence = preprocessHandler.process(sentence);
        Set<String > questions = new HashSet<String>();
        questions.add("soru1");
        questions.add("soru2");
        Sentence sentenceObject = new Sentence(preprocessedSentence.getOriginalSentence());
        sentenceObject.setQuestions(questions);
        sentenceObject.setStemmedWordsList(preprocessedSentence.getStemList());
        sentenceObject.setTokenList(preprocessedSentence.getTokenList());
        sentenceObject.setTags(questions);
        SentenceDAO sentenceDAO = new SentenceDAO();
        sentenceDAO.prepareForInsert();
        sentenceDAO.insert(sentenceObject);

    }
}




