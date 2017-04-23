package momo.test;

import momo.preprocess.Morphology;
import momo.preprocess.PreprocessHandler;
import momo.preprocess.PreprocessedSentence;
import momo.validation.ValidationHandler;
import org.antlr.v4.runtime.Token;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.tokenization.TurkishSentenceExtractor;
import zemberek.tokenization.TurkishTokenizer;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ZemberekTest {
    public static void main(String[] args) throws IOException {
        /*TurkishMorphology morphology = TurkishMorphology.createWithDefaults();

        String word = "kitaba";

        List<WordAnalysis> results = morphology.analyze(word);
        for (WordAnalysis result : results) {
            System.out.println(result.getDictionaryItem().lemma);
            //System.out.println("\tStems = " + result.getStems());
            //System.out.println("\tLemmas = " + result.getLemmas());
        }*/

        /*List<WordAnalysis> results = Morphology.getMorphology().analyze("kitabımın");
        for (WordAnalysis result : results) {
            System.out.println(result.dictionaryItem.lemma);
        }

        String sentence = "39. single'ı \"Startin'/Born to Be...\" (2006) ile birlikte Hamasaki, Japonya'nın en çok bir numara single'ı bulunan bayan solo sanatçısı haline geldi. 2007'de \"Talkin' 2 Myself\" singleının çıkışıyla Hamasaki, 33 bir-numara singlea (\"A Song Is Born\" da dahil edildiğinde 34) ve Japon Oricon listelerinin Top 10'unda zirveye çıkmış 43 single'a imza atmış oldu.";
        Iterator<Token> tokenIterator = TurkishTokenizer.DEFAULT.getTokenIterator(sentence);
        while (tokenIterator.hasNext()) {
            Token token = tokenIterator.next();

            System.out.println(token.getText());
        }

        TurkishSentenceExtractor extractor = TurkishSentenceExtractor.DEFAULT;

        List<String> sentencesFromDetector = extractor.fromParagraph(sentence);
        for(String snt: sentencesFromDetector){
            System.out.println(snt);
        }*/

        ValidationHandler validationHandler = new ValidationHandler();
        String sentence = "Ali yarın okula arkadaşıyla gelecek .";
        if(validationHandler.validate(sentence)) {
            PreprocessHandler preprocessHandler = new PreprocessHandler();
            PreprocessedSentence preprocessedSentence = preprocessHandler.process(sentence);

            System.out.println("sentence is valid.");
        } else {
            System.out.println("sentence is not valid.");
        }

        System.out.println("qq");
    }
}