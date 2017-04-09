package momo.test;

import momo.application.Application;
import momo.crawler.WebPage;

import java.io.IOException;
import java.util.*;

/**
 * Created by ercan on 23.03.2017.
 */
public class Main {
    public static void main(String [] args) throws IOException {
        /*
        ContentHandler contentHandler = new ContentHandler(url, new UrlContent());
        String content = contentHandler.fetchContent();

        FileContentSaver fileContentSaver = new FileContentSaver(1);
        for(int i = 0; i < 5; i++){
            fileContentSaver.save(contentHandler.getSentences(), url);
            fileContentSaver.setFileNameIndex(fileContentSaver.getFileNameIndex() + 1);
        }*/

         /*String sentence1 = "Ali bilgisayarını masaya koydu fakat kitabını dolaba koymayı unuttu.";
        String sentence2 = "Adem elmasını masaya koydu fakat karpuzunu dolaba koymayı unuttu.";
        List<String> sentences = new ArrayList<String>();
        sentences.add(sentence1);
        sentences.add(sentence2);*/

        // sentence test
        /*Set<String > questions = new HashSet<String>();
        questions.add("soru?");
        Sentence sentenceObject = new Sentence(preprocessedSentence.getOriginalSentence(), "test_url");
        sentenceObject.setQuestions(questions);
        sentenceObject.setStemmedWordsList(preprocessedSentence.getStemList());
        sentenceObject.setTags(questions);

        SentenceDAO sentenceDAO = new SentenceDAO("tp", "sentence");
        sentenceDAO.insert(sentenceObject);

        // unique word test
        UniqueWord uniqueWord = new UniqueWord("ercan");
        List<String> documents = new ArrayList<String>();
        documents.add("3");
        documents.add("4");
        uniqueWord.setDocumentList(documents);

        UniqueWordDAO uniqueWordDAO = new UniqueWordDAO("tp", "unique_word");
        uniqueWordDAO.update(uniqueWord);*/

        String seed = "https://tr.wikipedia.org/wiki/Bizans_%C4%B0mparatorlu%C4%9Fu";
        Application application = new Application("tp", "source", "unique_word");
        Set<WebPage> webPages = application.getWebPagesFromCrawler(seed);
        application.saveWebPageData(webPages);


    }
}




