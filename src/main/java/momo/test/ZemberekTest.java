package momo.test;

import momo.preprocess.Morphology;
import zemberek.morphology.analysis.WordAnalysis;

import java.io.IOException;
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

        List<WordAnalysis> results = Morphology.getMorphology().analyze("kitabımın");
        for (WordAnalysis result : results) {
            System.out.println(result.dictionaryItem.lemma);
        }
    }
}