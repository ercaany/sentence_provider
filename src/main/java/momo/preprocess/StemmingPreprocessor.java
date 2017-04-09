package momo.preprocess;

import org.antlr.v4.runtime.Token;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.tokenization.TurkishTokenizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ercan on 09.04.2017.
 */
public class StemmingPreprocessor extends Preprocessor{
    private TurkishTokenizer tokenizer;
    private TurkishMorphology morphology;

    public StemmingPreprocessor(){
        this.tokenizer = TurkishTokenizer.DEFAULT;
        this.morphology = Morphology.getMorphology();
    }

    public PreprocessedSentence process(PreprocessedSentence preprocessedSentence) {
        List<String> stemList = getStemListFromSentence(preprocessedSentence.getOriginalSentence());
        preprocessedSentence.setStemList(stemList);

        return proceedToNext(preprocessedSentence);
    }

    private List<String> getStemListFromSentence(String sentence){
        Iterator<Token> tokenIterator = tokenizer.getTokenIterator(sentence);

        List<String> stemList = new ArrayList<String>();
        while (tokenIterator.hasNext()) {
            Token token = tokenIterator.next();

            if(isAcceptable(token.getType())){
                List<WordAnalysis> results = morphology.analyze(token.getText());
                String tokenLemma = results.get(0).dictionaryItem.lemma;
                stemList.add(tokenLemma);
            }
        }

        return stemList;
    }

    private boolean isAcceptable(int type){
        return type == 1 || type == 4 || type == 5 || type == 6 || type == 7 ||
                type == 8 || type == 9 || type == 13 || type == 14 || type == 15 ||
                type == 16;
    }
    // 1-4-5-6-7-9-8-13-14-15-16     (18-19) ?
}
