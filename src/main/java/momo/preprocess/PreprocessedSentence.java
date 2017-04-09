package momo.preprocess;

import java.util.List;
import java.util.Map;

/**
 * Created by ercan on 09.04.2017.
 */
public class PreprocessedSentence {
    private String originalSentence;
    private List<String> stemList;
    private Map<String, Integer> wordCountMap;

    public PreprocessedSentence(String sentence){
        this.originalSentence = sentence;
    }

    public PreprocessedSentence(){

    }


    public String getOriginalSentence() {
        return originalSentence;
    }

    public void setOriginalSentence(String originalSentence) {
        this.originalSentence = originalSentence;
    }

    public List<String> getStemList() {
        return stemList;
    }

    public void setStemList(List<String> stemList) {
        this.stemList = stemList;
    }

    public Map<String, Integer> getWordCountMap() {
        return wordCountMap;
    }

    public void setWordCountMap(Map<String, Integer> wordCountMap) {
        this.wordCountMap = wordCountMap;
    }
}
